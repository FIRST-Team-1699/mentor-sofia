package frc.robot.lib.components.pivot;

import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.MotorAlignmentValue;

import edu.wpi.first.math.geometry.Rotation2d;

public class PivotConstants {
    public final Rotation2d tolerance;
    public final int leadCanID, followCanID, sensorToMechanismRatio;
    public final MotorAlignmentValue followerAlignment;
    public final double kP, kD, kS, kV, kA, kG;
    public final InvertedValue leadInversion;

    public PivotConstants(int leadCanID, int followCanID, Rotation2d tolerance, MotorAlignmentValue followerAlignment,
            double kP, double kD, double kS, double kV, double kA, double kG, int sensorToMechanismRatio,
            InvertedValue leadInversion) {
        this.leadCanID = leadCanID;
        this.followCanID = followCanID;
        this.tolerance = tolerance;
        this.followerAlignment = followerAlignment;
        this.kP = kP;
        this.kD = kD;
        this.kS = kS;
        this.kV = kV;
        this.kA = kA;
        this.kG = kG;
        this.sensorToMechanismRatio = sensorToMechanismRatio;
        this.leadInversion = leadInversion;
    }

}