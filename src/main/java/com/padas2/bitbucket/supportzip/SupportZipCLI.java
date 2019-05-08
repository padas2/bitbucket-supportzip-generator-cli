package com.padas2.bitbucket.supportzip;

import org.apache.commons.cli.*;

public class SupportZipCLI {
    private static void showHelp(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("SupportZipCLI", options);
    }

    public static void main(String[] args) throws ParseException {
        Options options = new Options();
        options.addOption("gitServerUrl", true, "Bitbucket server url");
        options.addOption("adminUser", true, "Admin user slug");
        options.addOption("adminPwd", true, "Admin user password");
        options.addOption("credentialsFilePath", true, "Local file path of credentials");
        options.addOption("showLogsInExplorer", false, "Open Unzipped dir in File Explorer");
        options.addOption("showLogsInChrome", false, "Open Unzipped dir in Google chrome browser");
        options.addOption("flattenUnzipDir", false, "Flatten unzipped dir from hierarchial to flat structure");
        options.addOption("help", false, "Help");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse( options, args);

        SupportZipCLI cli = new SupportZipCLI();
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
        else if(commandLine.hasOption("gitServerUrl") && commandLine.hasOption("credentialsFilePath"))
            command = new GenerateSupportZipCommand(commandLine);
        else
            command = new NoOptionsPassedCommand(options);
        return command;
    }

    private void attachExtraCommandsIfPresent(CommandLine cmd, GenerateSupportZipCommand generateSupportZipCommand) {
        if(cmd.hasOption("adminUser") && cmd.hasOption("adminPwd"))
            generateSupportZipCommand.getCredentialsFromCommandLine(true);
        if(cmd.hasOption("credentialsFilePath"))
            generateSupportZipCommand.getCredentialsFromCredentialsFile(true);
        if(cmd.hasOption("flattenUnzipDir"))
            generateSupportZipCommand.setFlattenResultDir(true);
        if(cmd.hasOption("showLogsInExplorer"))
            generateSupportZipCommand.setResultsToBeDisplayedInFileExplorer(true);
        if(cmd.hasOption("showLogsInChrome"))
            generateSupportZipCommand.setResultsToBeDisplayedInChrome(true);
    }
}