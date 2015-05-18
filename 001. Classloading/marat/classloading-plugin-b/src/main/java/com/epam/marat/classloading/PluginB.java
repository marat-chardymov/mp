package com.epam.marat.classloading;

/**
 * Created by nobody on 17.05.2015.
 */
public class PluginB implements Plugin {
    @Override
    public void doWork() {
        System.out.println("PluginB is working");
    }
}
