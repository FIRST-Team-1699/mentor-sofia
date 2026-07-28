// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.lib.components.roller.RollerIOTalonFX;
import frc.robot.subsystems.floor.Floor;
import frc.robot.subsystems.floor.FloorConstants;
import frc.robot.subsystems.indexer.Indexer;
import frc.robot.subsystems.indexer.IndexerConstants;

public class RobotContainer {
  private Indexer indexer;
  private Floor floor;
  private CommandXboxController drivController, opController;
  public RobotContainer() {
    this.indexer = new Indexer(new RollerIOTalonFX(IndexerConstants.rollerConstants));
    this.floor = new Floor(new RollerIOTalonFX(FloorConstants.rollerConstants));
    this.drivController = new CommandXboxController(RobotConstants.DRIVER_CONTROLLER_PORT);
    this.opController = new CommandXboxController(RobotConstants.OPERATOR_CONTROLLER_PORT);
    configureBindings();
  }

  private void configureBindings() {
    opController.x().onTrue(indexer.feedShooter()).onFalse(indexer.stop());
    opController.y().onTrue(floor.feedShooter()).onFalse(floor.stop());
    opController.b().onTrue(indexer.feedShooter().alongWith(floor.feedShooter())).onFalse(indexer.stop().alongWith(floor.stop()));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }


}
