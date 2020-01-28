package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.RobotMap;

public class Queuer
{
    private WPI_TalonSRX lock2;
    private WPI_TalonSRX lock3;
    private WPI_TalonSRX lock4;
    private WPI_TalonSRX lock5;

    public Queuer()
    {
        lock2 = new WPI_TalonSRX(RobotMap.LOCK_TWO_MOTOR_ID);
        lock3 = new WPI_TalonSRX(RobotMap.LOCK_THREE_MOTOR_ID);
        lock4 = new WPI_TalonSRX(RobotMap.LOCK_FOUR_MOTOR_ID);
        lock5 = new WPI_TalonSRX(RobotMap.LOCK_FIVE_MOTOR_ID);

        lock2.configFactoryDefault();
        lock3.configFactoryDefault();
        lock4.configFactoryDefault();
        lock5.configFactoryDefault();
        
    }
}