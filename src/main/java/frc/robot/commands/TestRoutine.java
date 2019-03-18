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
import frc.robot.commands.ArmJointTimed;
import frc.robot.commands.ClawOpenTimed;
import frc.robot.commands.EncoderTurn;

public class TestRoutine extends CommandGroup {
  public TestRoutine() {
    
    /*  CARGO AUTO (Straight)  */
    addSequential(new PIDDriveStraight(270));
    addSequential(new EncoderTurn(90));
    addSequential(new PIDDriveStraight(160));
    addSequential(new EncoderTurn(-90));
    addSequential(new PIDDriveStraight(234));
    addSequential(new EncoderTurn(-90));
    addSequential(new ArmJointTimed(1));
    addSequential(new LiftUpTimed(3));
    addSequential(new ClawOpenTimed(0.75));
  }
}
