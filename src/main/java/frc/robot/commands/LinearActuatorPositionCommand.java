package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LinearActuatorSubsystem;

public class LinearActuatorPositionCommand extends CommandBase {
    private final LinearActuatorSubsystem m_linearActuatorSubsystem;
    private final double m_Position;
    
    public LinearActuatorPositionCommand(LinearActuatorSubsystem linearActuatorSubsystem, double Position) {
        m_linearActuatorSubsystem = linearActuatorSubsystem;
        m_Position = Position;
        addRequirements(m_linearActuatorSubsystem);
    }

    public void execute() {
        m_linearActuatorSubsystem.setPosition(m_Position);
    }
}
