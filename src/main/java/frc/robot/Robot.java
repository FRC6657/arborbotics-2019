/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.Doubles;
import frc.robot.Constants.Speeds;
import frc.robot.Hardware.Carriage;
import frc.robot.Hardware.Drivetrain;
import frc.robot.Hardware.Lift;

//Main Code File For the Robot
public class Robot extends TimedRobot {
  //Subsystem Initialization
  public static Drivetrain drivetrain = new Drivetrain();
  public static Carriage carriage = new Carriage();
  public static Lift lift = new Lift();
  //Controller Initialization
  private Joystick driveStick;
  private XboxController controller;
  //Creates Power Variables That Can be Printed
  private double leftPower = 0;
  private double rightPower = 0;
  //This code runs when the robot code is turned on
  @Override
  public void robotInit() {
    //This code prints the encoder values scaled to 1ft = ~1
    SmartDashboard.putNumber("Left Encoder: ", drivetrain.getLeftEncoderDistance());
    SmartDashboard.putNumber("Right Encoder: ", drivetrain.getRightEncoderDistance());
    //This code prints the motor powers
    SmartDashboard.putNumber("Left Power: ", leftPower);
    SmartDashboard.putNumber("Right Power: ", rightPower);
  }
  //This code runs when autonomous is started
  @Override
  public void autonomousInit() {}
  //This code is a loop that runs when autonomous is happening
  @Override
  public void autonomousPeriodic() {}
  //This code runs when TeleOp is started
  @Override
  public void teleopInit() {
    drivetrain.ResetEncoders();
  }
  //This code is a loop that runs when TeleOp is happening
  @Override
  public void teleopPeriodic() {
    //Localization of Encoder Distances scaled to 1ft = ~1
    double LED = drivetrain.getLeftEncoderDistance();
    double RED = drivetrain.getRightEncoderDistance();
    //Gets the motor power that is scaled based on how far away the encoders are from the target
    double leftDriveSpeed = drivetrain.scaleLeftSpeedWithEncoders(0);  //Value In Constructor is Target
    double rightDriveSpeed = drivetrain.scaleRightSpeedWithEncoders(0);//Value In Constructor is Target
                                                                                                                       //________________\\
    //This thicc code brick is what allows the robot to move to its target encoder positions                           // Robot Position \\                                                                                                                  //(Robot Position)\\
    if((!(LED < Doubles.KTR) & !(LED > -Doubles.KTR)) ||(!(RED < Doubles.KTR) & !(RED > -Doubles.KTR))){               //    !(0,0)      \\    
      if((LED < -Doubles.KTR) & (RED < -Doubles.KTR)){drivetrain.Drive(leftDriveSpeed, rightDriveSpeed);}              //     (-,-)      \\
      if((LED > Doubles.KTR) & (RED > Doubles.KTR)){drivetrain.Drive(-leftDriveSpeed, -rightDriveSpeed);}              //     (+,+)      \\
      if((LED < -Doubles.KTR) & (RED > Doubles.KTR)){drivetrain.Drive(-leftDriveSpeed, rightDriveSpeed);}              //     (+,-)      \\
      if((LED > -Doubles.KTR) & (RED < -Doubles.KTR)){drivetrain.Drive(leftDriveSpeed, -rightDriveSpeed);}             //     (-,+)      \\
      if((!(LED < -Doubles.KTR) & !(LED > Doubles.KTR) & RED > Doubles.KTR)){drivetrain.Drive(0, -rightDriveSpeed);}   //     (0,+)      \\
      if((!(LED < -Doubles.KTR) & !(LED > Doubles.KTR) & RED < -Doubles.KTR)){drivetrain.Drive(0, rightDriveSpeed);}   //     (0,-)      \\
      if((!(RED < -Doubles.KTR) & !(RED > Doubles.KTR) & LED > Doubles.KTR)){drivetrain.Drive(-leftDriveSpeed, 0);}    //     (+,0)      \\
      if((!(RED < -Doubles.KTR) & !(RED > Doubles.KTR) & LED < -Doubles.KTR)){drivetrain.Drive(leftDriveSpeed, 0);}    //     (-,0)      \\
                                                                                                                       //________________\\
      //This code prints the encoder values scaled to 1ft = ~1 
      SmartDashboard.putNumber("Left Encoder: ", drivetrain.getLeftEncoderDistance());
      SmartDashboard.putNumber("Right Encoder: ", drivetrain.getRightEncoderDistance());
      //This code prints the motor powers
      SmartDashboard.putNumber("Left Power: ", leftPower);
      SmartDashboard.putNumber("Right Power: ", rightPower);
    }
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

    SmartDashboard.putNumber("Gyro Angle: ", drivetrain.gyroGetAngle());

    drivetrain.gyroOverflowPrevention(); //Prevents gyro angle from getting over 360 or -360
  }
  //This code runs when test mode is started
  @Override
  public void testInit() {}
  //This code is a loop that runs when Test Mode is happening
  @Override
  public void testPeriodic() {}
}
