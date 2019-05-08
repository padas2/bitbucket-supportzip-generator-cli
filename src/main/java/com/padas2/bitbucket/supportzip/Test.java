package com.padas2.bitbucket.supportzip;

import java.awt.*;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Test {
    public static void main(String [] args) {
        try {
            Path sampleFile = Paths.get("C:\\Users\\padas2\\workSpaces\\duplicate\\vagrant-ubuntu-desktop");
            Desktop.getDesktop().browse(sampleFile.toUri());
        } catch (Exception e) {
        }
    }
}
