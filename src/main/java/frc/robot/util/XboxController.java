package frc.robot.util;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class XboxController extends Joystick{

	public XboxController(int port) {
        super(port);
        
        a = new JoystickButton(this, 1);
        b = new JoystickButton(this, 2);
        x = new JoystickButton(this, 3);
        y = new JoystickButton(this, 4);
        lBumper = new JoystickButton(this, 5);
        rBumper = new JoystickButton(this, 6);
        select = new JoystickButton(this, 7);
        start = new JoystickButton(this, 8);
        lStickDown = new JoystickButton(this, 9);
        rStickDown = new JoystickButton(this, 10);
		
    }
    
    public JoystickButton a;
    public JoystickButton b;
    public JoystickButton x;
    public JoystickButton y;
    public JoystickButton lBumper;
    public JoystickButton rBumper;
    public JoystickButton select;
    public JoystickButton start;
    public JoystickButton lStickDown;
    public JoystickButton rStickDown;
    public Trigger rTrigger;
    public Trigger lTrigger;

    public double getLeftX()
    {
        return getRawAxis(0);
    }

    public double getLeftY()
    {
        return getRawAxis(1);
    }

    public double getTriggerX()
    {
        return getRawAxis(2);
    }

    public double getTriggerY()
    {
        return getRawAxis(3);
    }

    public double getRightX()
    {
        return getRawAxis(4);
    }

    public double getRightY()
    {
        return getRawAxis(5);
    }

}