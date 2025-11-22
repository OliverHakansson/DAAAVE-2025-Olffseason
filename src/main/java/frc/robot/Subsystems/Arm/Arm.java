package frc.robot.Subsystems.Arm;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Subsystems.Arm.ArmState.ArmPositions;
import frc.robot.Subsystems.Arm.ArmState.ArmStates;
import frc.robot.Subsystems.Arm.Elevator.Elevator;
import frc.robot.Subsystems.Arm.Wrist.Wrist;
import frc.robot.Subsystems.Vision.AllignVision;
import frc.robot.Subsystems.Vision.VisionConstants;
import frc.robot.Subsystems.Arm.ArmState;

public class Arm extends SubsystemBase{
    public ArmStates currentArmState = ArmStates.IDLE;
    public ArmStates wantedArmState = ArmStates.IDLE;
    public ArmPositions wantedPosition = ArmPositions.SAFE;
    private Elevator elevator = Elevator.GetInstance();
    private Wrist wrist = Wrist.getInstance();
    
    public Arm(){
        
    }

    @Override
    public void periodic() {
        // #1 Logging
        Logger.processInputs();

        // #2 Handling Wanted State
        handleStateTransitions();

        // #3 Applying desired state
        applyStates();
    }
    
    public void handleStateTransitions() {
        if(this.wantedArmState == ArmStates.MANUAL){
            this.currentArmState = ArmStates.MANUAL;
        }else if(this.currentArmState == ArmStates.IDLE && !this.stateInTolerance(0.1)){ //if current position is not equal to wanted position
            this.currentArmState = ArmStates.MOVING;
        }else if(this.currentArmState == ArmStates.MOVING && this.stateInTolerance(0.05)){
            this.currentArmState = ArmStates.IDLE;
        }
            
        
    }
        

    public double GetWristPosition() {
        return this.wrist.io.GetPosition();
    }


    public double GetElevatorPosition() {
        return this.elevator.GetInstance().io.GetPosition();
    }
    public void SetElevatorWantedPosition() {
        this.elevator.GetInstance().SetPosition(this.currentArmState.elevatorPosition);
    }



    public boolean stateInTolerance (double tolerance) {
        //ArmStates current_arm_state = getCurrent
        //ArmStates 
        
        //double t1=0;
        //dob
        //if(tolerance=>t1 && tolerance=<t2){
        //if(tolerance=>t1 && tolerance=<t2){
            //return true//more code
            
        //} else{
        //return false;    
        //}
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
    public void safeArmMovement(){
        Elevator elevatorObj = Elevator.GetInstance();
        if(
            (
                AllignVision.getInstance().io.getAverageLidarDistance() <= VisionConstants.alignPlaceDistance
                &&
                (
                    (
                        this.wantedPosition == ArmPositions.L1
                        &&
                        !elevatorObj.isAtPosition(this.wantedPosition)
                    )
                    ||
                    (
                        this.wantedPosition == ArmPositions.L4
                        &&
                        !elevatorObj.isAtPosition(this.wantedPosition)
                    )
                    ||
                    (
                        this.wantedPosition == ArmPositions.REEF_ALGAE_HIGH 
                        &&
                        (AllignVision.getInstance().io.getAverageLidarDistance() <= VisionConstants.alignDealagaeDistance)
                    )
                    ||
                    (
                        this.wantedPosition == ArmPositions.REEF_ALGAE_LOW
                        &&
                        (AllignVision.getInstance().io.getAverageLidarDistance() <= VisionConstants.alignDealagaeDistance))
                    ||
                    (
                        this.wantedPosition == ArmPositions.INTAKE_CORAL
                        &&
                        (AllignVision.getInstance().io.getAverageLidarDistance() <= VisionConstants.alignSafeIntake)
                    )
                )
                &&
                !Wrist.getInstance().isAtPosition(ArmPositions.SAFE)
            )
        ){
            Elevator.GetInstance().io.SetPosition(Elevator.GetInstance().io.GetPosition());
        }
        else if (
            wrist.io.getPosition() > VisionConstants.minSafeWristAngle
            &&
            (AllignVision.getInstance().io.getAverageLidarDistance() <= VisionConstants.alignDealagaeDistance)
            && 
            (
                (
                    elevator.io.GetPosition() < VisionConstants.minElevatoDangerrange
                    &&
                    wantedPosition.elevatorPosition < VisionConstants.minElevatoDangerrange
                )
                ||
                (
                    wantedPosition.elevatorPosition > VisionConstants.maxElevatoDangerrange 
                    &&
                    elevator.io.GetPosition() > VisionConstants.maxElevatoDangerrange
                )
            )
        ){
            
        }
        else{
            
        }
        /* OLD CODE
        boolean passingHorizon = (wrist.getPosition() > wrist.getHorizonAngle() && wantedPosition.wristPosition < wrist.getHorizonAngle())
        boolean inHorizonZone = !(Math.abs(wrist.getPosition()-wrist.getHorizonAngle()) < 0.1 && Math.abs(wantedPosition.wristPosition) < 0.1);
        //boolean backedOffReef = (vision.getLeftLidarDistance() > 0.2 || vision.getRightLidarDistance() > 0.2);
        if(backedOffReef || (!passingHorizon || !inHorizonZone))
        {
              // only let the wrist got to intake when elvator is at L1
              if(wantedState == WristStates.INTAKE && this.elevator.getPosition() < Elevator.BOT_CROSSBAR_POS) {
                  this.wrist.setState(wantedState, slot);
              }
              else {
                  this.wrist.setState(wantedState, slot);
            }
        }
        */
    }
}