package frc.robot.util;

import edu.wpi.first.wpilibj2.command.button.Button;

public class Trigger extends Button
{
    private XboxController controller;
    private int axisID;

    public Trigger( XboxController controller, int axisID )
    {
        this.controller = controller;
        this.axisID = axisID;
    }

    @Override
    public boolean get()
    {
        return controller.getRawAxis( axisID ) > 0.75;
    }
}