package frc.robot.Subsystems.Arm;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Subsystems.Arm.ArmStates.ArmPositions;
import frc.robot.Subsystems.Arm.ArmStates.ArmState;
import frc.robot.Subsystems.Arm.Elevator.Elevator;
import frc.robot.Subsystems.Arm.Wrist.Wrist;

public class Arm extends SubsystemBase{
    public ArmState armState = ArmState.IDLE;
    public ArmState wantedArmState = ArmState.IDLE;
    public ArmPositions wantedPosition = ArmPositions.SAFE;
    private Elevator elevator = Elevator.GetInstance();
    private Wrist wrist = Wrist.getInstance();
    
    public Arm(){
        
    }

    @Override
    public void periodic() {
        //Logger.processInputs();

        handleStateTransitions(null);

        HandleElevatorTrapazoidal();
    }
    
    public void handleStateTransitions() {
        if(this.wantedArmState == ArmState.MANUAL){
            this.armState = ArmState.MANUAL;
        }
        else{
            switch(this.wantedPosition){
                case L1:
                    if(true){//have coral 
                    //wrist in l1
                    //elevator l1
                    
                    }
                    break;
                case L2:
                    if(true){//have coral
                    //wrist in l2
                    //elevator l2
                    }
                    break;
                case L3:
                    if(true){//have coral
                    //wrist in l3
                    //elevator l3
                    }
                    break;
                case L4:
                    if(true){//have coral
                    //wrist in l4
                    //elevator l4

                    }
                    break;
                case SAFE:
                    if(true){
                        //wrist all the way up
                        //elevator down
                    }
                    break;
                case INTAKECORAL:
                    if(true){//no coral no algea elevator down
                        //wrist in
                    }
                    break;
                case REEFALGAELOW:
                    if(true){//no coral no algea
                    //low algae
                    //elevator to low algae
                    }
                    break;
                case REEFALGAEHIGH:
                    if(true){//no coral no algae
                    //wrist to high algae
                    //elevator to high algae

                    }
                    break;
                case BARGE:
                    if(true){//has algae 
                    //elevator to net
                    //wrist to net
                    }
                    break;
                case PROCESSOR:
                    if(true){//have algae
                    //wrist processor
                    //elevator down
                    }
                    break;
                case LOLLIPOP:
                    if(true){// no game piece
                    //elevator to right level   
                    //wrist to lollipop 
                    }
                case GROUNDALGAE:
                    if(true){//no game piece
                        //elevator down
                        //wrist ground algae
                    }
                case SUPERCYCLEALGAEHIGH:
                    if(true){//supercycling high
                    //make it like be in the right spot

                    }
                    break;
                case SUPERCYCLEALGAELOW:
                    if(true){//supercycling low
                    //make it like be in the right spot
                    }
                    break;
                default:
                    this.wantedArmState = ArmState.IDLE;                
            }   
        }
    }
        

    public double GetWristPosition() {
        return this.wrist.io.GetPosition();
    }


    public double GetElevatorPosition() {
        return this.elevator.GetInstance().io.GetPosition();
    }
    public void SetElevatorWantedPosition() {
        this.elevator.GetInstance().SetPosition(this.armState.elevatorPosition);
    }



    public boolean stateInTolerance (double tolerance) {
        //ArmState current_arm_state = getCurrent
        //ArmState 
        
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
        switch(this.armState) {
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