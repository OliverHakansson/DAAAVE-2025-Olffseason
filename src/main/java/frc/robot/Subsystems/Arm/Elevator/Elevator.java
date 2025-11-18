package frc.robot.Subsystems.Arm.Elevator;

import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.littletonrobotics.junction.Logger;

import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.SparkBase.ControlType;

public class Elevator extends SubsystemBase{
    
    static Elevator current_instance = null;

    private double wantedPosition = 0;

    private double currentTrapzoidalSetpoint = 0;

    public ElevatorIO io = new ElevatorIO();

    private final double maxspeed=1;

    private final double maxacceleration=1;

    //working here rui

    public Elevator(){
        current_instance = this;
    }
    public void SetPosition(double position){
        this.wantedPosition=position;
    
    }
    public static Elevator GetInstance(){
        return current_instance;
    }

    public static void SetInstance(Elevator instance){
        current_instance = instance;
    }

    public void SetState(ElevatorStates state) {
        if (state.position < 0)
            state.position = 0;

        io.GetRightMotor()
            .getClosedLoopController()
            .setReference(state.position, ControlType.kPosition, ClosedLoopSlot.kSlot0, io.getFeedForward());

        io.GetLeftMotor()
            .getClosedLoopController()
            .setReference(state.position, ControlType.kPosition, ClosedLoopSlot.kSlot0, io.getFeedForward());
    
    }
}



