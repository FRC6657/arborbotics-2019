/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.RobotMap;
import frc.robot.hardware.MB1013Ultrasonic;
import frc.robot.commands.ArcadeDrive;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;

public class DriveLocomotive extends Subsystem {
	
	//Locomotives are better than trains.
	
  	private WPI_TalonSRX motorFrontLeft = new WPI_TalonSRX(RobotMap.motorFrontLeftID);   //Declaration of Motors using RobotMap ID's
  	private WPI_TalonSRX motorBackLeft = new WPI_TalonSRX(RobotMap.motorBackLeftID);	
  	private WPI_TalonSRX motorFrontRight = new WPI_TalonSRX(RobotMap.motorFrontRightID);
	private WPI_TalonSRX motorBackRight = new WPI_TalonSRX(RobotMap.motorBackRightID);

	private MB1013Ultrasonic ultrasonic = new MB1013Ultrasonic(0);
	private Encoder encoderLeft = new Encoder(0, 1, false, Encoder.EncodingType.k1X);
  	private Encoder encoderRight = new Encoder(2, 3, true, Encoder.EncodingType.k1X);
	private ADXRS450_Gyro gyro = new ADXRS450_Gyro();

	private DifferentialDrive drive;
	
	private static double distancePerRevolution = 15.2 * Math.PI;
	private static double pulsesPerRevolution = 360;
	private static double distancePerPulse = distancePerRevolution / pulsesPerRevolution;
  
  private double driveMax = 1.0d;
  
  public DriveLocomotive() {
		encoderLeft.setDistancePerPulse(distancePerPulse);
		encoderRight.setDistancePerPulse(distancePerPulse);

		motorBackLeft.follow(motorFrontLeft);
		motorBackRight.follow(motorFrontRight);

		motorFrontLeft.configOpenloopRamp(0.3, 0);
		motorFrontRight.configOpenloopRamp(0.3, 0);
		drive = new DifferentialDrive(motorFrontLeft, motorFrontRight);
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
		encoderLeft.reset();
		encoderRight.reset();
		gyro.reset();
	}

	public double getDistance() {
		return ((encoderLeft.getDistance()  + encoderRight.getDistance()) / 2);
	}
	
	public double getEncoderLeft() {
		return encoderLeft.getDistance();
	}
	
	public double getEncoderRight() {
		return encoderRight.getDistance();
	}
	
	public double getAngle() {
		return gyro.getAngle();
	}
	
	public void gyroCalibrate() {
		gyro.calibrate();
	}

	public void stop() {
		drive.tankDrive(0, 0);
		reset();
	}
	
	public double getUltraSonicDistance() {
		return ultrasonic.getDistance();
	}
}
