/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.Constants.CanIDs;

//This holds the Talon for the ArmJoint
public class ArmJoint extends Subsystem {
  
  public WPI_TalonSRX armJointMotor = new WPI_TalonSRX(CanIDs.armJointMotor.value);

  @Override
  public void initDefaultCommand() {

  }
}
