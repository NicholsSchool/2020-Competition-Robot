#Robot

The way all the code we write actually gets run, is through Robot. You can think of the class as being the `public static void main(String[] args)` of the code. In the years prior to 2020, we would instantiate our Subsystems and our created Sensors in Robot itself, but as of 2020, WPI has chosen to move away from writing programs like that. Instead, that task is handled by RobotContainer. In all Robot projects moving forward, the Robot class does not really need to be adjusted. For testing purpose, such as testing each talon individually, the code can be run in the`testPeriodic()` method. 

### **Reminders**
It is very important that `CommandScheduler.getInstance().run();` is called inside of `robotInit()` other


    @Override
    public void robotPeriodic() {
	    // Runs the Scheduler. This is responsible for polling buttons, adding
	    // newly-scheduled
	    // commands, running already-scheduled commands, removing finished or
	    // interrupted commands,
	    // and running subsystem periodic() methods. This must be called from the
	    // robot's periodic
	    // block in order for anything in the Command-based framework to work.
	    CommandScheduler.getInstance().run(); // <--- MAKE SURE THIS IS THERE
    }
