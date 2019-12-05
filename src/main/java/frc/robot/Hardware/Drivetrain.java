package frc.robot.Hardware;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.*;
import frc.robot.Robot;
import frc.robot.Constants.*;
//The comment in the class title is for if we ever get a pid source
public class Drivetrain extends Subsystem /*implements PIDOutput*/ {
    //Encoder Declarations
    private Encoder leftDriveEncoder = new Encoder(0, 1, true, EncodingType.k2X);  //Declares Left E4T Encoder and Sets it to Medium Precition
    private Encoder rightDriveEncoder = new Encoder(2, 3, false, EncodingType.k2X);//Declares Right E4T Encoder and Sets it to Medium Precition
    //Drivetrain Motor Controller Declarations
    private WPI_TalonSRX motorFL = new WPI_TalonSRX(Ports.frontLeftMotor.value); //Declares the Front Left Motor
    private WPI_TalonSRX motorFR = new WPI_TalonSRX(Ports.frontRightMotor.value);//Declares the Front Right Motor
    private WPI_TalonSRX motorBL = new WPI_TalonSRX(Ports.backLeftMotor.value);  //Declares the Back Left Motor
    private WPI_TalonSRX motorBR = new WPI_TalonSRX(Ports.backRightMotor.value); //Declares the Back Right Motor
    private PigeonIMU gyro = new PigeonIMU(Ports.gyro.value);
    //This code is just incase we ever get a pid source
    //public final PIDController turnController;

    //private final double kP = 0;
    //private final double kI = 0;
    //private final double kD = 0;

    private ShuffleboardTab tab = Shuffleboard.getTab("Drivetrain");
    private NetworkTableEntry xPositionEntry = tab.add("X:", 0).getEntry();
    private NetworkTableEntry yPositionEntry = tab.add("Y: ", 0).getEntry();

