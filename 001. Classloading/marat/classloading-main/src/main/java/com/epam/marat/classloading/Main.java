package com.epam.marat.classloading;

import java.io.File;
import java.util.List;

/**
 * Created by nobody on 17.05.2015.
 */
public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        MyClassLoader myClassLoader = new MyClassLoader(Main.class.getClassLoader());
        try {
            File folder = new File(args[0]);
            List<Class> classes = myClassLoader.loadClassesFromFolder(folder);
            for (Class aClass : classes) {
                if (Plugin.class.isAssignableFrom(aClass)) {
                    System.out.println(aClass.getName() + "class has been loaded by " + aClass.getClassLoader());
                    Plugin pluginImpl = (Plugin) aClass.newInstance();
                    pluginImpl.doWork();
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
