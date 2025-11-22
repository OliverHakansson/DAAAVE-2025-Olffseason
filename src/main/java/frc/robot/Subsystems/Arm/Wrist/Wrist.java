package frc.robot.Subsystems.Arm.Wrist;

import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.SparkBase.ControlType;

import edu.wpi.first.math.MathUtil;
import frc.robot.Subsystems.Arm.ArmState.ArmPositions;

public class Wrist {

    private static Wrist current_instance = null;

    public WristIO io = new WristIOSparkMax();

    public Wrist(){
        current_instance = this;
    }

    public double getPosition(){
        return this.io.getPosition();
    }
    public static Wrist getInstance(){
        return current_instance;
    }

    public static void setInstance(Wrist instance){
        current_instance = instance;
    }

    public void setState(WristStates state) {
        if (state.wristPosition < 0)
            state.wristPosition = 0;

        io.getWristMotor()
            .getClosedLoopController()
            .setReference(state.wristPosition, ControlType.kPosition, ClosedLoopSlot.kSlot0, io.getFeedForward());
    }

    public boolean isAtPosition(ArmPositions armPos){
        return MathUtil.isNear(armPos.wristPosition, io.getPosition(),0.05);
    }

}
