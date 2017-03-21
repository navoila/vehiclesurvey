package com.nav.aconex;

public class Application {
    private static VehicleSurveyor surveyor = new VehicleSurveyor();

    public static void main(String[] args) {
        try {
            surveyor.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static void setSurveyor(VehicleSurveyor surveyor){
        Application.surveyor = surveyor ;
    }
}
