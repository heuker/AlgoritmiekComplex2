/**
 * Created by Sander on 29-11-2016.
 */
public class Main {

    public static void main(String[] args) {
        new Main().run();
    }
    MinHeap minHeap = new MinHeap(5);

    public void run(){
//        int[] data = {55 ,56, 42, 23, 90, 23, 83, 25, 1, 21, 45, 76};
//        Disk INDisck = new Disk(data);


        minHeap.insert(7);
        minHeap.insert(2);
        minHeap.insert(2);
        minHeap.insert(4);
        minHeap.insert(3);

        print();

        minHeap.pop();
        System.out.println("POP");
        print();
        System.out.println("Insert 5");
        minHeap.insert(5);

        print();

        minHeap.pop();
        System.out.println("POP");
        print();
        System.out.println("Insert 3");
        minHeap.insert(3);

        print();


    }

    public void print(){
        for (int i = 0; i < minHeap.getHeap().length; i++){
            System.out.print(minHeap.getHeap()[i]);
        }
        System.out.println();
        System.out.println("Deadspace: " + minHeap.getDeadSpace());
    }
}
