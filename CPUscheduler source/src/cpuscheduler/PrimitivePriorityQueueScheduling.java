package cpuscheduler;

import java.util.ArrayList;
import java.util.Arrays;

public class PrimitivePriorityQueueScheduling {

    private Process p;
    private double totalTime;
    private ArrayList<Integer> timeSpent = new ArrayList<>();
    private ArrayList<Integer> names = new ArrayList<>();

    private Chart chart;

    public PrimitivePriorityQueueScheduling(Process p, Chart C) {
        this.p = p;
        this.chart = C;

        calculate();
        calculateWaitingTime();

    }

    private void calculateWaitingTime() {
        for (int i = 0; i < p.size; ++i) {
            boolean readyToSum = false;
            int finishedTimePerProcess = 0;
            for (int x = names.size() - 1; x >= 0; --x) {
                if (names.get(x) == i) {
                    readyToSum = true;
                    continue;
                }
                if (readyToSum) {
                    if (names.get(x) == i) {
                        continue;
                    } else {

                        finishedTimePerProcess += timeSpent.get(x);

                    }
                }
            }
            finishedTimePerProcess += p.ArrivalTime[0];
            totalTime += finishedTimePerProcess - p.ArrivalTime[i];
        }
    }

    public double getWaitingTime() {
        return totalTime;

    }

    public double getAverageTime() {
        return totalTime / p.size;
    }
//


    public void calculate() {

        java.util.PriorityQueue<Container> pq = new java.util.PriorityQueue<>();
        int id = 0;
        pq.add(new Container(id++, p.BurstTime[0], p.ArrivalTime[0], p.Priority[0]));

        int tempTime = p.ArrivalTime[0];

        for (int i = 1; i < p.size; ++i) {

            while (!pq.isEmpty() && p.ArrivalTime[i] - tempTime >= pq.peek().getBurstTime()) {
                tempTime += pq.peek().getBurstTime();
                names.add(pq.peek().getID());
                timeSpent.add(pq.poll().getBurstTime());

            }

            if (pq.isEmpty() && p.ArrivalTime[i] - tempTime != 0) {
                names.add(-1);
                timeSpent.add(p.ArrivalTime[i] - tempTime);
                tempTime = p.ArrivalTime[i];
            }

            if (!pq.isEmpty() && p.Priority[i] < pq.peek().getPriority()) {
                pq.peek().setBurstTime(pq.peek().getBurstTime() - (p.ArrivalTime[i] - tempTime));
                if (p.ArrivalTime[i] - tempTime != 0) {
                    names.add(pq.peek().getID());
                    timeSpent.add(p.ArrivalTime[i] - tempTime);
                    tempTime = p.ArrivalTime[i];
                }
            }
            pq.add(new Container(id++, p.BurstTime[i], p.ArrivalTime[i], p.Priority[i]));

        }

        while (!pq.isEmpty()) {
            names.add(pq.peek().getID());
            tempTime += pq.peek().getBurstTime();
            timeSpent.add(pq.poll().getBurstTime());
        }

        chart.divde(timeSpent, names, p.ArrivalTime[0]);  // for GUI draw

    }

}

//
class Container implements Comparable<Container> {

    private int id, burstTime, arrivalTime, priority;

    public Container(int id, int burstTime, int arrivalTime, int priority) {
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
        this.priority = priority;
        this.id = id;
    }

    @Override
    public int compareTo(Container t) {
        if (priority > t.priority) {
            return 1;
        } else if (priority < t.priority) {
            return -1;
        } else {
            return 0;
        }

    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int t) {
        arrivalTime = t;
    }

    public int getPriority() {
        return priority;
    }

    public int getID() {
        return id;
    }

}