/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;

public class SystemsCheck extends CommandGroup {

  public SystemsCheck() {

    addSequential(new TimedDrive(1, 0.1)); //Drive Strait at 10% for 1 seccond
    addSequential(new TimedDrive(1, 0.5)); //Drive Strait at 50% for 1 seccond
    addSequential(new TimedDrive(1, 1.0)); //Drive Strait at 100% for 1 seccond
    addSequential(new TimedDrive(1, -0.1));//Drive Back at 10% for 1 seccond
    addSequential(new TimedDrive(1, -0.5));//Drive Back at 50% for 1 seccond
    addSequential(new TimedDrive(1, -1.0));//Drive Back at 100% for 1 seccond

    addSequential(new LiftUpTimed(2, 0.7));//Raise Lift for 2 Secconds at 70%
    addSequential(new LiftDownTimed(1, 0.7));//Lower Lift for 2 Secconds at 70%

    addSequential(new ArmJointMoveTimed(1, -0.1));//Lower the arm joint at 10%
    addSequential(new ArmJointMoveTimed(1, 0.1));//Raist the arm joint at 10%

    addSequential(new ClawIntakeTimed(1, 0.4));//Run intake in for 1 seccond at 40%
    addSequential(new ClawReleaseTimed(1, 0.4));//Run intake out for 1 seccond at 40%

    addSequential(new SolOut()); //Fire Pistons
    addSequential(new SolIn()); //Retract Pistons

  }
}
