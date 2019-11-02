/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestCargoAuto extends CommandGroup {
  /**
   * Add your docs here.
   */
  public TestCargoAuto() {

    addSequential(new TimedDrive(2, 0.35));
    addSequential(new LiftUpTimed(2, 0.7));
    addSequential(new ArmJointMoveTimed(1, -0.2));
    addSequential(new ClawReleaseTimed(1));
    addSequential(new ArmJointMoveTimed(1, 0.3));
    addSequential(new LiftDownTimed(1, 0.7));
    addSequential(new ACTurn(1));
    addSequential(new TimedDrive(2, 0.3));

  }
}
