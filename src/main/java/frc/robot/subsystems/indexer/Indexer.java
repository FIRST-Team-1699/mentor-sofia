package frc.robot.subsystems.indexer;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.lib.components.roller.Roller;
import frc.robot.lib.components.roller.RollerConstants;
import frc.robot.lib.components.roller.RollerIO;

public class Indexer extends SubsystemBase{
    private Roller roller;
    public Indexer(RollerIO io, RollerConstants constants){
        this.roller = new Roller(io, constants, "Indexer/Roller");
    }

    public Command setVoltage(double Voltage){
        return runOnce(() -> roller.setVoltage(Voltage));
    }
}
