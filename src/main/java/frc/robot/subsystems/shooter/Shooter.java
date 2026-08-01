package frc.robot.subsystems.shooter;

import static edu.wpi.first.units.Units.RotationsPerSecond;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.lib.components.flywheel.Flywheel;
import frc.robot.lib.components.flywheel.FlywheelIO;

public class Shooter extends SubsystemBase {
    private Flywheel topFlywheel;
    private Flywheel botFlywheel;

    public Shooter(FlywheelIO topIo, FlywheelIO botIo) {
        this.topFlywheel = new Flywheel(topIo, ShooterConstants.topFlywheelConstants, "Shooter/TopFlywheel");

        this.botFlywheel = new Flywheel(botIo, ShooterConstants.botFlywheelConstants, "Shooter/BotFlywheel");

    }

    @Override
    public void periodic() {
        topFlywheel.periodic();
        botFlywheel.periodic();
        Logger.recordOutput("ShooterFlywheel/TopFlywheel/atGoal", topFlywheel.atGoal());
        Logger.recordOutput("ShooterFlywheel/BotFlywheel/atGoal", botFlywheel.atGoal());
    }

    private Command setVelocityGoal(AngularVelocity goal) {
        return runOnce(() -> {
            topFlywheel.setGoal(goal);
            botFlywheel.setGoal(goal);
        });
    }

    public Command aimLow() {
        return setVelocityGoal(ShooterConstants.lowVelocity);
    }

    public Command aimHigh() {
        return setVelocityGoal(ShooterConstants.highVelocity);
    }

    public Command stop() {
        return runOnce(() -> {
            topFlywheel.setVoltage(0);
            botFlywheel.setVoltage(0);
        });
    }
}