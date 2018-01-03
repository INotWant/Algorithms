package chapter27;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author kissx on 2017/2/12.
 */
public class Utils {

    public static void generateData(int num, int size) {
        String filePath = "data/array.txt";
        Random random = new Random();
        try (BufferedWriter bfWriter = new BufferedWriter(new FileWriter(filePath))) {
            for (int i = 0; i < num; i++) {
                bfWriter.write(random.nextInt(size) + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * get array from file
     */
    public static int[] getArray(String filePath) {
        List<Integer> list = new ArrayList<>();
        try (BufferedReader bfReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bfReader.readLine()) != null)
                list.add(Integer.parseInt(line));
        } catch (Exception e) {
            e.printStackTrace();
        }
        int[] array = new int[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    /**
     * save array to file
     */
    public static void save(String filePath, int[] array) {
        try (BufferedWriter bfWriter = new BufferedWriter(new FileWriter(filePath))) {
            for (int a : array) {
                bfWriter.write(String.valueOf(a) + '\n');
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        generateData(10000, 100000);
    }
}
