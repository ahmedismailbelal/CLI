package CLI;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class Terminal{

    private static String currDir=System.getProperty("user.dir");

    public void cd(String newPath)
    {
        if(newPath.equalsIgnoreCase(".."))
        {
            int splashIndex = currDir.lastIndexOf("\\");
            currDir = currDir.substring(0, splashIndex);
        }
        else {
            currDir=newPath;
        }
            //System.setProperty(currDir, newPath);
    }

    public void mv(String fileName, String destinationPath) throws IOException
    {
        String fullpath = currDir + "\\" + fileName;
        cp(fileName,destinationPath);
        File f = new File(fullpath);
        f.delete();
    }

    public void mkdir(String folderName)
    {
        String path = currDir + "\\" + folderName;
        File file = new File(path);
        boolean bool = file.mkdir();
        if (bool) {
            System.out.println("Directory created successfully");
        } else {
            System.out.println("Couldn't create the directory");
        }
    }

    public void rm(String fileName)
    {
        File f = new File(currDir+"\\"+fileName);
        f.delete();
    }

    public void exit()
    {
        System.exit(0);
    }

    public void ls()
    {
        File curDir = new File(currDir);
        File[] filesList = curDir.listFiles();
        for (File f : filesList) {
            if (f.isDirectory())
                System.out.println(f.getName());
            if (f.isFile()) {
                System.out.println(f.getName());
        }
        }
    }

    public void date()
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
    }

    public void clear()
    {
        //System.out.print("\033[H\033[2J");
      //  System.out.flush();

        for(int i = 0; i < 50; i++)
              System.out.print("\n");
    }
    
    public void pwd()
    {
        System.out.println(currDir);
    }

    public void display()
    {
        System.out.print(currDir + "> ");
    }

    public void cp(String filename, String destinationPath) throws IOException
    {

        File source = new File(currDir+"\\"+filename);
        File dest = new File(destinationPath+"\\"+filename);
        try (FileInputStream is = new FileInputStream(source);
             FileOutputStream os = new FileOutputStream(dest)) {

            byte[] buffer = new byte[1024];
            int length;

            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        }
    }

    public void cat(String redirect, String fileName) throws IOException {

        Scanner input = new Scanner(System.in);
        String text="yes";
        if(redirect.equalsIgnoreCase(">"))
            {
                FileWriter w = new FileWriter(currDir + "//" + fileName);
                w.write("");
            }

            FileWriter writer = new FileWriter(currDir + "//" + fileName,true);
            while(true)
            {
                text=input.nextLine();
                if(text.equalsIgnoreCase("^Z"))
                {
                    break;
                }
                else
                {
                    writer.write(text+"\n");
                }
            }
            writer.close();
    }

    public void cat(String fileName) throws IOException
    {
        File file = new File(currDir + "//" + fileName);
        Scanner myReader = new Scanner(file);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            System.out.println(data);
        }
        myReader.close();
    }
    public void rmdir(String folderName)
    {
        File folder = new File(currDir+"\\"+folderName);
        if(folder.isDirectory() && folder.list().length==0)
        {
            folder.delete();
        }
        else {
            System.out.println("Couldn't remove the folder");
        }
    }

    public void help() {
        System.out.println("cd: is used to change the current directory.");
        System.out.println("ls: is used to display a list of content of a directory.");
        System.out.println("cp: is used to copy a file or directory.");
        System.out.println("cat: It can be used to create a file, display content of the file, copy the content of one file to another file.");
        System.out.println("more: it is used to display the file content and To scroll down page by line.");
        System.out.println("mkdir: is used to create a new directory under any directory.");
        System.out.println("rmdir: is used to delete a directory.");
        System.out.println("mv: is used to move a file or a directory form one location to another location.");
        System.out.println("rm: is used to remove a file.");
        System.out.println("args: Displays all commands arguments");
        System.out.println("date: is used to display date, time, time zone, and more.");
        System.out.println("help:  displays information about Commands");
        System.out.println("pwd: is used to display the location of the current working directory.");
        System.out.println("clear: is used to clear the terminal screen.");
        System.out.println("exit: is used to Terminates the program");
    }

    public static void args() {
        System.out.println("cd: [arg] changes the current directory to the given [arg]");
        System.out.println("cd: [zero arg] changes the current directory to Default directory");
        System.out.println("ls: [Zero arg] displays contents of a file or directory");
        System.out.println("ls: [-a] displays directory contents of files and directories.");
        System.out.println("cp: [arg1] [arg2] copies contents of arg1(source) to arg2(destination)");
        System.out.println("cat: [arg] Reads file sequentially and prints it out..arg=(file)");
        System.out.println("more: [zero arg] it is used to display the file content and To scroll down page by line.");
        System.out.println("mkdir: [arg] creates a new directory, name of directory is the argument");
        System.out.println("rmdir: [arg] Removes an empty directory, name of directory is the given argument");
        System.out.println("mv: [arg1] [arg2] copies contents of arg1(Sourse) to arg2(Destination) and delete arg1(source)");
        System.out.println("rm: [arg] remove objects,directories and files,tha name is the argument.");
        System.out.println("date: No args");
        System.out.println("help: No args");
        System.out.println("pwd: No args");
        System.out.println("Clear: No args ");
        System.out.println("args: No args");
        System.out.println("exit: No args");
    }

    public static void more(String fileName) {
        File f = new File(currDir + "\\" + fileName);
        if (f.exists()) {
            try {
                FileInputStream is = new FileInputStream(f);
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String l;
                int c = 0;
                String moreLines;
                Scanner in = new Scanner(System.in);
                while ((l = br.readLine()) != null)
                {
                    System.out.println(l);
                    c++;
                    if (c % 10 == 0)
                    {
                        moreLines = in.nextLine();
                        if (moreLines.equalsIgnoreCase("q"))
                        {
                            break;
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
