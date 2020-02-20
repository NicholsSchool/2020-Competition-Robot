package frc.robot.sensors;

import frc.robot.Constants;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Cameras
{

  private UsbCamera firstCam;
  private VideoSink server;

  public Cameras() {
    CameraServer cs = CameraServer.getInstance();
    // start usb cameras
    firstCam = cs.startAutomaticCapture("cam0", 0);
    
    // settings for cam 1
    firstCam.setResolution(Constants.CAMERA_WIDTH, Constants.CAMERA_HEIGHT);
    firstCam.setFPS(Constants.FRAME_RATE);
    
    // cs.addCamera(firstCam);
    
    // server = cs.getServer();
    // server.setSource(firstCam);

  }

}