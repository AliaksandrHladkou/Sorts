/*
* Aliaksandr Hladkou
*
* Class with sorting algorithms
* */

public class Sorts {

    //Merge Sort
    public void mergeSort(int[] data, int left, int right)
    {
        if (right <= left) return;
        int mid = (left+right)/2;
        mergeSort(data, left, mid);
        mergeSort(data, mid+1, right);
        merge(data, left, right, mid);
    }

    //Bubble sort
    public void bubbleSort(int[] data)
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
    public void insertionSort(int[] data)
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
    public void selectionSort(int[] data)
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

    //merging sorted elements of array
    private void merge(int[] data, int left, int right, int mid)
    {
        int leftArr[] = new int[mid - left + 1];
        int rightArr[] = new int[right - mid];
        int lIndex = 0;
        int rIndex = 0;

        for (int i = 0; i < leftArr.length; i++) {
            leftArr[i] = data[left+i];
        }
        for (int i = 0; i < rightArr.length; i++) {
            rightArr[i] = data[mid+i+1];
        }

        for (int i = left; i < right + 1; i++) {
            if (lIndex < leftArr.length && rIndex < rightArr.length)
            {
                if (leftArr[lIndex] < rightArr[rIndex])
                {
                    data[i] = leftArr[lIndex];
                    lIndex++;
                }
                else
                {
                    data[i] = rightArr[rIndex];
                    rIndex++;
                }
            }
            else if (lIndex < leftArr.length)
            {
                data[i] = leftArr[lIndex];
                lIndex++;
            }
            else if (rIndex < rightArr.length)
            {
                data[i] = rightArr[rIndex];
                rIndex++;
            }
        }
    }

    //Swapping elements in array
    private void swap(int[] data, int pos)
    {
        int temp = data[pos];
        data[pos] = data[pos+1];
        data[pos+1] = temp;
    }
}
