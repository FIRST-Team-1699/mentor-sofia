package frc.robot.subsystems.intakePivot;

import static edu.wpi.first.units.Units.Degrees;

import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import edu.wpi.first.math.geometry.Rotation2d;
import frc.robot.lib.components.pivot.PivotConstants;

public class IntakePivotConstants {
    public static final PivotConstants pivotConstants = new PivotConstants(46, 53, Rotation2d.fromDegrees(5),
            MotorAlignmentValue.Opposed, 22.0, 0.0, 0.0, 0.0, 0.0, 0.3, 47, InvertedValue.Clockwise_Positive, Degrees.of(89.0));
    public static final Rotation2d lowPosition = Rotation2d.fromDegrees(8);
    public static final Rotation2d highPosition = Rotation2d.fromDegrees(89);
}