    //Drivetrain Function
    public Drivetrain() {
        //Slave motor declaration
        motorBL.follow(motorFL);//Sets back left motor to be the slave to the master front left motor 
        motorBR.follow(motorFR);//Sets back right motor to be the slave to the master front right motor
        //Encoder 
        leftDriveEncoder.setMinRate(0.03); //sets the rate in ft/s that determines if the robot is stopped or not
        rightDriveEncoder.setMinRate(0.03); //sets the rate in ft/s that determines if the robot is stopped or not

        leftDriveEncoder.setDistancePerPulse(0.005); //scales the encoder value to 1ft = ~1
        rightDriveEncoder.setDistancePerPulse(0.005);//scales the encoder value to 1ft = ~1

        leftDriveEncoder.setMaxPeriod(15); //sets the max speed the robot can go to be considered moving.
        rightDriveEncoder.setMaxPeriod(15);//sets the max speed the robot can go to be considered moving.

        gyro.configFactoryDefault();

        //turnController = new PIDController(kP, kI, kD, source, this);

    }
    //Function that we use whenever we want to drive the robot
    public void Drive(double leftPower, double rightPower){
        //Motor setting code for each master motor.
        motorFL.set(leftPower);
        motorFR.set(-rightPower); //Right side is reversed so forward is + on each side

    }
    //Code to drive the robot with driver control
    public void TeleDrive(){
        //Checks if the joystick is over one of the deadbands deadband
        if ((Robot.oi.getJoystickAxis(2) > Doubles.driveDeadband || Robot.oi.getJoystickAxis(2) < -Doubles.driveDeadband) || (Robot.oi.getJoystickAxis(1) > Doubles.turnDeadband || Robot.oi.getJoystickAxis(1) < -Doubles.turnDeadband)){
        
            double drive = -Robot.oi.getJoystickAxis(2) * Doubles.driveSpeedMod;
            double turn = Robot.oi.getJoystickAxis(1) * Doubles.turnSpeedMod;
    
            if ((!(Robot.oi.getJoystickAxis(2) > Doubles.driveDeadband) || !(Robot.oi.getJoystickAxis(2) < -Doubles.driveDeadband))){drive = 0;}
            if ((!(Robot.oi.getJoystickAxis(1) > Doubles.turnDeadband) || !(Robot.oi.getJoystickAxis(1) < -Doubles.turnDeadband))){turn = 0;}

            //Calculates the motor powers for the left and right of the drivetrain
            double leftPower = drive + turn;
            double rightPower = drive - turn;

            //Drives
            Drive(leftPower,rightPower);
        }
    }
    public void driveRobotToTargetWithEncoders(double targetL, double targetR){
        //Localization of Encoder Distances scaled to 1ft = ~1
        double LED = getLeftEncoderDistance();
        double RED = getRightEncoderDistance();
        //Gets the motor power that is scaled based on how far away the encoders are from the target
        double leftDriveSpeed = scaleLeftSpeedWithEncoders(targetL);  //Value In Constructor is Target
        double rightDriveSpeed = scaleRightSpeedWithEncoders(targetR);//Value In Constructor is Target
                                                                                                                  //________________\\ 
        //This thicc code brick is what allows the robot to move to its target encoder positions                  // Robot Position \\  
        while((!(LED < Doubles.KTR) & !(LED > -Doubles.KTR)) ||(!(RED < Doubles.KTR) & !(RED > -Doubles.KTR))){   //    !(0,0)      \\   
            if((LED < -Doubles.KTR) & (RED < -Doubles.KTR)){Drive(leftDriveSpeed, rightDriveSpeed);}              //     (-,-)      \\
            if((LED > Doubles.KTR) & (RED > Doubles.KTR)){Drive(-leftDriveSpeed, -rightDriveSpeed);}              //     (+,+)      \\
            if((LED < -Doubles.KTR) & (RED > Doubles.KTR)){Drive(leftDriveSpeed, -rightDriveSpeed);}              //     (+,-)      \\
            if((LED > -Doubles.KTR) & (RED < -Doubles.KTR)){Drive(-leftDriveSpeed, rightDriveSpeed);}             //     (-,+)      \\
            if((!(LED < -Doubles.KTR) || !(LED > Doubles.KTR) & RED > Doubles.KTR)){Drive(0, -rightDriveSpeed);}  //     (0,+)      \\
            if((!(LED < -Doubles.KTR) || !(LED > Doubles.KTR) & RED < -Doubles.KTR)){Drive(0, rightDriveSpeed);}  //     (0,-)      \\
            if((!(RED < -Doubles.KTR) || !(RED > Doubles.KTR) & LED > Doubles.KTR)){Drive(-leftDriveSpeed, 0);}   //     (+,0)      \\
            if((!(RED < -Doubles.KTR) || !(RED > Doubles.KTR) & LED < -Doubles.KTR)){Drive(leftDriveSpeed, 0);}   //     (-,0)      \\
                                                                                                                  //________________\\
        }
    }
    //Code to stop the motors
    public void Stop(){
        //Sets each motor to 0
        motorFL.set(0);
        motorFR.set(0);

    }
    //Code to reset the encoders on each side of the drivetrain
    public void ResetEncoders(){leftDriveEncoder.reset(); rightDriveEncoder.reset();} 

    public void driveRobotToCoordinate(double x, double y) throws InterruptedException {

        double angle = Math.atan(x/y);
        double hDistance = Math.sqrt((x*x)+(y*y));

        while(((getLeftEncoderDistance() - hDistance > 0.1) || (getLeftEncoderDistance() - hDistance < -0.1)) & ((getRightEncoderDistance() - hDistance > 0.1) || (getRightEncoderDistance() - hDistance < -0.1))){
            if(y > 0 & x < 0){gyroTurn(-angle);}
            else{if(y > 0 & x > 0){gyroTurn(angle);}
            else{if(y < 0 & x < 0){gyroTurn(180 - angle);}
            else{if(y < 0 & x > 0){gyroTurn(-180 + angle);}
            else{gyroTurn(0);}}}}
            Thread.sleep(5);
            driveRobotToTargetWithEncoders(hDistance, hDistance);
        }
    }
    public void gyroOverflowPrevention(){if((gyroGetAngle() > 360) || (gyroGetAngle() < -360)){gyroReset();}}

