import java.util.*;
/**
 * Name: Ruoxin Huang
 * PID: A99084753
 * Login: cs12whl
 */
public class Project3
{
    /**
     * Adds a random number to the beginning of an array
     * @param arr  takes in the array to add the random number to
     * @return     the new array with the random number added
     */
    public static int[] addManyFrontArray(int [] arr)
    {
        // Create temporary array to be returned
        int[] temp = new int[arr.length+1]; 
        // Create random number and put it at beginning of the temporary array
        Random random = new Random(); 
        temp[0] = random.nextInt(); 
        // Copy the other elements of the original array into the temporary array
        for (int i = 1; i <= arr.length; i++)
        {
            temp[i] = arr[i-1];
        }
        return temp;
    }

    /**
     * Adds a random number to the beginning of a linked list
     * @param arr  takes in the linked list to add the random number to
     * @return     the new linked list with the random number added
     */
    public static LinkedList addManyFrontList (LinkedList list)
    {
        // Create random number and put it at beginning of the temporary array
        Random random = new Random();
        list.add(0, random.nextInt());
        return list;
    }

    /**
     * Create 10 arrays and add size number of element to them and record time
     * @param size  takes in the number of eleements to add to each array
     * @return     the average time used to add size number of elements to the 10 arrays
     */
    public static long testArrayTime(int size) 
    {
        long startTime, endTime, usedTime, totalTime=0;
        // Create 10 arrays 
        for(int j=0; j<10; j++)
        {
            int [] array = new int[0];
            // Record time before adding elements
            startTime = System.nanoTime();
            // Add size number of elements to the arrays
            for(int i=0; i<size; i++)
            {
                array = addManyFrontArray(array);
            }
            // Record time after adding elements and calculate time used
            endTime = System.nanoTime();
            usedTime = endTime - startTime;
            // Record total time used
            totalTime += usedTime;
        }
        // return the average of 10 runs
        return totalTime/10;
    }
    
    /**
     * Create 10 linked lists and add size number of element to them and record time
     * @param size  takes in the number of eleements to add to each linked list
     * @return     the average time used to add size number of elements to the 10 linked lists
     */
    public static long testListTime(int size) 
    {
        long startTime, endTime, usedTime, totalTime=0;
        // Create 10 arrays 
        for(int j=0; j<10; j++)
        {
            LinkedList list = new LinkedList();
            // Record time before adding elements
            startTime = System.nanoTime();
            // Add size number of elements to the arrays
            for(int i=0; i<size; i++)
            {
                list = addManyFrontList(list);
            }
            // Record time after adding elements and calculate time used
            endTime = System.nanoTime();
            usedTime = endTime - startTime;
            // Record total time used
            totalTime += usedTime;
        }
        // return the average of 10 runs
        return totalTime/10;
    }
    
    /**
     * Print out the time used to add size number of elements to arrays and linked lists
     * with varying values of size
     */
    public static void main(String[] args) 
    {
        int size = 1000;
        // Print average time used for various value of size
        for(int i=0; i<10; i++)
        {
            long averageTime = testArrayTime(size);
            System.out.println(averageTime);
            size += 1000;
        }
        // Reset size
        size = 1000;
        // Print average time used for various value of size
        for(int i=0; i<10; i++)
        {
            long averageTime = testListTime(size);
            System.out.println(averageTime);
            size += 1000;
        }
        System.out.println();
    }
}
