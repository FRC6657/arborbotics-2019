/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.InstantCommands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class EncoderDriveStrait extends InstantCommand {

  private double Distance;
  private double leftPower = 0.7;
  private double rightPower = -0.7;
  
  public EncoderDriveStrait(double distance) {
    super();

    requires(Robot.drivetrain);
    
    Distance = distance;
  }

  // Called once when the command executes
  @Override
  protected void initialize() {

    if (Robot.drivetrain.getLeftEncoderDistance() < Distance & Robot.drivetrain.getRightEncoderDistance() < Distance) {Robot.drivetrain.Drive(leftPower, rightPower);}
    if (Robot.drivetrain.getLeftEncoderDistance() < Distance & Robot.drivetrain.getRightEncoderDistance() >= Distance) {Robot.drivetrain.Drive(leftPower, 0);}
    if (Robot.drivetrain.getLeftEncoderDistance() >= Distance & Robot.drivetrain.getRightEncoderDistance() < Distance) {Robot.drivetrain.Drive(0, rightPower);}
    if (Robot.drivetrain.getLeftEncoderDistance() >= Distance & Robot.drivetrain.getRightEncoderDistance() >= Distance) {Robot.drivetrain.Drive(0,0);}

  }
}
 