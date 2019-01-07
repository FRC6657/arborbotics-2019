/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.RobotMap;
import frc.robot.commands.ArcadeDrive;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveLocomotive extends Subsystem {
  
  private WPI_TalonSRX motorFrontLeft = new WPI_TalonSRX(RobotMap.motorFrontLeftID);
	private WPI_TalonSRX motorBackLeft = new WPI_TalonSRX(RobotMap.motorBackLeftID);	
	private WPI_TalonSRX motorFrontRight = new WPI_TalonSRX(RobotMap.motorFrontRightID);
  private WPI_TalonSRX motorBackRight = new WPI_TalonSRX(RobotMap.motorBackRightID);
	
	private DifferentialDrive drive;
	
	private static double distancePerRevolution = 15.2 * Math.PI;
	private static double pulsesPerRevolution = 1440;
	private static double distancePerPulse = distancePerRevolution / pulsesPerRevolution;
  
  private double driveMax = 1.0d;
  
  public DriveLocomotive() {		
		motorBackLeft.follow(motorFrontLeft);
		motorBackRight.follow(motorFrontRight);
		motorFrontLeft.configOpenloopRamp(0.3, 0);
		motorFrontRight.configOpenloopRamp(0.3, 0);
	}

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ArcadeDrive());
  }

  public void setMax(double max) {
		driveMax = max;
	}
	
	public void arcadeDrive(double speed, double rotation) {
		drive.arcadeDrive(driveMax * speed, driveMax * rotation);
	}
	
	public void drive(double left, double right) {
		drive.tankDrive(left, right);
	}
	
	public void reset() {

	}

	public void stop() {
		drive.tankDrive(0, 0);
	}
}
