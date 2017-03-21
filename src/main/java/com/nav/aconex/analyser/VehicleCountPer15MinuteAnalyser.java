package com.nav.aconex.analyser;

import com.nav.aconex.analyser.result.VehicleDataAnalysisResult;
import com.nav.aconex.model.VehicleDetails;

import java.util.List;

public class VehicleCountPer15MinuteAnalyser implements VehicleDataAnalyser{

    @Override
    public VehicleDataAnalysisResult analyse(List<VehicleDetails> vehicleDetails) {
        return new VehicleCountPerTimeIntervalAnalyser(15, "Vehicle Count " +
                "Analysis For 15 minute interval").analyse
                (vehicleDetails);
    }

}
