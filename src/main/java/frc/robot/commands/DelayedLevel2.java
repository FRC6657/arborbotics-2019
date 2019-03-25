/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class DelayedLevel2 extends CommandGroup {

  public DelayedLevel2() {
    addSequential(new SleepDrive(7)); //wait 7 secconds
    addSequential(new TimedDrive(4, -0.5)); //drive backwards
  }
}
