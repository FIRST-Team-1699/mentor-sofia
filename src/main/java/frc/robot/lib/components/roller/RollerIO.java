package frc.robot.lib.components.roller;

import org.littletonrobotics.junction.AutoLog;

public interface RollerIO {
    public void setVoltage(double voltage);
    
    @AutoLog
    public static class RollerIOInputs{
        public double voltage, current, velocity, position, temperature;
    }
}
