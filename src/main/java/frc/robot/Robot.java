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

public class Robot extends TimedRobot {

  public static Drivetrain drivetrain = new Drivetrain();
  public static Carriage carriage = new Carriage();
  public static Lift lift = new Lift();

  private Joystick driveStick;
  private XboxController controller;

  private double leftPower = 0;
  private double rightPower = 0;

  @Override
  public void robotInit() {

    SmartDashboard.putNumber("Left Encoder: ", drivetrain.getLeftEncoderDistance());
    SmartDashboard.putNumber("Right Encoder: ", drivetrain.getRightEncoderDistance());

    SmartDashboard.putNumber("Left Power: ", leftPower);
    SmartDashboard.putNumber("Right Power: ", rightPower);

  }

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    drivetrain.ResetEncoders();
  }

  @Override
  public void teleopPeriodic() {
    double LED = drivetrain.getLeftEncoderDistance();
    double RED = drivetrain.getLeftEncoderDistance();
    
    double leftDriveSpeed = drivetrain.scaleLeftSpeedWithEncoders(0);    //Value In Constructor is Target
    double rightDriveSpeed = drivetrain.scaleRightSpeedWithEncoders(0);  //Value In Constructor is Target
                                                                                                                       //________________\\
                                                                                                                       //(Robot Position)\\
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

      SmartDashboard.putNumber("Left Encoder: ", drivetrain.getLeftEncoderDistance());
      SmartDashboard.putNumber("Right Encoder: ", drivetrain.getRightEncoderDistance());
  
      SmartDashboard.putNumber("Left Power: ", leftPower);
      SmartDashboard.putNumber("Right Power: ", rightPower);

    }
  }
  @Override
  public void robotPeriodic() {
    
    SmartDashboard.putNumber("Left Encoder: ", drivetrain.getLeftEncoderDistance());
    SmartDashboard.putNumber("Right Encoder: ", drivetrain.getRightEncoderDistance());

    SmartDashboard.putNumber("Left Power: ", leftPower);
    SmartDashboard.putNumber("Right Power: ", rightPower);
  
  }

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void startCompetition() {
  
    super.startCompetition();
  }

}
