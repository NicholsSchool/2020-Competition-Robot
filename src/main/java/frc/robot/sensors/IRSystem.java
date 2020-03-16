/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.sensors;


import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;


public class IRSystem {
    private NetworkTableEntry beamsEntry;
    
    /**
     * Creates a new IRSystem instance
     */
    public IRSystem()
    {
        beamsEntry = NetworkTableInstance.getDefault().getTable("SmartDashboard").getEntry("BeamArray");
    }

    /**
     * Returns a boolean array of sensor values, true indicating empty location,
     * false indicating ball in location
     * 
     * @return a boolean array of sensor values, true indicating empty location,
     *         false indicating ball in location
     */
    public boolean[] getValues()
    {
        return beamsEntry.getBooleanArray(new boolean[5]);
    }
}
