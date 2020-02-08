/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotMap;

/**
 * This class controls the intake subsystem on the robot.
 */
public class Intake extends SubsystemBase {

  private WPI_TalonSRX intake;

  /**
   * Creates a new Intake.
   */
  public Intake() 
  {
    intake = new WPI_TalonSRX(RobotMap.INTAKE_ID);
    intake.configFactoryDefault();
  }

  /**
   * Sets the intake motor values to INTAKE_SPEED, located in the Constants class.
   */
  public void takeIn() 
  {
    move(Constants.INTAKE_SPEED);
  }
  /**
   * Does the same thing as takeIn(), but in the other direction.
   */
  public void takeOut() 
  {
    move(-Constants.INTAKE_SPEED);
  }

  /**
   * Moves the motors.
   * 
   * @param speed - How fast the intake motors will turn.
   */
  private void move(double speed) 
  {
    intake.set(speed);
  }

  /**
   * Stops the motors.
   */
  public void stop() 
  {
    intake.stopMotor();
  }

  
  /**
   * Runs constantly while subsystem is in use.
   */
  @Override
  public void periodic() 
  {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Intake Currant", intake.getStatorCurrent());
  }
}
