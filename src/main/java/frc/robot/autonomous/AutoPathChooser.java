/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.AutoShoot;
import frc.robot.commands.VisionPIDTurn;

/**
 * Add your docs here.
 */
public class AutoPathChooser {
    private static final double BB_DRIVE_SPEED = 0.55;
    private static final double BB_DRIVE_DISTANCE = -36;

    private static final int AUTO_SHOOT_TIMEOUT = 10;


    private static final int BALLS_IN_SYSTEM = 3;
    private static final int TIMED_AUTO_SHOOT_TIMEOUT = (int)(Constants.TIMED_SHOOT_GAP * BALLS_IN_SYSTEM) + 2;

    public static CommandBase getPath()
    {
        int dial = RobotContainer.app.getDialPosition();
        switch(dial)
        {
            case 0:
                return getShootPath();
            case 1: 
                return getShootPath2();
            case 2: 
                return getShootPath3();
            case 3:
                return getShootPath4();
            case 4:
                return layupPath();
            case 5:
                return new TimedAutoShoot(BALLS_IN_SYSTEM);
            default:
                return new BBDrive(BB_DRIVE_DISTANCE, BB_DRIVE_SPEED);
        }
    }

    private static CommandBase layupPath()
    {
        return new BBDrive(10*12, 0.75).withTimeout(5).alongWith(new PIDDartMove(270).withTimeout(5))
        .andThen(new TimedAutoShoot(BALLS_IN_SYSTEM).withTimeout(TIMED_AUTO_SHOOT_TIMEOUT));
    }

    /*
        No vision align, at an angle, a little in front of line
    */
    private static CommandBase getShootPath4()
    {
        int dartMoveValue = 345;
        if (RobotContainer.app.isSwitchPressed())
            RobotContainer.irSensorOveride = true;

        if (RobotContainer.distanceSensor.isRangeValid())
            return new PIDDartMove(dartMoveValue).withTimeout(5)
                    .andThen(new TimedAutoShoot(BALLS_IN_SYSTEM).withTimeout(TIMED_AUTO_SHOOT_TIMEOUT))
                    .andThen( new BBDrive(BB_DRIVE_DISTANCE, BB_DRIVE_SPEED).withTimeout(4) );
        else
            return new DRArmMove(false).withTimeout(0.5)
                    .andThen(new TimedAutoShoot(BALLS_IN_SYSTEM).withTimeout(TIMED_AUTO_SHOOT_TIMEOUT))
                    .andThen( new BBDrive(BB_DRIVE_DISTANCE, BB_DRIVE_SPEED).withTimeout(4));
    }

    /*
     * Used for vision align and directly at an angle for the goal 
     */
    private static CommandBase getShootPath3()
    {
        int PID_DART_MOVE_VALUE = 345;
        return getShootFirstPath(PID_DART_MOVE_VALUE);
    }


    /*
    Used for vision align and directly in front of goal
    */
    private static CommandBase getShootPath2()
    {
        int PID_DART_MOVE_VALUE = 355; 
        return getShootFirstPath(PID_DART_MOVE_VALUE);
  
    }

    private static CommandBase getShootFirstPath(int dartMoveValue)
    {
      if (RobotContainer.app.isSwitchPressed())
            RobotContainer.irSensorOveride = true;

        if (RobotContainer.distanceSensor.isRangeValid())
            return  new VisionPIDTurn().withTimeout(2).alongWith(new PIDDartMove(dartMoveValue).withTimeout(5))
                    .andThen(new TimedAutoShoot(BALLS_IN_SYSTEM).withTimeout(TIMED_AUTO_SHOOT_TIMEOUT))
                    .andThen(new BBDrive(BB_DRIVE_DISTANCE, BB_DRIVE_SPEED));
        else
            return new VisionPIDTurn().withTimeout(2).alongWith(new DRArmMove(false).withTimeout(0.5))
                    .andThen(new TimedAutoShoot(BALLS_IN_SYSTEM).withTimeout(TIMED_AUTO_SHOOT_TIMEOUT))
                    .andThen(new BBDrive(BB_DRIVE_DISTANCE, BB_DRIVE_SPEED));
    }


    private static CommandBase getShootPath()
    {
        int PID_DART_MOVE_VALUE = 345;
        if(RobotContainer.app.isSwitchPressed())
            RobotContainer.irSensorOveride = true;

        if (RobotContainer.distanceSensor.isRangeValid())
            return new BBDrive(
                    BB_DRIVE_DISTANCE, BB_DRIVE_SPEED)
            .andThen(new VisionPIDTurn().withTimeout(2)).alongWith(new PIDDartMove(PID_DART_MOVE_VALUE).withTimeout(5))
            .andThen(new AutoShoot().withTimeout(AUTO_SHOOT_TIMEOUT));
        else
            return new BBDrive(
                    BB_DRIVE_DISTANCE, BB_DRIVE_SPEED).andThen(new VisionPIDTurn().withTimeout(2))
                    .alongWith(new DRArmMove(false).withTimeout(0.5)).andThen(new AutoShoot().withTimeout(
                                    AUTO_SHOOT_TIMEOUT));
    }
}
