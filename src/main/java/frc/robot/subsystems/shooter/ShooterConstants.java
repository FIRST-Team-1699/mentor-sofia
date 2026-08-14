package frc.robot.subsystems.shooter;

import static edu.wpi.first.units.Units.RotationsPerSecond;

import com.ctre.phoenix6.signals.InvertedValue;

import edu.wpi.first.units.measure.AngularVelocity;
import frc.robot.lib.components.flywheel.FlywheelConstants;

public class ShooterConstants {
    public static final FlywheelConstants topFlywheelConstants = new FlywheelConstants(8, RotationsPerSecond.of(10), 0, 0, 0, 0.235, 0, 0, 2, InvertedValue.CounterClockwise_Positive);
    public static final FlywheelConstants botFlywheelConstants = new FlywheelConstants(9, RotationsPerSecond.of(10), 0, 0, 0, 0.235, 0, 0, 2, InvertedValue.CounterClockwise_Positive);
    public static final AngularVelocity lowVelocity = RotationsPerSecond.of(10);
    public static final AngularVelocity highVelocity = RotationsPerSecond.of(30);
}
