/**
 * Non-preemptive priority scheduling algorithm.
 */

import java.util.*;

public class Priority implements Algorithm
{
    private List<Task> queue;           // Queue list
    private Task currentTask;           // Current task list
    private List<Task> finishedTasks;   // Finished tasks list

    // -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    // Priority constructor
    public Priority(List<Task> queue) {
        this.queue = queue;
        this.finishedTasks = new ArrayList<Task>(queue.size());
    }

    // -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    // It runs the Priority schedule
    public void schedule() {
        System.out.println("Priority Scheduling \n");

        while (!queue.isEmpty()) {              // While the queue is not empty
            currentTask = pickNextTask();
            CPU.run(currentTask, currentTask.getBurst());
            System.out.println("Task " + currentTask.getName() + " finished.\n");
        }
    }

    // -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    // It picks the next task based on Priority Scheduling
    public Task pickNextTask() {
        Task temp;
        Task nextTask = null;
        int highTask = 0;

        for(int i = 0; i < queue.size(); i++){    // To get the highest priority
            temp = queue.get(i);
            if(temp.getPriority() > highTask){
                highTask = temp.getPriority();
                nextTask = temp;
            }
        }
        if (currentTask != null){               // If current task is not null
            finishedTasks.add(currentTask);     // It picks the first task in the queue
        }

        if (queue.size() == 1){                 // If it's the last task
            finishedTasks.add(queue.get(0));
        }
        queue.remove(nextTask);
        return nextTask;
    }

    // -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    // It calculates the average wait time of Priority
    public double getAverageWaitTime() {
        int waitDuration = 0;
        Task temp;
        int totalTasks = finishedTasks.size();
        for(int i = totalTasks-1; i >= 0; i--){
            temp = finishedTasks.remove(0);
            finishedTasks.add(temp);
            waitDuration = waitDuration + (temp.getBurst() * i);
        }
        return waitDuration/totalTasks;
    }

    // -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    // It calculates the average response time of Priority
    public double getAverageResponseTime() {
        return this.getAverageWaitTime();
    }

    // -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    // It calculates the average turnaround time of Priority
    public double getAverageTurnaroundTime() {
        int turnaround = 0;
        Task temp;
        int totalTasks = finishedTasks.size();
        for(int i = totalTasks; i > 0; i--){
            temp = finishedTasks.remove(0);
            finishedTasks.add(temp);
            turnaround = turnaround + (temp.getBurst() * i);
        }
        return turnaround/totalTasks;
    }
}