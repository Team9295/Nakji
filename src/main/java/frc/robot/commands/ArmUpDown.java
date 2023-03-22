package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmUpSubsystem;

public class ArmUpDown extends CommandBase {
    private final ArmUpSubsystem m_ArmUpSubsystem;
    private final double m_speed;
    private final double m_direction;

    public ArmUpDown(ArmUpSubsystem ArmUpSubsystem, double speed, double direction) {
        m_ArmUpSubsystem = ArmUpSubsystem;
        m_speed = speed;
        m_direction = direction;
        addRequirements(m_ArmUpSubsystem);
    }

    public void initialize() {
        m_ArmUpSubsystem.setSpeed(m_speed);
    }

    public void end(boolean interrupted) {
        m_ArmUpSubsystem.setSpeed(0);
    }
}
