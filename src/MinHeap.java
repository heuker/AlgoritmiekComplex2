/**
 * Created by Sander on 13-12-2016.
 */
public class MinHeap {
    private int[] heap;
    private int numberOfNodes;
    private int deadSpace;

    /**
     * constructor for new heap
     * @param size
     */
    public MinHeap(int size) {
        this.heap = new int[size];
        this.numberOfNodes = 0;
        this.deadSpace = 0;
    }

    /**
     * Insert a number in the tree
     * @param number to be added number
     */
    public void insert(int number){
//        if (number < heap[0]){
//            deadSpace++;
//            heap[heap.length - deadSpace] = number;
//            return;
//        }

        heap[numberOfNodes] = number;
        int currentItem = numberOfNodes;
        numberOfNodes++;

        //while my parent is bigger perculate up
        while( heap[getParent(currentItem)] > heap[currentItem] ){
            swap(getParent(currentItem),currentItem);
            currentItem = getParent(currentItem);
        }

    }

    public int pop(){
        int popped = heap[0];

        heap[0] = heap[numberOfNodes - 1];

        int currentPosition = 0;
        boolean goodTree = false;
        while(heap[getLeftChild(currentPosition)] < heap[currentPosition] || heap[getRightChild(currentPosition)] < heap[currentPosition]) {
            //swap with the lower of my children
            if (heap[getLeftChild(currentPosition)] < heap[getRightChild(currentPosition)]){
                swap(currentPosition, getLeftChild(currentPosition));
                currentPosition = getLeftChild(currentPosition);
            } else {
                swap(currentPosition, getRightChild(currentPosition));
                currentPosition = getRightChild(currentPosition);
            }

            //if we are a leaf node now than we are done
            if (isLeaf(currentPosition)){
                break;
            }

            //if we dont have a right child now we can try to swap else we're done
            if (getRightChild(currentPosition) > numberOfNodes - 1 && heap[getLeftChild(currentPosition)] < heap[currentPosition]){
                swap(currentPosition, getLeftChild(currentPosition));
            } else {
                break;
            }
        }



//        swap(currentPosition, (numberOfNodes-1));
        heap[numberOfNodes - 1] = -1;
        numberOfNodes--;

        return popped;
    }

    public void incrementDeadspace(){
        deadSpace++;
    }

    /**
     * Switches 2 nodes with each other
     * @param position1 first node to switch
     * @param position2 second node to switch
     */
    private void swap(int position1, int position2){
        int temp = heap[position1];
        heap[position1] = heap[position2];
        heap[position2] = temp;
    }

    /**
     * Returns the left child of a position, if its in the deadspace returns max
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

    private boolean isLeaf(int position){
        return getLeftChild(position) >= this.numberOfNodes;
    }

    private boolean isDeadSpace(int position){
        return position > (heap.length - deadSpace);
    }

    public int[] getHeap() {
        return heap;
    }

    public int getDeadSpace() {
        return deadSpace;
    }

    public int getNumberOfNodes() {
        return numberOfNodes;
    }
}
