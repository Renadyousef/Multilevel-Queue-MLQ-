
package osproject;

import java.io.*;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
public class Scheduler {
	List<PCB> q1 = new ArrayList<>();
	List<PCB> q2 = new ArrayList<>();
	int Quantum = 3 ; 
	
	public void addProcess (String processID ,int priority, int arrival_Time, int cpu_Burst) {
	PCB process = new PCB(processID, priority, arrival_Time, cpu_Burst);
	if (priority == 1)
	q1.add(process); 
	else
	q2.add(process);
	}
        
	public String calculateAverages(List<PCB> schedule_Order) {
            if (schedule_Order.isEmpty()) {
    return "Schedule Order is empty. Cannot calculate averages.";
  }

	int total_TurnaroundTime = 0;
	int total_WaitingTime = 0;
	int total_ResponseTime = 0;
	// first Calculate totals
        int numProcesses = schedule_Order.size();
        for (int i = 0; i < numProcesses; i++) {
         PCB process = schedule_Order.get(i);
         total_TurnaroundTime += process.turnaroundTime;
         total_WaitingTime += process.waitingTime;
         total_ResponseTime += process.responseTime;
}
        // average TRT WT R
	double avgTurnaroundTime = total_TurnaroundTime / numProcesses;
	double avgWaitingTime =  total_WaitingTime / numProcesses;
	double avgResponseTime = total_ResponseTime /numProcesses;
	
	String averages = "Average Turnaround Time: " + avgTurnaroundTime + "\n" 
	+ "Average Waiting Time: " + avgWaitingTime + "\n" 
			+ "Average Response Time: " + avgResponseTime ;
	 return averages;
}
        public void schedule() {
		List<PCB> scheduleOrder = new ArrayList<>();
		  int currentTime = 0;
		  int notFinish = 0; // Flag to track unfinished shortest job in queue 2

		  // Round Robin 
		  while (!q1.isEmpty() || !q2.isEmpty()) {
		    // Handle queue 1 processes arriving at current time
		    if (!q1.isEmpty() && q1.get(0).arrival_Time <= currentTime) {
		      PCB currentProcess = q1.remove(0);
		      System.out.print(currentProcess.process_ID + " | ");

		      // Update process information for first time execution
		      if (currentProcess.start_Time == 0) {
		        currentProcess.start_Time = currentTime;
                      }
		      int remainingBurst = currentProcess.cpu_Burst;

		      // Handle process with remaining burst exceeding quantum
		      if (remainingBurst > Quantum) {
		        currentTime += Quantum;
		        currentProcess.new_arrivalTime = currentTime;
		        currentProcess.cpu_Burst -= Quantum;
		        q1.add(currentProcess);

		        // Sort queue 1 based on new arrival time after quantum execution
		        Collections.sort(q1, Comparator.comparingInt(process -> process.new_arrivalTime));
		      } 
                      else { // Process completes within quantum
		        currentTime += remainingBurst;
		        currentProcess.responseTime = currentProcess.start_Time - currentProcess.arrival_Time;
		        currentProcess.terminationTime = currentTime;
		        currentProcess.turnaroundTime = currentProcess.terminationTime - currentProcess.arrival_Time;
		        currentProcess.waitingTime = currentProcess.turnaroundTime - currentProcess.stCpuBurst;
		        scheduleOrder.add(currentProcess);
		      }
		    } 
                    else if (q2.isEmpty()) { // Handle idle CPU if queue 2 is empty
		      System.out.print(" NON |");
		      currentTime++;
		    } 
                    else { // Handle queue 2 processes
		      PCB shortestJob = null;

		      // Find the shortest job among arriving processes in queue 2
		      if (notFinish == 1) {
		        shortestJob = q2.get(0);
		      } 
                      else {
		        if (q2.get(0).arrival_Time <= currentTime) {
		          shortestJob = q2.get(0);
		          for (PCB process : q2) {
		            if (process.arrival_Time <= currentTime && process.cpu_Burst < shortestJob.cpu_Burst) {
		              shortestJob = process;
		            }
		          }
		        }
		      }

		      if (shortestJob != null) {
		        q2.remove(shortestJob);
		        System.out.print(shortestJob.process_ID + " | ");

		        // Update process information for first time execution
		        if (shortestJob.start_Time == 0) {
		          shortestJob.start_Time = currentTime;
		        }
		        shortestJob.responseTime = shortestJob.start_Time - shortestJob.arrival_Time;
		        // Simulate shortest job execution until completion or exceeding remaining queue 1 processes' arrival times
		        while ((q1.isEmpty() || q1.get(0).arrival_Time > currentTime) && shortestJob.cpu_Burst > 0) {
		          shortestJob.cpu_Burst--;
		          currentTime++;
		          shortestJob.terminationTime = currentTime;
		        }

		        // Handle unfinished shortest job
		        if (shortestJob.cpu_Burst > 0) {
		          notFinish = 1;
		          q2.add(0, shortestJob);
		        } else { // Job completes
		          notFinish = 0;
		          shortestJob.turnaroundTime = shortestJob.terminationTime - shortestJob.arrival_Time;
		          shortestJob.waitingTime = shortestJob.turnaroundTime - shortestJob.stCpuBurst;
		          scheduleOrder.add(shortestJob);
		          currentTime = shortestJob.terminationTime;
		        }
		      } else { // Handle idle CPU with no jobs in queue 2
		        System.out.print(" | NON | ");
		        currentTime++;
		      }
		    }
                         }
                 
                  
           // Print detailed information
 System.out.print("]\n\n");
System.out.println("| ProcessID |   Priority | Arrival Time | CPU Burst | Start Time | Termination Time | Turnaround Time | Waiting Time | Response Time"); 
System.out.print("");

if (scheduleOrder.isEmpty()) {
    System.out.println("No processes to display.");
} else {
    for (int i = 0 ; i < scheduleOrder.size(); i++ ) {
        PCB process = scheduleOrder.get(i);
        System.out.printf("| %-12s| %-9d| %-14d| %-13d| %-16d| %-14d| %-17d| %-13d| %-10d%n",
            process.process_ID,
            process.priority,
            process.arrival_Time,
            process.stCpuBurst,
            process.start_Time,
            process.terminationTime,
            process.turnaroundTime,
            process.waitingTime,
            process.responseTime);
    }
}
// in file 
System.out.println(calculateAverages(scheduleOrder));
try(
                FileWriter fileWriter = new FileWriter("Report.txt"); 
                PrintWriter printWriter = new PrintWriter(fileWriter)) {
 
                 printWriter.println("| ProcessID | Priority | Arrival Time | CPU Burst | Start Time | Termination Time | Turnaround Time | Waiting Time | Response Time");
                
                 printWriter.println("-----------------------------------------------------------------------------------------------------------------------------------");
                  int index = 0;
   while (index < scheduleOrder.size()) {
     PCB process = scheduleOrder.get(index);
     index++ ; 
                   printWriter.printf("| %-10s | %-8d | %-12d | %-9d | %-10d | %-16d | %-15d | %-12d | %-13d |\n",
                          process.process_ID, process.priority, process.arrival_Time, process.stCpuBurst, process.start_Time,
                           process.terminationTime, process.turnaroundTime, process.waitingTime, process.responseTime);
                 }
                 String averages = calculateAverages(scheduleOrder);
                 printWriter.println("\n" + averages);
                 fileWriter.close();
 }
 catch (IOException e) {
 }
        }
             }


