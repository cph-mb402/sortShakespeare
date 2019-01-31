import java.io.*;

public class Sorting {

    public static void main(String[] args) {
        StopWatch stopwatch = new StopWatch();
        String text = readFileToString(System.getProperty("user.dir") + "/src/shakespeare.txt");
        String[] words = toWords(text);
        stopwatch.start();
        selectionSort(words);
        stopwatch.stop();
        writeStringArrToFile(words, System.getProperty("user.dir") + "/src/sortedShakespeare.txt");
        System.out.println(stopwatch.getElapsedTimeSecs());
//        for(int c=0; c<=100; c++) {
//            System.out.print(words[c] + " ");
//        }
    }

    private static String readFileToString(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
        {

            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null)
            {
                contentBuilder.append(sCurrentLine).append("\n");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }
    
    private static void writeStringArrToFile(String[] arr, String fileName) {
        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            for ( int i = 0; i < 100; i++)
            {
                writer.write(arr[i] + " ");
            }
            writer.close();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    private static String[] toWords(String text) {
        return text.split("[^a-zA-Z0-9_]+");
    }

    public static void selectionSort(String[] arr) {
        for (int i = 0; i < 100 - 1; i++)
        {
            int index = i;
            for (int j = i + 1; j < 100; j++) {
                if (arr[j].charAt(0) < arr[index].charAt(0)){
                    index = j; //searching for lowest index
                }
            }
            String temp = arr[index];
            arr[index] = arr[i];
            arr[i] = temp;
        }
    }

    public static void insertionSort(String[] arr) {
        for (int j = 1; j < arr.length; j++) {
            String key = arr[j];
            int i = j-1;
            while ( (i > -1) && ( arr[i].charAt(0) > key.charAt(0))) {
                arr[i+1] = arr[i];
                i--;
            }
            arr[i+1] = key;
        }
    }

    // Merges two subarrays of arr[].
    // First subarray is arr[l..m]
    // Second subarray is arr[m+1..r]
    void merge(int arr[], int l, int m, int r)
    {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        int L[] = new int [n1];
        int R[] = new int [n2];

        /*Copy data to temp arrays*/
        for (int i=0; i<n1; ++i)
            L[i] = arr[l + i];
        for (int j=0; j<n2; ++j)
            R[j] = arr[m + 1+ j];


        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2)
        {
            if (L[i] <= R[j])
            {
                arr[k] = L[i];
                i++;
            }
            else
            {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1)
        {
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2)
        {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Main function that sorts arr[l..r] using
    // merge()
    void mergeSort(int arr[], int l, int r)
    {
        if (l < r)
        {
            // Find the middle point
            int m = (l+r)/2;

            // Sort first and second halves
            mergeSort(arr, l, m);
            mergeSort(arr , m+1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }
}
