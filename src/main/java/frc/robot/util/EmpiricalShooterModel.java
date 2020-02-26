/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.util;

/**
 * Add your docs here.
 */
public class EmpiricalShooterModel {

    public static final int AUTO_DART_POSITION = 330;
    public static final int TRENCH_DART_POSITION = 350;

    public static final int M = 1;
    public static final int B = 270;

    /**
     * Gets the dart position necessary to shoot from the specified distance.
     * 
     * @param distance the distance in inches
     * @return the dart position in millimeters.
     */
    public static int get(double distance) {
        return (int) (M * distance + B);
    }
}
