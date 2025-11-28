package frc.robot.Subsystems.Arm.Elevator;

import org.littletonrobotics.junction.LogTable;
import org.littletonrobotics.junction.inputs.LoggableInputs;

public class ElevatorIOInputsAutoLogged extends ElevatorIO.ElevatorIOInputs implements LoggableInputs, Cloneable {
    @Override
    public void toLog(LogTable table){

    }

    @Override
    public void fromLog(LogTable table) {

    }

    // public ElevatorIOInputsAutoLogged clone() {
    //     CoralRollersIOInputsAutoLogged copy = new CoralRollersIOInputsAutoLogged();
    //     copy.velocityRPM = this.velocityRPM;
    //     copy.appliedVolts = this.appliedVolts;
    //     copy.currentAmps = this.currentAmps;
    //     copy.hasCoral = this.hasCoral;
    //     copy.isIntaking = this.isIntaking;
    //     copy.coralMeasureDist = this.coralMeasureDist;
    //     copy.temperature = this.temperature;
    //     copy.isStalled = this.isStalled;
    //     return copy;
    // }
}
