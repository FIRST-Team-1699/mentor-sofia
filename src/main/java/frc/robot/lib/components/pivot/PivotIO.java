package frc.robot.lib.components.pivot;

import org.littletonrobotics.junction.AutoLog;

import edu.wpi.first.math.geometry.Rotation2d;

public interface PivotIO {

    
    public boolean atGoal();
    public void setPositionGoal(Rotation2d goal);
    public void updateInputs(PivotIOInputsAutoLogged inputs);
    
    @AutoLog
    public static class PivotIOInputs{
        public double voltage, current, velocity, position, leadTemperature, followTemperature;
        public Rotation2d positionGoal;
    }

}
