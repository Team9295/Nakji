package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmUpSubsystem;

public class ArmUpDownCommand extends CommandBase {
    private final ArmUpSubsystem m_ArmUpSubsystem;
    private final Supplier<Double> m_speed;

    public ArmUpDownCommand(ArmUpSubsystem ArmUpSubsystem, Supplier<Double> speed) {
        m_ArmUpSubsystem = ArmUpSubsystem;
        m_speed = speed;
        addRequirements(m_ArmUpSubsystem);
    }
    public void execute() {
        System.out.println("COMMAND RUNNING");
        m_ArmUpSubsystem.setSpeed(m_speed.get());
    }

    public void end(boolean interrupted) {
        m_ArmUpSubsystem.setSpeed(0);
    }
}
