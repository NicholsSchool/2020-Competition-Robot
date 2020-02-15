package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.RobotMap;
import frc.robot.sensors.NavX;

public class DriveTrain extends SubsystemBase {

  private double govener;
  private WPI_TalonSRX lMaster;
  private WPI_TalonSRX lSlave;
  private WPI_TalonSRX rMaster;
  private WPI_TalonSRX rSlave;

  private DifferentialDrive drive;

  /**
   * Creates a new DriveTrain.
   */
  public DriveTrain() {
    lMaster = new WPI_TalonSRX(RobotMap.LEFT_MASTER_ID);
    lSlave = new WPI_TalonSRX(RobotMap.LEFT_SLAVE_ID);

    rMaster = new WPI_TalonSRX(RobotMap.RIGHT_MASTER_ID);
    rSlave = new WPI_TalonSRX(RobotMap.RIGHT_SLAVE_ID);

    lMaster.configFactoryDefault();
    lSlave.configFactoryDefault();

    rMaster.configFactoryDefault();
    rSlave.configFactoryDefault();

    lSlave.set(ControlMode.Follower, RobotMap.LEFT_MASTER_ID);
    rSlave.set(ControlMode.Follower, RobotMap.RIGHT_MASTER_ID);

    drive = new DifferentialDrive(new SpeedControllerGroup(lMaster), new SpeedControllerGroup(rMaster));

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

  }

  /**
   * 
   * method used to move.
   * 
   * @param leftSpeed  - speed of left motor.
   * @param rightSpeed - speed of right motor.
   */
  public void move(double leftSpeed, double rightSpeed) {
    drive.tankDrive(leftSpeed * govener, rightSpeed * govener);

  }

  public void setFastMode(boolean setFast) {
    if (setFast) {
      govener = Constants.DRIVE_FAST_MODE;
    } else {
      govener = Constants.DRIVE_SLOW_MODE;
    }
  }

  /**
   * 
   * Projects the encoder values of the Master motors, and angles for the navX.
   * 
   */
  public void encoderTest() {
    SmartDashboard.putNumber("lMaster", lMaster.getSelectedSensorPosition());
    SmartDashboard.putNumber("rMaster", rMaster.getSelectedSensorPosition());
    SmartDashboard.putNumber("NavX Angle: ", RobotContainer.navX.getAngle());

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

  @Override
  public void periodic() {
    encoderTest();

    // This method will be called once per scheduler run
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