
package frc.robot.Subsystems.Lights;

import com.ctre.phoenix.led.CANdle;


public class LightsIOSystem implements LightsIO {

    public CANdle candle;
    private LightStates animation;

    public LightsIOSystem() {
        this.candle = new CANdle(24, "KrakensBus");
    }

    public void setAnimation(LightStates animations) {
        this.animation = animations;
    }

    public void setAnimation(LightStates[] animations) {
        for (LightStates animations2 : animations) {
            setAnimation(animations2);
        }
    }

    @Override
    public LightStates getAnimation() {
        return this.animation;
    }

    @Override
    public void playAnimation() {
        this.candle.setLEDs(1, 1, 1);
    }

    @Override
    public void updateInputs(LightsIOInputs inputs) {

    }

    public CANdle getcandle() {
        return this.candle;
    }
}