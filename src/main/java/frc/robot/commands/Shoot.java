/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

/**
 * Command to start and end shooter.
 */
public class Shoot extends CommandBase {
    private long timeStarted;

    public Shoot() {
        addRequirements(RobotContainer.shooter);
        addRequirements(RobotContainer.queuer);
    }

    @Override
    public void initialize() {
        timeStarted = System.currentTimeMillis();
    }

    @Override
    public void execute() {
        RobotContainer.shooter.shoot();
        System.out.println("Shooting Time: " + (System.currentTimeMillis() - timeStarted));
        if (RobotContainer.shooter.isAtVelocity() || System.currentTimeMillis() - timeStarted > Constants.SHOOT_TIMEOUT * 1000) {
            if(RobotContainer.shooter.isAtVelocity())
                timeStarted = System.currentTimeMillis();
            RobotContainer.queuer.unload();
        } else
            RobotContainer.queuer.stop();
    }

    @Override
    public void end(boolean interrupted) {
        RobotContainer.shooter.stop();
        RobotContainer.queuer.stop();
        // RobotContainer.c0.setRumble(RumbleType.kRightRumble, 1);
        // RobotContainer.c1.setRumble(RumbleType.kRightRumble, 1);
    }

    @Override
    public boolean isFinished() {
        if (RobotContainer.irSensorOveride)
            return false;
        boolean[] sensorValues = RobotContainer.irSystem.getValues();
        for (boolean b : sensorValues)
            if (!b)
                return false;
        return true;
    }

}
