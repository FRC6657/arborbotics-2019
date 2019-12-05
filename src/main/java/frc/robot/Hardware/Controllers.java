/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Hardware;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants.Ports;

/**
 * Add your docs here.
 */
public class Controllers extends Subsystem {

  private Joystick joystick = new Joystick(Ports.joyStick.value);
  private XboxController controller = new XboxController(Ports.Controller.value);

  public double getJoystickAxis(int axis){return joystick.getRawAxis(axis);}
  public double getControllerAxis(int axis){return controller.getRawAxis(axis);}

  @Override
  public void initDefaultCommand() {
;
  }
}
