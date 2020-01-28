/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.Constants;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Shooter {
    private WPI_TalonFX shooter;

    public Shooter() {
        shooter = new WPI_TalonFX(RobotMap.SHOOTER_ID);
    }

    public void shoot(){
         move(Constants.SHOOTER_SPEED);
    }

    private void move(double SHOOTER_SPEED) {
        double speed;
        shooter.set(speed);
    }

    public void stop(){
        shooter.stopMotor();
    }

    @Override 
    public void periodic() {

    }


}
