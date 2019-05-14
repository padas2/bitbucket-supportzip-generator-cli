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
        formatter.printHelp("BitbucketSupportZipCLI", options);
    }

    protected void printSampleUsage() {
        System.out.println("\nSample usage :");
        System.out.println("java -jar bitbucket-suuportzip-generator-cli-0.0.2-SNAPSHOT.jar " +
                           "-gitServerUrl http://localhost:7990 " +
                           "-adminUser <admin-user> " +
                           "-adminPwd <admin-password> " +
                           "-showLogsDirInFileExplorer " +
                           "-flattenUnzipDir");
    }

    @Override
    public void run() {
        printHelpUsing(options);
        printSampleUsage();
    }
}
