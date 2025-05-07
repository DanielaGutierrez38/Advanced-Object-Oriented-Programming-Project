package com.spaceweb.model;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DebrisDensityAnalysis{

    /**
     * Generate a CSV report of space objects within a given longitude range.
     * @param objectList List of space objects to analyze
     * @param minLongitude Minimum longitude value
     * @param maxLongitude Maximum longitude value
     * @throws DensityReportException if writing to the file fails
     */
    public void generateDensityReport(List<SpaceObject> objectList, double minLongitude, double maxLongitude)
            throws DensityReportException{
        int count = 0;
        String fileName = "density_report.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))){
            writer.write(String.format("---- Objects in Range [%.2f, %.2f] ----\n", minLongitude, maxLongitude));
            writer.write("Record ID,Satellite Name,Country,Orbit Type,Launch Year,Object Type\n");

            for (SpaceObject obj : objectList){
                if (obj.longitude >= minLongitude && obj.longitude <= maxLongitude){
                    count++;
                    writer.write(String.format("%s,%s,%s,%s,%d,%s\n",
                            obj.recordId,
                            obj.satelliteName,
                            obj.country,
                            obj.orbitType,
                            obj.launchYear,
                            obj.getClass().getSimpleName()));
                }
            }

            writer.write("Total objects in longitude range: " + count + "\n\n");
            System.out.println("Report updated: " + fileName);
            
        // User-defined exception - requirment
        } catch (IOException e){
            throw new DensityReportException("Failed to write density report to " + fileName, e);
        }
    }
}
