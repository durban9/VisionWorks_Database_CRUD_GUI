package com.viewsAndControllers;

import com.models.Developer;

public interface ControllerClass {

    public default void preloadData(Developer developer) {

    }
}
