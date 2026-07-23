package frc.robot.lib.components.roller;

import org.littletonrobotics.junction.Logger;

public class Roller {
    private RollerIO io;
    private RollerIOInputsAutoLogged inputs;
    private String AkitTopic;

    public Roller(RollerIO io, RollerConstants constants, String AkitTopic) {
        this.io = io;
        this.inputs = new RollerIOInputsAutoLogged();
        this.AkitTopic = AkitTopic;
    }

    public void periodic(){
        io.updateInputs(inputs);
        Logger.processInputs(AkitTopic, inputs);
    }

    public void setVoltage(double voltage){
        io.setVoltage(voltage);
    }
}
