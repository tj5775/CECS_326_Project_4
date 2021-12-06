/**
 * Interface representing a generic scheduling algorithm.
 *
 */

public interface Algorithm
{
    /**
     * Invokes the scheduler
     */
    public abstract void schedule();

    /**
     * Selects the next task using the appropriate scheduling algorithm
     */
    public abstract Task pickNextTask();

    /**
     * Calculates the average wait time of all tasks
     */
    public abstract double getAverageWaitTime();

    /**
     * Calculates the average turnaround time of all tasks
     */
    public abstract double getAverageTurnaroundTime();

    /**
     * Calculates the average response time of all tasks
     */
    public abstract double getAverageResponseTime();
}
