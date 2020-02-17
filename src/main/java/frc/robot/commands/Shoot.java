/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

/**
 * Command to start and end shooter.
 */
public class Shoot extends CommandBase {

    public Shoot(){
        addRequirements(RobotContainer.shooter);
        addRequirements(RobotContainer.queuer);
    }

    @Override
    public void initialize(){

    }

    @Override
    public void execute() {
      RobotContainer.shooter.shoot();
      if (RobotContainer.shooter.isAtVelocity())
        RobotContainer.queuer.unload();
    }

    @Override
    public void end(boolean interrupted) {
      RobotContainer.shooter.stop();
      RobotContainer.queuer.stop();
    }

    @Override
    public boolean isFinished() {
      boolean[] sensorValues = RobotContainer.irSystem.getValues();
      for (boolean b : sensorValues)
        if (!b)
          return false;
      return true;
    }



}
