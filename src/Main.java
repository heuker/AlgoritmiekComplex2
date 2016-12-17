import java.util.ArrayList;
import java.util.Random;

/**
 * Main class that contains Replacement Selection
 */
public class Main {
    //The RSHeap
    RSHeap RSHeap = new RSHeap(5);

    public void run() {
        //Make a list of random numbers(Duplicates allowed)
        ArrayList<Integer> data = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            data.add(random.nextInt(100));
        }

        //Fill a Disk with the random numbers and run ReplacementSelection
        Disk disk = new Disk(data);
        ReplacementSelection(5, disk);

        //print the result
        disk.printResult();
    }

    /**
     * RepalcementSelection algorithm that uses a RSHeap
     *
     * @param runSize the given runSize(Max memory)
     * @param disk    The disk containing the data
     */
    public void ReplacementSelection(int runSize, Disk disk) {
        RSHeap heap = new RSHeap(runSize);
        boolean lessThanRun = disk.getData().size() < runSize;

        //build heap
        heap.buildHeap(disk.multipleAsArray(runSize));
        ArrayList<Integer> run = new ArrayList<>();
        int lastAdded = -1;

        //While there are numbers to progress
        while (disk.getData().size() != 0) {
            //add the next element to the run
            lastAdded = heap.pop();
            run.add(lastAdded);

            //get the next number
            int next = disk.getNext();

            //If next can be added to the heap add it
            if (next >= lastAdded) {
                heap.insert(next);

                //else increase the deadspace and add the number to it
            } else {
                heap.incrementDeadspace();
                heap.insertInDeadSpace(next);

                //if deadspace is at max, build heap from it
                if (heap.getDeadSpaceSize() == runSize) {
                    heap.buildHeap(heap.getHeap());

                    //write the run to Disk and than clear run
                    disk.writeRunToDisk(run);
                    run.clear();
                }
            }
        }

        //write remaining run to the disk
        if (!run.isEmpty()) {
            disk.writeRunToDisk(run);
            run.clear();
        }

        //special case: data is less than run size so olny progress the current numbers
        if (lessThanRun){
            //make the heap think all 0's are deadspace
            for (int i = 0; i < heap.getHeap().length - heap.getNumberOfNodes(); i++){
                heap.incrementDeadspace();
            }

            //progress all 'real' numbers
           while (heap.getNumberOfNodes() != 0){
                run.add(heap.pop());
            }
            disk.writeRunToDisk(run);
            return;
        }

        //Progress the remaining numbers
        heap.buildHeap(heap.getHeap());

        //the heap is sorted so we dont have to check for deadspace
        for (int i = 0; i < heap.getHeap().length; i++) {
            run.add(heap.pop());
            //Increase the deadspace so that the heap thinks there are less elements
            heap.incrementDeadspace();
        }
        disk.writeRunToDisk(run);
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
