

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main 
{
    static Stack stack ;
    static Queue queue ;
    static String stackOutFileName = "stackOut.txt";
    static String queueOutFileName = "queueOut.txt";
    
    public static void main(String[] args) 
    {
        
            
        
            //String filename = args[0];
            String filename = "command.txt";
            initStackAndQueue();
            ReadOperations(filename);
        
        
        
    }
    
    // reads all elements from the file of stack.txt and queue.txt ,and pushes them to the stack and queue.
    // for the stack pushing it starts to read from end of the file that contains only a single line.
    // i used sc.nextLine() without checking if there is more lines or no line. because the given stack.txt and queue.txt files contains only a single line.
    public static void initStackAndQueue()
    {
        stack = new Stack();
        queue = new Queue();
        File file = new File("stack.txt");
        if(file.exists())
        {
            try {
                    Scanner sc = new Scanner(file);
                    String line = sc.nextLine();
                    String[] tokens = line.split(" ");
                    for(int i=tokens.length-1 ; i>=0 ; i--)
                    {
                        if(tokens[i].length() != 0)
                        {
                            try{
                              int num = Integer.parseInt(tokens[i]);
                              stack.push(num);
                            }catch(NumberFormatException ex)
                            {
                                ex.printStackTrace();
                            }
                        }
                        
                                        
                    }
                
                    sc.close();
                
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        
        file = new File("queue.txt");
        if(file.exists())
        {
            try {
                    Scanner sc = new Scanner(file);
                
                    String line = sc.nextLine();
                    String[] tokens = line.split(" ");
                    for(int i=0 ; i<tokens.length ; i++)
                    {
                        if(tokens[i].length() != 0)
                        {
                            try{
                              int num = Integer.parseInt(tokens[i]);
                              queue.offer(num);
                            }catch(NumberFormatException ex)
                            {
                                ex.printStackTrace();
                            }
                        }
                        
                    }
                sc.close();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
                
            }
            
        }
        
    }
    
    // that reads all operations from command.txt and operates them line by line.
    public static void ReadOperations(String filename)
    {
        File file = new File(filename);

        
        try{
            if(!file.exists())
            {
                file.createNewFile();   
            }
            
            Scanner sc = new Scanner(file);
            
            while(sc.hasNext())
            {
                String line = sc.nextLine();
                
                operation(line);
            }
            sc.close();
            
        }catch(IOException ex)
        {
            ex.printStackTrace();
        }
        
    }
    
    // remove the empty entries from the given array of String that is used in operation method. 
    public static String[] removeEmptyEntries(String[] arr)
    {
        int newLength = 0;
        for(int i=0; i<arr.length ; i++)
        {
            if(arr[i].length() != 0)
            {
                newLength++;
            }
        }
        
        String[] newArr = new String[newLength];
        int currIndex = 0;
        
        for(int i=0 ; i< arr.length; i++)
        {
            if(arr[i].length() != 0)
            {
                newArr[currIndex++] = arr[i];
            }
        }
        
        return newArr;
    }
    
    // operates one operation. it takes one string line that directly taken from file. and split it then operates if it is appropriate. 
    public static boolean operation(String line)
    {
        String[] tokens = line.trim().split(" ");
        
        tokens = removeEmptyEntries(tokens);
        
        if(tokens.length < 2 && tokens.length > 3)
        {
            return false;
        }
        try{
            
        
            if(tokens[0].equals("S"))
            {
                if(tokens.length == 2)
                {
                    switch (tokens[1]) 
                    {
                        case "sortElements":
                            stack.sort();
                            return writeToFile(stackOutFileName  ,"After sortElements:", stack.toString());
                        case "distinctElements":
                            int distinctElementCounter = stack.distinctElementCounter();
                            return writeToFile(stackOutFileName ,"After distinctElements:",  String.format("Total distinct element=%d", distinctElementCounter));
                        case "calculateDistance":
                            int distance = stack.calculateDistance();
                            return writeToFile(stackOutFileName ,"After calculateDistance:",  String.format("Total distance=%d", distance));
                        default:
                            break;
                    }
                }
                else if(tokens.length == 3)
                {
                    int k = Integer.parseInt(tokens[2]);
                    switch (tokens[1]) 
                    {
                        case "removeGreater":
                            stack.removeGreaterNumber(k);
                            return writeToFile(stackOutFileName ,"After removeGreater "+k+":", stack.toString());
                        case "reverse":
                            stack.reverse(k);
                            return writeToFile(stackOutFileName ,"After reverse "+k+":", stack.toString());
                        case "addOrRemove":
                            stack.addOrRemove(k);
                            return writeToFile(stackOutFileName ,"After addOrRemove "+k+":",  stack.toString());
                        default:
                            break;
                    }
                }
            }
            else if(tokens[0].equals("Q"))
            {
                if(tokens.length == 2)
                {
                    switch (tokens[1]) 
                    {
                        case "sortElements":
                            queue.sort();
                            return writeToFile(queueOutFileName ,"After sortElements:", queue.toString());
                        case "distinctElements":
                            int distinctElementCounter = queue.distinctElementCounter();
                            return writeToFile(queueOutFileName ,"After distinctElements:",  String.format("Total distinct element=%d", distinctElementCounter));
                        case "calculateDistance":
                            int distance = queue.calculateDistance();
                            return writeToFile(queueOutFileName ,"After calculateDistance:",  String.format("Total distance=%d", distance));
                        default:
                            break;
                    }
                }
                else if(tokens.length == 3)
                {
                    int k = Integer.parseInt(tokens[2]);
                    switch (tokens[1]) 
                    {
                        case "removeGreater":
                            queue.removeGreaterNumber(k);
                            return writeToFile(queueOutFileName ,"After removeGreater "+k+":", queue.toString());
                        case "reverse":
                            queue.reverse(k);
                            return writeToFile(queueOutFileName ,"After reverse "+k+":", queue.toString());
                        case "addOrRemove":
                            queue.addOrRemove(k);
                            return writeToFile(queueOutFileName ,"After addOrRemove "+k+":",  queue.toString());
                        default:
                            break;
                    }
                }
            }

        }catch(NumberFormatException ex)
        {
            return false;
        }
        
        return false;
    }  
    
    
    // writes the given two lines to the given fileName in the true append mode.
    public static boolean writeToFile(String fileName ,String firstLine, String secondLine)
    {
        File file = new File(fileName);
        
        try{
            
            if(!file.exists())
            {
                file.createNewFile();
            }
            
            FileWriter filewriter = new FileWriter(file, true);
            
            filewriter.append(firstLine.trim() + "\n" + secondLine.trim() + "\n");
            
            filewriter.close();
            return true;
            
        }catch(IOException ex)
        {
            return false;
        }
        
        
        
    }
    
    
}
