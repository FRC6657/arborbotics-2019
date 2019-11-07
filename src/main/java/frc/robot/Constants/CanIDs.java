package frc.robot.Constants;

//This stores all of the CAN IDs.
public enum CanIDs{

    frontLeftMotor(1),
    backLeftMotor(2),
    frontRightMotor(3),
    backRightMotor(4),
    gyro(5),
    armJointMotor(6),
    liftMotor(7);

    public final int value;
    CanIDs(int value){this.value = value;}

}