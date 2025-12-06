package frc.robot.Subsystems.Lights;

import org.littletonrobotics.junction.AutoLog;
import com.ctre.phoenix.led.CANdle;

public interface LightsIO {
    @AutoLog
    public static class LightsIOInputs {
        LightStates animationState;
    }

    public default void setAnimation(LightStates animations) {}

    public default void setAnimation(LightStates[] animations) {}

    public default void updateInputs(LightsIOInputs inputs) {}

    public default void playAnimation(){}

    public default LightStates getAnimation() {
        return LightStates.IDLE;
    }

    public default void updateSim() {}

    public default CANdle getcandle() {return null;}
}
