package frc.robot.subsystems.shooterPivot;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.lib.components.pivot.Pivot;
import frc.robot.lib.components.pivot.PivotIO;

public class ShooterPivot extends SubsystemBase {
    private Pivot pivot;

    public ShooterPivot(PivotIO pivotIo) {
        this.pivot = new Pivot(pivotIo, ShooterPivotConstants.pivotConstants, "ShooterPivot/Pivot");
    }

    @Override
    public void periodic() {
        pivot.periodic();
        Logger.recordOutput("ShooterPivot/Pivot/atGoal", pivot.atGoal());
    }

    private Command setPositionGoal(Rotation2d goal) {
        return runOnce(() -> pivot.setGoal(goal));
    }

    public Command aimLow() {
        return setPositionGoal(ShooterPivotConstants.lowPosition);
    }

    public Command aimHigh() {
        return setPositionGoal(ShooterPivotConstants.highPosition);
    }
}
