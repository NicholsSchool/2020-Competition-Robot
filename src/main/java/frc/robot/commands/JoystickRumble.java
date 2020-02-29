/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.util.Condition;

public class JoystickRumble extends CommandBase {
    private double rumbleTime;
    private double waitTime;
    private int joysticks;
    private Condition condition;

    private long startTime;
    private boolean shouldRumble;

    /**
     * Creates a new JoystickRumble.
     */
    public JoystickRumble(double rumbleTime, double waitTime, int joysticks, Condition c) {
        this.rumbleTime = rumbleTime * 1000;
        this.waitTime = waitTime * 1000;
        this.joysticks = joysticks;
        this.condition = c;
        // Use addRequirements() here to declare subsystem dependencies.

    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        startTime = System.currentTimeMillis();
        shouldRumble = true;
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if(!condition.isReady()) {
            shouldRumble = false;
        }

        if (shouldRumble && System.currentTimeMillis() - startTime > waitTime) {
            if (joysticks == 0)
                RobotContainer.c0.setRumble(RumbleType.kRightRumble, 1);
            if (joysticks == 1)
                RobotContainer.c1.setRumble(RumbleType.kRightRumble, 1);
            else {
                RobotContainer.c0.setRumble(RumbleType.kRightRumble, 1);
                RobotContainer.c1.setRumble(RumbleType.kRightRumble, 1);
            }
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        RobotContainer.c0.setRumble(RumbleType.kRightRumble, 0);
        RobotContainer.c1.setRumble(RumbleType.kRightRumble, 0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return System.currentTimeMillis() - startTime > rumbleTime;
    }
}
