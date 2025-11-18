package frc.robot.Subsystems.Arm;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Subsystems.Arm.ArmState.ArmPositions;
import frc.robot.Subsystems.Arm.ArmState.ArmStates;
import frc.robot.Subsystems.Arm.Elevator.Elevator;
import frc.robot.Subsystems.Arm.Wrist.Wrist;

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
        handleStateTransitions(null);

        HandleElevatorTrapazoidal();

        // #3 Applying desired state
        applyStates();
    }
    
    public void handleStateTransitions() {
        if(this.wantedArmState == ArmStates.MANUAL){
            this.currentArmState = ArmStates.MANUAL;
        }else if(this.currentArmState == ArmStates.IDLE){ //if current position is not equal to wanted position
            //set state to "moving"
            //else, set state to idle
        }else if(this.currentArmState == ArmStates.IDLE){

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
        switch(this.ArmStates) {
            case MOVING:
         
                this.SetPosition(this.wantedPosition);
                }
                break;
            case IDLE:
                this.SetPosition(this.wantedPosition);
                break;
            case MANUAL:
                //manual position
                break;
        }
        
    }
}public void safeCode(){
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
}