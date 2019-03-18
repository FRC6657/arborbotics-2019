/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.Robot;

public class EncoderTurn extends PIDCommand {
  private double driveSpeed = 0;
  private double targetAngle = 0;
  public EncoderTurn(double angle) {
    super(0.03, 0.0, 0.05);

    getPIDController().reset();
    getPIDController().setPID(0.03, 0.0, 0.033, 0.10);
    getPIDController().setContinuous(false);
    getPIDController().setAbsoluteTolerance(0.3);
    getPIDController().setOutputRange(-0.7, 0.7);
    targetAngle = angle;
    
    requires(Robot.driveLocomotive);
  }
  
  @Override
  protected double returnPIDInput() {
    return Robot.driveLocomotive.getDegreesFromEncoderValues();    
  }

  @Override
  protected void usePIDOutput(double output) {
    Robot.driveLocomotive.arcadeDrive(driveSpeed, output);   
  }               

  @Override
  protected void initialize() {
    this.setSetpoint(targetAngle);
    this.getPIDController().enable();
  }

  @Override
  protected boolean isFinished() {
    return getPIDController().onTarget();
  }
}