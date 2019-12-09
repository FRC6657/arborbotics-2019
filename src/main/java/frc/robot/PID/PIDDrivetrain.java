/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.PID;

import edu.wpi.first.wpilibj.command.Subsystem;

 Back Right Motor

public class PIDDrivetrain extends Subsystem {

  private WPI_TalonSRX motorFL = new WPI_TalonSRX(Ports.frontLeftMotor.value); //Declares the Front Left Motor
  private WPI_TalonSRX motorFR = new WPI_TalonSRX(Ports.frontRightMotor.value);//Declares the Front Right Motor
  private WPI_TalonSRX motorBL = new WPI_TalonSRX(Ports.backLeftMotor.value);  //Declares the Back Left Motor
  private WPI_TalonSRX motorBR = new WPI_TalonSRX(Ports.backRightMotor.value); //Declares the
  
  public PIDDrivetrain(){

    motorBL.follow(motorFL);//Sets back left motor to be the slave to the master front left motor 
    motorBR.follow(motorFR);//Sets back right motor to be the slave to the master front right motor

  }
  public void Drive(double leftPower, double rightPower){
    //Motor setting code for each master motor.
    motorFL.set(leftPower);
    motorFR.set(-rightPower); //Right side is reversed so forward is + on each side

}
  @Override
  public void initDefaultCommand() {

  }
}
