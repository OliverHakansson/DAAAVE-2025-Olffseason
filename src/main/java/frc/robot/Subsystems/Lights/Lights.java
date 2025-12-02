package frc.robot.Subsystems.Lights;

import java.util.ArrayList;

import com.ctre.phoenix.led.Animation;
import com.ctre.phoenix.led.CANdle;
import com.ctre.phoenix6.signals.LossOfSignalBehaviorValue;

import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Subsystems.Rollers.RollerStates;
import frc.robot.Subsystems.Rollers.Rollers;
import frc.robot.Subsystems.Rollers.RollersIO;

import org.littletonrobotics.junction.Logger;




public class Lights extends SubsystemBase {
    private AnimationStates wantedAnimationState;
    private AnimationStates currentAnimationState;
    public final LightsIO io;
    private static Lights instance = null;
    private final LightsIOInputsAutoLogged inputs = new LightsIOInputsAutoLogged();

    private CANdle candle;
    private AnimationStates lastAnimation;
    private static Lights light;    




    public static Lights getInstance() {
        if (light == null) {
            light = new Lights();
        }
        return light;
    }


    public Lights() {
        this.candle = new CANdle(19);
    }
    
    public void setAnimation(AnimationStates animation) {
        if (animation.time == 0) {
            configAnimation(animation);
            return;
        }
        this.currentAnimationState = animation;
    }

    public void setAnimation(AnimationStates[] animations) {
        for (AnimationStates animation : animations) {
            setAnimation(animation);
        }
    }

    private void configAnimation(AnimationStates animation) {
        if (animation == lastAnimation) return;
        candle.animate(animation.animation);
        lastAnimation = animation;
    }

    int flag = 0;
    double start = 0;





    public void handleStateTransitions() {
        switch (wantedAnimationState) {
            default:
                this.currentAnimationState = AnimationStates.IDLE;
                break;
            case CORAL_L4:
                this.currentAnimationState = AnimationStates.CORAL_L4;
                break;
        }
    }

    public void applyStates() {
        this.setState(currentLightState);
    }

    public void setState(LightStates currentLightState){

    }


    public void setState(LightStates currentLightState){

    }





    @Override
    public void periodic() {
        io.updateInputs(inputs);

        handleStateTransitions();

        applyStates();
    }
}
