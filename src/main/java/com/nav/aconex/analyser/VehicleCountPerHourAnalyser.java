package com.nav.aconex.analyser;

import com.nav.aconex.analyser.result.VehicleDataAnalysisResult;
import com.nav.aconex.model.VehicleDetails;

import java.util.List;

public class VehicleCountPerHourAnalyser implements VehicleDataAnalyser{
    @Override
    public VehicleDataAnalysisResult analyse(List<VehicleDetails> vehicleDetails) {
        return new VehicleCountPerTimeIntervalAnalyser(60, "Analysis For 60" +
                " minute interval").analyse
                (vehicleDetails);
    }
}
