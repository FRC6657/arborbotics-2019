/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
/*
package frc.robot.PID;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.*;
import edu.wpi.first.wpilibj.*;
import frc.robot.Robot;
import frc.robot.Constants.*;



public class PIDDrivetrain extends Subsystem implements PIDOutput{

  private WPI_TalonSRX motorFL = new WPI_TalonSRX(Ports.frontLeftMotor.value); //Declares the Front Left Motor
  private WPI_TalonSRX motorFR = new WPI_TalonSRX(Ports.frontRightMotor.value);//Declares the Front Right Motor
  private WPI_TalonSRX motorBL = new WPI_TalonSRX(Ports.backLeftMotor.value);  //Declares the Back Left Motor
  private WPI_TalonSRX motorBR = new WPI_TalonSRX(Ports.backRightMotor.value); //Declares the
  
  public final PIDController pid;

  private final AHRS ahrs;

  private final double kP = 0;//Proportional
  private final double kI = 0;//Integral
  private final double kD = 0;//Derivative

  public PIDDrivetrain(){

    motorBL.follow(motorFL);//Sets back left motor to be the slave to the master front left motor 
    motorBR.follow(motorFR);//Sets back right motor to be the slave to the master front right motor

    ahrs = new AHRS(SPI.Port.kMXP);

    pid = new PIDController(kP, kI, kD, ahrs, this);

    pid.setInputRange(-180.0f,180.0f);
    pid.setOutputRange(-0.45, 0.45);
    pid.setAbsoluteTolerance(2.0f);
    pid.setContinuous();

  }

  public void rotateDeg(double angle){

    ahrs.reset();
    pid.reset();
    pid.setPID(kP, kI, kD);
    pid.setSetpoint(angle);
    pid.enable();

  }
  public void Drive(double leftPower, double rightPower){
    //Motor setting code for each master motor.
    motorFL.set(leftPower);
    motorFR.set(-rightPower); //Right side is reversed so forward is + on each side

}
  @Override
  public void initDefaultCommand() {

  }

  @Override
  public void pidWrite(double output) {
    
    Drive(output, output);

  }
}
*/
