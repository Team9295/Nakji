package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.ControllerConstants;

public class DriveSubsystem extends SubsystemBase {
  private final TalonSRX m_masterLeft = new TalonSRX(DriveConstants.kMasterLeftPort);
  private final TalonSRX m_followerLeft = new TalonSRX(DriveConstants.kFollowerLeftPort);
  private final TalonSRX m_masterRight = new TalonSRX(DriveConstants.kMasterRightPort);
  private final TalonSRX m_followerRight = new TalonSRX(DriveConstants.kFollowerRightPort);
  private double leftDriveSpeed;
  private double rightDriveSpeed;

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

    // Returns sign of double
    public int getSign(double num) {
      if(num>0) {return 1;}
      else if(num==0) {return 0;}
      else if(num<0) {return -1;}
      else {return 1/0;}
    }

    // Multiplying nth power times sign to get same sign as before
    // public void arcadeDrive(double straight, double left, double right) {
    //   tankDrive(Math.pow(DriveConstants.kSpeedLimitFactor * (straight + left - right), DriveConstants.kSpeedPowerMultiplier) * getSign(straight + left - right),
    //             Math.pow(DriveConstants.kSpeedLimitFactor * (straight - left + right), DriveConstants.kSpeedPowerMultiplier) * getSign(straight - left + right));
    // }

    public void arcadeDrive(double straight, double left, double right) {
      leftDriveSpeed = DriveConstants.kSpeedLimitFactor * (straight + left - right) / (1-ControllerConstants.kDeadzone);
      rightDriveSpeed = DriveConstants.kSpeedLimitFactor * (straight - left + right) / (1-ControllerConstants.kDeadzone);

      if(DriveConstants.kSpeedPowerMultiplier != 1) {
        leftDriveSpeed = Math.pow(leftDriveSpeed, DriveConstants.kSpeedPowerMultiplier);
        rightDriveSpeed = Math.pow(rightDriveSpeed, DriveConstants.kSpeedPowerMultiplier);
      }

      tankDrive(leftDriveSpeed, rightDriveSpeed);
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