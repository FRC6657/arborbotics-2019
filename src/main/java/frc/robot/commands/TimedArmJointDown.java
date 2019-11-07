/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.TimedCommand;

import frc.robot.Robot;

public class TimedArmJointDown extends TimedCommand {

  double speed;

  public TimedArmJointDown(double secs, double spd) {
    
    super(secs);

    speed = spd;

  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.armJoint.armJointMotor.set(speed);
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
