package frc.robot.lib.components.flywheel;

import static edu.wpi.first.units.Units.RotationsPerSecond;

import org.littletonrobotics.junction.AutoLogOutput;

import com.ctre.phoenix6.configs.FeedbackConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;


import edu.wpi.first.units.measure.AngularVelocity;

public class FlywheelIOTalonFX implements FlywheelIO{
    private TalonFX motor;
    private AngularVelocity goal;
    private FlywheelConstants constants;

    public FlywheelIOTalonFX(FlywheelConstants constants) {
        motor = new TalonFX(constants.canID);
        TalonFXConfiguration config = new TalonFXConfiguration();
        config.withSlot0(new Slot0Configs().withKP(constants.kP).withKD(constants.kD).withKS(constants.kS)
                .withKV(constants.kV).withKA(constants.kA).withKG(constants.kG));
        config.withFeedback(new FeedbackConfigs().withSensorToMechanismRatio(constants.sensorToMechanismRatio));
        config.withMotorOutput(new MotorOutputConfigs().withInverted(constants.inversion));
        motor.getConfigurator().apply(config);
        this.constants = constants;
        this.goal = RotationsPerSecond.of(0);
    }

    @Override
    public void updateInputs(FlywheelIOInputsAutoLogged inputs) {
        inputs.voltage = motor.getMotorVoltage().getValueAsDouble();
        inputs.current = motor.getSupplyCurrent().getValueAsDouble();
        inputs.position = motor.getPosition().getValueAsDouble();
        inputs.temperature = motor.getDeviceTemp().getValueAsDouble();
        inputs.velocity = motor.getVelocity().getValue();
        inputs.error = RotationsPerSecond.of(Math.abs(goal.in(RotationsPerSecond) - motor.getVelocity().getValueAsDouble()));
    }

    @Override
    @AutoLogOutput
    public boolean atGoal() {
        return Math.abs(goal.in(RotationsPerSecond) - motor.getVelocity().getValueAsDouble()) < constants.tolerance
                .in(RotationsPerSecond);
    }

    @Override
    public void setVelocityGoal(AngularVelocity goal) {
        this.goal = goal;
        motor.setControl(new VelocityVoltage(goal));
    }

    @Override
    public void setVoltage(double volts) {
        motor.setControl(new VoltageOut(volts));
    }
}
