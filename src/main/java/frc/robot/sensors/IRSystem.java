/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.sensors;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * Add your docs here.
 */
public class IRSystem {
    private NetworkTableEntry beamsEntry;
    
    public IRSystem()
    {
        beamsEntry = NetworkTableInstance.getDefault().getTable("SmartDashboard").getEntry("BeamArray");
    }

    public boolean[] getValues()
    {
        return beamsEntry.getBooleanArray(new boolean[5]);
    }
}
