package frc.robot.Subsystems.Arm.ArmStates;

public class ArmStates{
    public enum ArmState {
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
        INTAKECORAL(1, 1),
        BARGE(1, 1),
        PROCESSOR(1, 1),
        GROUNDALGAE(1, 1),
        LOLLIPOP(1, 1),
        REEFALGAEHIGH(1, 1),
        REEFALGAELOW(1, 1),
        SUPERCYCLEALGAEHIGH(1, 1),
        SUPERCYCLEALGAELOW(1, 1);

        public final float elevatorPosition;
        public final float wristPosition;

        private ArmPositions(float elevatorPosition, float wristPosition) {
            this.elevatorPosition = elevatorPosition;
            this.wristPosition = wristPosition;
        }    
    }
        
        
    private ArmState current_arm_state = ArmState.IDLE;
    private ArmPositions desired_arm_position = ArmPositions.SAFE;

    public ArmPositions getDesiredArmPosition () {
        return desired_arm_position;
    }
    
    

    
}





