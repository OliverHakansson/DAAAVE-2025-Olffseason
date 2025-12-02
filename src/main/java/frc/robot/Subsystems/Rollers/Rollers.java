package frc.robot.Subsystems.Rollers;

import com.ctre.phoenix6.mechanisms.swerve.LegacySwerveRequest.Idle;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Subsystems.Arm.Elevator.ElevatorStates;

public class Rollers extends SubsystemBase{
    private RollerStates wantedRollerState;
    private RollerStates currentRollerState = RollerStates.IDLE;
    private boolean hasCoral = true;
    private boolean hasAlgae = false;
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
        switch (wantedRollerState) {
          case IDLE:
            this.currentRollerState = RollerStates.IDLE;
            break;
          case EJECT_CORAL:
            if (hasCoral)
              this.currentRollerState = RollerStates.EJECT_CORAL;              
            break;
          case BARGE_ALGAE:
            if (hasAlgae) {
              this.currentRollerState = RollerStates.BARGE_ALGAE;
            }
            break;
          case GROUND_ALGAE:
            if (!hasAlgae && !hasCoral) {
              this.currentRollerState = RollerStates.GROUND_ALGAE;
            }
            break;
          case HOLD_ALGAE:
            if (hasAlgae) {
              this.currentRollerState = RollerStates.HOLD_ALGAE;
            }
            break;
          case INTAKE_ALGAE:
            if (!hasCoral && !hasAlgae) {
              this.currentRollerState = RollerStates.INTAKE_ALGAE;
            }
            break;
          case INTAKE_CORAL:
          if (!hasCoral && !hasAlgae) {
            this.currentRollerState = RollerStates.INTAKE_CORAL;
            }
            break;
          case PROCESSOR_ALGAE:
          if (hasAlgae) {
            this.currentRollerState = RollerStates.PROCESSOR_ALGAE;
          }
            break;
          default:
            this.currentRollerState = RollerStates.IDLE;
            break;
            


        }
    }

    public void applyStates() {
      io.setVoltage(currentRollerState.RollerVoltage);
        switch (currentRollerState) {
          case IDLE:
            break;
          case EJECT_CORAL:
            this.wantedRollerState = RollerStates.IDLE;
            break;
          case BARGE_ALGAE:
            this.wantedRollerState = RollerStates.IDLE;
            break;
          case GROUND_ALGAE:
            this.wantedRollerState = RollerStates.IDLE;
            break;
          case HOLD_ALGAE:
            break;
          case INTAKE_ALGAE:
            this.wantedRollerState = RollerStates.HOLD_ALGAE;
            break;
          case INTAKE_CORAL:
            this.wantedRollerState = RollerStates.IDLE;
            break;
          case PROCESSOR_ALGAE:
            this.wantedRollerState = RollerStates.IDLE;
            break;
          default:
            io.setVoltage(RollerStates.IDLE.RollerVoltage);
            break;
            


        }
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
