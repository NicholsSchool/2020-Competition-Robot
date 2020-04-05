#  Robot Setup

Before deploying code to the robot, there are multiple steps that must be taken to update and configure the robot. Most of these steps require following instructions written by WPI, this article will link to those steps, as well as offer insight our team has gain through the years if applicable. 

## Driver Station Setup

Follow the directions given by WPI [here](https://docs.wpilib.org/en/latest/docs/getting-started/getting-started-frc-control-system/frc-game-tools.html) for how to set up the driver station. Make sure to always have at least **_TWO_ DRIVERSTATIONS** setup and ready for use. 

## RoboRio Setup 

Follow the directions given by WPI [here](https://docs.wpilib.org/en/latest/docs/getting-started/getting-started-frc-control-system/imaging-your-roborio.html). Read all the instructions carefully before actually doing anything. Make sure to use **the correct cables** and **DO NOT TURN OFF THE RIO OR DISCONNECT A CABLE DURING THIS PROCESS**

## Router Setup

Again, read all instructions before beginning, instructions by WPI are [here](https://docs.wpilib.org/en/latest/docs/getting-started/getting-started-frc-control-system/radio-programming.html). Some years of competition have reused the same firmware as the prior year, so updating a router's firmware isn't always necessary, so check what the router's firmware is and what it is supposed to be to save time. Do not worry if you try updating a router's firmware even though it already has that version, it shouldn't damage the device. The **configuration** does need to be done each year. If you are unable to configure or update firmware of a router on a particular laptop, try using a different laptop, this has fixed our problems in the past. 

Important addresses to note for acessing the router once configured are:

* 10.TE.AM.2 (For us, **10.49.30.2**)

* roboRIO-####-FRC.local (For us, **roboRIO-4930-FRC.local**)

## Talon Firmware Updates

First thing the team must do is make sure that each driver station has the phoenix tuner. Our team's driverstations should already have the phoenix tuner installed, it may be beneficial to remove the tuner application and download the most recent version. These are the [instructions](https://phoenix-documentation.readthedocs.io/en/latest/ch05_PrepWorkstation.html#what-to-download-and-why) for downloading the phoneix tuner. 

To update the firmware of a talon, falcon, or victor, first download the latest firmware version from the [CTRE Website](http://www.ctr-electronics.com/control-system/motor-control.html) by selecting each device you want to update, navigating to the "Tech Resources" tab and selecting the most latest firmware. Save all firmware on a specific folder in the driverstation for good organization. 

Connect the driver station to the roboRIO using a usb A - usb B cable. In the phoenix tuner, select "Install Phoenix Library/Diagnostics" to set up communication between the laptop and roboRIO. Once communication is setup, select the "CAN Devices" tab. Then select a talon, falcon, or victor motor, and then in the box which says "Field-Upgrade Device Firmware" in order to select the new firmware file, select the "..." button and navigate to where you saved the file. Once you have selected the file, check the box that says "Update all devices" and then select "Update Device." This should update all motor controllers of the same type to the selected firmware. Repeat this firmware installation with any remaining talon, falcon, or victors. 


## Talon ID-ing

It is extremely important to be aware of the ID for any particular motor controller. On paper, assign each physical motor controller on the robot an ID in an organized fashion. Our team generally has had a board of two columns of motor controllers, with the IDs of each controller starting at 0 at the bottom of the board increasing value upwards, with one column containing even IDs and the other odd IDs. 

Once you have decided what the ID of each motor controller should be, it is necessary to set the IDs with the Phoenix tuner. Connect the driver station to the roboRIO using a usb A - usb B cable. In the phoenix tuner, select "Install Phoenix Library/Diagnostics" to set up communication between the laptop and roboRIO. Once communication is setup, select the "CAN Devices" tab. Each motor controller should already have an ID given to it. To know which motor controller in the Phoenix tuner corresponds to the physical motor controller on the robot, select any motor controller and click "Blink". This will cause a motor controller on the Robot to have its lights blink faster than normal for a few seconds. That device corresponds to the controller you have selected. You can now change the ID of that device to what you had decided earlier by inputting the value in the "Change the ID" box and then selecting "Change ID." **(NOTE: To ensure that every device has a unique ID, add 20 to whatever ID you want the motor controller to be. So if you want the ID to be 5, set it to 25)** 

On startup, it can be possible that not all motor controllers will appear on the screen. This can be because some devices may have the exact same ID as each other, resulting in only one appearing on screen, just change the ID of that device to what it is supposed to be, and the other will appear. Also, it can also be possible that certain devices are set to an ID that a different device is supposed to have. Change the incorrect device to an ID to a value that will not actually be used by any device, and then go about giving each device its correct ID. 

Also, if a device has a name other than "Talon SRX (Device ID: #)" (Or Victor or Falcon), then the easiest way to give it that default name is to delete the content inside the "Change the name" text box and then select "Change Name." This will result in the device having the default name. 


