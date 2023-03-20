package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LinearActuatorSubsystem;

public class WristBend extends CommandBase{
    private final LinearActuatorSubsystem m_LinearActuatorSubsystem;
    private final double m_speed; 

    public WristBend(LinearActuatorSubsystem linearActuatorSubsystem, double speed) {
        m_LinearActuatorSubsystem = linearActuatorSubsystem; 
        m_speed = speed;
        addRequirements(m_LinearActuatorSubsystem);
    }

    public void initialize() {
        m_LinearActuatorSubsystem.setSpeed(m_speed);
    }

    public void end(boolean interrupted) {
        m_LinearActuatorSubsystem.setSpeed(0);
    }
}
