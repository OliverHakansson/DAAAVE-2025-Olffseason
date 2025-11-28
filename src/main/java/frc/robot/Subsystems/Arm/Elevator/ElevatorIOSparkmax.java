package frc.robot.Subsystems.Arm.Elevator;

import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;

import frc.robot.Subsystems.Arm.Arm;

import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

public class ElevatorIOSparkmax implements ElevatorIO{
    
    private final SparkMax rightMotor = new SparkMax(11, MotorType.kBrushless);
    private final SparkMax leftMotor = new SparkMax(10, MotorType.kBrushless);

    private SparkMaxConfig rightConfig = new SparkMaxConfig();
    private SparkMaxConfig leftConfig = new SparkMaxConfig();

    private double feedForward = 0.0; //TODO set
    
    public Elevator elevator;
    public Arm arm;


    public ElevatorIOSparkmax(){
        rightConfig.inverted(false);
        rightConfig.idleMode(IdleMode.kBrake);
        rightConfig.signals.primaryEncoderPositionAlwaysOn(true);
        rightConfig.signals.primaryEncoderPositionPeriodMs(10);
        rightConfig.closedLoop.feedbackSensor(FeedbackSensor.kPrimaryEncoder);
        rightConfig.closedLoop.maxMotion.allowedClosedLoopError(0);
        rightConfig.closedLoop.positionWrappingEnabled(false);
        rightConfig.voltageCompensation(10);
        rightConfig.smartCurrentLimit(60, 60);
        rightConfig.closedLoop.feedbackSensor(FeedbackSensor.kPrimaryEncoder).pid(0,0,0); //TODO SET
        rightConfig.closedLoop.iZone(0);
        rightMotor.configure(leftConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        rightMotor.setPeriodicFrameTimeout(30);
        rightMotor.setCANTimeout(30);
        rightMotor.setCANMaxRetries(5);
        rightMotor.getEncoder().setPosition(0);
        

        leftConfig.inverted(false);
        leftConfig.idleMode(IdleMode.kBrake);
        leftConfig.signals.primaryEncoderPositionAlwaysOn(true);
        leftConfig.signals.primaryEncoderPositionPeriodMs(10);
        leftConfig.closedLoop.feedbackSensor(FeedbackSensor.kPrimaryEncoder);
        leftConfig.closedLoop.maxMotion.allowedClosedLoopError(0);
        leftConfig.closedLoop.positionWrappingEnabled(false);
        leftConfig.voltageCompensation(10);
        leftConfig.smartCurrentLimit(60, 60);
        leftConfig.closedLoop.feedbackSensor(FeedbackSensor.kPrimaryEncoder).pid(0,0,0); //TODO SET
        leftConfig.closedLoop.iZone(0);
        leftMotor.configure(leftConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        leftMotor.setPeriodicFrameTimeout(30);
        leftMotor.setCANTimeout(30);
        leftMotor.setCANMaxRetries(5);
        leftMotor.getEncoder().setPosition(0);
    }

    @Override
    public SparkMax GetRightMotor(){
        return rightMotor;
    }

    @Override
    public SparkMax GetLeftMotor(){
        return leftMotor;
    }

    public double GetElevatorPosition(){
        return rightMotor.getEncoder().getPosition();
    }

    public double getFeedForward(){
        return feedForward;
    }

    @Override
    public void UpdateInputs(ElevatorIOInputs inputs) {
        if (elevator == null)
            elevator = Elevator.GetInstance();
        inputs.leftPositionRotations = leftMotor.getEncoder().getPosition(); // .getPosition();
        inputs.leftVelocityRPM = leftMotor.getEncoder().getVelocity();
        inputs.leftAppliedVolts = leftMotor.getAppliedOutput() * leftMotor.getBusVoltage();
        inputs.leftCurrentAmps = leftMotor.getOutputCurrent();
        inputs.leftTemperature = leftMotor.getMotorTemperature();

        inputs.rightPositionRotations = -rightMotor.getEncoder().getPosition();
        inputs.rightVelocityRPM = rightMotor.getEncoder().getPosition();
        inputs.rightAppliedVolts = rightMotor.getAppliedOutput() * rightMotor.getBusVoltage();
        inputs.rightTemperature = rightMotor.getMotorTemperature();
        inputs.rightCurrentAmps = rightMotor.getOutputCurrent();

        if (arm==null){
            arm = Arm.getInstance();
        }
        inputs.elevatorWantedPosition = arm.wantedPosition.elevatorPosition;
        // inputs.elevatorTargetState = elevator.getTargetState();
    }

    @Override
    public void SetPosition(double position) {
        if (position < 0) {
            position = 0;
        }
        leftMotor
                .getClosedLoopController()
                .setReference(position, ControlType.kPosition, ClosedLoopSlot.kSlot0, feedForward);
        rightMotor
                .getClosedLoopController()
                .setReference(-position, ControlType.kPosition, ClosedLoopSlot.kSlot0, -feedForward);
    }
    
    public double GetPostion(){
        return (rightMotor.getEncoder().getPosition() + leftMotor.getEncoder().getPosition())/2;
    }
    
    @Override
    public void ConfigurePID(double kP, double kI, double kD) {
        rightConfig.closedLoop.pid(kP, kI, kD);

        leftConfig.closedLoop.pid(kP, kI, kD);

    }
}