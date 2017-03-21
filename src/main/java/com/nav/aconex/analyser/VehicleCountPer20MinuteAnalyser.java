package com.nav.aconex.analyser;

import com.nav.aconex.analyser.result.VehicleDataAnalysisResult;
import com.nav.aconex.model.VehicleDetails;

import java.util.List;

public class VehicleCountPer20MinuteAnalyser implements VehicleDataAnalyser{
    @Override
    public VehicleDataAnalysisResult analyse(List<VehicleDetails> vehicleDetails) {
        return new VehicleCountPerTimeIntervalAnalyser(20, "Analysis For 20 minute interval").analyse
                (vehicleDetails);
    }
}
