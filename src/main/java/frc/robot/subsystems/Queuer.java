package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Queuer extends SubsystemBase {
    private WPI_TalonSRX lock2;
    private WPI_TalonSRX lock3;
    private WPI_TalonSRX lock4;

    private WPI_TalonSRX[] locks;

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
    }

    public void moveAll(double speed) {
        for (int i = 0; i < locks.length; i++)
            locks[i].set(speed);
    }

    public void move(double speed, int index) {
        if (index < 0 || index > locks.length)
            return;
        locks[index].set(speed);
    }

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

    public void stop()
    {
        lock2.stopMotor();
        lock3.stopMotor();
        lock4.stopMotor();
    }
}