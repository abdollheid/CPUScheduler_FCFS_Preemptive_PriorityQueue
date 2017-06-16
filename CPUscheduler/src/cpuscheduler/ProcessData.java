/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpuscheduler;

import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author abdullah
 */

 public class ProcessData {
    private final SimpleIntegerProperty p;
     private final SimpleIntegerProperty arrival;
       private final SimpleIntegerProperty priority;
         private final SimpleIntegerProperty burst;
 
     ProcessData(int p   , int arrival, int priority , int burst) {
        this.p = new SimpleIntegerProperty(p);
        this.arrival = new SimpleIntegerProperty(arrival);
        this.priority = new SimpleIntegerProperty(priority);
         this.burst = new SimpleIntegerProperty(burst);
    }
 
    public int getP() {
        return p.get();
    }
    public void setP(int p) {
    this.p.set(p);
    }
        
       public int getArrival() {
        return arrival.get();
    }
    public void setArrival(int p) {
    this.arrival.set(p);
    }
       public int getPriority() {
        return priority.get();
    }
    public void setPriority(int p) {
    this.priority.set(p);
    }
       public int getBurst() {
        return burst.get();
    }
    public void setBurst(int p) {
    this.burst.set(p);
    }
    
   
        
}
