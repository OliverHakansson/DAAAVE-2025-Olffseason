package frc.robot.Subsystems.Lights;

import com.ctre.phoenix.led.Animation;
import com.ctre.phoenix.led.RainbowAnimation;
import com.ctre.phoenix.led.StrobeAnimation;

import frc.robot.Constants;

public enum LightStates {
    DEFAULT(new StrobeAnimation(255, 255, 255, 0, 1, Constants.NUM_LEDS), 0),
    HOLD_ALGAE(new StrobeAnimation(255, 18, 255, 0, 1, Constants.NUM_LEDS), 0),
    INTAKE_CORAL(new StrobeAnimation(255, 255, 0, 0, 0.5, Constants.NUM_LEDS), 0),
    INTAKE_ALGAE(new StrobeAnimation(255, 0, 255, 0, 1, Constants.NUM_LEDS), 0),
    PROCESSOR_ALGAE(new StrobeAnimation(0, 0, 255, 0, 1, Constants.NUM_LEDS), 0),
    BARGE_ALGAE(new StrobeAnimation(255, 0, 0, 0, 0.5, Constants.NUM_LEDS), 0),
    CORAL_L4(new StrobeAnimation(0, 255, 0, 0, 1, Constants.NUM_LEDS), 0),
    CORAL_L3(new StrobeAnimation(255, 0, 255, 0, 0.8, Constants.NUM_LEDS), 0),
    CORAL_L2(new RainbowAnimation(), 0),
    CORAL_L1(new StrobeAnimation(0, 255, 0, 0, 0.5, Constants.NUM_LEDS), 0),
    LOLLIPOP(new StrobeAnimation(0, 255, 0, 0, 1, Constants.NUM_LEDS), 0), 
    GROUND_ALGAE(new StrobeAnimation(0,255,200,0,1,Constants.NUM_LEDS),0),
    IDLE(new StrobeAnimation(0,0,0,0,1,Constants.NUM_LEDS),0);

    
    public Animation animation;
    public double time;
    private LightStates (Animation animation, double time) {
        this.animation = animation;
        this.time = time;
    }

    
}
