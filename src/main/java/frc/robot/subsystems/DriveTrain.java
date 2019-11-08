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
import edu.wpi.first.wpilibj.Encoder;
import frc.robot.Constants.CanIDs;

//This holds the Talons for the DriveTrain aswell as the Gyro and encoders
public class DriveTrain extends Subsystem {

  public WPI_TalonSRX frontLeftMotor = new WPI_TalonSRX(CanIDs.frontLeftMotor.value);
  public WPI_TalonSRX backLeftMotor = new WPI_TalonSRX(CanIDs.backLeftMotor.value);
  public WPI_TalonSRX frontRightMotor = new WPI_TalonSRX(CanIDs.frontRightMotor.value);
  public WPI_TalonSRX backRightMotor = new WPI_TalonSRX(CanIDs.backRightMotor.value);
  public PigeonIMU gyro = new PigeonIMU(CanIDs.gyro.value);
  public double SpeedModifier = 0.5;

   //Creates the encoders makes forward positive and backward negative.
   //Sets the encoders precision to not super precise but not super not precise
  public Encoder leftEncoder = new Encoder(0,1, false, Encoder.EncodingType.k2X);
  public Encoder rightEncoder = new Encoder(2,3, false, Encoder.EncodingType.k2X);

  @Override
  public void initDefaultCommand() {}
}
