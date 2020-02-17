/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotMap;

public class Dart extends SubsystemBase {
  /**
   * Creates a new Dart.
   */
  
  private WPI_TalonSRX dart;

  /**
   * this instantiates the dart object
   */
  public Dart() {
    dart = new WPI_TalonSRX(RobotMap.DART);
    dart.configFactoryDefault();
    dart.configOpenloopRamp(Constants.DART_RAMP_TIME);
  }

  public void control(int pov)
  {
    if (pov == 315 || pov == 0 || pov == 45)
      move(Constants.DART_SPEED);
    else if (pov == 225 || pov == 180 || pov == 135)
      move(-Constants.DART_SPEED);
    else
      move(0);
  }
  
/**
 * this sets the dart to the speed inputed
 * @param speed dart movement speed
 */
  private void move(double speed) {
    dart.set(speed);
  }

  /**
   * this can be called apon to stop the dart object from moving
   */
  public void stop() {
    dart.stopMotor();
  }

  /**
   * this displays the eletrical current the dart is getting
   */
  @Override
  public void periodic() {
    SmartDashboard.putNumber("Dart Eletrical Current: ", dart.getStatorCurrent());
    SmartDashboard.putNumber("Battery Voltage: ", dart.getBusVoltage());
  }
}
