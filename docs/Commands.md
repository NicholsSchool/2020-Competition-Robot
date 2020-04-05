# Commands

In order for the robot to perform actions, we have to create commands. All commands must extends WPI's CommandBase class. There are 5 parts to every command we create, the Constructor, `initialize()`, `execute()`, `end()`, `isFinished()`. 

### The Constructor 
Almost all Commands use one or more subsystems. In order to make sure two commands don't use the same subsystem at the same time, we must inform the command of what subsystems it requires using `addRequirements()`. WPI's documentation of commands shows commands having Subsystems as parameters to their constructors. We do not write our commands this way. Instead of passing in the required subsystem as a parameter in the constructor, the constructor can just call the subsystem statically from RobotContainer. Here is an example of a constructor for  a Shoot command. 

	public Shoot() {
		// This commands requires the "shooter" subsystem
		addRequirements(RobotContainer.shooter); 	// Statically acesses shooter from RobotContainer
	}
	
### initialize()

This method runs once at the very beginning of the Command. It is not always needed to add code inside this method. It is generally used for resetting sensors or other values before the command does its action. 

### execute()

This method runs repeatedly until the commmand's `isFinished()` method returns true. This is where the code for the task of the task runs. It should call methods the subsystem has in order to manipulate its movements. Here is an example for a shoot Command. 

	public void execute() {
		RobotContainer.shooter.move(0.8);
	}
	
### isFinished()

This method runs repeatedly and controls when the Command should end. If this Command is supposed to run **while** a button is held, then this method should just return false. Also if this Command is supposed to run for a certain amount of time, then it should return false as well, but should be decorated later to set it to run for that amount of time (Decorating is explained below). Otherwise, this command should have a statement which will eventually become true to end it. **Always make sure the Command will eventually end before running it** Here is an example for a Shoot Command.

	public boolean isFinished() {
		// This will eventually return true when 5 balls are shot
		return RobotContainer.shooter.numberBallsShot() == 5;
	}
	
### end(boolean interrupted)

This method runs when after `isFinished()` returns true or if the command is interrupted. This method is responsible for stopping all moving parts and reseting any values if needed. Here is an example for a Shoot Command.

	public void end(boolean interrupted) {
		RobotContainer.shooter.stop();
	}
	
	
## Command Types and Command Decorators
As of 2020, WPILIB introduced a variety of features to make creating commands and using commands faster and easier. Read through their [docs](https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html) for more information. Be sure to read their tutorial about **Lamda Expressions**
and look online further if you still do not understand them. 