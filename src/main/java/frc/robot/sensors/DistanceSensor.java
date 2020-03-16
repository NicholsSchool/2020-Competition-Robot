/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.sensors;

import com.revrobotics.Rev2mDistanceSensor;
import com.revrobotics.Rev2mDistanceSensor.Port;
import com.revrobotics.Rev2mDistanceSensor.Unit;
import com.revrobotics.Rev2mDistanceSensor.RangeProfile;

import frc.robot.Constants;

public class DistanceSensor {

    private Rev2mDistanceSensor distanceSensor;

    /**
     * Creates a new DistanceSensor instance
     */
    public DistanceSensor()
    {
        distanceSensor = new Rev2mDistanceSensor(Port.kOnboard, Unit.kMillimeters, RangeProfile.kHighAccuracy);
        distanceSensor.setAutomaticMode(true);
    } 

    /**
     * Returns the distance away from arm detected by sensor
     * 
     * @return the distance away from arm detected by sensor
     */
    public double getDistance()
    {
        return distanceSensor.getRange();
    }

    /**
     * Returns true if the distance sensor is getting valid values
     * 
     * @return true if the distance sensor is getting valid values
     */
    public boolean isRangeValid()
    {
        return distanceSensor.isRangeValid();
    }
}
