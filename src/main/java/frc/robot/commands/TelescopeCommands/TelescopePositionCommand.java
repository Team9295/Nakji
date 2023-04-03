package frc.robot.commands.TelescopeCommands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TelescopeSubsystem;

public class TelescopePositionCommand extends CommandBase {
    private final TelescopeSubsystem m_telescopeSubsystem;
    private final  double m_position;
    private final Supplier<Double> m_thresh;

    public TelescopePositionCommand(TelescopeSubsystem telescopeSubsystem, double position) {
        m_telescopeSubsystem = telescopeSubsystem;
        m_position = position;
        m_thresh = () -> -1.0;
        addRequirements(m_telescopeSubsystem);
    }

    public TelescopePositionCommand(TelescopeSubsystem shoulderSubsystem, Supplier<Double> thresh, double position) {
        m_telescopeSubsystem = shoulderSubsystem;
        m_position = position;
        m_thresh = thresh;
        addRequirements(m_telescopeSubsystem);
    }

    public void execute() {
        if(m_thresh.get() <= -0.1){
            m_telescopeSubsystem.setPosition(m_position);
        }
        else if(m_thresh.get() >= 0.1){
            m_telescopeSubsystem.setPosition(0);
        }
        else if(m_thresh.get() <= 0.1 && m_thresh.get() >= -0.1){
            m_telescopeSubsystem.setSpeed(0);
        }
     }

    public void end(boolean interrupted) {
        m_telescopeSubsystem.setSpeed(0);
    }
}
