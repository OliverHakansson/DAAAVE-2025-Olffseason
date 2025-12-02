package frc.robot.Subsystems.Climb;

public enum ClimbStates {
    STORED(0,0),
    PREP(0,0),
    CLIMBED(0,0);

    public double climbPosition;
    public double climbRollerVoltage;

    ClimbStates(double position, double voltage){
        this.climbPosition = position;
        this.climbRollerVoltage = voltage;
    }
}
