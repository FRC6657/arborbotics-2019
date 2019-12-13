/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.PID;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.*;
import frc.robot.Constants.*;



public class PIDDrivetrain extends Subsystem implements PIDOutput{

  private WPI_TalonSRX motorFL = new WPI_TalonSRX(Ports.frontLeftMotor.value); //Declares the Front Left Motor
  private WPI_TalonSRX motorFR = new WPI_TalonSRX(Ports.frontRightMotor.value);//Declares the Front Right Motor
  private WPI_TalonSRX motorBL = new WPI_TalonSRX(Ports.backLeftMotor.value);  //Declares the Back Left Motor
  private WPI_TalonSRX motorBR = new WPI_TalonSRX(Ports.backRightMotor.value); //Declares the
  
  public final PIDController pid;

  AHRS navX;

  private final double kP = 0;//Proportional
  private final double kI = 0;//Integral
  private final double kD = 0;//Derivative

  public PIDDrivetrain(){

    motorBL.follow(motorFL);//Sets back left motor to be the slave to the master front left motor 
    motorBR.follow(motorFR);//Sets back right motor to be the slave to the master front right motor

    navX = new AHRS(SPI.Port.kMXP);// (Center RIO Port)

    pid = new PIDController(kP, kI, kD, navX, this);//(P,I,D,Source,Controller)

    pid.setInputRange(-180.0f,180.0f); //This prevents the robot from rotating inefficiently aka over 180 in one direction.
    pid.setOutputRange(-0.45, 0.45);//Sets output to a max of 45%
    pid.setAbsoluteTolerance(2.0f);//Sets angle tollerance
    pid.setContinuous(true);//Max and min are the same value so that the degrees run in a circle not a line

  }
  //Rotates to a specific angle with the angle that the robot is in when the command is called being 0
  public void rotateDegRelative(double angle){ //Robots current angle is 0

    navX.reset();//Resets Gyro Angle
    pid.reset();//Resets the PID Values just in case
    pid.setPID(kP, kI, kD);//Reassigns the PID Values becasue the line before removed it
    pid.setSetpoint(angle);//Sets the target angle
    pid.enable();//Rotates to target

  }
  //Rotates to a specific angle with the angle that the robot is in when the command is called as the angle that the robot is in when the command is called.
  public void rotateDegAbsolute(double angle){ //Robots current angle is the robots current angle

    pid.reset();//Resets the PID Values just in case
    pid.setPID(kP, kI, kD);//Reassigns the PID Values because the line before removed it
    pid.setSetpoint(angle);//Sets the target angle
    pid.enable();//Rotates to the target

  }
  //Drives Robot
  public void Drive(double leftPower, double rightPower){
    //Motor setting code for each master motor.
    motorFL.set(leftPower); //Left side is normal because clockwise goes forward
    motorFR.set(-rightPower); //Right side is reversed because clockwise goes backward

}
  @Override
  public void initDefaultCommand() {}

  @Override
  public void pidWrite(double output) {
    
    Drive(output, output);//Drives the robot to the angle

  }
}

