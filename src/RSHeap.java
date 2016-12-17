/**
 * RSHeap that is implemented as an array
 *
 * Has an index 0 that is used
 */
public class RSHeap {
    private int[] heap;
    private int numberOfNodes;
    private int deadSpace;

    /**
     * constructor for new heap
     * @param size of the heap
     */
    public RSHeap(int size) {
        this.heap = new int[size];
        this.numberOfNodes = 0;
        this.deadSpace = 0;
    }

    /**
     * Build a heap from a given array
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
     * @param number to be added number
     */
    public void insert(int number) {
        heap[numberOfNodes] = number;
        int currentPosition = numberOfNodes;
        numberOfNodes++;

        //while my parent is bigger perculate up
        while (heap[getParent(currentPosition)] > heap[currentPosition]) {
            swap(getParent(currentPosition), currentPosition);
            currentPosition = getParent(currentPosition);
        }
    }

    /**
     * Add a number to the first index of the deadspace
     * @param number to be added
     */
    public void insertInDeadSpace(int number) {
        heap[heap.length - deadSpace] = number;
    }

    /**
     * Gets the lowest value of the heap and reassembles the heap
     * @return the lowest value
     */
    public int pop() {
        int popped = heap[0];
        heap[0] = heap[numberOfNodes - 1];
        int currentPosition = 0;

        //find somebody to swap with as long as a node below me is lower than me(and is available)
        while (canSwap(currentPosition)) {

            //swap with the lower of my children
            if ((heap[getLeftChild(currentPosition)] < heap[getRightChild(currentPosition)])) {
                swap(currentPosition, getLeftChild(currentPosition));
                currentPosition = getLeftChild(currentPosition);
            } else {
                swap(currentPosition, getRightChild(currentPosition));
                currentPosition = getRightChild(currentPosition);
            }
        }

        //If we arent a leaf see if we can swap with our left child
        if (!isLeaf(currentPosition) && !isDeadSpace(getLeftChild(currentPosition)) && heap[currentPosition] > heap[getLeftChild(currentPosition)]) {
            swap(currentPosition, getLeftChild(currentPosition));
        }

        //put -1 in the empty space, for a better overview
        heap[numberOfNodes - 1] = -1;
        numberOfNodes--;
        return popped;
    }

    /**
     * Checks if children for a node are swappable
     * @param position of the node
     * @return true if children are ok(Not deadspace and Not out of bounds and lower values)
     */
    private boolean canSwap(int position) {
        //check if children are deadspace or out of bounds
        if (isLeaf(position) || isDeadSpace(getLeftChild(position)) || getRightChild(position) > numberOfNodes || isDeadSpace(getRightChild(position))) {
            return false;

            //check if children have a lower value than the parent
        } else if (heap[position] > heap[getLeftChild(position)] || heap[position] > heap[getRightChild(position)]) {
            return true;
        }

        //default return false
        return false;

    }

    /**
     * Increments the deadspace
     */
    public void incrementDeadspace() {
        deadSpace++;
    }

    /**
     * Switches 2 nodes with each other
     * @param position1 first node to switch
     * @param position2 second node to switch
     */
    private void swap(int position1, int position2) {
        int temp = heap[position1];
        heap[position1] = heap[position2];
        heap[position2] = temp;
    }

    /**
     * Returns the left child's position of a node
     * @param position of the node
     * @return childPosition
     */
    private int getLeftChild(int position) {
        return 2 * position + 1;
    }

    /**
     * Returns the right child's position of a node
     * @param position of the node
     * @return childPosition
     */
    private int getRightChild(int position) {
        return 2 * position + 2;
    }

    /**
     * Returns the node's parent's position
     * @param position of the node
     * @return the parent's position
     */
    private int getParent(int position) {
        return (position - 1) / 2;
    }

    /**
     * Checks if a node is a leaf
     * @param position the position of the node
     * @return true or false
     */
    private boolean isLeaf(int position) {
        return getLeftChild(position) >= this.numberOfNodes;
    }

    /**
     * Returns if a node is inside the deadspace
     * @param position of the node
     * @return true or false
     */
    private boolean isDeadSpace(int position) {
        return position >= (heap.length - deadSpace);
    }

    //Getters
    public int[] getHeap() {
        return heap;
    }
    public int getDeadSpaceSize() {
        return deadSpace;
    }
    public int getNumberOfNodes() {
        return numberOfNodes;
    }
}
