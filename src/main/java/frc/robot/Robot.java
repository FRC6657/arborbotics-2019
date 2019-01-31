/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.GyroTurn;
import frc.robot.commands.PIDDriveStraight;
import frc.robot.commands.PIDTurn;
import frc.robot.commands.TimedReverse;
import frc.robot.commands.UltrasonicAuto;
import frc.robot.subsystems.DriveLocomotive;
import frc.robot.subsystems.WheelClaw;
import frc.robot.subsystems.Lift;
import frc.robot.subsystems.Foot;

public class Robot extends TimedRobot {
  public static WheelClaw claw = new WheelClaw();
  public static Lift lift = new Lift();
  public static Foot foot = new Foot();
  public static DriveLocomotive driveLocomotive  = new DriveLocomotive();
  public static OI oi;
  public static double driveMaxOutput = 1.0d;
  
  Command autonomousCommand;
  SendableChooser<Command> autoChooser = new SendableChooser<>();

  PIDDriveStraight pidStraight;
  GyroTurn gyroTurn;
  PIDTurn pidTurn;
  UltrasonicAuto ultraAuto;
  TimedReverse reverseTimed;

  @Override
  public void robotInit() {
    oi = new OI();

    pidStraight = new PIDDriveStraight(100);
    autoChooser.addOption("PID Straight", pidStraight);
    gyroTurn = new GyroTurn(30);
    autoChooser.addOption("Gyro Turn", gyroTurn);
    pidTurn = new PIDTurn(-30);
    autoChooser.addOption("PID Turn", pidTurn);
    ultraAuto = new UltrasonicAuto(50);
    autoChooser.addOption("Ultrasonic Auto", ultraAuto);
    reverseTimed = new TimedReverse();
    autoChooser.addOption("Timed Reverse", reverseTimed);
    SmartDashboard.putData("Auto mode", autoChooser);
    SmartDashboard.putNumber("Gyro Angle", driveLocomotive.getAngle());
    SmartDashboard.putNumber("Left Encoder", driveLocomotive.getEncoderLeft());
    SmartDashboard.putNumber("Right Encoder", driveLocomotive.getEncoderRight());
    SmartDashboard.putNumber("Distance", driveLocomotive.getDistance());
    SmartDashboard.putNumber("UltraSonic", driveLocomotive.getUltraSonicDistance());
    SmartDashboard.putNumber("Throttle", oi.getScaledThrottle());
    SmartDashboard.putNumber("Rotation", oi.getArcadeRotation());
    SmartDashboard.putNumber("Speed", oi.getArcadeSpeed());

    driveLocomotive.reset();
  }
  
  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("Gyro Angle", driveLocomotive.getAngle());
    SmartDashboard.putNumber("Left Encoder", driveLocomotive.getEncoderLeft());
    SmartDashboard.putNumber("Right Encoder", driveLocomotive.getEncoderRight());
    SmartDashboard.putNumber("Distance", driveLocomotive.getDistance());
    SmartDashboard.putNumber("UltraSonic", driveLocomotive.getUltraSonicDistance());
    SmartDashboard.putNumber("Throttle", oi.getScaledThrottle());
    SmartDashboard.putNumber("Rotation", oi.getArcadeRotation());
    SmartDashboard.putNumber("Speed", oi.getArcadeSpeed());
  }

  @Override
  public void disabledInit() {
    driveLocomotive.reset();
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
    driveLocomotive.reset();
    autonomousCommand = autoChooser.getSelected();
    // schedule the autonomous command (example)
    if (autonomousCommand != null) {
      autonomousCommand.start();
    }
  }
  
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();

  }

  @Override
  public void teleopInit() {
    if (autonomousCommand != null) {
      autonomousCommand.cancel();
    }
  }
  
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void testPeriodic() {
    LiveWindow.run();
  }
}