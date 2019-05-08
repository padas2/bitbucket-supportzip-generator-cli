package com.padas2.bitbucket.supportzip;

import com.padas2.bitbucket.supportzip.api.BitbucketServerDetails;
import org.apache.commons.cli.CommandLine;
import java.awt.Desktop;

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
        BitbucketServerDetails b = new BitbucketServerDetails();
        b.setGitHostUrl(commandLine.getOptionValue("gitServerUrl"));
        b.setGitUser(commandLine.getOptionValue("adminUser"));
        b.setGitPassWord(commandLine.getOptionValue("adminPwd"));
        BitbucketSupportZipEngine bitbucketSupportZipEngine = new BitbucketSupportZipEngine(b);
        if(flattenResultDir)
            bitbucketSupportZipEngine.flattenUnzippedDir();
        try {
            bitbucketSupportZipEngine.start();
            if(resultsToBeDisplayedInFileExplorer)
                Desktop.getDesktop().open(bitbucketSupportZipEngine.getFinalResultDir());
        } catch (Exception e) {
            e.printStackTrace();
        }
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
