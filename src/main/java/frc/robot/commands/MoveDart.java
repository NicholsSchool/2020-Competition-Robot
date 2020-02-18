/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class MoveDart extends CommandBase {
    /**
     * Creates a new MoveDart.
     */
    public MoveDart() {

        addRequirements(RobotContainer.dart);

    }

    // this does nothing of my knowledge
    @Override
    public void initialize() {
    }

    // this takes the joystickinput and sets the dart to that speed
    @Override
    public void execute() {
        RobotContainer.dart.control(RobotContainer.c1.getPOV());
    }

    // this stops the dart from moving
    @Override
    public void end(boolean interrupted) {

        RobotContainer.dart.stop();

    }

    // this does nothing of my knowledge
    @Override
    public boolean isFinished() {
        return false;
    }
}
