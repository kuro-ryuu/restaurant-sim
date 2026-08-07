package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ResultWriter {
    private static int simulationId = 0;

    public static void loadLastId(String filename) {
        simulationId = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Simulation ")) {
                    String numStr = line.replace("Simulation ", "").trim();
                    try {
                        int num = Integer.parseInt(numStr);
                        if (num > simulationId) {
                            simulationId = num;
                        }
                    } catch (NumberFormatException e) {}
                }
            }
        } catch (IOException e) {
            simulationId = 0;
        }
    }

    public static void saveResults(String filename, List<Customer> customers, long simDuration, int totalServed, double avgResponseTime) {
        simulationId++;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write("\nSimulation " + simulationId + "\n");
            writer.write("\nid, arrival, departure, responseTime, state\n");
            for (Customer c : customers) {
                if (c.getDepartureTime() > 0) {
                    long response = c.getDepartureTime() - c.getArrivalTime();
                    writer.write(c.getId() + ", " + c.getArrivalTime() + ", " + c.getDepartureTime() + ", " + response + ", " + c.getState() + "\n");
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
    public static void clearResults(String filename) {
        simulationId = 0;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }    
}