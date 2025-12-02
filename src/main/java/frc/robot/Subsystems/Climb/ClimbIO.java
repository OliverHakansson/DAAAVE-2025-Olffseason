package frc.robot.Subsystems.Climb;

import org.littletonrobotics.junction.AutoLog;

import com.revrobotics.spark.ClosedLoopSlot;

public interface ClimbIO {
    @AutoLog
    public static class ClimbIOInputs {
        public double positionRotations;
        public double currentAmps;
        public double appliedVolts;
        public double busVoltage;
        public boolean climbClamped;
        public double velocityRPM;

    }

    /** Updates the set of loggable inputs. */
    public default void updateInputs(ClimbIOInputs inputs) {}

    public default void setPosition(double newPosition, doublerollerVoltage,ClosedLoopSlot slot) {}

    public default double getPosition() {
        return 0;
    }

    public default void allStop() {}

    public default void updateSim() {}

    public default void setVoltage(double voltage) {};

    public default void setTalonVoltage(double voltage) {};

    public default boolean talonIsStalled() {
        return false;
    }

    public default boolean isClimbClamped () {
        return false;
    }
}