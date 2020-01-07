import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/*
* Author: Aliaksandr Hladkou
*
* Program to check performance of sorting algorithms. Requires parameter to indicate the size of an array.
*
* */

public class Main {
    public static void main(String[] args)
    {
        if (args.length < 1)
            throw new RuntimeException("No arguments provided.");
        else
        {
            boolean repeat = true;

            while (repeat)
            {
                System.out.println("Generating an array of size " + args[0] + "..");
                int[] data = arrayGenerator(Integer.parseInt(args[0]), 100);
                //printArr(data);

                System.out.println("Indicate the sorting algorithm with a number or press 0 for exit:\n" +
                        "0: Exit \n" +
                        "1: Bubble \n" +
                        "2: Insertion \n" +
                        "3: Selection");

                Scanner read = new Scanner(System.in);
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
                else if (!(input >= 0 && input <= 3))
                {
                    System.err.println("The integer must be chosen within provided options!");
                    break;
                }

                long startTime = System.nanoTime();
                switch (input)
                {
                    case 1:
                        System.out.println("Performing Bubble sort..");
                        sorts.bubble(data);
                        break;
                    case 2:
                        System.out.println("Performing Insertion sort..");
                        sorts.insertion(data);
                        break;
                    case 3:
                        System.out.println("Performing Selection sort..");
                        sorts.selection(data);
                    default: break;
                }
                long endTime = System.nanoTime();
                long totalTime = endTime - startTime;
                System.out.println("Time took: " + TimeUnit.SECONDS.convert(totalTime, TimeUnit.NANOSECONDS) + " seconds.");

                repeat = isRepeat(read);

                //printArr(data);
            }
        }
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
