package frc.robot.Subsystems.Lights;

import com.ctre.phoenix.led.Animation;
import com.ctre.phoenix.led.StrobeAnimation;

import frc.robot.Constants;

public enum LightStates {
    IDLE(new StrobeAnimation(255, 255, 0, 0, 1, Constants.NUM_LEDS), 0),
    MOVING(new StrobeAnimation(0, 0, 255, 0, 1, Constants.NUM_LEDS), 0),
    UNABLE(new StrobeAnimation(255, 0, 0, 0, 1, Constants.NUM_LEDS), 0),
    HOLDINGGAMEPIECE(new StrobeAnimation(255, 157, 0, 0, 1, Constants.NUM_LEDS), 0),
    ALIGNED(new StrobeAnimation(0, 255, 0, 0, 1, Constants.NUM_LEDS), 0),
    AUTO(new StrobeAnimation(255, 0, 234, 0, 1, Constants.NUM_LEDS), 0);

    
    public Animation animation;
    public double time;
    private LightStates (Animation animation, double time) {
        this.animation = animation;
        this.time = time;
    }
}
