/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.InvertType;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Autos.*;

import frc.robot.Constants.*;
import frc.robot.Subsystems.*;

//This class controlls all of the functions of robot and handles controller inputs
public class Robot extends TimedRobot {  

  //Subsystem Declaration
  public static DriveTrain driveTrain = new DriveTrain();
  public static Lift lift = new Lift();
  public static Pneumatics pneumatics = new Pneumatics();
  public static ArmJoint armJoint = new ArmJoint();

  //Controller Declaration
  private Joystick driveStick;
  private XboxController controller;


  //Creating the autonomous picker combo box in smart dashboard
  Command autonomousCommand;
  SendableChooser<Command> autoChooser = new SendableChooser<>();

  //Populates the autonomous picker combobox with autonomous commands
  BasicHatchAuto basicHatchAuto;
  SystemsCheck systemsCheck;
  EncoderAuto encoderAuto;

  @Override
  public void robotInit() {

    Robot.driveTrain.leftEncoder.reset();
    Robot.driveTrain.rightEncoder.reset();

    Robot.driveTrain.leftEncoder.setDistancePerPulse((6*Math.PI) / (1) / (360)); //Wheel Diameter * Pi * Inches * 360
    Robot.driveTrain.rightEncoder.setDistancePerPulse(((6*Math.PI) / (1) / (360)));

    Robot.driveTrain.leftEncoder.setPIDSourceType(PIDSourceType.kDisplacement);
    Robot.driveTrain.rightEncoder.setPIDSourceType(PIDSourceType.kDisplacement);

    //Gives assignes each controller to its value in driver station
    driveStick = new Joystick(ControlDeviceIDs.Joystick.value);
    controller = new XboxController(ControlDeviceIDs.Controller.value);


    //Inverts the master motors
    Robot.driveTrain.frontLeftMotor.setInverted(true);
    Robot.driveTrain.frontRightMotor.setInverted(true);

    //Sets the slave motors to follow the master motors
    Robot.driveTrain.backLeftMotor.follow(Robot.driveTrain.frontLeftMotor);
    Robot.driveTrain.backRightMotor.follow(Robot.driveTrain.frontRightMotor);

    //Sets the invert to the same as the master motors
    Robot.driveTrain.backLeftMotor.setInverted(InvertType.FollowMaster);
    Robot.driveTrain.backRightMotor.setInverted(InvertType.FollowMaster);

    //Assigns autonomous' to a command group
    basicHatchAuto = new BasicHatchAuto();
    autoChooser.addOption("Basic Hatch Auto", basicHatchAuto);
    systemsCheck = new SystemsCheck();
    autoChooser.addOption("Systems Check", systemsCheck);
    encoderAuto = new EncoderAuto();
    autoChooser.addOption("EncoderTest", encoderAuto);
    //Adds the option to not have an auto
    autoChooser.addOption("No Auto", null);

    SmartDashboard.putData("Auto Chooser", autoChooser);

    //Adds a black and white camera to the driverstation that runs at 10fps at 400x300 resoluton
    UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
    camera.setVideoMode(PixelFormat.kMJPEG, 400, 300, 10);
    Shuffleboard.getTab("SmartDashboard").add("Video", camera);

    Robot.driveTrain.leftEncoder.reset();
    Robot.driveTrain.rightEncoder.reset();
 
  }

  @Override
  public void autonomousInit() {
    //Runs the selected autonomous when the autonomous period starts
    Robot.driveTrain.leftEncoder.reset();
    Robot.driveTrain.rightEncoder.reset();

    autonomousCommand = autoChooser.getSelected();
    if (autonomousCommand != null) {
      autonomousCommand.start();
   }

  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    
    Robot.driveTrain.leftEncoder.reset();
    Robot.driveTrain.rightEncoder.reset();//Stops the autonomous if it is still running when TeleOp begins
    
    if (autonomousCommand != null) {
      autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {

    //Gets sets the speed and turn values that are used to calculate the motor powers for the drivetrain
    
    double speed = -driveStick.getRawAxis(2) * Robot.driveTrain.SpeedModifier;
    double turn = driveStick.getRawAxis(1) * Robot.driveTrain.SpeedModifier;
    
    //Calculates the motor powers for the left and right of the drivetrain
    double leftPower = speed + turn;
    double rightPower = speed - turn;

    //Sets the motor powers of each side. You only need to set the powers for the master motors
    Robot.driveTrain.frontLeftMotor.set(leftPower);
    Robot.driveTrain.frontRightMotor.set(rightPower);

    if(driveStick.getRawButton(6) == true & driveStick.getRawButton(4) == false | controller.getRawButton(4) == true & controller.getRawButton(5) == false){Robot.armJoint.armJointMotor.set(Speeds.armJoint);}
    else if(driveStick.getRawButton(6) == false & driveStick.getRawButton(4) == true | controller.getRawButton(4) == false & controller.getRawButton(5) == true){Robot.armJoint.armJointMotor.set(-Speeds.armJoint);}
    else{Robot.armJoint.armJointMotor.set(0);}

    if(driveStick.getRawButton(1) == true & driveStick.getRawButton(2) == false){Robot.lift.liftMotor.set(Speeds.Lift);}
    else if(driveStick.getRawButton(1) == false & driveStick.getRawButton(2) == true){Robot.lift.liftMotor.set(-Speeds.Lift);}
    else{Robot.lift.liftMotor.set(0);}

    if(driveStick.getRawButton(5) == true & driveStick.getRawButton(3) == false | controller.getRawButton(4) == true & controller.getRawButton(3) == false){Robot.pneumatics.solForward();}
    else if(driveStick.getRawButton(5) == false & driveStick.getRawButton(3) == true| controller.getRawButton(4) == false & controller.getRawButton(3) == true){Robot.pneumatics.solReverse();}

  }
 



  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

  public void stop(){

    //Stops all of the motors and retracts the solenoid
    Robot.driveTrain.frontLeftMotor.set(0);
    Robot.driveTrain.frontLeftMotor.set(0);
    Robot.driveTrain.frontLeftMotor.set(0);
    Robot.driveTrain.frontLeftMotor.set(0);
    Robot.lift.liftMotor.set(0);
    Robot.armJoint.armJointMotor.set(0);
    Robot.pneumatics.compressorOff();
    Robot.pneumatics.solReverse();

  }
  @Override
  public void robotPeriodic(){
    
    //Puts the values of the encoders on each of the sides to SuffleBoard
    SmartDashboard.putNumber("Left Encoder", Robot.driveTrain.leftEncoder.getDistance());
    SmartDashboard.putNumber("Right Encoder", Robot.driveTrain.rightEncoder.getDistance());

    //Puts the angle from the gyro onto ShuffleBoard
    SmartDashboard.putNumber("Gyro Angle", Robot.driveTrain.gyro.getFusedHeading());
    SmartDashboard.putBoolean("Button 6", driveStick.getRawButton(6));
  }
}
