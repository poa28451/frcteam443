/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.freelance.sensors;

import edu.wpi.first.wpilibj.camera.AxisCamera;

/**
 */
public class Camera {

    public Camera() {
        initAxisCamera();
    }

    private void initAxisCamera() {
        AxisCamera cam = AxisCamera.getInstance();
        cam.writeResolution(AxisCamera.ResolutionT.k320x240);
        cam.writeBrightness(0);
    }
}
