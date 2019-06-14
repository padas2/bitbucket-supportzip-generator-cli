# bitbucket-supportzip-generator-cli
A Java based Command Line Interface built using Apache Commons CLI, to download support zip from bitbucket server.

## Internal Makeup
This internally makes use of bitbucket-supportzip-generator API and Apache Commons CLI.

## Usage
Download the latest jar from the releases tab.
Run the jar in any terminal.

    java -jar bitbucket-support-generator-cli.jar -help
    usage: BitbucketSupportZipCLI
     -adminPwd <arg>              Admin user password
     -adminUser <arg>             Admin user slug
     -flattenUnzipDir             Flatten unzipped dir from hierarchial to
                                  flat structure
     -gitServerUrl <arg>          Bitbucket server url
     -help                        Help
     -showLogsDirInFileExplorer   Open Unzipped dir in File Explorer

    Sample usage :
    java -jar bitbucket-suuportzip-generator-cli-0.0.2-SNAPSHOT.jar -gitServerUrl http://localhost:7990 -adminUser <admin-user> -adminPwd    <admin-password> -showLogsDirInFileExplorer -flattenUnzipDir
  
