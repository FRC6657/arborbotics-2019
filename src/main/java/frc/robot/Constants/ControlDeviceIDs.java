package frc.robot.Constants;

//This controlls all of the DriverStation IDs of the controllers.
public enum ControlDeviceIDs{

    Joystick(0),
    Controller(1);

    public final int value;
    ControlDeviceIDs(int value){this.value = value;}

}