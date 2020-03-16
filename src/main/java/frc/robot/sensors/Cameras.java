package frc.robot.sensors;

import frc.robot.Constants;

import org.opencv.core.Mat;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;

public class Cameras extends Thread {


    private CameraServer cs;
    private UsbCamera cam;

    private long timeOfLastFrame;

    /**
     * Creates a new Cameras instance
     */
    public Cameras() {
        cs = CameraServer.getInstance();
        cam = cs.startAutomaticCapture("cam0", 0);
        cam.setResolution(Constants.CAMERA_WIDTH, Constants.CAMERA_HEIGHT);
        cam.setFPS(Constants.FRAME_RATE);

        timeOfLastFrame = 0;
    }

    /**
     * Displays camera footage at a set FPS
     */
    @Override
    public void run() {
        CvSink sink = cs.getVideo();
        CvSource outputStream = cs.putVideo("Driver FPS Limited", Constants.CAMERA_WIDTH, Constants.CAMERA_HEIGHT);

        Mat frame = new Mat();

        while (!Thread.interrupted()) {
            if (sink.grabFrame(frame) == 0 || !readyForFrame()) {
                continue;
            }

            outputStream.putFrame(frame);
        }
    }

    private boolean readyForFrame() {
        if (System.currentTimeMillis() - timeOfLastFrame > Constants.STREAM_FRAME_TIME_MS) {
            timeOfLastFrame = System.currentTimeMillis();
            return true;
        }
        return false;
    }

}