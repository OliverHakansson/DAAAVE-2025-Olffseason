package frc.robot.Subsystems.Arm;

import org.littletonrobotics.junction.Logger;

import com.revrobotics.spark.ClosedLoopSlot;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Subsystems.Arm.ArmState.ArmPositions;
import frc.robot.Subsystems.Arm.ArmState.ArmStates;
import frc.robot.Subsystems.Arm.Elevator.Elevator;
import frc.robot.Subsystems.Arm.Elevator.ElevatorIO;
import frc.robot.Subsystems.Arm.Elevator.ElevatorIOInputsAutoLogged;
import frc.robot.Subsystems.Arm.Wrist.Wrist;
import frc.robot.Subsystems.Arm.Wrist.WristIO;
import frc.robot.Subsystems.Arm.Wrist.WristIOInputsAutoLogged;
import frc.robot.Subsystems.Lights.Lights;
import frc.robot.Subsystems.Lights.LightsIO;
import frc.robot.Subsystems.Vision.AllignVision;
import frc.robot.Subsystems.Vision.VisionConstants;

public class Arm extends SubsystemBase{
    public ArmStates currentArmState = ArmStates.IDLE;
    public ArmStates wantedArmState = ArmStates.IDLE;
    public ArmPositions wantedPosition = ArmPositions.SAFE;
    public WristIOInputsAutoLogged wristInputs = new WristIOInputsAutoLogged();
    public final ElevatorIOInputsAutoLogged elevatiorIOInputs = new ElevatorIOInputsAutoLogged();
    public final WristIOInputsAutoLogged wristIOInputs = new WristIOInputsAutoLogged(); // not finished
    private Elevator elevator;
    private Wrist wrist;
    
    private static Arm instance = null;

    public Arm(Elevator elevator, Wrist wrist){
        this.elevator = elevator;
        this.wrist = wrist;
    }

    public static Arm getInstance() {
        if(instance == null) {
            return setInstance(Elevator.GetInstance(), Wrist.getInstance());
        }
        return instance;
    }

    public static Arm setInstance(Elevator elevator, Wrist wrist) {
      instance = new Arm(elevator, wrist);
      return instance;
    }

    @Override
    public void periodic() {
        // #1 Logging
        // this.elevator.io.updateInputs(this.elevatiorIOInputs);
        Logger.processInputs("Elevator", this.elevatiorIOInputs);
        Logger.processInputs("Wrist", this.wristIOInputs);

        // #2 Handling Wanted State
        handleStateTransitions();

        // #3 Applying desired state
        applyStates();
    }
    
    public void handleStateTransitions() {
        if(this.wantedArmState == ArmStates.MANUAL){
            this.currentArmState = ArmStates.MANUAL;
        }else if(this.currentArmState == ArmStates.IDLE && !this.positionInTolerance(0.1)){ //if current position is not equal to wanted position
            this.currentArmState = ArmStates.MOVING;
        }else if(this.currentArmState == ArmStates.MOVING && this.positionInTolerance(0.05)){
            this.currentArmState = ArmStates.IDLE;
        }
            
        
    }
        

    public double GetWristPosition() {
        return this.wrist.io.getPosition();
    }


    public double GetElevatorPosition() {
        return this.elevator.io.GetPosition();
    }
    public void SetElevatorWantedPosition() {
        this.elevator.SetPosition(this.wantedPosition.elevatorPosition);
    }



    public boolean positionInTolerance (double tolerance) {
        return elevator.isAtPosition(wantedPosition, tolerance) && wrist.isAtPosition(wantedPosition, tolerance);
    }


    public void applyStates(){
        switch(this.currentArmState) {
            case MOVING:
                this.safeArmMovement();
                break;
            case IDLE:
                break;
            case MANUAL:
                //manual position
                break;
        }
        
    }
    //

