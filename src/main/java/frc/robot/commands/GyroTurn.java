/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.Robot;
import frc.robot.subsystems.DriveLocomotive;

public class GyroTurn extends PIDCommand {
  private double targetAngle;
  public GyroTurn(double degrees) {
    super(0.5,0.0,0.0);
    requires(Robot.driveLocomotive);  
    getPIDController().reset();
		getPIDController().setOutputRange(-0.5d,  0.5d);
		getPIDController().setAbsoluteTolerance(0.05);
    getPIDController().setContinuous(false);
    targetAngle = degrees;
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
		Robot.driveLocomotive.arcadeDrive(0.5, targetAngle);
	}
  


  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    Robot.driveLocomotive.stop();
    return getPIDController().onTarget();
    
  }

}
