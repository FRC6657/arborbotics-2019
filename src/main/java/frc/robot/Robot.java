/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.Doubles;
import frc.robot.Hardware.Carriage;
import frc.robot.Hardware.Drivetrain;
import frc.robot.Hardware.Lift;
import frc.robot.Hardware.OI;


//import frc.robot.PID.PIDDrivetrain;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import frc.robot.Commands.*;

//Main Code File For the Robot
public class Robot extends TimedRobot {
  //Subsystem Initialization
  public static Drivetrain drivetrain = new Drivetrain();
  public static Carriage carriage = new Carriage();
  public static Lift lift = new Lift();
  //public static PIDDrivetrain pidDrivetrain = new PIDDrivetrain();
  public static OI oi;

  private int leftDirection = 0;
  private int rightDirection = 0;

  //Creates Power Variables That Can be Printed
  private double leftPower = 0;
  private double rightPower = 0;

  //Initialization of the Rio's built in accelerometer
  private BuiltInAccelerometer accel = new BuiltInAccelerometer();
  //This code runs when the robot code is turned on
  @Override
  public void robotInit() {
    oi = new OI();
    //This code prints the encoder values scaled to 1ft = ~1
    SmartDashboard.putNumber("Left Encoder: ", drivetrain.getLeftEncoderDistance());
    SmartDashboard.putNumber("Right Encoder: ", drivetrain.getRightEncoderDistance());
    //This code prints the motor powers
    SmartDashboard.putNumber("Left Power: ", leftPower);
    SmartDashboard.putNumber("Right Power: ", rightPower);
  }
  //This code runs when autonomous is started
  @Override
  public void autonomousInit() {
    drivetrain.ResetEncoders();
    leftDirection = 0;
    rightDirection = 0;
  }
  //This code is a loop that runs when autonomous is happening
  @Override
  public void autonomousPeriodic() {

    //Localization of Encoder Distances scaled to 1ft = ~1
    double LED = drivetrain.getLeftEncoderDistance();
    double RED = drivetrain.getRightEncoderDistance();
    //Gets the motor power that is scaled based on how far away the encoders are from the target
    double leftDriveSpeed = drivetrain.scaleLeftSpeedWithEncoders(5,4,0.7);  //Value In Constructor is Target
    double rightDriveSpeed = drivetrain.scaleRightSpeedWithEncoders(5,4,0.7);//Value In Constructor is Target

    drivetrain.Drive(leftDriveSpeed, rightDriveSpeed);

  }
  //This code runs when TeleOp is started
  @Override
  public void teleopInit() {}
  //This code is a loop that runs when TeleOp is happening
  @Override
  public void teleopPeriodic() {
    
    drivetrain.TeleDrive();

  }                                                                                               
  
  //This code runs whenever the robot is turned on reguardless of state
  @Override
  public void robotPeriodic() {
    //This code prints the encoder values scaled to 1ft = ~1 
    SmartDashboard.putNumber("Left Encoder: ", drivetrain.getLeftEncoderDistance());
    SmartDashboard.putNumber("Right Encoder: ", drivetrain.getRightEncoderDistance());
    //This code prints the motor powers
    SmartDashboard.putNumber("Left Power: ", leftPower);
    SmartDashboard.putNumber("Right Power: ", rightPower);
    //Print the Gyro Z Axis Angle
    SmartDashboard.putNumber("Gyro Angle: ", drivetrain.gyroGetAngle());
    //Prints the X and Y accelerometer Values No Z Because why would we ever need it
    SmartDashboard.putString("Rio Accelerometer Value: X: ", accel.getX() + " Y: " + accel.getY());
    //This allows us to set a custom value for the robot to go to
    SmartDashboard.putData("Drive To Location: ",new DriveToLocation(drivetrain.shuffleboardGetY(),drivetrain.shuffleboardGetX()));//Drive(F/B, R/L) Direction is same as on coordinate plane
    SmartDashboard.putNumber("LeftDirection", leftDirection);
    SmartDashboard.putNumber("RightDirection", rightDirection);
    //SmartDashboard.putBoolean("LeftInTollerance", LeftInZone);
    //SmartDashboard.putBoolean("RightInTollerance", RightInZone);
    
    //Prevents the gyro angle from going over 360*
    drivetrain.gyroOverflowPrevention(); //Prevents gyro angle from getting over 360 or -360
  }
  //This code runs when test mode is started
  @Override
  public void testInit() {}
  //This code is a loop that runs when Test Mode is happening
  @Override
  public void testPeriodic() {}
}