    public void safeArmMovement(){
        if ( // if elevator is moving past crossbar and wrist and wantedposition are also
            (
                (
                    elevator.io.GetPosition() < VisionConstants.minElevatorDangerRange
                    &&
                    wantedPosition.elevatorPosition < VisionConstants.minElevatorDangerRange
                )
                ||
                (
                    wantedPosition.elevatorPosition > VisionConstants.maxElevatorDangerRange 
                    &&
                    elevator.io.GetPosition() > VisionConstants.maxElevatorDangerRange
                )
            )
            &&
            this.wrist.io.getPosition() > VisionConstants.maxWristDangerRange
            &&
            this.wantedPosition.wristPosition > VisionConstants.maxWristDangerRange
        )
        {
            if ( // will wrist hit the reef when getting to wanted position or is the elevator in its final position
                (
                    (
                        (
                            this.wantedPosition.wristPosition > VisionConstants.wristDangerOfHittingReefMax
                            &&
                            this.wrist.io.getPosition() > VisionConstants.wristDangerOfHittingReefMax
                        )
                        ||
                        (
                            this.wantedPosition.wristPosition < VisionConstants.wristDangerOfHittingReefMin
                            &&
                            this.wrist.io.getPosition() < VisionConstants.wristDangerOfHittingReefMin
                        )
                    )
                    &&
                    (
                        AllignVision.getInstance().io.getAverageLidarDistance() > VisionConstants.wristsSafeDistanceFromReef
                        ||
                        (
                            wantedPosition != ArmPositions.SUPERCYCLE_ALGAE_HIGH
                            ||
                            wantedPosition != ArmPositions.SUPERCYCLE_ALGAE_LOW
                            
                        )
                    )
                )
                ||
                (
                    this.elevator.isAtPosition(wantedPosition,0.1)
                    &&
                    this.wantedPosition != ArmPositions.L1
                )
            ){
                
                if(this.wantedPosition != ArmPositions.L4 || this.elevator.isAtPosition(wantedPosition, 0.1)){ // prevents L4 wrist from moving down unless elevator is all the way up
                    this.wrist.io.setPosition(wantedPosition.wristPosition, ClosedLoopSlot.kSlot0); //no idea what K slot to put it on
                    this.elevator.SetPosition(this.elevator.io.GetPosition());
                }else{ 
                    this.wrist.io.setPosition(ArmPositions.SAFE.wristPosition, ClosedLoopSlot.kSlot0); //no idea what K slot to put it on
                    if(this.wrist.isAtPosition(ArmPositions.SAFE,0.1)){
                        this.elevator.io.SetPosition(wantedPosition.elevatorPosition);
                    }else{
                        this.elevator.SetPosition(this.elevator.io.GetPosition());
                }
                }
            }
            else if(this.wantedPosition.wristPosition > VisionConstants.wristDangerOfHittingReefMin && this.wrist.io.getPosition() > VisionConstants.wristDangerOfHittingReefMax) { //is the wrist not going from below
                this.wrist.io.setPosition(ArmPositions.SAFE.wristPosition, ClosedLoopSlot.kSlot0); //no idea what K slot to put it on
                if(this.wrist.isAtPosition(ArmPositions.SAFE,0.1)){
                    this.elevator.io.SetPosition(wantedPosition.elevatorPosition);
                }else{
                    this.elevator.SetPosition(this.elevator.io.GetPosition());
                }
            }
            else{
                this.wrist.io.setPosition(ArmPositions.INTAKE_CORAL.wristPosition, ClosedLoopSlot.kSlot0); //no idea what K slot to put it on
            }
        }
        else if(AllignVision.getInstance().io.getAverageLidarDistance() <= VisionConstants.wristsSafeDistanceFromReef && this.wrist.io.getPosition() > VisionConstants.wristDangerOfHittingReefMax){
            this.elevator.SetPosition(this.elevator.io.GetPosition());
            this.wrist.io.setPosition(ArmPositions.SAFE.wristPosition, ClosedLoopSlot.kSlot0); //no idea what K slot to put it on
        }
        else{
            this.elevator.SetPosition(this.elevator.io.GetPosition());
            //wait for robot to back up(not done here)
        }
    }
}