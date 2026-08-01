// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.lib.components.flywheel.Flywheel;
import frc.robot.lib.components.flywheel.FlywheelIOTalonFX;
import frc.robot.lib.components.pivot.PivotIOTalonFX;
import frc.robot.lib.components.roller.RollerIO;
import frc.robot.lib.components.roller.RollerIOTalonFX;
import frc.robot.subsystems.floor.Floor;
import frc.robot.subsystems.floor.FloorConstants;
import frc.robot.subsystems.indexer.Indexer;
import frc.robot.subsystems.indexer.IndexerConstants;
import frc.robot.subsystems.intakeRollers.IntakeRollers;
import frc.robot.subsystems.intakeRollers.IntakeRollersConstants;
import frc.robot.subsystems.shooter.Shooter;
import frc.robot.subsystems.shooter.ShooterConstants;
import frc.robot.subsystems.shooterPivot.ShooterPivot;
import frc.robot.subsystems.shooterPivot.ShooterPivotConstants;

public class RobotContainer {
  private Indexer indexer;
  private Floor floor;
  private IntakeRollers intakeRollers;
  private Shooter shooter;
  private CommandXboxController drivController, opController;
  private ShooterPivot shooterPivot;
  public RobotContainer() {
    this.indexer = new Indexer(new RollerIOTalonFX(IndexerConstants.rollerConstants));
    this.floor = new Floor(new RollerIOTalonFX(FloorConstants.rollerConstants));
    this.shooter = new Shooter(new FlywheelIOTalonFX(ShooterConstants.topFlywheelConstants), new FlywheelIOTalonFX(ShooterConstants.botFlywheelConstants));
    this.drivController = new CommandXboxController(RobotConstants.DRIVER_CONTROLLER_PORT);
    this.opController = new CommandXboxController(RobotConstants.OPERATOR_CONTROLLER_PORT);
    this.intakeRollers = new IntakeRollers(new RollerIOTalonFX(IntakeRollersConstants.topRollerConstants), new RollerIOTalonFX(IntakeRollersConstants.botRollerConstants));
    this.shooterPivot = new ShooterPivot(new PivotIOTalonFX(ShooterPivotConstants.pivotConstants));
    configureBindings();
  }

  private void configureBindings() {
    opController.x().onTrue(indexer.feedShooter()).onFalse(indexer.stop());
    opController.y().onTrue(floor.feedShooter()).onFalse(floor.stop());
    opController.b().onTrue(indexer.feedShooter().alongWith(floor.feedShooter())).onFalse(indexer.stop().alongWith(floor.stop()));
    opController.a().onTrue(intakeRollers.intake()).onFalse(intakeRollers.stop());
  }

  public Command getAutonomousCommand() {
    return Commands.sequence(
      shooter.aimHigh(),
      Commands.waitSeconds(5),
      shooter.stop(),
      Commands.waitSeconds(5)
    ).repeatedly();
  }


}
