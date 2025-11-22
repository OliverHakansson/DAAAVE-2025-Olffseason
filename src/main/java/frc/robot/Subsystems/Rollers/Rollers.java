package frc.robot.Subsystems.Rollers;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Subsystems.Arm.Elevator.ElevatorStates;

public class Rollers extends SubsystemBase{
    private RollerStates wantedRollerState;
    private RollerStates currentRollerState = RollerStates.IDLE;
    public final RollersIO io;
    private static Rollers instance = null;
    private final RollersIOInputsAutoLogged inputs = new RollersIOInputsAutoLogged();

    
    public Rollers(RollersIO io) {
        this.io = io;
    }

    public static Rollers getInstance() {
        if (instance == null) {
          throw new IllegalStateException("CoralRollers instance not set");
        }
        return instance;
      }
    
      public static Rollers setInstance(RollersIO io) {
        instance = new Rollers(io);
        return instance;
      }

    @Override
    public void periodic(){
        io.updateInputs(inputs);
        //Log

        //Handle Statements
        handleStateTransitions();

        //Apply States
        applyStates();
    }

  public RollerStates getState() {
    return  currentRollerState;
  }


    public void handleStateTransitions() {
        this.currentRollerState = this.wantedRollerState;
    }

    public void applyStates() {
        this.setState(currentRollerState);
    }

    public void setState(RollerStates wantedState){
        //
        io.setVoltage(wantedState.RollerVoltage);
    }
    public boolean IsIntaking() {
        return io.IsIntaking();
    }

    public boolean clawFrontSensorTriggered() {
        return io.clawFrontSensorTriggered();
      }
    
      public boolean clawBackSensorTriggered() {
        return io.clawBackSensorTriggered();
      }
    
      public void rotateBy(double movement) {
        io.rotateBy(movement);
      }
    
      public void updateSim() {
        io.updateSim();
      }
    
      public boolean isStalled() {
        return io.isStalled();
      }


}
