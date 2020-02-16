#Constants

Every Robot has its own set of tuned values. For example, subsystem speeds, PID values, encoder tick values, speed buffers, etc. Any value that could ever change or that needs a label, should be stored in Constants. Just like RobotMap, we have this class becuase if a change is ever made to the Robot, or the drivers want it to behave differently, we only need to change one area of the code code, the Constants class.  


## **Examples**

***In DriveTrain Class***

    //BAD PRACTICE
    public Shooter() {
    	/*Other methods and objects*/	
    	public void shoot() {
    		move(0.73);
    	}
    }
    
-

    //Good Practice
    public Shooter() {
        /*Other methods and objects*/	
    	public void shoot() {
    		move(Constants.SHOOTER_SHOOT_SPEED);
    	}
    }
***In Constants Class***

    public Constants { 
    	public static final double SHOOTER_SHOOT_SPEED = 0.73;
    }
	
