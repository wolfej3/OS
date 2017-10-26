package operatingsystems;

/**
 *
 * @author jwolfe
 */
public class Job {

    private String Event_Type;
    private int time;
    private int job_number;
    private int memory;
    private int runtime;
    private int state;
    private int burst;

//For A jobs
    public Job(String Event_Type, int Time, int job_number, int memory_required, int runtime) {
        this.Event_Type = Event_Type;
        this.time = Time;
        this.job_number = job_number;
        this.memory = memory_required;
        this.runtime = runtime;
    }
// for D jobs and T jobs

    public Job(String Event_Type, int time) {
        this.Event_Type = Event_Type;
        this.time = time;
    }
// for I jobs

    public Job(String Event_Type, int time, int burst) {
        this.Event_Type = Event_Type;
        this.time = time;
        this.burst = burst;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getBurst() {
        return burst;
    }

    public void setBurst(int burst) {
        this.burst = burst;
    }

    public String getEvent_Type() {
        return Event_Type;
    }

    public void setEvent_Type(String Event_Type) {
        this.Event_Type = Event_Type;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getJob_number() {
        return job_number;
    }

    public void setJob_number(int job_number) {
        this.job_number = job_number;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

}
