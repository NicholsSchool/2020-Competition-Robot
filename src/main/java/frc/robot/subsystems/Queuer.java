package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.RobotMap;

public class Queuer
{
    private WPI_TalonSRX lockTwo;
    private WPI_TalonSRX lockThree;
    private WPI_TalonSRX lockFour;
    private WPI_TalonSRX lockFive;

    public Queuer()
    {
        lockTwo = new WPI_TalonSRX(RobotMap.LOCK_TWO_MOTOR_ID);
        lockThree = new WPI_TalonSRX(RobotMap.LOCK_THREE_MOTOR_ID);
        lockFour = new WPI_TalonSRX(RobotMap.LOCK_FOUR_MOTOR_ID);
        lockFive = new WPI_TalonSRX(RobotMap.LOCK_FIVE_MOTOR_ID);
    }
}