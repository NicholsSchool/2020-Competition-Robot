/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
//import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotMap;

/**
 * The Shooter contains motors that make the robot shoot.
 */
public class Shooter extends SubsystemBase{
    private WPI_TalonFX shooter;    
    private WPI_TalonSRX lock5; 
    

    
/**
 * Crestes a new Shooter.
 */
    public Shooter() {

        

        shooter = new WPI_TalonFX(RobotMap.SHOOTER_ID);
        lock5 = new WPI_TalonSRX(RobotMap.LOCK_FIVE_MOTOR_ID);
        
        
     

        shooter.configFactoryDefault();
        lock5.configFactoryDefault();
        shooter.configOpenloopRamp(Constants.SHOOTER_RAMP_TIME);
        shooter.configClosedloopRamp(Constants.SHOOTER_RAMP_TIME);

        shooter.config_kF(0, Constants.SHOOTER_F);
        shooter.config_kP(0, Constants.SHOOTER_P);
        shooter.config_kI(0, Constants.SHOOTER_I);
        shooter.config_kD(0, Constants.SHOOTER_D);
        

    
    
    }
    
    

    /**
     * starts the shooter
     */

    public void shoot(){
       //  move(Constants.SHOOTER_SPEED);
       setVelocity(Constants.SHOOT_VELOCITY);

    }
    /**
     * moves the shooter.  
     * @param speed
     */

    private void move(double speed) {
        shooter.set(speed);
    }
     /**
         * stops the shooter's motor.
         */

    public void stop(){
        shooter.stopMotor();
    }

    @Override 
    public void periodic() {

    }

    private void setVelocity(double velocity){
        shooter.set(ControlMode.Velocity, velocity);
        

    }

   

    }








