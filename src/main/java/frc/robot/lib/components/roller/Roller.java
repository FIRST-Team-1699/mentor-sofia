package frc.robot.lib.components.roller;

public class Roller {
    private RollerIO io;
    private RollerIOInputsAutoLogged inputs;

    public Roller(RollerIO io, RollerConstants constants) {
        this.io = io;
        this.inputs = new RollerIOInputsAutoLogged();
    }
}
