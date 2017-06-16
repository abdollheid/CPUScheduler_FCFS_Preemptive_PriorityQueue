/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpuscheduler;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author abdullah
 */
public class Process {
    public int size ;
    public int BurstTime[]  , ArrivalTime[] , Priority[];
    
    public Process(int size ) {
        this.size = size; 
      BurstTime = new int [size];
      ArrivalTime = new int [size];
      Priority = new int [size];
    
    }
    

    
    public void generate(){
        Random rand =new Random();
          
        for(int i =0; i <size ; ++i){
          ArrivalTime[i] = rand.nextInt(11) ;
        }
        Arrays.sort(ArrivalTime);
      
        for(int i = 0 ; i<size ; ++ i ){
            BurstTime[i]  = rand.nextInt(10) +1;
            Priority[i] = rand.nextInt(101) ; 
        }
    }
    public void generate(int seed){
        Random rand =new Random(seed);
         for(int i =0; i <size ; ++i){
          ArrivalTime[i] = rand.nextInt(11) ;
        }
      
         Arrays.sort(ArrivalTime);

        for(int i = 0 ; i<size ; ++ i ){
            BurstTime[i]  = rand.nextInt(10)+1;
            Priority[i] = rand.nextInt(101) ; 
        }
    }
      
    
  
    

   
    
    
}
