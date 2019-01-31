/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

public class RobotMap {
	public static int motorFrontLeftID = 1;
	public static int motorBackLeftID = 2;
	public static int motorFrontRightID = 3;
	public static int motorBackRightID = 4;
	public static int gyroID = 5;
	public static int liftID = 1;
	
	public static int driveControllerID = 0;

	public static double joystickSpeedDeadband = 0.35;
	public static double joystickRotationDeadband = 0.35;
	
	public static double joystickArcadeSpeedModifier = 1.0;
	public static double joystickArcadeRotationModifier = 1.0;

	public static double wheelClawSpeed = 0.4d;
	public static double liftSpeed = 0.3d;
	public static double footSpeed = 0.4d;
}
