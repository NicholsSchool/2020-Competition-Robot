/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.sql.ClientInfoStatus;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
   public static final double LEFT_MASTER_F = 0.2;
   public static final double LEFT_MASTER_P = 0.2;
   public static final double LEFT_MASTER_I = 0.0;
   public static final double LEFT_MASTER_D = 0.0;

   public static final double RIGHT_MASTER_F = 0.2;
   public static final double RIGHT_MASTER_P = 0.2;
   public static final double RIGHT_MASTER_I = 0.0;
   public static final double RIGHT_MASTER_D = 0.0;

   public static final double RAMP_TIME = 0.05;

   public static final int CONFIG_TIMEOUT = 100;

   public static final int TICKS_PER_INCH = 68;
      public static final double QUEUE_MOVE_SPEED = 1;
    public static final double DEQUEUE_WAIT_TIME = 2;
    public static final double DART_SPEED_MULTIPLIER = 1;
    public static final double SHOOTER_SPEED = 1;
    public static final double INTAKE_SPEED = 1;
  
    public static final double SHOOTER_F = 0.055;
    public static final double SHOOTER_P = 0.14;
    public static final double SHOOTER_I = 0;
    public static final double SHOOTER_D = 0;
    public static final double SHOOTER_RAMP_TIME = 1;
    public static final double SHOOTER_MAX_VELOCITY = 16300;
    public static final double SHOOT_VELOCITY = 0.8 * SHOOTER_MAX_VELOCITY;
  
    public static final double CWS_SPINNER_SPEED = 0.25;

    public static final String MUSIC_FILE = "megalovania.chrp";
    public static final boolean BREAK_SWITCH_ENGAGE = true;
    public static final boolean BREAK_SWITCH_DISENGAGE = !BREAK_SWITCH_ENGAGE;
    public static final boolean CLIMBER_EXTEND = true;
    public static final boolean CLIMBER_STOP = !CLIMBER_EXTEND;
    public static final double DRIVE_FAST_MODE = 1.0;
    public static final double DRIVE_SLOW_MODE = 0.5;


    public static final double METERS_PER_INCH = 0.023;
    public static final double DRIVE_TICKS_PER_REV = 4096;
    public static final double DRIVE_WHEEL_DIAMETER = 4.0; // inches
    public static final double INCHES_PER_TICK = Math.PI * DRIVE_WHEEL_DIAMETER / DRIVE_TICKS_PER_REV;
    public static final double METERS_PER_TICK = INCHES_PER_TICK * METERS_PER_INCH;


    // values for your robot.
    public static final double ksVolts = 1.05;
    public static final double kvVoltSecondsPerMeter = 0.08;
    public static final double kaVoltSecondsSquaredPerMeter = 0.0138;

    // Example value only - as above, this must be tuned for your drive!
    public static final double kPDriveVel = 0.65;

    public static final double kTrackwidthMeters = 20.6878 * METERS_PER_INCH;
    public static final DifferentialDriveKinematics kDriveKinematics = new DifferentialDriveKinematics(
            kTrackwidthMeters);

    public static final double kMaxSpeedMetersPerSecond = 1;
    public static final double kMaxAccelerationMetersPerSecondSquared = 1;

    public static final double kRamseteB = 2;
    public static final double kRamseteZeta = 0.7;

}
