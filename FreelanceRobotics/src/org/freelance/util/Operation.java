/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.freelance.util;

import org.freelance.Main;

/**
 * Operation.java
 *  -Definition For A Local Operation (Tele, Auto)
 * @author Freelance Robotics (Hadyn Fitzgerald)
 */
public interface Operation {

    /**
     * Initialization Tasks For The Operation
     */
    public abstract void init();

    /**
     * Periodic Process For The Operation
     * @param main
     */
    public abstract void process(Main main);

    /**
     * Reset Function For The Operation
     */
    public abstract void reset();

}