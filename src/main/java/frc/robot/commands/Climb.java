/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
// creates class Climb; Contains methods which allow the robot to climb
public class Climb extends CommandBase{ 
// gives the requirements for the method Climb 
  public Climb() {
    addRequirements(RobotContainer.climb);     
  }

  // Calls Climb to begin begins robot climb
  public void initialize() {
      
  }

  // Called after initialize infinite times until end to make the robot climb
  public void execute() {
      RobotContainer.climb.climb();
  }

  // ends robot climb when robot reaches its final point
  public void end(boolean interupted) {
    RobotContainer.climb.stop();
  }

  // ends climb code
  public boolean isFinished() {
    return false;
  }
}



