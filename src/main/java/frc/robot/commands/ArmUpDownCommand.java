package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmUpSubsystem;

public class ArmUpDownCommand extends CommandBase {
    private final ArmUpSubsystem m_ArmUpSubsystem;
    private final double m_speed;

    public ArmUpDownCommand(ArmUpSubsystem ArmUpSubsystem, double speed) {
        m_ArmUpSubsystem = ArmUpSubsystem;
        m_speed = speed;
        addRequirements(m_ArmUpSubsystem);
    }

    public void initialize() {
        m_ArmUpSubsystem.setSpeed(m_speed);
    }

    public void end(boolean interrupted) {
        m_ArmUpSubsystem.setSpeed(0);
    }
}
