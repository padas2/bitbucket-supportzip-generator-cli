package com.padas2.bitbucket.supportzip;

import org.apache.commons.cli.*;

public class BitbucketSupportZipCLI {
    private static Options getDefaultOptions() {
        Options options = new Options();
        options.addOption("gitServerUrl", true, "Bitbucket server url");
        options.addOption("adminUser", true, "Admin user slug");
        options.addOption("adminPwd", true, "Admin user password");
        options.addOption("showLogsInExplorer", false, "Open Unzipped dir in File Explorer");
        options.addOption("flattenUnzipDir", false, "Flatten unzipped dir from hierarchial to flat structure");
        options.addOption("help", false, "Help");
        return options;
    }

    public static void main(String[] args) throws ParseException {
        Options options = getDefaultOptions();

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse( options, args);

        BitbucketSupportZipCLI cli = new BitbucketSupportZipCLI();
        ExecutableCommand command = cli.getExecutableCommand(cmd, options);
        command.run();
    }

    private ExecutableCommand getExecutableCommand(CommandLine commandLine, Options options) {
        ExecutableCommand command ;
        if(commandLine.hasOption("help"))
            command = new HelpCommand(options);
        else if(commandLine.hasOption("gitServerUrl")) {
            command = new GenerateSupportZipCommand(commandLine);
            GenerateSupportZipCommand generateSupportZipCommand = (GenerateSupportZipCommand)command;
            attachExtraCommandsIfPresent(commandLine, generateSupportZipCommand);
        }
        else
            command = new NoOptionsPassedCommand(options);
        return command;
    }

    private void attachExtraCommandsIfPresent(CommandLine cmd, GenerateSupportZipCommand generateSupportZipCommand) {
        if(cmd.hasOption("flattenUnzipDir"))
            generateSupportZipCommand.setFlattenResultDir(true);
        if(cmd.hasOption("showLogsInExplorer"))
            generateSupportZipCommand.setResultsToBeDisplayedInFileExplorer(true);
    }
}