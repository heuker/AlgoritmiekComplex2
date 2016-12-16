import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Sander on 29-11-2016.
 */
public class Disk {
    private ArrayList<Integer> data;
    private ArrayList<ArrayList<Integer>> outArray;

    public Disk(ArrayList data) {
        this.data = data;
        outArray = new ArrayList<>();
    }

    public ArrayList<Integer> getData() {
        return data;
    }

    public int getNext(){
        int value = data.get(0);
        data.remove(0);
        return value;
    }

    public int[] multipleAsArray(int amount){
        if (amount > data.size()){
            amount = data.size();
        }

        int[] multiple = new int[amount];
        for (int i = 0; i < amount; i++){
            multiple[i] = data.get(i);
        }

        return multiple;
    }

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
