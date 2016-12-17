import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Main class that contains Replacement Selection
 */
public class Main {
    //The RSHeap
    RSHeap RSHeap = new RSHeap(5);

    public void run() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        while (true){
            System.out.println("\nMake a choice:");
            System.out.println("1: Standard");
            System.out.println("2: Less data than run");
            System.out.println("3: Data fits run perfectly");
            System.out.println("4: Data already sorted");
            System.out.println("5: No data");
            System.out.println("-1: Quit\n");

            choice = scanner.nextInt();

            if (choice == -1){
                break;
            }

            //print the result
            runReplaceMentSelection(choice).printResult();{
            }
        }
    }

    /**
     * Method to start making runs of a disk
     * @param howTo parameter that tells with what data to run
     * @return a filled in Disk
     */
    public Disk runReplaceMentSelection(int howTo){
        ArrayList<Integer> data = new ArrayList<>();
        Random random = new Random();
        int runSize = 0;

        System.out.println("Input");
        switch (howTo){
            case 1:
                for (int i = 0; i < 50; i++) {
                    data.add(random.nextInt(100));
                    System.out.print(data.get(i) + ", ");
                }
                runSize = 5;
                break;

            case 2:
                for (int i = 0; i < 3; i++) {
                    data.add(random.nextInt(100));
                    System.out.print(data.get(i) + ", ");
                }
                runSize = 5;
                break;

            case 3:
                for (int i = 0; i < 5; i++) {
                    data.add(random.nextInt(100));
                    System.out.print(data.get(i) + ", ");
                }
                runSize = 5;
                break;

            case 4:
                data.add(1);
                data.add(3);
                data.add(5);
                data.add(7);
                data.add(8);
                data.add(10);
                data.add(14);
                data.add(15);

                runSize = 5;
                break;

            case 5:
                runSize = 5;

        }

        //make a Disk with the data and run replacementSelection on it
        Disk disk = new Disk(data);
        replacementSelection(runSize, disk);
        return disk;
    }

    /**
     * RepalcementSelection algorithm that uses a RSHeap
     *
     * @param runSize the given runSize(Max memory)
     * @param disk    The disk containing the data
     */
    public void replacementSelection(int runSize, Disk disk) {
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
                heap.incrementDeadspace();
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
