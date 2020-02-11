/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.Arrays;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
//import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.music.Orchestra;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotMap;

/**
 * The Shooter contains motors that make the robot shoot.
 */
public class Shooter extends SubsystemBase {
    private WPI_TalonFX shooter;
    private WPI_TalonSRX lock5;
    private Orchestra orchestra;

    /**
     * Crestes a new Shooter.
     */
    public Shooter() {

        shooter = new WPI_TalonFX(RobotMap.SHOOTER_ID);
        lock5 = new WPI_TalonSRX(RobotMap.LOCK_FIVE_MOTOR_ID);

        shooter.configFactoryDefault();
        lock5.configFactoryDefault();
        shooter.configOpenloopRamp(1);

        orchestra = new Orchestra(Arrays.asList(shooter), Constants.MUSIC_FILE);

    }

    /**
     * starts the shooter
     */

    public void shoot() {
        move(Constants.SHOOTER_SPEED);
    }

    /**
     * moves the shooter.
     * 
     * @param speed
     */

    private void move(double speed) {
        shooter.set(speed);
    }

    /**
     * stops the shooter's motor.
     */

    public void stop() {
        shooter.stopMotor();
    }

    @Override
    public void periodic() {

    }

    public void playMusic() {

        orchestra.play();
    }

    public void stopMusic() {
        orchestra.stop();
    }

    public void pauseMusic() {
        orchestra.pause();
    }

}
