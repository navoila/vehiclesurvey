package com.nav.aconex;

import com.nav.aconex.analyser.VehicleDataAnalyserFactory;
import com.nav.aconex.analyser.VehicleDataAnalyserType;
import com.nav.aconex.analyser.result.VehicleDataAnalysisResult;
import com.nav.aconex.exception.InvalidVehicleDataException;
import com.nav.aconex.io.reader.VehicleDataFileReader;
import com.nav.aconex.model.VehicleDetails;
import com.nav.aconex.parser.VehicleDataParser;

import java.util.ArrayList;
import java.util.List;

public class VehicleSurveyor {
    private VehicleDataParser vehicleDataParser;
    private VehicleDataFileReader vehicleDataFileReader;
    private VehicleDataAnalyserFactory vehicleDataAnalyserFactory;

    public VehicleSurveyor() {
        vehicleDataFileReader = new VehicleDataFileReader();
        vehicleDataParser = new VehicleDataParser(vehicleDataFileReader);
        vehicleDataAnalyserFactory = new VehicleDataAnalyserFactory();
    }

    public List<VehicleDataAnalysisResult> run() throws InvalidVehicleDataException {
        //2 steps
        //step1 - parse the data
        List<VehicleDetails> vehicleDetails = vehicleDataParser.parse();
        if (vehicleDetails==null || vehicleDetails.isEmpty()){
            throw new InvalidVehicleDataException("Error parsing vehicle input data. Please " +
                    "check contents of data file.");
        }
        // step2 - analyse data
        List<VehicleDataAnalysisResult> results = new ArrayList<>();
        for (VehicleDataAnalyserType analyserType : VehicleDataAnalyserType.values()){
            results.add(vehicleDataAnalyserFactory.getAnalyser
                    (analyserType).analyse(vehicleDetails));
        }
        System.out.println(results);
        return results;
    }

    public void setVehicleDataParser(VehicleDataParser vehicleDataParser) {
        this.vehicleDataParser = vehicleDataParser;
    }

    public void setVehicleDataFileReader(VehicleDataFileReader vehicleDataFileReader) {
        this.vehicleDataFileReader = vehicleDataFileReader;
    }

    public void setVehicleDataAnalyserFactory(VehicleDataAnalyserFactory vehicleDataAnalyserFactory) {
        this.vehicleDataAnalyserFactory = vehicleDataAnalyserFactory;
    }
}
