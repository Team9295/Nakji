package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

import frc.robot.Constants.DriveConstants;

public class DriveSubsystem extends SubsystemBase {
  private final TalonSRX m_masterLeft = new TalonSRX(DriveConstants.kMasterLeftPort);
  private final TalonSRX m_followerLeft = new TalonSRX(DriveConstants.kFollowerLeftPort);
  private final TalonSRX m_masterRight = new TalonSRX(DriveConstants.kMasterRightPort);
  private final TalonSRX m_followerRight = new TalonSRX(DriveConstants.kFollowerRightPort);

  public DriveSubsystem() {
    m_masterLeft.configFactoryDefault();
    m_masterLeft.setInverted(DriveConstants.kMasterLeftInvert);
    m_followerLeft.configFactoryDefault();
    m_followerLeft.setInverted(DriveConstants.kFollowerLeftOppose);
    m_masterRight.configFactoryDefault();
    m_masterRight.setInverted(DriveConstants.kMasterRightInvert);
    m_followerRight.configFactoryDefault();
    m_followerRight.setInverted(DriveConstants.kFollowerRightOppose);
  }
    public void periodic() {
    }

    public void arcadeDrive(double straight, double left, double right) {
      tankDrive(DriveConstants.kSpeedLimitFactor * (straight - left + right),
              DriveConstants.kSpeedLimitFactor * (straight + left - right));
    }

  /**
   * @param leftSpeed  Left motors percent output
   * @param rightSpeed Right motors percent output
   */
  public void tankDrive(double leftSpeed, double rightSpeed) {
      m_masterLeft.set(ControlMode.PercentOutput, leftSpeed);
      m_masterRight.set(ControlMode.PercentOutput, rightSpeed);

      //one option using output percents
      m_followerLeft.set(ControlMode.PercentOutput, m_masterLeft.getMotorOutputPercent());
      m_followerRight.set(ControlMode.PercentOutput, m_masterRight.getMotorOutputPercent());

      //another option using voltage
      // m_followerLeft.setVoltage(m_masterLeft.getMotorOutputVoltage());
      // m_followerRight.setVoltage(m_masterRight.getMotorOutputVoltage());
  }

}