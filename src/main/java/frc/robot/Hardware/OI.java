/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Hardware;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Commands.*;

import frc.robot.Constants.Ports;

public class OI extends Subsystem {

  private Joystick joystick = new Joystick(Ports.joyStick.value);
  private XboxController controller = new XboxController(Ports.Controller.value);

    public JoystickButton liftUp;
    public JoystickButton liftDown;
    public JoystickButton squishyIn;
    public JoystickButton squishyOut;
    public JoystickButton hingeUp;
    public JoystickButton hingeDown;

    public OI(){
      liftUp = new JoystickButton(joystick, 1);
      liftDown = new JoystickButton(joystick, 2);
      squishyIn = new JoystickButton(joystick,4);
      squishyOut = new JoystickButton(joystick,6);
      hingeUp = new JoystickButton(joystick, 5);
      hingeDown = new JoystickButton(joystick, 3);

      LiftUp lu = new LiftUp();
      LiftDown ld = new LiftDown();
      SquishyIn  si = new SquishyIn();
      SquishyOut so = new SquishyOut();
      HingeUp hu = new HingeUp();
      HingeDown hd = new HingeDown();

      liftUp.whileHeld(lu);
      liftDown.whileHeld(ld);
      squishyIn.whileHeld(si);
      squishyOut.whileHeld(so);
      hingeUp.whileHeld(hu);
      hingeDown.whileHeld(hd);

    }

  public double getJoystickAxis(int axis){return joystick.getRawAxis(axis);}
  public double getControllerAxis(int axis){return controller.getRawAxis(axis);}

  @Override
  public void initDefaultCommand() {
;
  }
}
