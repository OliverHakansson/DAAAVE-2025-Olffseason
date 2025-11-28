package frc.robot.Subsystems.Arm.Elevator;

import org.littletonrobotics.junction.AutoLog;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;
import com.revrobotics.spark.SparkMax;

public interface ElevatorIO {
  @AutoLog 
  public static class ElevatorIOInputs {
    public double leftPositionRotations = 0.0;
    public double leftVelocityRPM = 0.0;
    public double leftAppliedVolts = 0.0;
    public double leftCurrentAmps = 0.0;
    public double leftTemperature = 0.0;

    public double rightPositionRotations = 0.0;
    public double rightVelocityRPM = 0.0;
    public double rightAppliedVolts = 0.0;
    public double rightCurrentAmps = 0.0;
    public double rightTemperature = 0.0;

    public double elevatorWantedPosition = 0.0;
  }
  public default double getFeedForward(){return 0;}

  public default void UpdateInputs(ElevatorIOInputs inputs) {}

  public default void SetPosition(double position) {}
  
  public default double GetPosition() {return 0;}

  public default void UpdateSim() {}
  
  public default void ConfigurePID(double kP, double kI, double kD) {}

  public default SparkMax GetRightMotor() {
    return null;
  }
  public default SparkMax GetLeftMotor() {
    return null;
  }
}
