package frc.robot.lib.components.pivot;

import org.littletonrobotics.junction.AutoLogOutput;

import com.ctre.phoenix6.configs.FeedbackConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.geometry.Rotation2d;

public class PivotIOTalonFX implements PivotIO {
    private TalonFX leadMotor;
    private TalonFX followMotor;
    private Rotation2d goal;
    private PivotConstants constants;

    public PivotIOTalonFX(PivotConstants constants) {
        leadMotor = new TalonFX(constants.leadCanID);
        followMotor = new TalonFX(constants.followCanID);
        TalonFXConfiguration config = new TalonFXConfiguration();
        config.withSlot0(new Slot0Configs().withKP(constants.kP).withKD(constants.kD).withKS(constants.kS)
                .withKV(constants.kV).withKA(constants.kA).withKG(constants.kG));
        config.withFeedback(new FeedbackConfigs().withSensorToMechanismRatio(constants.sensorToMechanismRatio));
        config.withMotorOutput(new MotorOutputConfigs().withInverted(constants.leadInversion));
        leadMotor.getConfigurator().apply(config);
        followMotor.getConfigurator().apply(config);
        this.constants = constants;
        this.goal = Rotation2d.kZero;
        followMotor.setControl(new Follower(constants.leadCanID, constants.followerAlignment));
    }

    @Override
    public void updateInputs(PivotIOInputsAutoLogged inputs) {
        inputs.voltage = leadMotor.getMotorVoltage().getValueAsDouble();
        inputs.current = leadMotor.getSupplyCurrent().getValueAsDouble();
        inputs.position = Rotation2d.fromRotations(leadMotor.getPosition().getValueAsDouble());
        inputs.leadTemperature = leadMotor.getDeviceTemp().getValueAsDouble();
        inputs.velocity = leadMotor.getVelocity().getValueAsDouble();
        inputs.followTemperature = followMotor.getDeviceTemp().getValueAsDouble();
        inputs.error = Rotation2d.fromRotations(Math.abs(goal.getRotations() - leadMotor.getPosition().getValueAsDouble()));
    }

    @Override
    @AutoLogOutput
    public boolean atGoal() {
        return Math.abs(goal.getRotations() - leadMotor.getPosition().getValueAsDouble()) < constants.tolerance
                .getRotations();
    }

    @Override
    public void setPositionGoal(Rotation2d goal) {
        this.goal = goal;
        leadMotor.setControl(new PositionVoltage(goal.getRotations()));
    }

}
