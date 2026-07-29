package frc.robot.subsystems.intakeRollers;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.lib.components.roller.Roller;
import frc.robot.lib.components.roller.RollerIO;
import frc.robot.subsystems.floor.FloorConstants;


public class IntakeRollers extends SubsystemBase{
    private Roller topRoller;
    private Roller botRoller;
    public IntakeRollers(RollerIO topIo, RollerIO botIo){
        this.topRoller = new Roller(topIo,IntakeRollersConstants.topRollerConstants, "IntakeRollers/TopRoller");

        this.botRoller = new Roller(botIo,IntakeRollersConstants.botRollerConstants, "IntakeRollers/BotRoller");

    }
    private Command setVoltage(double topVoltage, double botVoltage){
        return runOnce(() -> {
          topRoller.setVoltage(topVoltage);
          botRoller.setVoltage(botVoltage);
        });
    }

    public Command intake(){
        return setVoltage(IntakeRollersConstants.topIntakeVoltage, IntakeRollersConstants.botIntakeVoltage);
    }
    public Command outtake(){
        return setVoltage(IntakeRollersConstants.topOutakeVoltage,IntakeRollersConstants.botOutakeVoltage);
    }

    public Command stop(){
        return setVoltage(0,0);
    }

    @Override
    public void periodic() {
        topRoller.periodic();
        botRoller.periodic();
    }
}