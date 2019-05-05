package com.padas2.bitbucket.supportzip;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public class Main {
    @Parameter(names={"-v","--verbose"},
            description="Enable verbose logging")
    private boolean verbose;

    @Parameter(names={"-f","--file"},
            description="Path and name of file to use",
            required=true)
    private String file;

    @Parameter(names={"-h", "--help"},
            description="Help/Usage",
            help=true)
    private boolean help;
    public static void main(String[] args) {
        Main main = new Main();
        final JCommander commander = JCommander.newBuilder().programName("supportzip-engine").addObject(main).build();
    }
}
