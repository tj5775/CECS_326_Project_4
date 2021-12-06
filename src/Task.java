/**
 * Task to be scheduled by the scheduling alogrithm.
 *
 * Each task is represented by
 *
 *  String name - a task name, not necessarily unique
 *
 *  int tid - unique task identifier
 *
 *  int priority - the relative priority of a task where a higher number indicates
 *  higher relative priority.
 *
 *  int burst - the CPU burst of this this task
 */

import java.util.concurrent.atomic.AtomicInteger;

public class Task
{
    private String name;
    private int tid;            // Task ID
    private int priority;       // Task priority
    private int burst;          // Task burst time
    private int waitTime;       // Task wait time
    private int finishedBurst;  // Finished burst time
    private int response;       // Task response time

    /**
     * We use an atomic integer to assign each task a unique task id.
     */
    private static AtomicInteger tidAllocator = new AtomicInteger();

    // -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    // Task constructor
    public Task(String name, int priority, int burst) {
        this.name = name;
        this.priority = priority;
        this.burst = burst;
        this.waitTime = 0;
        this.finishedBurst = 0;
        this.response = 0;

        this.tid = tidAllocator.getAndIncrement();
    }

    // -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    /**
     * Appropriate getters
     */
    public String getName() {
        return name;
    }

    public int getTid() {
        return tid;
    }

    public int getPriority() {
        return priority;
    }

    public int getBurst() {
        return burst;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public int getFinishedBurst() {
        return finishedBurst;
    }

    public int getResponse() {
        return response;
    }

    // -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    /**
     * Appropriate setters
     */
    public int setPriority(int priority) {
        this.priority = priority;

        return priority;
    }

    public int setBurst(int burst) {
        this.burst = burst;

        return burst;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    public void setFinishedBurst(int finishedBurst) {
        this.finishedBurst = finishedBurst;
    }

    public void setResponse(int response) {
        this.response = response;
    }

    // -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    /**
     * We override equals() so we can use a
     * Task object in Java collection classes.
     */
    public boolean equals(Object other) {
        if (other == this)
            return true;

        if (!(other instanceof Task))
            return false;

        /**
         * Otherwise we are dealing with another Task.
         * two tasks are equal if they have the same tid.
         */
        Task rhs = (Task)other;
        return (this.tid == rhs.tid) ? true : false;
    }

    // -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    public String toString() {
        return
                "Name: " + name + "\n" +
                        "Tid: " + tid + "\n" +
                        "Priority: " + priority + "\n" +
                        "Burst: " + burst + "\n";
    }
}
