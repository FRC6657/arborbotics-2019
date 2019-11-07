/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Autos;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Commands.*;
import frc.robot.Commands.Timed.*;
public class EncoderHatchAuto extends CommandGroup {
  /**
   * Add your docs here.
   */
  public EncoderHatchAuto() {

    addSequential(new EncoderDrive(10.5, 0.5)); //Drive 10.5ft at 50% Power. This should get the robot to the cargoship.

    addParallel(new TimedTurnLeft(1, 0.5)); //Turn the robot to face the loading zone

    addSequential(new EncoderDrive(18.4, 0.5)); //Drive the robot towards the loading zone

  }
}
