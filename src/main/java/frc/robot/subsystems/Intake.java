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

  public void takeIn() 
  {
    move(Constants.INTAKE_SPEED);
  }

  public void takeOut() 
  {
    move(-Constants.INTAKE_SPEED);
  }

  private void move(double speed) 
  {
    intake.set(speed);
  }

  public void stop() 
  {
    intake.stopMotor();
  }

  @Override
  public void periodic() 
  {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Intake Currant", intake.getStatorCurrent());
  }
}
