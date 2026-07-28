package frc.robot.subsystems.shooter;

import frc.robot.lib.components.roller.RollerConstants;

public class ShooterConstants {
    public static final RollerConstants topRollerConstants = new RollerConstants(8);
    public static final RollerConstants botRollerConstants = new RollerConstants(9);
    
    public static final double topIntakeVoltage = -6.0;
    public static final double botIntakeVoltage = -6.0;
    public static final double topOutakeVoltage = 6.0;
    public static final double botOutakeVoltage = 6.0;
}
