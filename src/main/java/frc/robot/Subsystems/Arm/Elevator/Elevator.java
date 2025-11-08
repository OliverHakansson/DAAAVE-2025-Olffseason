package frc.robot.Elevator.Elevator;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.littletonrobotics.junction.Logger;

import com.revrobotics.spark.ClosedLoopSlot;

public class Elevator extends SubsystemBase{
    
    static Elevator current_instance = null;

    public Elevator(){
        current_instance = this;
    }

    public static Elevator GetInstance(){
        return current_instance;
    }

    public static void SetInstance(Elevator instance){
        current_instance = instance;
    }
}



