package frc.robot.lib.components.pivot;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.math.geometry.Rotation2d;



public class Pivot {
    private PivotIO io;
    private PivotIOInputsAutoLogged inputs;
    private String AkitTopic;

       public Pivot(PivotIO io, PivotConstants constants, String AkitTopic) {
        this.io = io;
        this.inputs = new PivotIOInputsAutoLogged();
        this.AkitTopic = AkitTopic;
    }
    
    public void periodic(){
        io.updateInputs(inputs);
        Logger.processInputs(AkitTopic, inputs);
    }
    public void setGoal(Rotation2d goal){
        inputs.positionGoal= goal;
        io.setPositionGoal(goal);
    }
public boolean atGoal(){
    return io.atGoal();
}
}
