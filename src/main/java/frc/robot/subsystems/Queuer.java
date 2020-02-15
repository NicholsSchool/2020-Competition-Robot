package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

/**
 *  Subsystem for the Queuer on the robot, uses 3 motors to achieve the queuer's 
 *  goal of lining up the balls for the Shooter to shoot
 */

public class Queuer extends SubsystemBase {

    private WPI_TalonSRX lock2;
    private WPI_TalonSRX lock3;
    private WPI_TalonSRX lock4;

    private WPI_TalonSRX[] locks; 

    /**
     *  Constructor Method creating motors 
     */

    public Queuer() {

        lock2 = new WPI_TalonSRX(RobotMap.LOCK_TWO_MOTOR_ID);
        lock3 = new WPI_TalonSRX(RobotMap.LOCK_THREE_MOTOR_ID);
        lock4 = new WPI_TalonSRX(RobotMap.LOCK_FOUR_MOTOR_ID);

        locks = new WPI_TalonSRX[3];
        locks[0] = lock2;
        locks[1] = lock3;
        locks[2] = lock4;

        lock2.configFactoryDefault();
        lock3.configFactoryDefault();
        lock4.configFactoryDefault();

        lock2.setInverted( true );
        lock3.setInverted( true );
        lock4.setInverted( true );
    }

    /**
     * @param speed paramater to input the speed of all the motors
     * 
     *  Method moving all the motors at the same speed
     */

    public void moveAll(double speed) {
        for (int i = 0; i < locks.length; i++)
            locks[i].set(speed);
    }

    /**
     * @param speed parameter to input the speed of the motors
     * @param index parameter for the index of the motor you want to move
     * 
     *  Method that moves one motor within the queuer class
     */

    public void move(double speed, int index) {
        if (index < 0 || index > locks.length)
            return;
        locks[index].set(speed);
    }

    /**
     * @return boolean to see if the ball is in place 
     * 
     *  Checking the queuer as to see if there is a ball in the locks
     */

    public boolean checkQueuer()
    {
        boolean[] sensorValues = new boolean[locks.length];
        boolean falseFound = false;

        for(int i = locks.length; i >= 0; i--)
        {
            if(sensorValues[i] == false)
            {
                falseFound = true;
            }

            if(sensorValues[i] == true && falseFound == true)
            {
                return false;
            }
        }

        return true;
    }

    /**
     *  Method stopping each lock motor
     */

    public void stop()
    {
        lock2.stopMotor();
        lock3.stopMotor();
        lock4.stopMotor();
    }

    /**
     *  Method getting the current of each lock motor 
     */

    @Override
    public void periodic() {
        lock2.getStatorCurrent();
        lock3.getStatorCurrent();
        lock4.getStatorCurrent();
    }
}