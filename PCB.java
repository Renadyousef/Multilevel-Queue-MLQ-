//class is used to represent the Process Control Block (PCB)



package osproject;

public class PCB {
    String process_ID;
	int priority ,arrival_Time , new_arrivalTime ;
	int stCpuBurst , cpu_Burst , start_Time ;
	int terminationTime , turnaroundTime ;
	int waitingTime , responseTime ;
	
	public PCB(String process_ID, int priority, int arrival_Time, int cpu_Burst) {
	this.process_ID = process_ID;
	this.priority = priority;
	this.arrival_Time = arrival_Time;
	this.new_arrivalTime = arrival_Time;
	this.stCpuBurst = cpu_Burst; // original CPU burst
	this.cpu_Burst = cpu_Burst;
	this.start_Time = 0 ;
	this.turnaroundTime = 0;
	this.waitingTime = 0;
	this.responseTime = 0;
	}
	public String getProcess_ID() {
		return process_ID;
	}
	public void setProcess_ID(String process_ID) {
		this.process_ID = process_ID;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public int getArrival_Time() {
		return arrival_Time;
	}
	public void setArrival_Time(int arrival_Time) {
		this.arrival_Time = arrival_Time;
	}
	public int getCpu_Burst() {
		return cpu_Burst;
	}
	public void setCpu_Burst(int cpu_Burst) {
		this.cpu_Burst = cpu_Burst;
	}
	public int getStart_Time() {
		return start_Time;
	}
	public void setStart_Time(int start_Time) {
		this.start_Time = start_Time;
	}
	public int getTerminationTime() {
		return terminationTime;
	}
	public void setTerminationTime(int terminationTime) {
		this.terminationTime = terminationTime;
	}
	public int getTurnaroundTime() {
		return turnaroundTime;
	}
	public void setTurnaroundTime(int turnaroundTime) {
		this.turnaroundTime = turnaroundTime;
	}
	public int getWaitingTime() {
		return waitingTime;
	}
	public void setWaitingTime(int waitingTime) {
		this.waitingTime = waitingTime;
	}
	public int getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(int responseTime) {
		this.responseTime = responseTime;
	}
}
