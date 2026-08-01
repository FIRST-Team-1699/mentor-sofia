package frc.robot.lib.components.flywheel;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.units.measure.AngularVelocity;

public class Flywheel {
    private FlywheelIO io;
    private FlywheelIOInputsAutoLogged inputs;
    private String AkitTopic;

    public Flywheel(FlywheelIO io, FlywheelConstants constants, String AkitTopic) {
        this.io = io;
        this.inputs = new FlywheelIOInputsAutoLogged();
        this.AkitTopic = AkitTopic;
    }

    public void periodic() {
        io.updateInputs(inputs);
        Logger.processInputs(AkitTopic, inputs);
    }

    public void setGoal(AngularVelocity goal) {
        inputs.velocityGoal = goal;
        io.setVelocityGoal(goal);
    }

    public boolean atGoal() {
        return io.atGoal();
    }

    public void setVoltage(double volts){
        io.setVoltage(volts);
    }
}
