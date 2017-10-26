package operatingsystems;

/**
 *
 * @author jwolfe
 */
public class DyQueue {

    private int capacity = 900;
    private Job queueArr[];
    private int front = 0;
    private int rear = -1;
    private int currentSize = 0;
    private Job temp;

    public DyQueue() {
        queueArr = new Job[this.capacity];
    }

    /**
     * this method adds element at the end of the queue.
     *
     * @param item
     */
    public void enqueue(Job item) {

        if (isQueueFull()) {
            //  System.out.println("Queue is full, increase capacity...");
            increaseCapacity();
        }
        rear++;
        if (rear >= queueArr.length && currentSize != queueArr.length) {
            rear = 0;
        }
        queueArr[rear] = item;
        currentSize++;
        // System.out.println("Adding: " + item);
    }

    /**
     * this method removes an element from the top of the queue
     */
    public Job dequeue() {
        if (isQueueEmpty()) {
            System.err.println("Underflow ! Unable to remove element from Queue, Halting simulation");
            System.exit(1);
        } else {
            front++;
            if (front > queueArr.length - 1) {
                //System.out.println("removed: "+queueArr[front-1]);
                temp = queueArr[front - 1];
                front = 0;

            } else {
                //System.out.println("removed: "+queueArr[front-1]);
                temp = queueArr[front - 1];
            }
            currentSize--;
            return temp;
        }
        return null;
    }

    /**
     * This method checks whether the queue is full or not
     *
     * @return boolean
     */
    public boolean isQueueFull() {
        boolean status = false;
        if (currentSize == queueArr.length) {
            status = true;
        }
        return status;
    }

    /**
     * This method checks whether the queue is empty or not
     *
     * @return
     */
    public boolean isQueueEmpty() {
        boolean status = false;
        if (currentSize == 0) {
            status = true;
        }
        return status;
    }

    private void increaseCapacity() {

        //create new array with double size as the current one.
        int newCapacity = this.queueArr.length * 2;
        Job[] newArr = new Job[newCapacity];
        //copy elements to new array, copy from rear to front
        int tmpFront = front;
        int index = -1;
        while (true) {
            newArr[++index] = this.queueArr[tmpFront];
            tmpFront++;
            if (tmpFront == this.queueArr.length) {
                tmpFront = 0;
            }
            if (currentSize == index + 1) {
                break;
            }
        }
        //make new array as queue
        this.queueArr = newArr;
        // System.out.println("New array capacity: "+this.queueArr.length);
        //reset front & rear values
        this.front = 0;
        this.rear = index;
    }

    //returns first in queue without removing
    public Job peek() {
        return queueArr[front];
    }
}
