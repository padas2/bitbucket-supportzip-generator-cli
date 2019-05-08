package com.padas2.bitbucket.supportzip;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

public class NoOptionsPassedCommand extends HelpCommand{
    public NoOptionsPassedCommand(Options options) {
        super(options);
    }

    @Override
    public void run() {
        System.out.println("Printing help and usage as no meaningful options have been passed");
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("SupportZipCLI", options);
    }
}
