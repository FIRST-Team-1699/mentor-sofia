package frc.robot.subsystems.shooterPivot;

import static edu.wpi.first.units.Units.Degrees;

import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import edu.wpi.first.math.geometry.Rotation2d;
import frc.robot.lib.components.pivot.PivotConstants;

public class ShooterPivotConstants {
    public static final PivotConstants pivotConstants = new PivotConstants(5, 49, Rotation2d.fromDegrees(5),
            MotorAlignmentValue.Opposed, 250.0, 0.0, 0.0, 0.0, 0.0, 0.0, 81, InvertedValue.CounterClockwise_Positive, Degrees.of(0));
    public static final Rotation2d lowPosition = Rotation2d.fromDegrees(5);
    public static final Rotation2d highPosition = Rotation2d.fromDegrees(95);
}
