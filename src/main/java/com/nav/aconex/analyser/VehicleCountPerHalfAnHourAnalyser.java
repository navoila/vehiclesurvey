package com.nav.aconex.analyser;

import com.nav.aconex.analyser.result.VehicleDataAnalysisResult;
import com.nav.aconex.model.VehicleDetails;

import java.util.List;

public class VehicleCountPerHalfAnHourAnalyser implements VehicleDataAnalyser{
    @Override
    public VehicleDataAnalysisResult analyse(List<VehicleDetails> vehicleDetails) {
        return new VehicleCountPerTimeIntervalAnalyser(30, "Analysis For 30" +
                " minute interval").analyse
                (vehicleDetails);
    }
}
