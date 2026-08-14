package frc.robot.subsystems.intakePivot;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.lib.components.pivot.Pivot;
import frc.robot.lib.components.pivot.PivotIO;

public class IntakePivot extends SubsystemBase {
    private Pivot pivot;
    private Boolean deployed;

    public IntakePivot(PivotIO pivotIo) {
        this.pivot = new Pivot(pivotIo, IntakePivotConstants.pivotConstants, "IntakePivot/Pivot");
        this.deployed = false;
    }

    @Override
    public void periodic() {
        pivot.periodic();
        Logger.recordOutput("IntakePivot/Pivot/atGoal", pivot.atGoal());
    }

    private Command setPositionGoal(Rotation2d goal) {
        return runOnce(() -> pivot.setGoal(goal));
    }

    public Command intakeDeploy() {
        return Commands.runOnce(()->
            deployed = true
            ).alongWith(setPositionGoal(IntakePivotConstants.lowPosition));
    }

    public Command intakeRetract() {
        return Commands.runOnce(()->
            deployed = false
            ).alongWith(setPositionGoal(IntakePivotConstants.highPosition));
    }

    public boolean isDeployed(){
        return deployed;
    }
}
