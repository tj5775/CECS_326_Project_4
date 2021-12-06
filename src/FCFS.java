import java.util.ArrayList;
import java.util.List;

/**
 * First-Come, First-Served scheduling algorithm.
 */

public class FCFS implements Algorithm{
    private List<Task> queue;
    private Task currentTask;
    private List<Task> finishedTasks;

    // -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    // FCFS constructor
    public FCFS(){}
    public FCFS(List<Task> queue){
        this.queue = queue;
        this.currentTask = null;
        this.finishedTasks = new ArrayList<Task>(queue.size());
    }

    // -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    // It runs the FCFS schedule
    @Override
    public void schedule() {
        System.out.println("First-Come, First-Served Scheduling \n");

        while (!queue.isEmpty()) {
            currentTask = pickNextTask();

            CPU.run(currentTask, currentTask.getBurst());
            System.out.println("Task " + currentTask.getName() + " finished.\n");
        }
    }

    // -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    // It picks the next task based on FCFS Scheduling
    @Override
    public Task pickNextTask() {
        if (currentTask != null){           // If current task is not null
            finishedTasks.add(currentTask); // It picks the first task in the queue
        }
        if (queue.size() == 1){             // If it's the last task
            finishedTasks.add(queue.get(0));
        }
        return queue.remove(0);
    }

    // -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    // It calculates the average wait time of FCFS
    @Override
    public double getAverageWaitTime() {
        int waitDuration = 0;
        Task temp;
        int totalTasks = finishedTasks.size();
        for(int i = totalTasks - 1; i >= 0; i--){
            temp = finishedTasks.remove(0);
            finishedTasks.add(temp);
            waitDuration = waitDuration + (temp.getBurst() * i);
        }
        return waitDuration/totalTasks;
    }

    // -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    // It calculates the average turnaround time of FCFS
    @Override
    public double getAverageTurnaroundTime() {
        return this.getAverageWaitTime();
    }

    // -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    // It calculates the average response time of FCFS
    @Override
    public double getAverageResponseTime() {
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