import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class that simulates a Disk with data, can write to and from
 */
public class Disk {
    private ArrayList<Integer> data;
    private ArrayList<ArrayList<Integer>> outArray;

    /**
     * Constructor
     * @param data the data to be sorted in runs
     */
    public Disk(ArrayList data) {
        this.data = data;
        outArray = new ArrayList<>();
    }

    /**
     * Get the whole data ArrayList
     * @return data
     */
    public ArrayList<Integer> getData() {
        return data;
    }

    /**
     * Get the next value of the ArrayList and than delete it
     * @return index 0 of data
     */
    public int getNext(){
        int value = data.get(0);
        data.remove(0);
        return value;
    }

    /**
     * Returns an array of the first numbers
     * @param amount the amount of numbers to be returned
     * @return the returned numbers
     */
    public int[] multipleAsArray(int amount){
        if (amount > data.size()){
            amount = data.size();
        }

        int[] multiple = new int[amount];
        for (int i = 0; i < amount; i++){
            multiple[i] = getNext();
        }

        return multiple;
    }

    /**
     * Writes a new run to the out ArrayList
     * @param numbers the run to be added (as ArrayList)
     */
    public void writeRunToDisk(ArrayList<Integer> numbers){
        outArray.add(new ArrayList<>(numbers));
    }

    /**
     * Prints all runs
     */
    public void printResult(){
        for (int i = 0; i < outArray.size(); i++){
            System.out.println("Run " + i + ":");
            for (int j = 0; j < outArray.get(i).size(); j++){
                System.out.println(outArray.get(i).get(j));
            }
        }
    }
}
