package frc.robot.Subsystems.Rollers;

import org.littletonrobotics.junction.AutoLog;

import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.SparkFlex;

public interface RollersIO {
    @AutoLog
    public static class WristIOInputs {
        public RollerStates currentRollerState = RollerStates.IDLE;
        public RollerStates wantedRollerState = RollerStates.IDLE;
        public double RollerVoltage = 0.0;
        public double RollerTemperature = 0.0;
        public double RollerCurrentAmps = 0.0;
        public double BusVoltage = 0.0;
        public double RollerVelocityRPM = 0.0;
        public boolean isStalled = false;
        public boolean hasCoral = false;
    }
    public static class RollersIOInputs {
        public double velocityRPM = 0.0;
        public double appliedVolts = 0.0;
        public double currentAmps = 0.0;
        public boolean hasCoral = false;
        public boolean isIntaking = false;
        public double coralMeasureDist = 0;
        public double temperature = 0;
        public boolean isStalled = false;
    }

    public default void updateInputs(WristIOInputs inputs) {}

    public default void setVoltage(double voltage) {}

    public default double getVoltage() {
        return 0;
    }

    public default void updateSim() {}

    public default SparkFlex getRollerMotor() {
        return null;
    }

    public default boolean clawFrontSensorTriggered() {
        return false;
    }

    public default boolean clawBackSensorTriggered() {
        return false;
    }

    public default boolean isStalled(){
        return false;
    }

    public default boolean HasCoral(){
        return false;
    }

    public default boolean IsIntaking(){
        return false;
    }
    public default void rotateBy(double movement) {}
}
