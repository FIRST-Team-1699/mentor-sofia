// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.swerve.SwerveRequest;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.DriveCommands;
import frc.robot.generated.TunerConstants;
import frc.robot.lib.components.flywheel.Flywheel;
import frc.robot.lib.components.flywheel.FlywheelIOTalonFX;
import frc.robot.lib.components.pivot.PivotIOTalonFX;
import frc.robot.lib.components.roller.RollerIO;
import frc.robot.lib.components.roller.RollerIOTalonFX;
import frc.robot.subsystems.drive.Drive;
import frc.robot.subsystems.drive.GyroIOPigeon2;
import frc.robot.subsystems.drive.ModuleIOTalonFX;
import frc.robot.subsystems.floor.Floor;
import frc.robot.subsystems.floor.FloorConstants;
import frc.robot.subsystems.indexer.Indexer;
import frc.robot.subsystems.indexer.IndexerConstants;
import frc.robot.subsystems.intakePivot.IntakePivot;
import frc.robot.subsystems.intakePivot.IntakePivotConstants;
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
  private CommandXboxController driveController, opController;
  private Drive drive;
  private ShooterPivot shooterPivot;
  private IntakePivot intakePivot;

  public RobotContainer() {
    this.indexer = new Indexer(new RollerIOTalonFX(IndexerConstants.rollerConstants));
    this.floor = new Floor(new RollerIOTalonFX(FloorConstants.rollerConstants));
    this.shooter = new Shooter(new FlywheelIOTalonFX(ShooterConstants.topFlywheelConstants),
        new FlywheelIOTalonFX(ShooterConstants.botFlywheelConstants));
    this.driveController = new CommandXboxController(RobotConstants.DRIVER_CONTROLLER_PORT);
    this.opController = new CommandXboxController(RobotConstants.OPERATOR_CONTROLLER_PORT);
    this.intakeRollers = new IntakeRollers(new RollerIOTalonFX(IntakeRollersConstants.topRollerConstants),
        new RollerIOTalonFX(IntakeRollersConstants.botRollerConstants));
    this.shooterPivot = new ShooterPivot(new PivotIOTalonFX(ShooterPivotConstants.pivotConstants));
    this.intakePivot = new IntakePivot(new PivotIOTalonFX(IntakePivotConstants.pivotConstants));
    configureBindings();
    this.drive = new Drive(new GyroIOPigeon2(),
        new ModuleIOTalonFX(TunerConstants.FrontLeft),
        new ModuleIOTalonFX(TunerConstants.FrontRight),
        new ModuleIOTalonFX(TunerConstants.BackLeft),
        new ModuleIOTalonFX(TunerConstants.BackRight));

    this.drive.setDefaultCommand(DriveCommands.joystickDrive(drive,
        () -> -driveController.getLeftY(),
        () -> -driveController.getLeftX(),
        () -> -driveController.getRightX()));
  }

  private void configureBindings() {

    opController.x().onTrue(
        new ConditionalCommand(intakePivot.intakeRetract(), intakePivot.intakeDeploy(), intakePivot::isDeployed));
    opController.y().onTrue(shooter.aimHigh()).onFalse(shooter.stop());
    opController.b().onTrue(indexer.feedShooter().alongWith(floor.feedShooter()))
        .onFalse(indexer.stop().alongWith(floor.stop()));
    opController.a().onTrue(intakeRollers.intake()).onFalse(intakeRollers.stop());
    driveController.y()
        .onTrue(Commands.runOnce(() -> drive.setPose(new Pose2d(drive.getPose().getTranslation(), new Rotation2d(0)))));
  }

  public Command getAutonomousCommand() {
    return Commands.sequence(
        intakePivot.intakeDeploy(),
        Commands.waitSeconds(5),
        intakePivot.intakeRetract(),
        Commands.waitSeconds(5)).repeatedly();
  }
}
