package com.padas2.bitbucket.supportzip;

import com.padas2.bitbucket.supportzip.api.BitbucketServerDetails;
import org.apache.commons.cli.*;

public class CLITester {
    private static void showHelp(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("CLITester", options);
    }

    public static void main(String[] args) throws ParseException {
        Options options = new Options();
        options.addOption("gitServerUrl", true, "Bitbucket server url");
        options.addOption("adminUser", true, "Admin user slug");
        options.addOption("adminPwd", true, "Admin user password");
        options.addOption("help", false, "Help");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse( options, args);

        if(cmd.hasOption("help")) {
            showHelp(options);
        }

        //***Interrogation Stage***
        //hasOptions checks if option is present or not
        if(cmd.hasOption("gitServerUrl"))
            System.out.println("Bitbucket server url        : " + cmd.getOptionValue("gitServerUrl"));
        if(cmd.hasOption("adminUser"))
            System.out.println("Bitbucket server admin user : " + cmd.getOptionValue("adminUser"));

        if(cmd.hasOption("gitServerUrl") && cmd.hasOption("adminUser") && cmd.hasOption("adminPwd")) {
            BitbucketServerDetails b = new BitbucketServerDetails();
            b.setGitHostUrl(cmd.getOptionValue("gitServerUrl"));
            b.setGitUser(cmd.getOptionValue("adminUser"));
            b.setGitPassWord(cmd.getOptionValue("adminPwd"));
            BitbucketSupportZipEngine bitbucketSupportZipEngine = new BitbucketSupportZipEngine(b);
            bitbucketSupportZipEngine.flattenUnzippedDir();
            try {
                bitbucketSupportZipEngine.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Looks like one or other required parameters have not been passed");
            showHelp(options);
        }
    }

    public static int getSum(String[] args) {
        int sum = 0;
        for(int i = 1; i < args.length ; i++) {
            sum += Integer.parseInt(args[i]);
        }
        return sum;
    }

    public static int getMultiplication(String[] args) {
        int multiplication = 1;
        for(int i = 1; i < args.length ; i++) {
            multiplication *= Integer.parseInt(args[i]);
        }
        return multiplication;
    }
}