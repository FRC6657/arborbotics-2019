/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;
import frc.robot.Constants.CanIDs;

//This holds the Talons for the DriveTrain aswell as the Gyro
public class DriveTrain extends Subsystem {

  public WPI_TalonSRX frontLeftMotor = new WPI_TalonSRX(CanIDs.frontLeftMotor.value);
  public WPI_TalonSRX backLeftMotor = new WPI_TalonSRX(CanIDs.backLeftMotor.value);
  public WPI_TalonSRX frontRightMotor = new WPI_TalonSRX(CanIDs.frontRightMotor.value);
  public WPI_TalonSRX backRightMotor = new WPI_TalonSRX(CanIDs.backRightMotor.value);
  public PigeonIMU gyro = new PigeonIMU(CanIDs.gyro.value);
  public double SpeedModifier = 0.5;

  @Override
  public void initDefaultCommand() {

  }
}
