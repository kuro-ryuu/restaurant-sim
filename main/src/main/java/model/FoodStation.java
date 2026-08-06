package model;

import java.util.Queue;
import java.util.LinkedList;

public class FoodStation {
    private int id;
    private static int idCounter = 1;
    private boolean busy;
    private Queue<KitchenTask> queue;
    private long totalBusyTime;
    private long busyStartTime;
    private StationType stationType;
    private long preparationTime;
    private KitchenTask currentTask;
    
    public FoodStation(StationType stationType) {
        this.id = idCounter++;
        this.busy = false;
        this.queue = new LinkedList<>();
        this.totalBusyTime = 0;
        this.currentTask = null;
    }
    public int getId() {
        return id;
    }
    public boolean isBusy() {
        return busy;
    }
    public KitchenTask getCurrentTask() {
        return currentTask;
    }
    public Queue<KitchenTask> getQueue() {
        return queue;
    }
    public long getTotalBusyTime() {
        return totalBusyTime;
    }
    public StationType getStationType() {
        return stationType;
    }
    public void enqueue(KitchenTask task) {
        queue.add(task);
    }
    public KitchenTask dequeue() {
        return queue.poll();
    }
    public KitchenTask peek() {
        return queue.peek();
    }
    public void startTask(KitchenTask task, long time) {
        this.busy = true;
        this.busyStartTime = time;
        this.currentTask = task;
    }

    public long getBusyStartTime() {
        return busyStartTime;
    }

    public KitchenTask endTask(long time) {
        if (busy) {
            totalBusyTime += (time - busyStartTime);
        }
        this.busy = false;
        KitchenTask finished = currentTask;
        this.currentTask = null;
        return finished;
    }
    public static void resetCounter() {
        idCounter = 1;
    }
}

// TO DO: id, busy, currentOrder