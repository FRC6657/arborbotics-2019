/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class VacuumGrab extends Command {

	public VacuumGrab() {
		requires(Robot.Vacuum);
	}
	
	@Override
	protected void execute() {
	Robot.vacuum.grab();
	}
	
	@Override
	protected void end() {
		Robot.Vacuum.stop();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
