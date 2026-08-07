package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ResultWriter {
    public static void saveResults(String filename, List<Customer> customers, long simDuration, int totalServed, double avgResponseTime) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("id, arrival, departure, responseTime, state\n");
            for (Customer c : customers) {
                if (c.getDepartureTime() > 0) {
                    long response = c.getDepartureTime() - c.getArrivalTime();
                    writer.write(c.getId() + "," + c.getArrivalTime() + "," + c.getDepartureTime() + "," + response + "," + c.getState() + "\n");
                }
                else {
                writer.write(c.getId() + ", " + c.getArrivalTime() + ", N/A" + ", N/A, " + c.getState() + "\n");
                }
            }
            writer.write("\nSummary\n");
            writer.write("Duration, " + simDuration + "\n");
            writer.write("Total Served, " + totalServed + "\n");
            writer.write("Avg Response Time, " + String.format("%.2f", avgResponseTime) + "\n");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}