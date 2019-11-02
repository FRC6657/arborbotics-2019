/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import frc.robot.commands.TimedDrive;
import frc.robot.commands.LiftUpTimed;
import frc.robot.commands.ArmJointMoveTimed;
import frc.robot.commands.ClawIntakeTimed;

public class MadTownAuto extends CommandGroup {

   public MadTownAuto() {
    TimedDrive timedDrive = new TimedDrive(2.8, 0.6);
    LiftUpTimed liftUp = new LiftUpTimed(4, 0.7);
    ArmJointMoveTimed armJointMoveTimed = new ArmJointMoveTimed(0.15, -0.25);
    ClawIntakeTimed clawIntakeTimed = new ClawIntakeTimed(0.5);

    addSequential(timedDrive);
    addSequential(liftUp);
    addSequential(armJointMoveTimed);
    addSequential(clawIntakeTimed);

  }
}
