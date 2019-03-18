/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.Robot;

public class PIDTurn extends PIDCommand {
  public PIDTurn(double angleTo) {
    super(0.3, 0.0, 0.05, 0.15);
    requires(Robot.driveLocomotive);
		
    getPIDController().reset();
    getPIDController().setInputRange(-23040, 23040);
		getPIDController().setOutputRange(-0.5d,  0.5d);
		getPIDController().setAbsoluteTolerance(0.10);
		getPIDController().setContinuous(false);
		setSetpoint(angleTo);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.driveLocomotive.reset();
  }

  @Override
  protected double returnPIDInput() {
    return Robot.driveLocomotive.getAngle();
  }

  @Override
  protected void usePIDOutput(double output) {
    Robot.driveLocomotive.drive(-output, output);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    /*if(Math.abs(Robot.driveLocomotive.getAngle() - targetAngle) <= 2) {
      Robot.driveLocomotive.stop();
    }*/
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return getPIDController().onTarget();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.driveLocomotive.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.driveLocomotive.stop();
  }
}
