package com.padas2.bitbucket.supportzip;

import com.padas2.bitbucket.supportzip.api.BitbucketServerDetails;
import org.apache.commons.cli.CommandLine;

public class GenerateSupportZipCommand implements ExecutableCommand{
    private CommandLine commandLine;
    private boolean resultsToBeDisplayedInFileExplorer;
    private boolean resultsToBeDisplayedInChrome;
    private boolean flattenResultDir;
    private boolean getCredentialsFromCommandLine;
    private boolean getCredentialsFromCredentialsFile;

    public GenerateSupportZipCommand(CommandLine commandLine) {
        this.commandLine = commandLine;
    }
    
    @Override
    public void run() {
        BitbucketServerDetails b = new BitbucketServerDetails();
        b.setGitHostUrl(commandLine.getOptionValue("gitServerUrl"));
        b.setGitUser(commandLine.getOptionValue("adminUser"));
        b.setGitPassWord(commandLine.getOptionValue("adminPwd"));
        BitbucketSupportZipEngine bitbucketSupportZipEngine = new BitbucketSupportZipEngine(b);
        if(flattenResultDir)
            bitbucketSupportZipEngine.flattenUnzippedDir();

        try {
            bitbucketSupportZipEngine.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setResultsToBeDisplayedInFileExplorer(boolean resultsToBeDisplayedInFileExplorer) {
        this.resultsToBeDisplayedInFileExplorer = resultsToBeDisplayedInFileExplorer;
    }

    public void setResultsToBeDisplayedInChrome(boolean resultsToBeDisplayedInChrome) {
        this.resultsToBeDisplayedInChrome = resultsToBeDisplayedInChrome;
    }

    public void setFlattenResultDir(boolean flattenResultDir) {
        this.flattenResultDir = flattenResultDir;
    }

    public void getCredentialsFromCommandLine(boolean getCredentialsFromCommandLine) {
        this.getCredentialsFromCommandLine = getCredentialsFromCommandLine;
    }

    public void getCredentialsFromCredentialsFile(boolean getCredentialsFromCredentialsFile) {
        this.getCredentialsFromCredentialsFile = getCredentialsFromCredentialsFile;
    }
}
