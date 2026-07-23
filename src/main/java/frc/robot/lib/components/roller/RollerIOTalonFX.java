package frc.robot.lib.components.roller;

import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;

public class RollerIOTalonFX implements RollerIO {
    private TalonFX motor;

    public RollerIOTalonFX(RollerConstants constants){
        motor = new TalonFX(constants.canID);
    }

    @Override
    public void setVoltage(double voltage) {
        motor.setControl(new VoltageOut(voltage));
    }

    @Override
    public void updateInputs(RollerIOInputsAutoLogged inputs) {
        inputs.voltage = motor.getSupplyVoltage().getValueAsDouble();
        inputs.current = motor.getSupplyCurrent().getValueAsDouble();
        inputs.position = motor.getPosition().getValueAsDouble();
        inputs.temperature = motor.getDeviceTemp().getValueAsDouble();
        inputs.velocity = motor.getVelocity().getValueAsDouble();
    }
    
}
