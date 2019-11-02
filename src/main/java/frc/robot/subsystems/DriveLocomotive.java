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
import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Encoder;

public class DriveLocomotive extends Subsystem {
	/*    Locomotives are better than trains!    */
	
	//Motors
  	private WPI_TalonSRX motorFrontLeft = new WPI_TalonSRX(RobotMap.motorFrontLeftID); 
  	private WPI_TalonSRX motorBackLeft = new WPI_TalonSRX(RobotMap.motorBackLeftID);	
  	private WPI_TalonSRX motorFrontRight = new WPI_TalonSRX(RobotMap.motorFrontRightID);
	private WPI_TalonSRX motorBackRight = new WPI_TalonSRX(RobotMap.motorBackRightID);
	private DifferentialDrive drive;

	//Encoders, Gyro, and UltraSonic
	private MB1013Ultrasonic ultrasonic = new MB1013Ultrasonic(0);
	private Encoder encoderLeft = new Encoder(0, 1, true, Encoder.EncodingType.k1X);
  	private Encoder encoderRight = new Encoder(2, 3, false, Encoder.EncodingType.k1X);
	private PigeonIMU gyro = new PigeonIMU(RobotMap.gyroID);
	
	//Other Constants
	private static double distancePerRevolution = 15.2 * Math.PI;
	private static double pulsesPerRevolution = 360;
	private static double distancePerPulse = distancePerRevolution / pulsesPerRevolution;
    private double driveMax = 1.0d;

  	public DriveLocomotive() {
		encoderLeft.setDistancePerPulse(distancePerPulse);
		encoderRight.setDistancePerPulse(distancePerPulse);

		motorFrontLeft.configFactoryDefault();
		motorBackLeft.configFactoryDefault();
		motorFrontRight.configFactoryDefault();
		motorBackRight.configFactoryDefault();
		gyro.configFactoryDefault();

		motorFrontLeft.configOpenloopRamp(0.3, 0);
		motorFrontRight.configOpenloopRamp(0.3, 0);
		//motorFrontLeft.configNeutralDeadband(0.35);
		//motorFrontRight.configNeutralDeadband(0.35);
		drive = new DifferentialDrive(motorFrontLeft, motorFrontRight);

		motorBackLeft.follow(motorFrontLeft);
		motorBackRight.follow(motorFrontRight);

		reset();
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
		gyroCalibrate();
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
		//double[] ypr = new double[3];
		//gyro.getYawPitchRoll(ypr);
		return gyro.getFusedHeading()/*ypr[0]*/;
	}
	
	public void gyroCalibrate() { 
		gyro.setFusedHeading(0, 30);
		//gyro.setYaw(0);
		//gyro.setAccumZAngle(0);
	}

	public void stop() { 
		drive.tankDrive(0, 0);
		reset();
	}
	
	public double getUltraSonicDistance() { 
		return ultrasonic.getDistance();
	}

	public double getDegreesFromEncoderValues() {
		return 180*(getEncoderLeft()-getEncoderRight())/(Math.PI*62.5);
    }
}