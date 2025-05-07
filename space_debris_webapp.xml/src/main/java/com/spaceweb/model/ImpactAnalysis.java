package com.spaceweb.model;
import java.util.List;

public class ImpactAnalysis{

    /**
     * Analyze long-term impact for LEO objects that are older than 200 days 
     * and have had at least 1 conjunction.
     * @param objectList List of all space objects
     */
    public void analyzeLongTermImpact(List<SpaceObject> objectList){
        boolean foundAny = false;
        for (SpaceObject obj : objectList){
            if ("LEO".equalsIgnoreCase(obj.orbitType) &&
                obj.daysOld > 200 &&
                obj.conjunctionCount > 0){

                foundAny = true;
                System.out.println("Record ID: " + obj.recordId);
                System.out.println("Satellite Name: " + obj.satelliteName);
                System.out.println("Country: " + obj.country);
                System.out.println("Orbit Type: " + obj.orbitType);
                System.out.println("Object Type: " + obj.getClass().getSimpleName());
                System.out.println("Days Old: " + obj.daysOld);
                System.out.println("Conjunction Count: " + obj.conjunctionCount);
                System.out.println("-------------------------------------");
            }
        }
        if (!foundAny){
            System.out.println("No LEO objects found with >200 days old and >0 conjunctions.");
        }
    }
}