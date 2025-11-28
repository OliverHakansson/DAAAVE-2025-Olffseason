package frc.robot.Subsystems.Arm;

public class ArmState{
    public enum ArmStates {
        IDLE,
        MOVING,
        MANUAL

    }
    
    public enum ArmPositions { //TODO add real values
        L1(1, 1),
        L2(1, 1),
        L3(1, 1),
        L4(1, 1),
        SAFE(1, 1),
        INTAKE_CORAL(1, 1),
        BARGE(1, 1),
        PROCESSOR(1, 1),
        GROUND_ALGAE(1, 1),
        LOLLIPOP(1, 1),
        REEF_ALGAE_HIGH(1, 1),
        REEF_ALGAE_LOW(1, 1),
        SUPERCYCLE_ALGAE_HIGH(1, 1),
        SUPERCYCLE_ALGAE_LOW(1, 1);

        public final float elevatorPosition;
        public final float wristPosition;

        private ArmPositions(float elevatorPosition, float wristPosition) {
            this.elevatorPosition = elevatorPosition;
            this.wristPosition = wristPosition;
        }    
    }
        
        
    private ArmStates current_arm_state = ArmStates.IDLE;
    private ArmPositions desired_arm_position = ArmPositions.SAFE;

    public ArmPositions getDesiredArmPosition () {
        return desired_arm_position;
    }   
}





