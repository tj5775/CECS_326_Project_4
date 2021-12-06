/**
 * SJF scheduling algorithm.
 */

import java.util.ArrayList;
import java.util.List;

public class SJF implements Algorithm
{
  private List<Task> queue;
  private Task currentTask;
  private List<Task> finishedTasks;

  // SJF constructor
  public SJF(List<Task> queue) {
    this.queue = queue;
    this.finishedTasks = new ArrayList<Task>(queue.size());
  }

  // -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
  // It runs the SJF schedule
  public void schedule() {
    System.out.println("Shortest Job First Scheduling \n");
    
    while (!queue.isEmpty()) {
      currentTask = pickNextTask();
      CPU.run(currentTask, currentTask.getBurst());
      System.out.println("Task " + currentTask.getName() + " finished.\n");
    }
  }

  // -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
  // It picks the next task based on SJF Scheduling
  public Task pickNextTask() {
    Task temp;
    Task nextTask = null;
    int minTask = Integer.MAX_VALUE;

    for(int i = 0; i<queue.size(); i++){        // To get the task with the shortest burst time
      temp = queue.get(i);
      if(temp.getBurst()<minTask){
        minTask = temp.getBurst();
        nextTask = temp;
      }
    }
    if (currentTask != null){                 // To add current task to finished tasks
      finishedTasks.add(currentTask);
    }

    if (queue.size() == 1){                   // In case there are tasks not added to finished tasks
      finishedTasks.add(queue.get(0));
    }
    queue.remove(nextTask);
    return nextTask;
  }

  // -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
  // It calculates the average wait time of SJF
  public double getAverageWaitTime() {
    int waitDuration = 0;
    Task temp;
    int numbTasks = finishedTasks.size();
    for(int i = numbTasks - 1; i >= 0; i--){
      temp = finishedTasks.remove(0);
      finishedTasks.add(temp);
      waitDuration = waitDuration + (temp.getBurst() * i);
    }
    return waitDuration/numbTasks;
  }

  // -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
  // It calculates the average response time of SJF
  public double getAverageResponseTime() {
    return this.getAverageWaitTime();
  }

  // -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
  // It calculates the average turnaround time of SJF
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
