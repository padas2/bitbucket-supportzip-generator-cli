package com.padas2.bitbucket.supportzip;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

public class NoOptionsPassedCommand extends HelpCommand{
    public NoOptionsPassedCommand(Options options) {
        super(options);
    }

    @Override
    public void run() {
        System.out.println("As no options have been passed, simply printing help");
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("SupportZipCLI", options);
    }
}
