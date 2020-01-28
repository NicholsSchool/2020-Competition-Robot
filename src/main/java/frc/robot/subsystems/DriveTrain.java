package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class DriveTrain extends SubsystemBase {

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
    
    lSlave.set(ControlMode.Follower,RobotMap.LEFT_MASTER_ID);
    rSlave.set(ControlMode.Follower,RobotMap.RIGHT_MASTER_ID);
    
    drive = new DifferentialDrive(new SpeedControllerGroup(lMaster),new SpeedControllerGroup(rMaster));

    
    }
    public void move(double leftSpeed, double rightSpeed){
      drive.tankDrive(leftSpeed, rightSpeed);
    }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void stop(){
    lMaster.stopMotor();
    rMaster.stopMotor();
  }
  
}