/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.util;

import edu.wpi.first.wpilibj2.command.button.Button;

/**
 * Add your docs here.
 */
public class POVButton extends Button{

    private XboxController controller;
    private int povValue;
    public POVButton( XboxController controller, int povValue)
    {
        this.controller = controller;
        this.povValue = povValue;
    }
    
    @Override
    public boolean get() {
        return controller.getPOV(povValue) != -1;
    }
}
