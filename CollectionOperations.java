import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

// The purpose of this code is to analyze the trading volume of Ethereum from March 10th 2021 to March 10th 2022. The program 
// finds the average of the volumes and counts the number of times the trading volume was lower than the average. 
// The object I am operating over is an ArrayList of Long values which holds 366 volume values.
// The filter checks if the volume of that day was lower than the average. The intermediate operation maps the volumes to a longValue  
// which is used when finding the average. The first terminal operation is a sum which is used to find the average. The second terminal operation
// is a count to count how many times the volume was below the average.

public class CollectionOperations {

    public static void main(String[] args) {

        // arraylist set to 366 as there are 366 data points in the csv file
        ArrayList<Long> volumes = new ArrayList<>(365);
        try {
            File file = new File("ETH-USD.csv");
            Scanner scanner = new Scanner(file);
            scanner.next();
            while (scanner.hasNext()) {

                String data = scanner.next();
                String[] values = data.split(",");

                volumes.add(Long.parseLong(values[6]));

            }
            // prints the number of times the trading volume of ethereum was lower than the
            // average

            System.out.println(forEachLoopTask(volumes));
            System.out.println(streamTask(volumes));
            System.out.println(iteratorLoopTask(volumes));

            scanner.close();

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public static int forEachLoopTask(ArrayList<Long> volumes) {
        int count = 0;
        Long sum = 0L;

        // Loops through volumes and adds up the sum
        for (Long t : volumes) {
            sum += t.longValue();

        }
        // uses the sum to find the average of the 366 data points
        Long average = sum / 366;

        // loops through again to compare the average with the volumes and counts the
        // number of times the
        // volume is lower than the average
        for (Long t : volumes) {
            if (t < average) {
                count++;
            }

        }

        return count;
    }

    public static int iteratorLoopTask(ArrayList<Long> volumes) {

        Iterator<Long> it = volumes.iterator();
        Long sum = 0L;
        int count = 0;

        // uses an iterator to find the sum of the volumes array list.
        while (it.hasNext()) {
            Long t = it.next();
            sum += t;

        }
        // a second iterator is used to compare each volume to average
        Iterator<Long> iterator = volumes.iterator();
        // uses the sum to find the average
        Long average = sum / 366;

        // counts everytime a volume is less than the average
        while (iterator.hasNext()) {
            Long t = iterator.next();
            if (t < average) {
                count++;
            }

        }
        return count;

    }

    public static Long streamTask(ArrayList<Long> volumes) {
        final Long average;

        // first aggregate operator is used to find the average of the volumes arraylist
        average = volumes
                .stream()
                .mapToLong(i -> i.longValue())
                .sum() / 366;

        // the second arrgregate operator is used to count the number of times each
        // volume is lower than the average
        Long count = volumes
                .stream()
                .mapToLong(i -> i.longValue())
                .filter(i -> i < average)
                .count();

        return count;

    }

}
// Lines of Code:
// For Each Loop: 8 Lines of Code
// Iterator: 12 Lines of Code
// StreamTask: 11 Lines of Code

// The StreamTask was better suited for my task. While it was more complex, the
// StreamTask was more concise and readable.
// Also, the StreamTask is better for larger sets of data and my csv file had
// 366 data points for one year.
// If this was expanded to 5 or 10 years, StreamTask would be able to work
// better than the other mehtods.