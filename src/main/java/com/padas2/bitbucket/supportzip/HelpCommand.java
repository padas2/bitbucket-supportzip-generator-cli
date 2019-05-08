package com.padas2.bitbucket.supportzip;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class HelpCommand implements ExecutableCommand {
    protected Options options;

    public HelpCommand(Options options) {
        this.options = options;
    }

    protected void printHelpUsing(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("SupportZipCLI", options);
    }

    @Override
    public void run() {
        printHelpUsing(options);
    }
}
