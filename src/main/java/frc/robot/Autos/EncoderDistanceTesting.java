/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Autos;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.InstantCommands.EncoderDriveStrait;


public class EncoderDistanceTesting extends CommandGroup {
  /**
   * Add your docs here.
   */
  public EncoderDistanceTesting() {

    addSequential(new EncoderDriveStrait(10)); //Drive forward 10...Idk how much 10 is

  }
}
