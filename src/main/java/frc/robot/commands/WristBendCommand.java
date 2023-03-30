package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.WristBendSubsystem;

public class WristBendCommand extends CommandBase{
    private final WristBendSubsystem m_wristBendSubsystem;
    private final int m_direction;

    public WristBendCommand(WristBendSubsystem wristBendSubsystem, int direction) {
        m_wristBendSubsystem = wristBendSubsystem; 
        m_direction = direction;
        addRequirements(m_wristBendSubsystem);
    }

    public void initialize() {
        double position = m_wristBendSubsystem.getPosition(); 
        while(position <= 1 && position >= 0){
            position += .1*m_direction;
            m_wristBendSubsystem.setPosition(position);
        }
        //m_wristBendSubsystem.setPosition(m_speed);
    }

    public void end(boolean interrupted) {}
}
