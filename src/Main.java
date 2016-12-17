import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Sander on 29-11-2016.
 */
public class Main {

    public static void main(String[] args) {
        new Main().run();
    }

    RSHeap RSHeap = new RSHeap(5);

    public void run() {
        ArrayList<Integer> data = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 50; i++){
            data.add(random.nextInt(100));
        }

        Disk disk = new Disk(data);

        getRuns(5, disk);
    }

    public void print() {
        for (int i = 0; i < RSHeap.getHeap().length; i++) {
            System.out.print(RSHeap.getHeap()[i]);
        }
        System.out.println();
        System.out.println("Deadspace: " + RSHeap.getDeadSpaceSize());
    }

    public void getRuns(int runSize, Disk disk) {
        RSHeap heap = new RSHeap(runSize);

        //build heap
        heap.buildHeap(disk.multipleAsArray(runSize));
        ArrayList<Integer> run = new ArrayList<>();
        int lastAdded = -1;

        //While there are numbers to progress
        while (disk.getData().size() != 0) {
            //add the next element to the run
            lastAdded = heap.pop();
            run.add(lastAdded);

            int next = disk.getNext();

            //If next fits in heap add it
            if (next >= lastAdded) {
                heap.insert(next);

                //else increase deadspace and add the number to it
            } else {
                heap.incrementDeadspace();
                heap.insertInDeadSpace(next);

                //if deadspace is at max, build heap from it
                if (heap.getDeadSpaceSize() == runSize) {
                    heap.buildHeap(heap.getHeap());

                    //write the run to Disk and clear it
                    disk.writeRunToDisk(run);
                    run.clear();
                }
            }
        }
        disk.writeRunToDisk(run);
        run.clear();

        //if there are numbers left in the deadspace than process them to
        if (heap.getDeadSpaceSize() != 0){
            heap.buildHeap(heap.getHeap());
            for (int i = 0; i < heap.getHeap().length; i++){
                run.add(heap.pop());
            }
            disk.writeRunToDisk(run);
        }

        //print the result
        disk.printResult();
    }
}
