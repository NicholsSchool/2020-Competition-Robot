/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.Arrays;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.music.Orchestra;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotMap;

/**
 * The Shooter contains motors that make the robot shoot.
 */
public class Shooter extends SubsystemBase {
    private WPI_TalonFX shooter;
    private Orchestra orchestra;

    private boolean isAtVelocity;

    /**
     * Crestes a new Shooter.
     */
    public Shooter() {

        shooter = new WPI_TalonFX(RobotMap.SHOOTER_ID);
        isAtVelocity = false;

        shooter.configFactoryDefault();
        shooter.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
        shooter.config_kF(0, Constants.SHOOTER_F);
        shooter.config_kP(0, Constants.SHOOTER_P);
        shooter.config_kI(0, Constants.SHOOTER_I);
        shooter.config_kD(0, Constants.SHOOTER_D);
        shooter.configOpenloopRamp(Constants.SHOOTER_RAMP_TIME);
        shooter.configClosedloopRamp(Constants.SHOOTER_RAMP_TIME);
        orchestra = new Orchestra(Arrays.asList(shooter), Constants.MUSIC_FILE);
    
    }

    public boolean isAtVelocity() {
        return isAtVelocity;
    }

    /**
     * starts the shooter
     */

    public void shoot() {
        setVelocity(Constants.SHOOT_VELOCITY);
        isAtVelocity = Math.abs( shooter.getSelectedSensorVelocity() - Constants.SHOOT_VELOCITY ) < Constants.SHOOTER_VELOCITY_THRESHOLD;
    }
    /**
     * stops the shooter's motor.
     */

    public void stop() {
        shooter.stopMotor();
        isAtVelocity = false;
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Desired Velocity", Constants.SHOOT_VELOCITY);
        SmartDashboard.putNumber("Shooter Velocity", shooter.getSelectedSensorVelocity());
        SmartDashboard.putNumber("Difference In Velocity", Constants.SHOOT_VELOCITY - shooter.getSelectedSensorVelocity());
    }

    private void setVelocity(double velocity) {
        shooter.set(ControlMode.Velocity, velocity);
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
