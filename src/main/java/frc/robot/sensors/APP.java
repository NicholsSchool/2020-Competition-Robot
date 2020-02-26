/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.sensors;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class APP {
    private AnalogPotentiometer dial;
    private DigitalInput appSwitch;

    public APP() {
        dial = new AnalogPotentiometer(RobotMap.DIAL_PORT_ID);
        appSwitch = new DigitalInput(RobotMap.APP_SWITCH_PORT_ID);
    }

    public double getRawDialPosition()
    {
        return dial.get();
    }

    public int getDialPosition() {
        double autoSwitch = dial.get();
        if(0.97 > autoSwitch && autoSwitch > 0.93 )
            return 9;
        if(0.07 > autoSwitch && autoSwitch > 0.02)
            return 1;

        return (int) Math.round(autoSwitch * 10);
    }

    public boolean isSwitchPressed()
    {
        return appSwitch.get();
    }
}
