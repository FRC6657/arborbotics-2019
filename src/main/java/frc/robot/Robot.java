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
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Autos.*;
import frc.robot.Commands.*;
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

  //Button Declaration
  public JoystickButton liftUp;
  public JoystickButton liftDown;
  public JoystickButton armJointUp1;
  public JoystickButton armJointDown1;
  public JoystickButton armJointUp2;
  public JoystickButton armJointDown2;
  public JoystickButton hatchGrab1;
  public JoystickButton hatchRelease1;
  public JoystickButton hatchGrab2;
  public JoystickButton hatchRelease2;
  public JoystickButton slowMode;

  //Creating the autonomous picker combo box in smart dashboard
  Command autonomousCommand;
  SendableChooser<Command> autoChooser = new SendableChooser<>();

  //Populates the autonomous picker combobox with autonomous commands
  BasicHatchAuto basicHatchAuto;
  SystemsCheck systemsCheck;

  @Override
  public void robotInit() {

    Robot.driveTrain.leftEncoder.reset();
    Robot.driveTrain.rightEncoder.reset();

    Robot.driveTrain.leftEncoder.setDistancePerPulse(4./256.);
    Robot.driveTrain.rightEncoder.setDistancePerPulse(4./256.);

    //Gives assignes each controller to its value in driver station
    driveStick = new Joystick(ControlDeviceIDs.Joystick.value);
    controller = new XboxController(ControlDeviceIDs.Controller.value);

    //Assigns each button to a specific button on the joystick
    liftUp = new JoystickButton(driveStick, 1);
    liftDown = new JoystickButton(driveStick, 2);
    armJointUp1 = new JoystickButton(driveStick, 6);
    armJointDown1 = new JoystickButton(driveStick, 4);
    hatchGrab1 = new JoystickButton(driveStick, 5);
    hatchRelease1 = new JoystickButton(driveStick, 3);
    slowMode = new JoystickButton(driveStick, 12);

    //Assigns each button to a specific button on the controller
    armJointUp2 = new JoystickButton(controller, 6);
    armJointDown2 = new JoystickButton(controller, 5);
    hatchGrab2 = new JoystickButton(controller, 3);
    hatchRelease2 = new JoystickButton(controller, 4);

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

    //Adds the option to not have an auto
    autoChooser.addOption("No Auto", null);

    //Adds a black and white camera to the driverstation that runs at 10fps at 400x300 resoluton
    UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
    camera.setVideoMode(PixelFormat.kGray, 400, 300, 10);
    Shuffleboard.getTab("SmartDashboard").add("Video", camera);
 
  }

  @Override
  public void autonomousInit() {
    //Runs the selected autonomous when the autonomous period starts
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
    //Stops the autonomous if it is still running when TeleOp begins
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


    if(driveStick.getRawButton(1) == true){
      Robot.lift.liftMotor.set(Speeds.Lift);
    }
    else if(driveStick.getRawButton(1) == false){
      Robot.lift.liftMotor.set(Speeds.Lift);
    }

    //Gives each button a command to run when it is held down.
    liftUp.whileHeld(new LiftUp());
    liftDown.whileHeld(new LiftDown());

    armJointUp1.whileHeld(new ArmJointUp());
    armJointDown1.whileHeld(new ArmJointDown());

    armJointUp2.whileHeld(new ArmJointUp());
    armJointDown2.whileHeld(new ArmJointDown());

    hatchGrab1.whenPressed(new HatchGrab());
    hatchRelease1.whenPressed(new HatchRelease());

    hatchGrab2.whenPressed(new HatchGrab());
    hatchRelease2.whenPressed(new HatchRelease());

    //Makes the slowMode button toggle SlowMode on and off.
    slowMode.toggleWhenActive(new SlowMode());

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
  }
}
