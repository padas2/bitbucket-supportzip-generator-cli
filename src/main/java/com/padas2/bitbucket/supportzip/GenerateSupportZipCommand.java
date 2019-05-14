package com.padas2.bitbucket.supportzip;

import com.padas2.bitbucket.supportzip.api.BitbucketServerDetails;
import org.apache.commons.cli.CommandLine;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

class IncompleteInputsException extends RuntimeException {
    public IncompleteInputsException(String s) {
        super(s);
    }
}

public class GenerateSupportZipCommand implements ExecutableCommand{
    private CommandLine commandLine;
    private boolean resultsToBeDisplayedInFileExplorer;
    private boolean flattenResultDir;

    public GenerateSupportZipCommand(CommandLine commandLine) {
        this.commandLine = commandLine;
    }
    
    @Override
    public void run() {
        checkIfMinimumInputsArePassed();
        BitbucketSupportZipEngine bitbucketSupportZipEngine = getConfiguredBitbucketSupportEngine();
        try {
            bitbucketSupportZipEngine.start();
            openResultDirInFileExplorerIfSpecified(bitbucketSupportZipEngine.getFinalResultDir());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openResultDirInFileExplorerIfSpecified(File finalResultDir) {
        try {
            if(resultsToBeDisplayedInFileExplorer)
                Desktop.getDesktop().open(finalResultDir);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    private BitbucketSupportZipEngine getConfiguredBitbucketSupportEngine() {
        BitbucketServerDetails serverDetails = getBitbucketServerDetailsFromCmdLine();
        BitbucketSupportZipEngine bitbucketSupportZipEngine = new BitbucketSupportZipEngine(serverDetails);
        if(flattenResultDir)
            bitbucketSupportZipEngine.flattenUnzippedDir();
        return bitbucketSupportZipEngine;
    }

    private BitbucketServerDetails getBitbucketServerDetailsFromCmdLine() {
        BitbucketServerDetails serverDetails = new BitbucketServerDetails();
        serverDetails.setGitHostUrl(commandLine.getOptionValue("gitServerUrl"));
        serverDetails.setGitUser(commandLine.getOptionValue("adminUser"));
        serverDetails.setGitPassWord(commandLine.getOptionValue("adminPwd"));
        return serverDetails;
    }

    public void setResultsToBeDisplayedInFileExplorer(boolean resultsToBeDisplayedInFileExplorer) {
        this.resultsToBeDisplayedInFileExplorer = resultsToBeDisplayedInFileExplorer;
    }

    public void setFlattenResultDir(boolean flattenResultDir) {
        this.flattenResultDir = flattenResultDir;
    }

    private void checkIfMinimumInputsArePassed() {
        if(!(commandLine.hasOption("gitServerUrl") && commandLine.hasOption("adminUser") && commandLine.hasOption("adminPwd")))
            throw new IncompleteInputsException("One of inputs gitServerUrl, adminUser, adminPwd is missing");
    }
}
