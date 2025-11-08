package frc.robot.Subsystems.Arm.ArmStates;

public class ArmStates{
    public enum ArmState {
        IDLE,
        MOVEING_TO_POS,
        HOME,

    }
    public enum ArmPositions {
        L1,
        L2,
        L3,
        L4,
        SAFE,
        INTAKE,
        BARGE,
        PROCESSOR,
        
    }
    private ArmState current_arm_state = ArmState.IDLE;
    private ArmPositions desired_arm_position = ArmPositions.SAFE;
}