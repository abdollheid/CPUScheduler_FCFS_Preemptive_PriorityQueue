package cpuscheduler;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;


public class FCFS {

    private Process p ;
    private int totalTime  ;
    private Chart chart ; 

    public FCFS(Process p , Chart chart)  {
        this.p =  p ; 
        this.chart = chart ; 
       
        calculate() ; 
    }
    

    
    private void calculate(){
        int  pNumber=1 ; 
        
        ArrayList<Integer>result = new ArrayList<Integer>() ; 
        ArrayList<Integer>names = new ArrayList<Integer>();
        
        result.add(p.BurstTime[0]) ; 
        int waiting = 0; 
        for(int i = 1 ; i < p.size ; ++i ){
            waiting = p.ArrivalTime[i-1] + p.BurstTime[i-1] + waiting;
            waiting-= p.ArrivalTime[i]; 
            
            names.add((pNumber++));
            
            if(waiting > 0){
                totalTime+= waiting ; 
            }
            else{
                result.add(Math.abs(waiting)) ; 
                waiting = 0 ; 
                names.add(-1);
            }
            
            result.add(p.BurstTime[i]) ; 
        }
         names.add((pNumber++));
         chart.divde(result,names,p.ArrivalTime[0]); 

    }
    

    public double getTotalTime (){
        return totalTime ; 
    }
    public double getAverageTime(){
        return (double) totalTime  / p.size ;
    }
    

    
  
}
