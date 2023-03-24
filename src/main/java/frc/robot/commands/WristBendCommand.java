package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LinearActuatorSubsystem;

public class WristBendCommand extends CommandBase{
    private final LinearActuatorSubsystem m_LinearActuatorSubsystem;
    private final int m_direction;

    public WristBendCommand(LinearActuatorSubsystem linearActuatorSubsystem, int direction) {
        m_LinearActuatorSubsystem = linearActuatorSubsystem; 
        m_direction = direction;
        addRequirements(m_LinearActuatorSubsystem);
    }

    public void initialize() {
        double position = m_LinearActuatorSubsystem.getPosition(); 
        while(position <= 1 && position >= 0){
            position += .1*m_direction;
            m_LinearActuatorSubsystem.setPosition(position);
        }
        //m_LinearActuatorSubsystem.setPosition(m_speed);
    }

    public void end(boolean interrupted) {}
}
