package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.RobotMap;

public class DriveTrain extends SubsystemBase {

    private double govener;
    private WPI_TalonSRX lMaster;
    private WPI_VictorSPX lSlave;
    private WPI_TalonSRX rMaster;
    private WPI_VictorSPX rSlave;

    public static Solenoid backOmnisSolenoid;

    private DifferentialDrive drive;
    // Odometry class for tracking robot pose
    private final DifferentialDriveOdometry m_odometry;

    /**
     * Creates a new DriveTrain.
     */
    public DriveTrain() {
        lMaster = new WPI_TalonSRX(RobotMap.LEFT_MASTER_ID);
        lSlave = new WPI_VictorSPX(RobotMap.LEFT_SLAVE_ID);

        rMaster = new WPI_TalonSRX(RobotMap.RIGHT_MASTER_ID);
        rSlave = new WPI_VictorSPX(RobotMap.RIGHT_SLAVE_ID);

        backOmnisSolenoid = new Solenoid(RobotMap.COMPRESSOR_ID, RobotMap.BACK_OMNIS_SOLENOID_CHANNEL);

        lMaster.configFactoryDefault();
        lSlave.configFactoryDefault();

        rMaster.configFactoryDefault();
        rSlave.configFactoryDefault();

        rSlave.setInverted(true);

        lSlave.follow(lMaster);
        rSlave.follow(rMaster);

        drive = new DifferentialDrive(new SpeedControllerGroup(lMaster), new SpeedControllerGroup(rMaster));

        rMaster.setSensorPhase(true);

        lMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
        rMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);

        lMaster.config_kF(0, Constants.LEFT_MASTER_F, Constants.CONFIG_TIMEOUT);
        lMaster.config_kP(0, Constants.LEFT_MASTER_P, Constants.CONFIG_TIMEOUT);
        lMaster.config_kI(0, Constants.LEFT_MASTER_I, Constants.CONFIG_TIMEOUT);
        lMaster.config_kD(0, Constants.LEFT_MASTER_D, Constants.CONFIG_TIMEOUT);

        rMaster.config_kF(0, Constants.RIGHT_MASTER_F, Constants.CONFIG_TIMEOUT);
        rMaster.config_kP(0, Constants.RIGHT_MASTER_P, Constants.CONFIG_TIMEOUT);
        rMaster.config_kI(0, Constants.RIGHT_MASTER_I, Constants.CONFIG_TIMEOUT);
        rMaster.config_kD(0, Constants.RIGHT_MASTER_D, Constants.CONFIG_TIMEOUT);

        lMaster.configOpenloopRamp(Constants.RAMP_TIME);
        rMaster.configOpenloopRamp(Constants.RAMP_TIME);

        setFastMode(true);

        m_odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(RobotContainer.navX.getAngle()));

    }

    /**
     * 
     * method used to move.
     * 
     * @param leftSpeed  - speed of left motor.
     * @param rightSpeed - speed of right motor.
     */
    public void move(double leftSpeed, double rightSpeed) {
        if(RobotContainer.climber.isClimbEngaged())
            leftSpeed = rightSpeed;

        drive.tankDrive(leftSpeed * govener, rightSpeed * govener);

    }

    public void setFastMode(boolean setFast) {
        if (setFast) {
            govener = Constants.DRIVE_FAST_MODE;
        } else {
            govener = Constants.DRIVE_SLOW_MODE;
        }
    }

    public Pose2d getPose() {
        return m_odometry.getPoseMeters();
    }

    public DifferentialDriveWheelSpeeds getWheelSpeeds() {
        return new DifferentialDriveWheelSpeeds(lMaster.getSelectedSensorVelocity() * Constants.METERS_PER_TICK,
                rMaster.getSelectedSensorVelocity() * Constants.METERS_PER_TICK);
    }

    public void tankDriveVolts(double leftVolts, double rightVolts) {
        lMaster.setVoltage(leftVolts);
        rMaster.setVoltage(-rightVolts);
        drive.feed();
    }

    /**
     * 
     * Projects the encoder values of the Master motors, and angles for the navX.
     * 
     */
    public void encoderTest() {
        SmartDashboard.putNumber("lMaster", lMaster.getSelectedSensorPosition());
        SmartDashboard.putNumber("rMaster", rMaster.getSelectedSensorPosition());
        SmartDashboard.putNumber("NavX", RobotContainer.navX.getAngle());
        SmartDashboard.putNumber("Drive Governor", govener);
    }

    public void resetEncoders() {
        lMaster.setSelectedSensorPosition(0);
        rMaster.setSelectedSensorPosition(0);
    }

    /**
     * returns sensor position.
     * 
     * @return
     */
    public int getEncoderValue() {
        return lMaster.getSelectedSensorPosition();
    }

    /**
     * resets encoder value.
     */
    public void resetEncoder() {
        lMaster.getSelectedSensorPosition(0);
        rMaster.getSelectedSensorPosition(0);
    }

    public boolean areBackOminsEngaged() {
        return backOmnisSolenoid.get() == Constants.BACK_OMNIS_ENGAGED;
    }

    //Can't turn with them engaged
    public void engageBackOmnis(){
        backOmnisSolenoid.set(Constants.BACK_OMNIS_ENGAGED);
    }

    public void disengageBackOmnis() {
        backOmnisSolenoid.set(Constants.BACK_OMNIS_DISENGAGED);
    }

    @Override
    public void periodic() {
        encoderTest();
        m_odometry.update(Rotation2d.fromDegrees(RobotContainer.navX.getAngle()),
                lMaster.getSelectedSensorPosition() * Constants.METERS_PER_TICK,
                rMaster.getSelectedSensorPosition() * Constants.METERS_PER_TICK);
        // This method will be called once per scheduler run
        SmartDashboard.putBoolean("BACK OMNIS ENGAGED", areBackOminsEngaged());
        SmartDashboard.putBoolean("APP Switch", RobotContainer.app.isSwitchPressed());
        SmartDashboard.putNumber("App Dial", RobotContainer.app.getDialPosition());
    }

    /**
     * 
     * stops motors.
     * 
     */
    public void stop() {
        lMaster.stopMotor();
        rMaster.stopMotor();
    }

}