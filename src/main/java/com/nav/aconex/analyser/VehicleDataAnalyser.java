package com.nav.aconex.analyser;

import com.nav.aconex.analyser.result.VehicleDataAnalysisResult;
import com.nav.aconex.model.VehicleDetails;

import java.util.List;

public interface VehicleDataAnalyser {

    public VehicleDataAnalysisResult analyse(List<VehicleDetails> vehicleDetails);
}
