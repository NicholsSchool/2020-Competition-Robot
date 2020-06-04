# Code Base Setup 

There are a few things to keep in mind when you start a new robot project. Use the "WPILib: Create a new project" button to create the project and we use the command template for java. You will have to add the vendor libraries for the Phoenix talons and the NavX. 


**The following instruction is subject to change based on what CTRE does with their software.** The phoenix libaries can be installed by going to "WPILib: Manage Vendor Libraries" > "Install new libraries (online)" and then inserting this link: http://devsite.ctr-electronics.com/maven/release/com/ctre/phoenix/Phoenix-latest.json

For installing the NavX libraries, follow the instructions on their [website](https://pdocs.kauailabs.com/navx-mxp/software/roborio-libraries/java/). It will probably be easiest to follow the instructions under "Online Installation Method". 

Then in the codebase you will have to make sure our four important classes exist, and create them if they don't, RobotMap, Constants, RobotContainer and Robot. Same goes for our five packages, Subsystems, Sensors, Commands, Autonomous and Util. 