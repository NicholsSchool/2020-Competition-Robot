# Troubleshooting

There is no specific answer or process that applies to every form of issues you will encounter when working with robots. This article will go over some types of issues and ways you can go about solving those.

## Programming Issues

### Null Pointers
	
There can be cases where your code syntactically is completely correct but when you try to run it or as you are running it, the driver station says "No Robot Code" or throws an error. The most common reason behind this is due to a null pointer exception in your code. This means you are trying to use an object somewhere in your code that had never been instantiated or hasn't been instantiated _yet_. The driver station will normally print out an error and tell you which line in which file contains the error. See which objects are being used on that line, and check to see that those objects are being instantiated at some time. 

For example, if an intake command is using a Gripper subsystem instance, but a null pointer exception is being thrown on the line where you are trying to move the Gripper, it is likely that you forgot to instaniate the Gripper instance in the constructor of RobotMap.

If you do have code in your program that instantiates the object which is giving you a null pointer exception, then what could be happening is that the code which is giving you an error is happening before the code which instaniates the object. 

For example, a subsystem may need to be in a specific state when the robot starts up, such as a gripper needs to open up, and you could be using a sensor to make sure that the gripper is in that state. The method to make the gripper open could be being used as one of the first lines in the gripper's constructor, and that method uses some sensor to check if it is in the correct state. If you instantiate the gripper before the sensor, then even though you do have code that instantiates the sensor, it comes after the code in the gripper class which is using the sensor. So to fix this, you would have to instantiate the sensor before the gripper. (This is kind of a bad code in general, because it relies on you having to remember that you have to instantiate the sensor before the gripper. Better practice would be either to have the sensor be a parameter for the gripper's constructor or have the sensor be an instance variable that gets instantiated and lives within the gripper object).


## Motor/Sensor Issues

You will very likely experience at least once per robot, an issue where you are unable to move a motor through code or unable to get values from a sensor. It is always smart to double check for if this is a code issue rather than a wiring issue. 

For motors, check to see that you can move the troublesome motor by a basic `.set()` to set power, you can write this in `testPeriodic()` in the Robot class and run it by setting the Driver Station to the test mode. The talon that controls the motor should light up green (if it's trying to go forwards) or red (if it's trying to go backwards). Make sure you are calling `.configFactoryDefault()` before setting power to it. If it begins to work after you add `.configFactoryDefault()` before the set power then that means some setting you are applying to the talon in your regular code is making it not move. If it is still not working, double check to make sure you have the correct talon ID set for the motor. You can also try running a different motor using the same code. If none of these tests show any results, then you should have electrical take a look at the wiring. 

For sensors, there is a variety of troubleshooting procedures. If you are not getting any values or if you are getting meaningless values, check to see if you are using the correct class for the sensor. If there are other potential classes tht can be used to program the sensor, try those. Make sure you have correct the port ID for the sensor. If you can, try changing the sensor's port physically and in your code. Try getting another sensor and plugging it into the current one's spot to see if you get any values. 


## Logic Issues

The code may be running and moving the robot, but the robot is not doing exactly what you want it to. There is no one solution to logic issues, as each robot has to perform unique tasks for the given year. To fix your logic, break it up the same way commands are broken up. What is supposed to happening in the beginning? What is supposed to happen continuously? How should the robot know when to stop? And what should the robot do when it stops? When trying to fix logic issues, identify which part is not working as expected. Go through that section of your code line by line and translate out loud what the code says should be happening and then compare it to what is actually happening. If you read through your code enough times, and you will likely realize you misunderstood your code, and the robot is actually doing exactly what the code says to do. This epiphany will either result in you thinking of a few statements that need to be added or removed or you realizing you need to rewrite your whole logic. It is always a good idea to get another student or mentor to work it out with you. Explaining the problem or getting another head is normally enough to think of a solution. 