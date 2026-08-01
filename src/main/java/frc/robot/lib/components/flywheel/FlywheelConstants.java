package frc.robot.lib.components.flywheel;

import com.ctre.phoenix6.signals.InvertedValue;
import edu.wpi.first.units.measure.AngularVelocity;

public class FlywheelConstants {
    public final AngularVelocity tolerance;
    public final int canID, sensorToMechanismRatio;
    public final double kP, kD, kS, kV, kA, kG;
    public final InvertedValue inversion;

    public FlywheelConstants(int canID, AngularVelocity tolerance,
            double kP, double kD, double kS, double kV, double kA, double kG, int sensorToMechanismRatio,
            InvertedValue inversion) {
        this.canID = canID;
        this.tolerance = tolerance;
        this.kP = kP;
        this.kD = kD;
        this.kS = kS;
        this.kV = kV;
        this.kA = kA;
        this.kG = kG;
        this.sensorToMechanismRatio = sensorToMechanismRatio;
        this.inversion = inversion;
    }
}
