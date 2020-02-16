package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.RobotMap;

/**
 *  Subsystem for the Queuer on the robot, uses 3 motors to achieve the queuer's 
 *  goal of lining up the balls for the Shooter to shoot
 */

public class Queuer extends SubsystemBase {

    private WPI_TalonSRX lock1;
    private WPI_TalonSRX lock2;
    private WPI_TalonSRX lock3;
    private WPI_TalonSRX lock4;
    private WPI_TalonSRX lock5;

    private WPI_TalonSRX[] locks; 

    private int numBallsInCorrectPos;
    private boolean dequeueStarted;
    private long dequeueStartTime;

    /**
     *  Constructor Method creating motors 
     */

    public Queuer() {

        lock1 = new WPI_TalonSRX(RobotMap.INTAKE_ID);
        lock2 = new WPI_TalonSRX(RobotMap.LOCK_TWO_MOTOR_ID);
        lock3 = new WPI_TalonSRX(RobotMap.LOCK_THREE_MOTOR_ID);
        lock4 = new WPI_TalonSRX(RobotMap.LOCK_FOUR_MOTOR_ID);
        lock5 = new WPI_TalonSRX(RobotMap.LOCK_FIVE_MOTOR_ID);

        locks = new WPI_TalonSRX[]{lock1, lock2, lock3, lock4, lock5};

        lock1.configFactoryDefault();
        lock2.configFactoryDefault();
        lock3.configFactoryDefault();
        lock4.configFactoryDefault();
        lock5.configFactoryDefault();

        lock1.setInverted( true );
        lock2.setInverted( true );
        lock3.setInverted( true );
        lock4.setInverted( true );
        lock5.setInverted( true );
        numBallsInCorrectPos = 0;
        dequeueStarted = false;
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
        boolean[] sensorValues = RobotContainer.irSystem.getValues();
        boolean falseFound = false;
        for(int i = 0; i < sensorValues.length; i ++)
        {
            if(!sensorValues[i])
                falseFound = true;

            if(falseFound && sensorValues[i])
                return false;
        }
        return true;
    }

    public void queue() {
        
        updateNumberOfBalls();
        for(int i = 0; i < locks.length - numBallsInCorrectPos - 1; i ++) // -1 because lock5 should not spin
                move(Constants.QUEUE_MOVE_SPEED, i);
        
    }

    public void unload()
    {
        boolean[] sensorValues = RobotContainer.irSystem.getValues();
        for(int i = locks.length - 1; i >= 0; i--)
        {
            if(i + 1 >= sensorValues.length)
                move(Constants.QUEUE_MOVE_SPEED, i);
            else if(sensorValues[i + 1])
            {
                move(Constants.QUEUE_MOVE_SPEED, i + 1);
                move(Constants.QUEUE_MOVE_SPEED, i);
            }
        }
    }

    public void updateNumberOfBalls()
    {
        int totalBalls = 0;
        boolean[] sensorValues = RobotContainer.irSystem.getValues();
        for(int i = sensorValues.length - 1; i >= 0; i --)
            if(!sensorValues[i])
                totalBalls ++;
            else
                break;
        numBallsInCorrectPos = totalBalls;
            
    }

    public int getNumberBallsInCorrectPosition()
    {
        return numBallsInCorrectPos;
    }

    /**
     * Sets the intake motor values to INTAKE_SPEED, a double value in the Constants
     * class.
     */
    public void intake() {
        move(Constants.INTAKE_SPEED, 0);
    }

    /**
     * Does the same thing as intake(), but in the other direction.
     */
    public void outtake() {
        move(-Constants.INTAKE_SPEED, 0);
    }

    /**
     *  Method stopping each lock motor
     */

    public void stop()
    {
        lock1.stopMotor();
        lock2.stopMotor();
        lock3.stopMotor();
        lock4.stopMotor();
        lock5.stopMotor();
        dequeueStarted = false;
    }

    public void stopIntake()
    {
        lock1.stopMotor();
    }

    /**
     *  Method getting the current of each lock motor 
     */

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Number of Balls In Correct Position", numBallsInCorrectPos);
        boolean[] beams = RobotContainer.irSystem.getValues();
        for(int i = 0; i < beams.length; i ++)
            SmartDashboard.putBoolean("Beam " + (i + 1), beams[i]);
    }
}