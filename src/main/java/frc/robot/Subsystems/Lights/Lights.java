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
// link to CANdle documentation:
// https://api.ctr-electronics.com/phoenix/release/java/com/ctre/phoenix/led/CANdle.html
// need to set up advantage kit or sumthin, idk

public class Lights extends SubsystemBase {
    private LightStates wantedLightState;
    private LightStates currentLightState = LightStates.IDLE;
    public final LightsIO io;
    private static Lights instance = null;
    private final RollersIOInputsAutoLogged inputs = new RollersIOInputsAutoLogged();

    private CANdle candle;
    private Animation lastAnimation;
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
    
    public void setAnimation(LightStates LightStates) {
        if (LightStates.time == 0) {
            configAnimation(LightStates.animation);
            return;
        }
        this.currentLightState = LightStates;
    }

    public void setAnimation(LightStates[] LightStates) {
        for (LightStates LightStates2 : LightStates) {
            setAnimation(LightStates2);
        }
    }

    private void configAnimation(Animation animation) {
        if (animation == lastAnimation) return;
        candle.animate(animation);
        lastAnimation = animation;
    }

    int flag = 0;
    double start = 0;





    public void handleStateTransitions() {
        this.currentLightState = this.wantedLightState;


        switch (wantedLightState) {
            default:
                currentLightState = LightStates.IDLE;
                break;
            case CORAL_L4:
                start = Timer.getFPGATimestamp();
                configAnimation(LightStatesList.get(0).animation);
                flag = 1;
                break;
            
        }
    }

    public void applyStates() {
        this.setState(currentRollerState);
    }




    @Override
    public void periodic() {
        io.updateInputs(inputs);
        //Log

        //Handle Statements
        handleStateTransitions();

        //Apply States
        applyStates();
    }
}
