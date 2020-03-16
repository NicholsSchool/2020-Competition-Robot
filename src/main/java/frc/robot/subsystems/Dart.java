/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.RobotMap;

public class Dart extends SubsystemBase {

    private WPI_TalonSRX dart;

    /**
     * Creates a new Dart instance
     */
    public Dart() {
        dart = new WPI_TalonSRX(RobotMap.DART);
        dart.configFactoryDefault();
        dart.configOpenloopRamp(Constants.DART_RAMP_TIME);
    }

     
    /**
     * Sets the dart in brake mode, making it resist changes in position when no power is applied
     * 
     * @param brake boolean, true to set brake mode, false for coast mode
     */
    public void setBrakeMode(boolean brake)
    {
        if(brake)
            dart.setNeutralMode(NeutralMode.Brake);
        else
            dart.setNeutralMode(NeutralMode.Coast);
    }

    /**
     * Controls the dart movement based off pov input 
     * 
     * @param pov int value of joystick pov
     */
    public void control(int pov) {
        if (pov == 315 || pov == 0 || pov == 45)
            move(Constants.DART_SPEED);
        else if (pov == 225 || pov == 180 || pov == 135)
            move(-Constants.DART_SPEED);
        else
            move(0);
    }

    /**
     * Sets the dart to the speed inputed
     * 
     * @param speed dart movement speed
     */
    public void move(double speed) {
        dart.set(speed);
    }

    /**
     * Stops the dart
     */
    public void stop() {
        dart.stopMotor();
    }

    /**
     * Outputs info to the SmartDashboard
     */
    @Override
    public void periodic() {
        SmartDashboard.putNumber("Arm Sensor Distance", RobotContainer.distanceSensor.getDistance());
        SmartDashboard.putBoolean("Arm Sensor Is Valid", RobotContainer.distanceSensor.isRangeValid());
    }
}
