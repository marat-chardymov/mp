package com.epam.marat.classloading;

import com.epam.marat.classloading.Plugin;

/**
 * Created by nobody on 17.05.2015.
 */
public class PluginA implements Plugin{
    @Override
    public void doWork() {
        System.out.println("PluginA is working");
    }
}
