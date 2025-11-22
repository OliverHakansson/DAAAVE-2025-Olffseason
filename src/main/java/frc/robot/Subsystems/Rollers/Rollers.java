package frc.robot.Subsystems.Rollers;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Rollers extends SubsystemBase{
    private RollerStates wantedRollerState;
    private RollerStates currentRollerState = RollerStates.IDLE;
    public final RollersIO io;
    private static Rollers instance = null;
    private final RollersIOInputsAutoLogged inputs = new RollersIOInputsAutoLogged();
    
    public Rollers(){

    }

    @Override
    public void periodic(){
        //Log

        //Handle Statements
        handleStateTransitions();

        //Apply States
        applyStates();
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




}
