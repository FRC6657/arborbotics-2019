package frc.robot.Hardware;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.Constants.*;

public class Drivetrain extends Subsystem /*implements PIDOutput*/ {

    private Encoder leftDriveEncoder = new Encoder(0, 1, true, EncodingType.k2X);
    private Encoder rightDriveEncoder = new Encoder(2, 3, false, EncodingType.k2X);

    private WPI_TalonSRX motorFL = new WPI_TalonSRX(Ports.frontLeftMotor.value);
    private WPI_TalonSRX motorFR = new WPI_TalonSRX(Ports.frontRightMotor.value);
    private WPI_TalonSRX motorBL = new WPI_TalonSRX(Ports.backLeftMotor.value);
    private WPI_TalonSRX motorBR = new WPI_TalonSRX(Ports.backRightMotor.value);

    //public final PIDController turnController;

    //private final double kP = 0;
    //private final double kI = 0;
    //private final double kD = 0;

    public Drivetrain() {

        motorBL.follow(motorFL);
        motorBR.follow(motorFR);

        leftDriveEncoder.setMinRate(10);
        rightDriveEncoder.setMinRate(10);

        leftDriveEncoder.setDistancePerPulse(0.005);
        rightDriveEncoder.setDistancePerPulse(0.005);

        leftDriveEncoder.setMaxPeriod(0.1);
        rightDriveEncoder.setMaxPeriod(0.1);

        //turnController = new PIDController(kP, kI, kD, source, this);

    }
    
    public void Drive(double leftPower, double rightPower){

        motorFL.set(leftPower);
        motorFR.set(-rightPower);

    }
    public void Stop(){

        motorFL.set(0);
        motorFR.set(0);

    }
    public void ResetEncoders(){leftDriveEncoder.reset(); rightDriveEncoder.reset();}
    public double scaleLeftSpeedWithEncoders(double targetPos) {
        double Fast = Speeds.Fast;
        double Medium = Speeds.Medium;
        double Slow = Speeds.Slow;
        double Stop = Speeds.Stop;

        if(getLeftEncoderDistance() - targetPos >= 5){return Fast;}
        else{if(getLeftEncoderDistance() - targetPos <= 5){return Medium;}
        else{if(getLeftEncoderDistance() - targetPos <= 2){return Slow;}
        else{if(getLeftEncoderDistance() - targetPos < Doubles.KTR){return Stop;}
        else{return Stop;}}}}
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
        else{return Stop;}}}}
    }

    public double getLeftEncoderDistance(){return leftDriveEncoder.getDistance();}
    public double getRightEncoderDistance(){return rightDriveEncoder.getDistance();}

    @Override
    protected void initDefaultCommand() {}

    /*@Override
    public void pidWrite(double output) {
  
        Drive(-output, output);

    }
    */
}