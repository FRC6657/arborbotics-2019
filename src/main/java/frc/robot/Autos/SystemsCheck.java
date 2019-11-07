/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Autos;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Commands.*;

public class SystemsCheck extends CommandGroup {

  public SystemsCheck() {

    addSequential(new HatchGrab());
    addSequential(new HatchRelease());

    addSequential(new TimedDrive(1, 0.5));
    addSequential(new TimedDrive(1, -0.5));

    addSequential(new TimedLiftUp(2, 0.7));
    addSequential(new TimedLiftDown(1, 0.7));

    addSequential(new TimedArmJointUp(2, 0.3));
    addSequential(new TimedArmJointDown(1, 0.3));

  } 
}
