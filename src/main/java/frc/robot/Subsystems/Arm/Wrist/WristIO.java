package frc.robot.Subsystems.Arm.Wrist;

import org.littletonrobotics.junction.AutoLog;

import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.SparkFlex;

public interface WristIO {
    @AutoLog
    public static class WristIOInputs {
        public double wristPositionRotations = 0.0;
        public double wristVelocityRPM = 0.0;
        public double wristAppliedVolts = 0.0;
        public double wristBusVoltage = 0.0;
        public double wristCurrentAmps = 0.0;
        public double wristTemperature = 0.0;
        public WristStates wristTargetState;
        public double wristTargetPositon;
    }

    public default void updateInputs(WristIOInputs inputs) {}

    public default void setPosition(double position, ClosedLoopSlot slot) {}

    public default double getPosition() {
        return 0;
    }

    public default void updateSim() {}

    public default void setOffset(double offset) {}

    public default SparkFlex getWristMotor() {
        return null;
    }

    public default double getFeedForward(){
        return 0;
    }

    public default void configurePID(double kP, double kI, double kD) {}
}
