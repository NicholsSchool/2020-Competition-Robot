/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.sensors;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class StringPot {
    private AnalogPotentiometer pot;
    public StringPot()
    {
        pot = new AnalogPotentiometer(RobotMap.STRING_POT_ID);
    }
}
