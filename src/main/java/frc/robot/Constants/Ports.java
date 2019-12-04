package frc.robot.Constants;

public enum Ports{

    //CanIDs
    frontLeftMotor(1),
    frontRightMotor(2),
    backLeftMotor(3),
    backRightMotor(4),
    gyro(5),
    armJointMotor(6),
    liftMotor(7),
    //PWM
    wheelClawL(0),
    wheelClawR(1),
    //Controllers
    joyStick(0),
    Controller(1);

    public final int value;
    Ports(int value){this.value = value;}

}