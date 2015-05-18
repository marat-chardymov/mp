package com.epam.marat.classloading;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by nobody on 17.05.2015.
 */
public class MyClassLoader extends ClassLoader {


    public MyClassLoader(ClassLoader parent) {
        super(parent);
    }

    public Class loadClass(String name, File file) {
        try {
            // trying to load class using parent classloader
            Class<?> aClass = super.loadClass(name);
            if (aClass != null) {
                return aClass;
            }
        } catch (ClassNotFoundException e) {
            try {
                byte[] classData = Files.readAllBytes(file.toPath());
                return defineClass(name,
                        classData, 0, classData.length);
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
        return null;
    }

    public List<Class> loadClassesFromFolder(File folder) {
        List<Class> classes = new ArrayList<Class>();
        Path basePath = Paths.get(folder.getPath());
        Collection<File> files = FileUtils.listFiles(folder, new RegexFileFilter("^.*\\.(class)$"), TrueFileFilter.INSTANCE);
        for (File file : files) {
            Path absolutePath = Paths.get(file.getPath());
            System.out.println(absolutePath);
            Path relativePath = basePath.relativize(absolutePath);
            String className = pathToPackage(relativePath.toString());
            Class aClass = loadClass(className, file);
            classes.add(aClass);
        }
        return classes;
    }

    private static String pathToPackage(String path) {
        String wPath = path.replace("/", ".");
        String uPath = wPath.replace("\\", ".");
        // here 6 is number of letters in .class extension
        return uPath.substring(0, uPath.length() - 6);
    }
}
