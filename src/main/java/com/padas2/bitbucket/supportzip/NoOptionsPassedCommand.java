package com.padas2.bitbucket.supportzip;

import org.apache.commons.cli.Options;

public class NoOptionsPassedCommand extends HelpCommand{
    public NoOptionsPassedCommand(Options options) {
        super(options);
    }

    @Override
    public void run() {
        System.out.println("Printing usage as no meaningful options have been passed");
        printHelpUsing(options);
        printSampleUsage();
    }
}
