/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Dart extends SubsystemBase {
  /**
   * Creates a new Dart.
   */
  
  private WPI_TalonSRX dart;

  public Dart() {

    dart = new WPI_TalonSRX(RobotMap.DART);

    dart.configFactoryDefault();

  }

  public void move(double speed) {

    dart.set(speed);

  }

  public void stop() {

    dart.stopMotor();

  }

  @Override
  public void periodic() {

    SmartDashboard.putNumber("Dart Eletrical Current: ", dart.getStatorCurrent());
    SmartDashboard.putNumber("Battery Voltage: ", dart.getBusVoltage());5
    
  }
}
