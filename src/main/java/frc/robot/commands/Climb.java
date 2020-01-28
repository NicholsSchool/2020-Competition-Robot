/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class Climb extends CommandBase{
  
  public Climb() {
    addRequirements(RobotContainer.climb);       
  }

  // Called when the command is initially scheduled.
  public void initialize() {
      
  }

  // Called every time the scheduler runs while the command is scheduled.
  public void execute() {
      RobotContainer.climb.reach(speed);
  }

  // Called once the command ends or is interrupted.
  public void end() {
    RobotContainer.climb.stop();
  }

  // Returns true when the command should end.
  public boolean isFinished() {
    return false;
  }
}



