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
        lStick = new JoystickButton(this, 9);
        rStick= new JoystickButton(this, 10);
        dpadUp = new POVButton(this, 0);
        dpadRight = new POVButton(this, 2);
        dpadDown = new POVButton(this, 4);
        dpadLeft = new POVButton(this, 6);
        lTrigger = new Trigger(this, 2);
        rTrigger = new Trigger(this, 3);
		
    }
    
    public JoystickButton a;
    public JoystickButton b;
    public JoystickButton x;
    public JoystickButton y;
    public JoystickButton lBumper;
    public JoystickButton rBumper;
    public JoystickButton select;
    public JoystickButton start;
    public JoystickButton lStick;
    public JoystickButton rStick;
    public Trigger rTrigger;
    public Trigger lTrigger;

    public POVButton dpadUp, dpadDown, dpadRight, dpadLeft;

    public double getLeftX()
    {
        return getRawAxis(0);
    }

    public double getLeftY()
    {
        return -getRawAxis(1);
    }

    public double getLeftTrigger()
    {
        return getRawAxis(2);
    }

    public double getRightTrigger()
    {
        return getRawAxis(3);
    }

    public double getRightX()
    {
        return getRawAxis(4);
    }

    public double getRightY()
    {
        return -getRawAxis(5);
    }

}