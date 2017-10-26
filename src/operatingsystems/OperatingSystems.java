/*
This program is a job scheduler and process scheduler
 */
package operatingsystems;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author jwolfe
 */
public class OperatingSystems {

    private static int Time = 0;
    private static int Memory = 512;
    private static final int TOTAL_MEM = 512;
    private static final int firstQuantum = 100;
    private static final int secondQuantum = 300;
    private static boolean CPUstatus = false;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        DyQueue JobQueue = new DyQueue();
        DyQueue FirstLevel = new DyQueue();
        DyQueue SecondLevel = new DyQueue();
        ArrayList finished = new ArrayList<Job>();
        //gets jobs from std input
         while (cin.hasNextLine()) {
        String Line = cin.nextLine();
        String delims = "[ ]+";
        String[] tokens = Line.split(delims);
        if ("A".equals(tokens[0])) {
            Job n = new Job(tokens[0], Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]));
            JobQueue.enqueue(n);
        }
        if ("D".equals(tokens[0])) {
            Job d = new Job(tokens[0], Integer.parseInt(tokens[1]));
            JobQueue.enqueue(d);
        }
      //  System.out.println(tokens[0]);
       // System.out.println(tokens[1]);
      //  System.out.println(tokens[2]);
        // System.out.println(tokens[3]);
        //  System.out.println(tokens[4]);
         }
        while (true) {

            if (!JobQueue.isQueueEmpty()) {
                if (JobQueue.peek().getTime() <= Time) {
//check to see if there is enough total memory (eg if it can ever run)
                    if (JobQueue.peek().getMemory() > TOTAL_MEM) {
                        //throw away process
                        System.out.println("This Job exceeds the Systems Memory Capacity ");
                        JobQueue.dequeue();
                    }
                    //Check to see if there is enough availible memory right now 
                    if (JobQueue.peek().getMemory() > Memory) {
                        //Move process to the back of the Job scheduling queue
                        Job c = JobQueue.dequeue();
                        JobQueue.enqueue(c);
                    } else {
                        Job c = JobQueue.dequeue();
                        Memory = Memory - c.getMemory(); //allocate memory
                        FirstLevel.enqueue(c); //add to first level   
                        System.out.println("Event: " + c.getEvent_Type() + "  " + "Time: " + Time);
                    }
                }
            }
            //end Job Scheduling
            //Begin Process Schedling 
            if (!FirstLevel.isQueueEmpty()) { //if Firstlevel is not empty
                Job b = FirstLevel.dequeue();
                b = runFirst(b);
                if (b.getRuntime() > 0) {
                    //put in second level queue
                    SecondLevel.enqueue(b);
                } else {
                    //put in finished process list
                    finished.add(b);
                    //return memory
                    Memory = Memory + b.getMemory();
                    if("D".equals(b.getEvent_Type())){
                        printStatus();
                    }
                }
            }
            //If first level is empty and second level is not, run the second level processes
            if (FirstLevel.isQueueEmpty() && !SecondLevel.isQueueEmpty()) {
                //run secondLevel
                Job c = SecondLevel.dequeue();
                c = runSecond(c);
                if (c.getRuntime() <= 0) {
                    finished.add(c); //if finished add to finished list
                    Memory = Memory + c.getMemory();
                } else {
                    //If not finished, put back in second level queue
                    SecondLevel.enqueue(c);
                }
            }

            Time++;
        }
    }

    public static void runFirst(Job n) {
       
    }

    public static Job runSecond(Job n) {
        int runtime = n.getRuntime();
        runtime = runtime - secondQuantum;
        n.setRuntime(runtime);
        return n;
    }

    private static void printStatus() {
       System.err.println("Method not yet implemented! ");
       System.exit(1);
       
    }

}
