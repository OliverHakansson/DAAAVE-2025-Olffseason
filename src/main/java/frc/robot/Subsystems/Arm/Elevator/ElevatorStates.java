package frc.robot.Subsystems.Arm.Elevator;

public enum ElevatorStates {
    LOW(0),
    LEVEL1(0),
    LEVEL2(0),
    LEVEL3(0), 
    LEVEL4(0),
    BARGE(0),
    INTAKE(0),
    PROCESSOR(0),
    GROUNDALGAE(0),
    BOTTOMALGAE(0),
    BOTTOMALGAEFAST(0),
    BOTTOMALGAENEW(0),
    TOPALGAEFAST(0),
    CLIMB(0);

    public double position;

    ElevatorStates(double totalPosition) {
        this.position = totalPosition;
    }
}
