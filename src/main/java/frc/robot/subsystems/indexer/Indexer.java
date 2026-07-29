package frc.robot.subsystems.indexer;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.lib.components.roller.Roller;
import frc.robot.lib.components.roller.RollerIO;

public class Indexer extends SubsystemBase{
    private Roller roller;
    public Indexer(RollerIO io){
        this.roller = new Roller(io, IndexerConstants.rollerConstants, "Indexer/Roller");
    }

    private Command setVoltage(double Voltage){
        return runOnce(() -> roller.setVoltage(Voltage));
    }

    public Command feedShooter(){
        return setVoltage(IndexerConstants.feedVoltage);
    }

    public Command stop(){
        return setVoltage(0);
    }

    @Override
    public void periodic() {
        roller.periodic();
    }
}
