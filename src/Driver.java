/**
 * Driver.java
 * 
 * Demonstrates different scheduling algorithms.
 * 
 * Usage:
 *  
 *  java Driver <schedule> <algorithm>
 *
 * where 
 *  schedule is schedule of tasks
 *
 *  algorithm = [FCFS, SJF, PRI, RR, PRI-RR]
 */
/**
 * Albert Toscano and Dorothy Nguyen
 * December 5, 2021
 * CECS 326 - Operating Systems
 * Project 4: CPU Scheduler
 *
 * This program demonstrates the different scheduling algorithms:
 *      - First-come, First-served   (FCFS)
 *      - Shortest Job First         (SJF)
 *      - Priority Scheduling        (PRI)
 *      - Round Robin                (RR)
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Driver
{
    public static void main(String[] args) throws IOException {

        if (args.length != 2) {
            System.err.println("Usage: java Driver <algorithm> <schedule>");
            System.exit(0);
        }
        BufferedReader inFile = new BufferedReader(new FileReader(args[1]));

        String schedule;

        // create the queue of tasks
        List<Task> queue = new ArrayList<Task>();

        // read in the tasks and populate the ready queue        
        while ( (schedule = inFile.readLine()) != null) {
            String[] params = schedule.split(",\\s*");
            queue.add(new Task(params[0], Integer.parseInt(params[1]), Integer.parseInt(params[2])));
        }

        inFile.close();
        
        Algorithm scheduler = null;
        String choice = args[0].toUpperCase();
        switch(choice) {
            case "FCFS":
                scheduler = new FCFS(queue);
                break;
            case "SJF":
                scheduler = new SJF(queue);
                break;
            case "PRI":
                scheduler = new Priority(queue);
                break;
            case "RR":
                scheduler = new RR(queue);
                break;
            default:
                System.err.println("Invalid algorithm");
                System.exit(0);
        }

        // start the scheduler
        scheduler.schedule();

        // Displays calculations
        System.out.println("Average Wait Time = " + scheduler.getAverageWaitTime());
        System.out.println("Average Turnaround Time = " + scheduler.getAverageTurnaroundTime());
        System.out.println("Average Response Time = " + scheduler.getAverageResponseTime());

        inFile.close();         // It closes inFile
    }
}
