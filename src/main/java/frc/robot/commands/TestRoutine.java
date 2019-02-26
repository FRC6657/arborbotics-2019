/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.PIDDriveStraight;
import frc.robot.commands.LiftUpTimed;
import frc.robot.commands.ArmJointDown;
import frc.robot.commands.ClawOpenTimed;

public class TestRoutine extends CommandGroup {
  public TestRoutine() {
    
    /*  CARGO AUTO  */
    addSequential(new PIDDriveStraight(20));
    //addSequential(new LiftUpTimed(2));
    //addSequential(new ArmJointDown());
    //addSequential(new ClawOpenTimed(0.5));
  }
}
