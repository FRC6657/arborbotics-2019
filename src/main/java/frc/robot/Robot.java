/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.commands.GyroTurn;
import frc.robot.commands.LiftFire;
import frc.robot.commands.EncoderTurn;
import frc.robot.commands.PIDDriveStraight;
import frc.robot.commands.PIDTurn;
import frc.robot.commands.PistonTest;
import frc.robot.commands.StandardTurnCalibration;
import frc.robot.commands.SystemsCheck;
import frc.robot.commands.TimedDrive;
import frc.robot.commands.UltrasonicAuto;
import frc.robot.subsystems.ArmJoint;
import frc.robot.subsystems.DriveLocomotive;
import frc.robot.subsystems.WheelClaw;
import frc.robot.subsystems.Lift;
import frc.robot.subsystems.Pneumatics;
import frc.robot.subsystems.Foot;
import frc.robot.commands.LiftUpTimed;
import frc.robot.commands.ACAutoTesting;
import frc.robot.commands.ArmJointMoveTimed;
import frc.robot.commands.ClawIntakeTimed;
import frc.robot.commands.DelayedLevel2;


public class Robot extends TimedRobot {
  //Physical subsystems
  public static WheelClaw claw = new WheelClaw();
  public static Lift lift = new Lift();
  public static Foot foot = new Foot();
  public static ArmJoint joint = new ArmJoint();
  public static DriveLocomotive driveLocomotive  = new DriveLocomotive();
  public static Pneumatics pneumatics = new Pneumatics();
  
  //Network table stuff
  public static NetworkTableInstance inst = NetworkTableInstance.getDefault();
  NetworkTableEntry angleEntry;
  double angle;

  //Auto Commands & Selector
  Command autonomousCommand;
  SendableChooser<Command> autoChooser = new SendableChooser<>();
  EncoderTurn encoderTurn;
  PIDDriveStraight pidStraight;
  GyroTurn gyroTurn;
  PIDTurn pidTurn;
  UltrasonicAuto ultraAuto;
  TimedDrive reverseTimed;
  TimedDrive timedStraight;
  LiftUpTimed liftTimed;
  ArmJointMoveTimed armTimed;
  ClawIntakeTimed clawTimed;

  //Other Constants
  public static double driveMaxOutput = 1.0d;
  public static OI oi;

  
  @Override
  public void robotInit() {
    oi = new OI();
    
    //Network table stuff
    NetworkTable table = inst.getTable("jetsoncamera");
    angleEntry = table.getEntry("degrees");

    
    //Auto Commands

    pidStraight = new PIDDriveStraight(100);
    autoChooser.addOption("PID Straight", pidStraight);
    encoderTurn = new EncoderTurn(90);
    autoChooser.addOption("EncoderTurn", encoderTurn);
    gyroTurn = new GyroTurn(30);
    autoChooser.addOption("Gyro Turn", gyroTurn);
    pidTurn = new PIDTurn(-30);
    autoChooser.addOption("PID Turn", pidTurn);
    ultraAuto = new UltrasonicAuto(50);
    autoChooser.addOption("Ultrasonic Auto", ultraAuto);
    reverseTimed = new TimedDrive(4, -0.5);
    autoChooser.addOption("Level 2 (Reverse)", reverseTimed);
    timedStraight = new TimedDrive(2, 0.5);
    autoChooser.addOption("Level 1", timedStraight);

    liftTimed = new LiftUpTimed(2, 0.7);
    autoChooser.addOption("Timed Lift", liftTimed);
    armTimed = new ArmJointMoveTimed(1, RobotMap.armSpeed);
    autoChooser.addOption("Timed Arm", armTimed);
    clawTimed = new ClawIntakeTimed(0.75,0.4);
    autoChooser.addOption("Timed Claw", clawTimed);
    autoChooser.addOption("None", null);
    autoChooser.addOption("DelayedL2", new DelayedLevel2());
    autoChooser.addOption("Actest", new ACAutoTesting());
    autoChooser.addOption("Acturntest", new ACAutoTesting());
    autoChooser.addOption("SystemsCheck", new SystemsCheck());
    autoChooser.addOption("TurnCali", new StandardTurnCalibration());
    autoChooser.addOption("LiftFire", new LiftFire());
    autoChooser.addOption("PistonTest", new PistonTest());
    SmartDashboard.putData("Auto Mode", autoChooser);
    
   

    //Prints Numbers to SmartDash
    SmartDashboard.putNumber("Gyro Angle", driveLocomotive.getAngle());
    SmartDashboard.putNumber("Left Encoder", driveLocomotive.getEncoderLeft());
    SmartDashboard.putNumber("Right Encoder", driveLocomotive.getEncoderRight());
    SmartDashboard.putNumber("Distance", driveLocomotive.getDistance());
    SmartDashboard.putNumber("UltraSonic", driveLocomotive.getUltraSonicDistance());
    SmartDashboard.putNumber("Throttle", oi.getScaledThrottle());
    SmartDashboard.putNumber("Rotation", oi.getArcadeRotation());
    SmartDashboard.putNumber("Speed", oi.getArcadeSpeed());

    UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
    camera.setVideoMode(PixelFormat.kMJPEG, 400, 300, 10);
    Shuffleboard.getTab("SmartDashboard").add("Video", camera);

    driveLocomotive.reset();
  }
  
  @Override
  public void robotPeriodic() {
    //Prints Numbers to SmartDash
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
