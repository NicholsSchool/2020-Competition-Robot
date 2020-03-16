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
    private long lastUpdateTime;

    /**
     *  Creates a new Queuer instance
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
    }

    /**
     * Moves all the locks at the given speed
     * @param speed  the speed to move all the locks at
     */
    public void moveAll(double speed) {
        for (int i = 0; i < locks.length; i++)
            locks[i].set(speed);
    }

    /**
     * Moves the specified lock at the given speed
     * 
     * @param speed  the speed to move the lock at
     * @param index  the index of the lock to move
     */

    public void move(double speed, int index) {
        if (index < 0 || index > locks.length)
            return;
        locks[index].set(speed);
    }

    /**
     * returns true if all balls are in the correct state within the queuer system
     * 
     * @return true if all balls are in the correct state within the queuer system
     */
    public boolean checkQueuer()
    {
        boolean[] sensorValues = RobotContainer.irSystem.getValues();
        boolean falseFound = false;
        // We start from the entry point of the system and work are way up
        for(int i = 0; i < sensorValues.length; i ++)
        {
            if(!sensorValues[i]) // If we find a false value, that means we have found a ball in the system
                falseFound = true;

            // If we found a ball and now we see an empty spot, the ball was in the
            // incorrect state
            if(falseFound && sensorValues[i]) 
                return false;
        }
        return true;
    }

    /**
     * Queues the balls in the system
     */
    public void queue() {
        
        updateNumberOfBalls();
        for(int i = 0; i < locks.length - 1; i ++) // -1 because lock5 should not spin
        {
            // Don't move locks which have balls in the correct position
            if(i >= locks.length - numBallsInCorrectPos - 1) 
                move(0, i);
            else
                move(Constants.QUEUE_MOVE_SPEED, i);
        }
    }
    
    /**
     * Shoots one ball (possibly two)
     */
    public void unloadOne()
    {
        move(Constants.QUEUE_MOVE_SPEED, locks.length - 1);
        move(Constants.QUEUE_MOVE_SPEED, locks.length - 2);
    }

    /**
     * Unloads the balls in the system upwards for the shooter to shoot them
     */
    public void unload()
    {
        boolean[] sensorValues = RobotContainer.irSystem.getValues();

        // Move everything regardless of sensors if driver has override on
        if(RobotContainer.irSensorOveride)
        {
            moveAll(Constants.QUEUE_UNLOAD_SPEED);
            return;
        }
        for(int i = locks.length - 1; i >= 0; i--)
        {
            if(i + 1 >= sensorValues.length) // lock5 should always move when shooting
                move(Constants.QUEUE_UNLOAD_SPEED, i); 
            //If the next position is empty and the current position has a ball, then move these locks
            else if(sensorValues[i + 1] && !sensorValues[i])  
            {
                move(Constants.QUEUE_UNLOAD_SPEED, i + 1);
                move(Constants.QUEUE_UNLOAD_SPEED, i);
            }
            else 
                move(0, i);
        }
        //If the top sensor isn't broken, the ball must be stuck at the fourth lock as well, so move that
        if(!sensorValues[sensorValues.length - 1])
            move(Constants.QUEUE_UNLOAD_SPEED, 3);
    }

    /**
     * Updates the number of balls in the correct position within the system
     */
    public void updateNumberOfBalls()
    {
        // Delay update if necessarry 
        if(System.currentTimeMillis() - lastUpdateTime < Constants.QUEUE_DELAY_TIME * 1000)
            return;
        if (RobotContainer.irSensorOveride)
        {
            numBallsInCorrectPos = 0;
            return;
        }
     
        int totalBalls = 0;
        boolean[] sensorValues = RobotContainer.irSystem.getValues();
        // Loop from the top of the system downwards and count the balls. 
        // If a ball is not there, then stop counting
        for(int i = sensorValues.length - 1; i >= 0; i --)
        {
            if(!sensorValues[i])
                totalBalls ++;
            else
                break;
        }
        numBallsInCorrectPos = totalBalls;
        lastUpdateTime = System.currentTimeMillis();
 
    }

    /**
     * Returns the number of balls in correct position within the system
     * 
     * @return the number of balls in correct position within the system
     */
    public int getNumberBallsInCorrectPosition()
    {
        return numBallsInCorrectPos;
    }

    /**
     * Agitates the system upwards
     */
    public void agitate()
    {
        for(int i = 0; i < locks.length; i ++)
            move(Constants.QUEUER_AGITATE_SPEED, i);
    }

    /**
     * Agitates the system downwards
     */
    public void reverseAgitate()
    {
        for (int i = 0; i < locks.length; i++)
            move(-Constants.QUEUER_AGITATE_SPEED, i);
    }

    /**
     * Returns true if the system is empty
     * @return true if the system is empty
     */
    public boolean isEmpty()
    {
        boolean[] values = RobotContainer.irSystem.getValues();
        for(boolean b : values)
            if(!b)
                return false;
        return true;
    }

    /**
     * Returns true if the system is full with 5 balls
     * 
     * @return true if the system is full with 5 balls
     */
    public boolean isFull()
    {
        return numBallsInCorrectPos == locks.length;
    }

    /**
     * Intakes balls from lock1
     */
    public void intake() {
        move(Constants.INTAKE_SPEED, 0);
    }

    /**
     * Outtakes balls from lock1
     */
    public void outtake() {
        move(-Constants.INTAKE_SPEED, 0);
    }

    /**
     * Stops all locks
     */
    public void stop()
    {
        lock1.stopMotor();
        lock2.stopMotor();
        lock3.stopMotor();
        lock4.stopMotor();
        lock5.stopMotor();
    }

    /**
     * Stops intaking (lock 1)
     */
    public void stopIntake()
    {
        lock1.stopMotor();
    }

    /**
     * Outputs info to the SmartDashboard
     */
    @Override
    public void periodic() {
      //  SmartDashboard.putNumber("Balls in Correct Position", numBallsInCorrectPos);
        boolean[] beams = RobotContainer.irSystem.getValues();
        for(int i = 0; i < beams.length; i ++)
            SmartDashboard.putBoolean("Beam " + (i + 1), beams[i]);
        SmartDashboard.putBoolean("IR Sensor Override", RobotContainer.irSensorOveride);
    }
}