/**
 * A min heap written like an array
 */
public class RSHeap {
    private int[] heap;
    private int deadSpace;

    public RSHeap() {
    }

    /**
     * Build a new heap
     * @param numbers
     */
    public void buildHeap(int[] numbers){
        //deadspace index starts at +1 last index
        deadSpace = numbers.length;
        //take over the numbers
        for (int i = 0; i < numbers.length; i++){
            if (i != 0 && heap[0] > numbers[i]){
                int stored = heap[0];
                heap[0] = numbers[i];
                heap[i] = stored;
            } else {
                heap[i] = numbers[i];
            }
        }
    }

    public void increaseDeadspace(){
        deadSpace++;
    }

    public int[] getHeap() {
        return heap;
    }

    public int getDeadSpace() {
        return deadSpace;
    }

    public void add(int number){

    }

}
