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
    private LightStates lightState;
    public final LightsIO io;
    private static Lights instance = null;
    private final LightsIOInputsAutoLogged inputs = new LightsIOInputsAutoLogged();

    private CANdle candle;
    private static Lights light;    




    public static Lights getInstance() {
        if (light == null) {
            light = new Lights();
        }
        return light;
    }


    public Lights() {
        this.candle = new CANdle(19);
        this.lightState = LightStates.IDLE;
    }
    
    public void setLightState(LightStates state) {
        candle.animate(state.animation);
    }

    public void setLightState(LightStates[] states) {
        for (LightStates state : states) {
            candle.animate(state.animation);
        }
    }

    int flag = 0;
    double start = 0;








    @Override
    public void periodic() {
        io.updateInputs(inputs);
    }
}
