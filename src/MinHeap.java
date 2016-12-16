/**
 * Created by Sander on 13-12-2016.
 */
public class MinHeap {
    private int[] heap;
    private int numberOfNodes;
    private int deadSpace;

    /**
     * constructor for new heap
     *
     * @param size
     */
    public MinHeap(int size) {
        this.heap = new int[size];
        this.numberOfNodes = 0;
        this.deadSpace = 0;
    }

    public int getSmallest() {
        return heap[0];
    }

    /**
     * Build a heap from a givven array
     *
     * @param numbers to add
     */
    public void buildHeap(int[] numbers) {
        //reset deadspace and numberOfNodes
        deadSpace = 0;
        numberOfNodes = 0;

        //valid input checking
        if (numbers.length > heap.length) {
            System.out.println("Too much to build given");
            return;
        }

        //add the numbers
        for (int i = 0; i < numbers.length; i++) {
            insert(numbers[i]);
        }
    }

    /**
     * Insert a number in the tree
     *
     * @param number to be added number
     */
    public void insert(int number) {
        heap[numberOfNodes] = number;
        int currentItem = numberOfNodes;
        numberOfNodes++;

        //while my parent is bigger perculate up
        while (heap[getParent(currentItem)] > heap[currentItem]) {
            swap(getParent(currentItem), currentItem);
            currentItem = getParent(currentItem);
        }

    }

    /**
     * Add a number to the deadspace
     *
     * @param number to be added
     */
    public void insertInDeadSpace(int number) {
        heap[heap.length - deadSpace] = number;
    }

    public int pop() {
        int popped = heap[0];
        heap[0] = heap[numberOfNodes - 1];
        int currentPosition = 0;

        //find somebody to swap with as long as a node below me is lower than me
        while (heap[getLeftChild(currentPosition)] < heap[currentPosition] || heap[getRightChild(currentPosition)] < heap[currentPosition]) {
            //swap with the lower of my children

            if ((heap[getLeftChild(currentPosition)] < heap[getRightChild(currentPosition)]) && !isDeadSpace(getLeftChild(currentPosition))) {
                swap(currentPosition, getLeftChild(currentPosition));
                currentPosition = getLeftChild(currentPosition);
            } else {
                if (isDeadSpace(getRightChild(currentPosition))){
                    if ((heap[getLeftChild(currentPosition)] < heap[currentPosition]) && !isDeadSpace(getLeftChild(currentPosition))){
                        swap(currentPosition, getLeftChild(currentPosition));
                        break;
                    }
                    break;
                }

                swap(currentPosition, getRightChild(currentPosition));
                currentPosition = getRightChild(currentPosition);
            }

            //if we are a leaf node now then we are done, if my left child is deadSpace we're also done
            if (isLeaf(currentPosition) || isDeadSpace(getLeftChild(currentPosition))) {
                break;
            }

            //if we don't have a right child(deadspace included) now we can try to swap else we're done
            if (getRightChild(currentPosition) > numberOfNodes - 1 || isDeadSpace(getRightChild(currentPosition))) {
                if (heap[getLeftChild(currentPosition)] < heap[currentPosition]) {
                    swap(currentPosition, getLeftChild(currentPosition));
                    break;
                } else {
                    break;
                }
            }
        }


        heap[numberOfNodes - 1] = -1;
        numberOfNodes--;

        return popped;
    }


    public void incrementDeadspace() {
        deadSpace++;
    }

    /**
     * Switches 2 nodes with each other
     *
     * @param position1 first node to switch
     * @param position2 second node to switch
     */
    private void swap(int position1, int position2) {
        int temp = heap[position1];
        heap[position1] = heap[position2];
        heap[position2] = temp;
    }

    /**
     * Returns the left child of a position, if its in the deadspace returns max
     *
     * @param position the position
     * @return childPosition or max
     */
    private int getLeftChild(int position) {
        int childPosition = 2 * position + 1;
//        if (isDeadSpace(childPosition) || childPosition > numberOfNodes - 1){
//            return Integer.MAX_VALUE;
//        }
        return childPosition;
    }

    /**
     * Returns the right child of a position, if its in the deadspace returns max
     *
     * @param position the position
     * @return childPosition or max
     */
    private int getRightChild(int position) {
        int childPosition = 2 * position + 2;
//        if (isDeadSpace(childPosition) || childPosition > numberOfNodes - 1){
//            return Integer.MAX_VALUE;
//        }
        return childPosition;
    }

    private int getParent(int position) {
        return (position - 1) / 2;
    }

    private boolean isLeaf(int position) {
        return getLeftChild(position) >= this.numberOfNodes;
    }

    private boolean isDeadSpace(int position) {
        return position >= (heap.length - deadSpace);
    }

    public int[] getHeap() {
        return heap;
    }

    public int getDeadSpaceSize() {
        return deadSpace;
    }

}
