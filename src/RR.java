/**
 * Non-preemptive priority scheduling algorithm using RR.
 *
 * This algorithm will run tasks according to round-robin scheduling.
 */

import java.util.*;

public class RR implements Algorithm
{
    private List<Task> queue;                           // List of queue
    private List<Task> finishedTasks;                   // List of finished tasks
    private List<Task> runningTasks;                    // List of running tasks
    private Task currentTask;                           // Current task
    private Task prevTask;                              // Previous task
    private int turnaround;                             // Turnaround time
    private int numbTasks;                              // Number of tasks

    // -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    // RR constructor
    public RR(List<Task> queue) {
        this.queue = queue;
        this.numbTasks = queue.size();
        this.finishedTasks = new ArrayList<Task>(numbTasks);
        this.runningTasks = new ArrayList<Task>(numbTasks);
        this.turnaround = 0;

    }

    // -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    // It runs the RR Schedule
    public void schedule() {
        System.out.println("Round Robin Scheduling \n");
        int runDuration;                                // Task run duration

        while (!queue.isEmpty()) {                      // while there are tasks in queue
            currentTask = pickNextTask();               // It defines current task

            if(!runningTasks.contains(currentTask)){    // It sets response time
                currentTask.setResponse(CPU.getTime());
            }
            else{
                if(!currentTask.equals(prevTask)){
                    currentTask.setWaitTime(CPU.getTime()
                            - (currentTask.getFinishedBurst()
                            + currentTask.getResponse()));
                }
            }

            if(currentTask.getBurst() < 10)             // Assigning run duration
                runDuration = currentTask.getBurst();
            else
                runDuration = 10;

            CPU.run(currentTask, runDuration);          // It runs the task

            currentTask.setFinishedBurst(currentTask.getFinishedBurst() + runDuration); // It updates finished burst time
            currentTask.setBurst(currentTask.getBurst() - runDuration);                 // It updates burst time

            prevTask = currentTask;                    // previous task is now current task

            if (currentTask.getBurst()>0){              // If task is not finished
                queue.add(currentTask);                 // Add it back to the queue
                runningTasks.add(currentTask);          // Add it to the running tasks
            }
            else{                                       // If task is finished
                System.out.println("Task "
                        + currentTask.getName()
                        + " finished.\n");
                finishedTasks.add(currentTask);         // Add current Task to finished tasks
                runningTasks.remove(currentTask);       // Removes current Task from running tasks
                turnaround += CPU.getTime();            // Updates turnaround time
            }
        }

    }

    // -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    // It picks the next task based on RR Scheduling
    public Task pickNextTask() {
        Task temp;
        Task nextTask = null;
        int highTask = 0;

        //Finds the task with the highest priority
        for(int i = 0; i<queue.size(); i++){
            temp = queue.get(i);
            if(temp.getPriority()>highTask){
                highTask = temp.getPriority();
                nextTask = temp;
            }
        }
        queue.remove(nextTask);
        return nextTask;
    }

    // -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    // It calculates the average wait time of RR
    public double getAverageWaitTime() {
        int waittime=0;
        for(int i = 0; i< numbTasks; i++){
            waittime += finishedTasks.get(i).getResponse();
            waittime += finishedTasks.get(i).getWaitTime();
        }
        return waittime/ numbTasks;
    }

    // -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    // It calculates the average turnaround time of RR
    public double getAverageTurnaroundTime() {
        return turnaround/ numbTasks;
    }

    // -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    // It calculates the average response time of RR
    public double getAverageResponseTime() {
        int response = 0;
        //Get response time from each task
        for(int i = 0; i< numbTasks; i++){
            response += finishedTasks.get(i).getResponse();
        }
        return response/ numbTasks;
    }

}