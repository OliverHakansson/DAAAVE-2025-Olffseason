package frc.robot.Subsystems.Rollers;

public enum RollerStates {
    HOLD_ALGAE(-3),
    INTAKE_CORAL(10),
    INTAKE_ALGAE(0),
    PROCESSOR_ALGAE(0),
    BARGE_ALGAE(0),
    EJECT_CORAL(0),
    CORAL_L1(0),
    GROUND_ALGAE(0),
    IDLE(0);

    public final float RollerVoltage;

    private RollerStates(float RollerVoltage){
        this.RollerVoltage = RollerVoltage;
    }
}
