/**
 * Created by Sander on 29-11-2016.
 */
public class ReplacementCollection {
    private int heapSize;
    private Disk INDisk;
    private int[] IN;
    //heap

    /**
     * Constructor
     * @param heapSize size of the heap
     * @param INDisck the disk with the data
     */
    public ReplacementCollection(int heapSize, Disk INDisck) {
        this.heapSize = heapSize;
        this.INDisk = INDisck;
    }

    public Disk getRuns(){
        IN = INDisk.getData();
        int i = 0;


        //While IN is not "empty"
        while (i != IN.length){

        }


        return null;
    }



    //Getters and Setters
    public int getHeapSize() {
        return heapSize;
    }

    public void setHeapSize(int heapSize) {
        this.heapSize = heapSize;
    }
}
