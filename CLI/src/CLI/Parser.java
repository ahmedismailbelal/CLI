package CLI;

import java.io.File;
import java.io.IOException;

public class Parser {

    static Terminal cli = new Terminal();
    public void parse(String input) throws IOException {
        int numOfPipes=1;
        String[] args;
        String[] splitter = new String[2];
        boolean checkPipe = input.contains("|");
        if (checkPipe) {
            input=input.replace("|","@");
            splitter = input.split("@", 2);
            splitter[0] = splitter[0].strip();
            splitter[1] = splitter[1].strip();
            numOfPipes = 2;
        } else {
            splitter[0] = input;
        }
        for (int i = 0; i < numOfPipes; i++) {
            args = splitter[i].split(" ");
            if (args[0].equalsIgnoreCase("cd")) {
                if(args.length<2)
                {
                    System.out.println("Path required");
                }
                else {
                    if (args[1].contains(".\\")) {
                        args[1] = args[1].replace(".\\", "C:\\Users\\eyady\\");
                    }

                    if (checkPath(args[1])) {
                        cli.cd(args[1]);
                    } else {
                        System.out.println("Invalid Directory");
                    }
                }
            } else if (args[0].equalsIgnoreCase("ls")) {
                cli.ls();
            } else if (args[0].equalsIgnoreCase("cp")) {
                if(args.length<3)
                {
                    System.out.println("An argument is missing");
                }
                else {
                    if (args[2].contains(".\\")) {
                        args[2] = args[2].replace(".\\", "C:\\Users\\eyady\\");
                    }
                    if (checkPath(args[2])) {
                        cli.cp(args[1], args[2]);
                    } else {
                        System.out.println("Invalid Directory");
                    }
                }
            } else if (args[0].equalsIgnoreCase("mv")) {
                if(args.length<3)
                {
                    System.out.println("An argument is missing");
                }
                else {
                    if (args[1].contains(".\\")) {
                        args[1] = args[1].replace(".\\", "C:\\Users\\eyady\\");
                    }
                    if (checkPath(args[2])) {
                        cli.mv(args[1], args[2]);
                    } else {
                        System.out.println("Invalid Directory");
                    }
                }
            } else if (args[0].equalsIgnoreCase("mkdir")) {
                if(args.length<2)
                {
                    System.out.println("Folder name is required");
                }
                else {
                    cli.mkdir(args[1]);
                }
            } else if (args[0].equalsIgnoreCase("rm")) {
                if(args.length<2)
                {
                    System.out.println("Folder name is required");
                }
                else {
                    cli.rm(args[1]);
                }
            } else if (args[0].equalsIgnoreCase("help")) {
                cli.help();
            } else if (args[0].equalsIgnoreCase("args")) {
                cli.args();
            } else if (args[0].equalsIgnoreCase("exit")) {
                cli.exit();
            } else if (args[0].equalsIgnoreCase("date")) {
                cli.date();
            } else if (args[0].equalsIgnoreCase("clear")) {
                cli.clear();
            } else if (args[0].equalsIgnoreCase("pwd")) {
                cli.pwd();
            } else if (args[0].equalsIgnoreCase("cat")) {
                if(args.length<2)
                {
                    System.out.println("An argument is required");
                }
                else {
                    if (args[1].equalsIgnoreCase(">") || args[1].equalsIgnoreCase(">>")) {
                        cli.cat(args[1], args[2]);
                    } else {
                        cli.cat(args[1]);

                    }
                }
            } else if (args[0].equalsIgnoreCase("rmdir")) {
                if(args.length<2)
                {
                    System.out.println("Folder name is required");
                }
                else {
                    cli.rmdir(args[1]);
                }
            } else if (args[0].equalsIgnoreCase("more")) {
                if (args.length < 2) {
                    System.out.println("File name is required");
                } else {
                    cli.more(args[1]);
                }
            }
            else
            {
                System.out.println("Invalid Command");
            }
        }
    }

    private boolean checkPath(String path)
    {
        if(new File(path).exists())
            return true;
        else
            return false;
    }
}
