// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class ControllerConstants {
    public static final int kDriverControllerPort = 0;
    public static final int kOperatorControllerPort = 1;
		public static final double kDeadzone = .1;
		public static final double kTriggerDeadzone = .05;

		public static final class Axis {
			public static final int kLeftX = 0;
			public static final int kLeftY = 1;
			public static final int kRightX = 4;
			public static final int kLeftTrigger = 2;
			public static final int kRightTrigger = 3;
			public static final int kRightY = 5;
		}

		public static final class Button {
			public static final int kA = 1;
			public static final int kB = 2;
			public static final int kX = 3;
			public static final int kY = 4;
			public static final int kLeftBumper = 5;
			public static final int kRightBumper = 6;
			public static final int kLeftMenu = 7; 
			public static final int kRightMenu = 8; 
			public static final int kLeftTriggerButton = 9;
			public static final int kRightTriggerButton = 10;
		}

		public static final class DPad {
			public static final int kUp = 0;
			public static final int kRight = 90;
			public static final int kDown = 180;
			public static final int kLeft = 270;
		}
  }
  public static final class DriveConstants {
    public static final int kMasterLeftPort = 1; // left motors 1
		public static final boolean kMasterLeftInvert = true;
		public static final int kFollowerLeftPort = 2; // left motor 2
		public static final boolean kFollowerLeftOppose = true;
		public static final double kFineTurningSpeed = 0.1;
		public static final int kMasterRightPort = 3; // right motor 3
		public static final boolean kMasterRightInvert = false;
		public static final int kFollowerRightPort = 4; // right motor 4
		public static final boolean kFollowerRightOppose = false;

		public static final double kSpeedLimitFactor = .7;
		public static final double kTurningMultiplier = 1;
		public static final double kSpeedPowerMultiplier = 1; // Not working rn - keep at 1
		public static final double kTurningPowerMultiplier = 1; // Not working rn - keep at 1
  }

  public static final class TurretConstants {
	public static final int kTurretPort = 5;
	public static final boolean kTurretInvert = true;
	public static final double kSpeedLimitFactor = 1;
	public static final int kPIDSlot = 0;
	public static final double kP = .01;
	public static final double kI = 0;
	public static final double kD = 0;
	public static final double kFF = 0;
	public static final double kIz = 0;
	public static final double kMaxPosition = 3;
  }
  public static final class ShoulderConstants {
	public static final int kShoulderPort = 8;
	public static final boolean kShoulderInvert = true;
	public static final double kSpeedLimitFactor = .5;
	public static final int kPIDSlot = 0;
	public static final double kP = .06;
	public static final double kI = 0.0000001;
	public static final double kD = 0.00001249999968422344;// 0.000_03;
	public static final double kIz = .1;
	public static final double kFF = 0.00008040000102482736;// 0.000_193;
	public static final double kMaxPosition = 57;
	public static final double kMinPosition = 10;
	public static final double kStepSize = 1;
	public static final double kDriverStepSize = .5;
	public static final double kBasePos = 56.5;
	public static final double kMidPos = 35;
	public static final double kTopPos = 20;
	public static final double kRetractPos = 5;
	public static final double kRapidSpeed = kSpeedLimitFactor;
  }
  public static final class TelescopeConstants {
	public static final int kTelescopePort = 6;
	public static final boolean kTelescopeInvert = false;
	public static final double kSpeedLimitFactor = 1;
	public static final int kPIDSlot = 0; 
	public static final double kP = .09;
	public static final double kI = 0;
	public static final double kD = 0;
	public static final double kFF = 0;
	public static final double kIz = 0;
	public static final double kMaxPosition = 190;
	//The following are randomly chosen idk what they should actually be
	public static final double kBasePos = 0;
	public static final double kMidPos = 95;
	public static final double kTopPos = 190;
	public static final double kRetractPos = 0;
	public static final double kRapidSpeed = kSpeedLimitFactor/2;
  }
  public static final class ParkConstants {
	public static final int kParkPort = 11; 
	public static final int kParkRelayPort = 0; 
	public static final boolean kParkInvert = false; 
	public static final double kParkSpeed = 1; 
  }
  public static final class SuctionConstants {
	public static final int kSuctionPort = 9;
	public static final int kSuctionRelayPort = 0;
	public static final boolean kSuctionInvert = false;
	public static final double kSuctionSpeed = .5;
	public static final double kParkSuctionSpeed = .5;
  }
  public static final class WristRotateConstants {
	public static final int kWristRotatePort = 7;
	public static final boolean kWristRotateInvert = false;
	public static final double kSpeedLimitFactor = .5;
	public static final int kPIDSlot = 0; 
	public static final double kP = .03;
	public static final double kI = 0;
	public static final double kD = 0;
	public static final double kFF = 0;
	public static final double kIz = 0;
	public static final double kMaxPosition = .25;
  }
  public static final class WristBendConstants {
	public static final int kWristBendChannel = 9;
	public static final int kWristServo1Channel = 8;
	public static final int kWristServo2Channel = 7;
	public static final boolean kWristBendInvert = false;
	public static final int kForwardDirection = 1;
	public static final int kBackwardDirection = -1;
	public static final double kWristBendSpeed = .00001;
	public static final double kBasePos = 56.5;
	public static final double kMidPos = 35;
	public static final double kTopPos = 20;
  }
}
