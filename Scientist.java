class Scientist extends User{

    public void trackObjectsInLEO() {
        
        TrackingSystem trackingSystem = new TrackingSystem("rso_metrics.csv");

        for (SpaceObject obj : trackingSystem.getAllObjects().values()) {
            if (obj.orbitType.equals("LEO")){
                obj.displayInfo();  
                System.out.println("-------------------------");
            }
        }
    }

    
}
