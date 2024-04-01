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
    
//Getters
    public String get_ProcID(){
       return process_id;
   }
   
   public int get_ProcPriority(){
       return process_priority;
   }
   
     public int get_CPUburst(){
       return cpu_burst;
   }
   
    public int get_ArivalTime(){
       return arival_time;
   }
   
   public int get_StratTime(){
       return strat_time;
   }
   
    public int get_TerminationTime() {
       return termination_time;
   }
   
   public int get_TurnRound(){
       return turn_round;
   }
   
  public int get_WaitingTime(){
       return waiting_time;
   }
   
   public int get_ResponsTime(){
       return respons_time;
   }
  
 
//Setters
   
   public void set_ProcID(String id){
       process_id= id;
   }
   
   public void set_ProcPriority(int priority){
      process_priority= priority;
   }
   
   public void  set_CPUburst(int burst){
       cpu_burst=burst;
   }
   
    public void set_ArivalTime(int arival){
       arival_time=arival;
   }
   
   public void set_StratTime(int start){
       strat_time=start;
   }
   
    public void set_TerminationTime(int term) {
      termination_time=term;
   }
   
   public void set_TurnRound(int turn){
       turn_round=turn;
   }
   
  public void set_WaitingTime(int wait){
       waiting_time=wait;
   }
   
   public void set_ResponsTime(int respons){
       respons_time=respons;
   }
    
   
     @Override
     public String toString() {
     String formattedString = String.format("%-10s%-10d%-10d%-10d%-10d%-10d%-10d%-10d%-10d",
                                           process_id,process_priority,cpu_burst,arival_time,
                                           strat_time,termination_time,turn_round,
                                           waiting_time,respons_time);
    return formattedString;
}

}
