package frc.robot.Subsystems.Rollers;

public enum RollerStates {
    HOLD_ALGAE(0),
    INTAKE_CORAL(0),
    INTAKE_ALGAE(0),
    PROCESSOR_ALGAE(0),
    BARGE_ALGAE(0),
    CORAL_L4(0),
    CORAL_L3(0),
    CORAL_L2(0),
    CORAL_L1(0),
    LOLLIPOP(0),
    GROUND_ALGAE(0),
    IDLE(0);

    public final float RollerVoltage;

    private RollerStates(float RollerVoltage){
        this.RollerVoltage = RollerVoltage;
    }
}
