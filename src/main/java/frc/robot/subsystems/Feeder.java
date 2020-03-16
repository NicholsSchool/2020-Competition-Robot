/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotMap;

public class Feeder extends SubsystemBase {

    private WPI_TalonSRX feederMotor;
    /**
     * Creates a new Feeder instance
     */
    public Feeder() {
        feederMotor = new WPI_TalonSRX(RobotMap.FEEDER_MOTOR_ID);
        feederMotor.configFactoryDefault();
        feederMotor.setInverted(true);
    }

    /**
     * Spits out balls in the system
     */
    public void spitOut()
    {
        move(Constants.FEEDER_SPIT_OUT_SPEED);
    }

    /**
     * Feeds in balls to the system
     */
    public void feed()
    {
        move(Constants.FEEDER_FEED_SPEED);
    }

    /**
     * Moves the feeder motor at the given speed
     * 
     * @param speed the speed to move the feeder motor
     */
    public void move(double speed)
    {
        feederMotor.set(speed);
    }

    /**
     * Stops the feeder
     */
    public void stop()
    {
        feederMotor.stopMotor();
    }

     /**
     * Outputs info to the SmartDashboard
     */
    @Override
    public void periodic() {
    // This method will be called once per scheduler run
    }
}
