/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.Robot;

public class PIDDriveStraight extends PIDCommand {
  public PIDDriveStraight(double distance) {
    super(0.3, 0.0, 0.0);
		
		requires(Robot.driveLocomotive);
		
		getPIDController().reset();
		getPIDController().setOutputRange(-0.7d,  0.7d);
		getPIDController().setAbsoluteTolerance(0.05);
		getPIDController().setContinuous(false);
		
		setSetpoint(distance);
  }

  @Override
	public void initialize() {
		Robot.driveLocomotive.reset();
	}
	
	@Override
	protected double returnPIDInput() {
		return Robot.driveLocomotive.getDistance();
	}

	@Override
	protected void usePIDOutput(double output) {
		Robot.driveLocomotive.drive(output, output);	
	}

	@Override
	protected boolean isFinished() {
		return getPIDController().onTarget();
  }
}