    public void gyroTurn(double angle){
        gyroReset();
        
        while (((gyroGetAngle() > 1) || (gyroGetAngle() < -1))){   
            if (gyroGetAngle() > angle){Drive(scaleTurnSpeedBasedOnTargetWithGyro(-angle), scaleTurnSpeedBasedOnTargetWithGyro(angle));}
            if (gyroGetAngle() < angle){Drive(scaleTurnSpeedBasedOnTargetWithGyro(-angle), scaleTurnSpeedBasedOnTargetWithGyro(angle));}
        }
    }
    public void gyroReset(){gyro.setFusedHeading(0);}
    public double scaleTurnSpeedBasedOnTargetWithGyro(double targetAngle){

        double Fast = Speeds.Fast;
        double Medium = Speeds.Medium;
        double Slow = Speeds.Slow;
        double Stop = Speeds.Stop;

        if ((gyroGetAngle() - targetAngle >= 180) || (gyroGetAngle() - targetAngle <= -180)){return Fast;}
        else{if(((gyroGetAngle() - targetAngle < 180) || (gyroGetAngle() - targetAngle < -180)) & ((gyroGetAngle() - targetAngle > 90) || (gyroGetAngle() - targetAngle < -90))){return Medium;}
        else{if(((gyroGetAngle() - targetAngle <= 90) || (gyroGetAngle() - targetAngle <= -90)) & ((gyroGetAngle() - targetAngle > 15) || (gyroGetAngle() - targetAngle < -15))){return Slow;}
        else{if(((gyroGetAngle() - targetAngle < 1) || (gyroGetAngle() - targetAngle < -1))){return Stop;}
        else{return Stop;}}}}
    }
    //Code to scale motor speed based on how far off the encoders position is relative to the target position
    public double scaleLeftSpeedWithEncoders(double targetPos) {
        //Localizes the speed variables fx rom the speeds file
        double Fast = Speeds.Fast;
        double Medium = Speeds.Medium;
        double Slow = Speeds.Slow;
        double Stop = Speeds.Stop;

        if(getLeftEncoderDistance() - targetPos >= 5 || getLeftEncoderDistance() - targetPos <= -5 ){return Fast;} //If the left side is 5 or more feet away go fast
        else{if(((getLeftEncoderDistance() - targetPos < 5) & getLeftEncoderDistance() - targetPos > 2) || ((getLeftEncoderDistance() - targetPos > -5) & getLeftEncoderDistance() - targetPos < -2 )){return Medium;} //If the left side is less than 5 feet away go medium speed
        else{if(((getLeftEncoderDistance() - targetPos <= 2) & getLeftEncoderDistance() - targetPos > Doubles.KTR) || ((getLeftEncoderDistance() - targetPos > -2) & getLeftEncoderDistance() - targetPos < -Doubles.KTR)){return Slow;} //If the left side is less than 2 feet away go fast
        else{if(getLeftEncoderDistance() - targetPos < Doubles.KTR || getLeftEncoderDistance() - targetPos > -Doubles.KTR){return Stop;} //If the left side is within the location tollerence
        else{System.out.println("Left Encoder Value Out of Bounds"); return Stop;}}}} //This is here so code works also so that if the robot doesnt agree with math it doesnt take over the world.
    }
    public double scaleRightSpeedWithEncoders(double targetPos){
        double Fast = Speeds.Fast;
        double Medium = Speeds.Medium;
        double Slow = Speeds.Slow;
        double Stop = Speeds.Stop;

        if(getRightEncoderDistance() - targetPos >= 5){return Fast;}
        else{if(getRightEncoderDistance() - targetPos <= 5){return Medium;}
        else{if(getRightEncoderDistance() - targetPos <= 2){return Slow;}
        else{if(getRightEncoderDistance() - targetPos < Doubles.KTR){return Stop;}
        else{System.out.println("Right Encoder Value Out of Bounds"); return Stop;}}}}
    }

    public double getLeftEncoderDistance(){return leftDriveEncoder.getDistance();}
    public double getRightEncoderDistance(){return rightDriveEncoder.getDistance();}
    public double gyroGetAngle(){
        double [] ypr = new double[3];
        gyro.getYawPitchRoll(ypr);
        return ypr[0];
    }
    public double shuffleboardGetX(){return xPositionEntry.getDouble(0);}
    public double shuffleboardGetY(){return yPositionEntry.getDouble(0);}

    @Override
    protected void initDefaultCommand() {}

    /*@Override
    public void pidWrite(double output) {
  
        Drive(-output, output);

    }
    */
}