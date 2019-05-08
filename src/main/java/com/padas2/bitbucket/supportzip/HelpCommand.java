package com.padas2.bitbucket.supportzip;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

public class HelpCommand implements ExecutableCommand {
    protected Options options;

    public HelpCommand(Options options) {
        this.options = options;
    }

    @Override
    public void run() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("SupportZipCLI", options);
    }
}
