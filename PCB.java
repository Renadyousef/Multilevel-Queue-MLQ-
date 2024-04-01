//class is used to represent the Process Control Block (PCB)
public class PCB {

private int arival_time,strat_time,termination_time;

private int turn_round,waiting_time,respons_time;

private int process_priority,cpu_burst;//Priority of a process: from 1 to 2, where 1>2 in Priority

private String process_id;



    public PCB(int arival_time, int process_priority, int cpu_burst, String process_id) {
        strat_time=0;
        this.arival_time = arival_time;
        this.process_priority = process_priority;
        this.cpu_burst = cpu_burst;
        this.process_id = process_id;
        termination_time=0;
        waiting_time=0;
        respons_time=0;
        turn_round=0;
      
    }

}
