/**
 * "Virtual" CPU
 *
 * This virtual CPU also maintains system time.
 *
 */
 
public class CPU
{
    // TIMES value for simulating executing the task
    public final static int TIMES = 10;

    // CPU time
    private static int time = 0;

    /**
     * Run the specified task for the specified slice of time.
     */
    public static void run(Task task, int slice) {
        System.out.println("Will run " + task);

        try {
            Thread.sleep(slice * TIMES);    // It sleeps time slice duration in order to simulate executing of task
        }
        catch (InterruptedException ie) {
            System.err.println(ie);
        }

        // It updates the current CPU time
        time += slice;
    }

    /**
     * Returns the current CPU time
     */
    public static int getTime() {
        return time;
    }

}
