/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants.CanIDs;

//This holds the Talon for the Lift
public class Lift extends Subsystem {
 
  public WPI_VictorSPX liftMotor = new WPI_VictorSPX(CanIDs.liftMotor.value);

  @Override
  public void initDefaultCommand() {

    
  }
}
