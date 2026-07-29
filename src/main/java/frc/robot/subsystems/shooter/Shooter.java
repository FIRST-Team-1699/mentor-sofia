package frc.robot.subsystems.shooter;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.lib.components.roller.Roller;
import frc.robot.lib.components.roller.RollerIO;


public class Shooter extends SubsystemBase{
    private Roller topRoller;
    private Roller botRoller;
    public Shooter(RollerIO topIo, RollerIO botIo){
        this.topRoller = new Roller(topIo,ShooterConstants.topRollerConstants, "Shooter/TopRoller");

        this.botRoller = new Roller(botIo,ShooterConstants.botRollerConstants, "Shooter/BotRoller");

    }
    private Command setVoltage(double topVoltage, double botVoltage){
        return runOnce(() -> {
          topRoller.setVoltage(topVoltage);
          botRoller.setVoltage(botVoltage);
        });
    }

    public Command intake(){
        return setVoltage(ShooterConstants.topIntakeVoltage, ShooterConstants.botIntakeVoltage);
    }
    public Command outtake(){
        return setVoltage(ShooterConstants.topOutakeVoltage,ShooterConstants.botOutakeVoltage);
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