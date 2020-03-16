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


public class APP {
    private AnalogPotentiometer dial;
    private DigitalInput appSwitch;

    /**
     * Creates a new APP instance
     */
    public APP() {
        dial = new AnalogPotentiometer(RobotMap.DIAL_PORT_ID);
        appSwitch = new DigitalInput(RobotMap.APP_SWITCH_PORT_ID);
    }

    /**
     * Returns the raw value of the dial
     * 
     * @return the raw value of the dial
     */
    public double getRawDialPosition()
    {
        return dial.get();
    }

    /**
     * Returns the value of the dial, an int from 0 to 10
     * 
     * @return the value of the dial, an int from 0 to 10
     */
    public int getDialPosition() {
        double autoSwitch = dial.get();
        if(0.97 > autoSwitch && autoSwitch > 0.93 )
            return 9;
        if(0.07 > autoSwitch && autoSwitch > 0.02)
            return 1;

        return (int) Math.round(autoSwitch * 10);
    }

    /**
     * Returns true if the switch is pressed
     * 
     * @return true if the switch is pressed
     */
    public boolean isSwitchPressed()
    {
        return appSwitch.get();
    }
}
