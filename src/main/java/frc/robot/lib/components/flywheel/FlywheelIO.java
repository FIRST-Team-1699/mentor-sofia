package frc.robot.lib.components.flywheel;

import static edu.wpi.first.units.Units.RotationsPerSecond;

import org.littletonrobotics.junction.AutoLog;

import edu.wpi.first.units.measure.AngularVelocity;

public interface FlywheelIO {
    public boolean atGoal();

    public void setVelocityGoal(AngularVelocity goal);

    public void updateInputs(FlywheelIOInputsAutoLogged inputs);

    public void setVoltage(double volts);
    @AutoLog
    public static class FlywheelIOInputs {
        public double voltage, current, position, temperature;
        public AngularVelocity velocity, error;
        public AngularVelocity velocityGoal = RotationsPerSecond.of(0);
    }
    
}
