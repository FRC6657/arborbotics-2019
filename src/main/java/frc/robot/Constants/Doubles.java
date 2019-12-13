package frc.robot.Constants;

//This file stores all of the axis deadbands
public interface Doubles {

    public double driveDeadband = 0.1; //Deadband for the drive axis
    public double turnDeadband = 0.1; //Deadband for the turn axis
    public double driveSpeedMod = 0.7; //Drive Speed Modifier
    public double turnSpeedMod = 0.7; //Turn Speed Modifier

    public double KTR = 0.2; //Kick the robot testing encoder deadbands
    
}