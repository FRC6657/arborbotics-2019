/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.PIDDriveStraight;
import frc.robot.subsystems.DriveLocomotive;
import frc.robot.subsystems.Vacuum;

public class Robot extends TimedRobot {
  public static Vacuum vacuum;
  public static DriveLocomotive driveLocomotive;
  public static OI oi;
  public static double driveMaxOutput = 1.0d;
  
  Command autonomousCommand;
  SendableChooser<Command> autoChooser = new SendableChooser<>();

  PIDDriveStraight pidStraight = new PIDDriveStraight(20);

  @Override
  public void robotInit() {
    oi = new OI();

    pidStraight = new PIDDriveStraight(20);
    driveLocomotive = new DriveLocomotive();
    SmartDashboard.putData(driveLocomotive);
    vacuum = new Vacuum();
    SmartDashboard.putData(vacuum);
    autoChooser.addDefault("BaseDriveTimed", pidStraight);
		SmartDashboard.putData("Auto mode", autoChooser);
    
    driveLocomotive.reset();
  }
  
  @Override
  public void robotPeriodic() {
 
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
