/*
* Aliaksandr Hladkou
*
* Class of sorting algorithms
* */

public class Sorts {

    //Bubble sort
    public void bubble(int[] data)
    {
        boolean sorted = false;
        while (!sorted)
        {
            sorted = true;
            for (int i = 0; i < data.length-1; i++)
            {
                if (data[i] > data[i+1])
                {
                    swap(data, i);
                    sorted = false;
                }
            }
        }
    }

    //Insertion sort
    public void insertion(int[] data)
    {
        for (int i = 1; i < data.length; i++)
        {
            int current = data[i];
            int prev = i - 1;
            while (prev >= 0 && data[prev] > current)
            {
                data[prev+1] = data[prev];
                swap(data, prev);
                prev--;
            }
            data[prev+1] = current;
        }
    }

    //Selection sort
    public void selection(int[] data)
    {
        for (int i = 0; i < data.length-1; i++)
        {
            int minIndex = i;
            for (int j = i+1; j < data.length; j++)
            {
                if (data[j] < data[minIndex])
                    minIndex = j;
            }
            int temp = data[minIndex];
            data[minIndex] = data[i];
            data[i] = temp;
        }
    }

    private void swap(int[] data, int pos)
    {
        int temp = data[pos];
        data[pos] = data[pos+1];
        data[pos+1] = temp;
    }
}
