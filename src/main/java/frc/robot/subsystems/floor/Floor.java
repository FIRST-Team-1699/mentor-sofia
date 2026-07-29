package frc.robot.subsystems.floor;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.lib.components.roller.Roller;
import frc.robot.lib.components.roller.RollerIO;

public class Floor extends SubsystemBase{
    private Roller roller;
    public Floor(RollerIO io){
        this.roller = new Roller(io, FloorConstants.rollerConstants, "Floor/Roller");
    }

    private Command setVoltage(double Voltage){
        return runOnce(() -> roller.setVoltage(Voltage));
    }

    public Command feedShooter(){
        return setVoltage(FloorConstants.feedVoltage);
    }

    public Command stop(){
        return setVoltage(0);
    }

    @Override
    public void periodic() {
        roller.periodic();
    }
}
