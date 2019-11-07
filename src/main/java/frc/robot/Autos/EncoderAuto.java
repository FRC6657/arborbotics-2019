/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Autos;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Commands.EncoderDrive;

public class EncoderAuto extends CommandGroup {

  public EncoderAuto() {

    addSequential(new EncoderDrive(4, 0.5)); //Drive 3ft at 50% speed

  }
}
