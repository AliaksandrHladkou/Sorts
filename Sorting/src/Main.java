import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

/*
* Author: Aliaksandr Hladkou
*
* Program to check the performance of sorting algorithms in seconds.
* Requires parameter to indicate the number of elements to be sorted.
* This class also creates a new file with randomly generated numbers every time when initialized.
* It is recommended to use arguments of at least 100,000 to see the performance.
*
* @param args
* */

public class Main {
    private final static int NUMOFOPTIONS = 4;
    public static void main(String[] args) throws IOException
    {
        if (args.length < 1)
            throw new RuntimeException("No arguments provided.");
        else
        {
            //Variables within else scope
            int arrayLength;
            try {
                arrayLength = Integer.parseInt(args[0]);
            }
            catch (NumberFormatException e)
            {
                System.out.println("An integer must be provided.");
                return;
            }
            boolean repeat = true;
            String fileName = Integer.toString(arrayLength) + ".txt";
            long startTime;
            long endTime;
            Scanner read = new Scanner(System.in);
            File myFile = new File(fileName);

            //Creating and writing into file random variables
            System.out.println("\nGenerating an array of size " + args[0] + "..");
            int[] data = arrayGenerator(arrayLength, 100);

            if (myFile.createNewFile())
                System.out.println("New file was created successfully.");
            else
                System.out.println("File was overwritten.");

            try {
                FileWriter writer = new FileWriter(fileName);

                for (int i = 0; i < data.length; i++) {
                    writer.write(data[i] + " ");
                }
                writer.close();
            }
            catch (IOException e) {
                System.out.println("Error while trying to write in file.");
                e.printStackTrace();
            }

            //A menu for implementing different sorts.
            while (repeat)
            {
                //Load file into array
                int[] array = loadFile(arrayLength, fileName, myFile);

                System.out.println("\nIndicate the sorting algorithm with a number or press 0 for exit:\n" +
                        "0: Exit \n" +
                        "1: Bubble \n" +
                        "2: Insertion \n" +
                        "3: Selection \n" +
                        "4: Merge");

                Sorts sorts = new Sorts();
                int input;

                while (true)
                {
                    try {
                        input = Integer.parseInt(read.nextLine());
                        break;
                    }
                    catch (Exception e) {
                        System.err.println("Please, provide an integer!");
                    }
                }
                if (input == 0)
                    System.exit(0);
                else if (!(input >= 0 && input <= NUMOFOPTIONS))
                {
                    System.err.println("The integer must be chosen within provided options!");
                    break;
                }

                startTime = System.nanoTime();
                switch (input)
                {
                    case 1:
                        System.out.println("Performing Bubble sort..");
                        sorts.bubbleSort(array);
                        break;
                    case 2:
                        System.out.println("Performing Insertion sort..");
                        sorts.insertionSort(array);
                        break;
                    case 3:
                        System.out.println("Performing Selection sort..");
                        sorts.selectionSort(array);
                    case 4:
                        System.out.println("Performing Merge sort..");
                        sorts.mergeSort(array, 0, arrayLength-1);
                    default: break;
                }
                endTime = System.nanoTime();

                //printArr(array);
                System.out.println("\nTime took: " + TimeUnit.SECONDS.convert((endTime - startTime), TimeUnit.NANOSECONDS) + " seconds.");

                repeat = isRepeat(read);
            }
        }
    }

    private static int[] loadFile(int arrayLength, String fileName, File myFile) {
        StringTokenizer st;
        int[] array = new int[arrayLength];
        int iterate = 0;
        try
        {
            Scanner scanner = new Scanner(myFile);
            System.out.println("Loading file " + fileName + "...");

            while (scanner.hasNextLine())
            {
                st = new StringTokenizer(scanner.nextLine());

                while (st.hasMoreTokens())
                {
                    int num = Integer.parseInt(st.nextToken());
                    array[iterate++] = num;
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return array;
    }

    private static boolean isRepeat(Scanner read)
    {
        boolean repeat;
        System.out.println("Enter 'Y' to repeat or any other key for exit.");
        String yesNo = read.nextLine();
        if (yesNo.toLowerCase().equals("y"))
            repeat = true;
        else
            repeat = false;
        return repeat;
    }

    private static int[] arrayGenerator(int size, int range)
    {
        int[] arr = new int[size];
        Random rand = new Random();
        for (int i = 0; i < arr.length; i++)
            arr[i] = rand.nextInt(range);

        return arr;
    }

    private static void printArr(int[] data)
    {
        for (int num:data) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
