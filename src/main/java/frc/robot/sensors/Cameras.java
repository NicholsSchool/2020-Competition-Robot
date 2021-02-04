package frc.robot.sensors;

import frc.robot.Constants;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;

public class Cameras
{

  private UsbCamera firstCam;

  public Cameras() {
    CameraServer cs = CameraServer.getInstance();
    // start usb cameras
    firstCam = cs.startAutomaticCapture("cam0", 0);
    
    // settings for cam 1
    firstCam.setResolution(Constants.CAMERA_WIDTH, Constants.CAMERA_HEIGHT);
    firstCam.setFPS(Constants.CAMERA_FRAME_RATE);
  }

}