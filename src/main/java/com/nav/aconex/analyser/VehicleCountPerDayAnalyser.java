package com.nav.aconex.analyser;

import com.nav.aconex.analyser.result.IndividualIntervalAnalysisResult;
import com.nav.aconex.analyser.result.VehicleDataAnalysisResult;
import com.nav.aconex.constants.Direction;
import com.nav.aconex.constants.VehicleSurveyConfigConstants;
import com.nav.aconex.model.VehicleDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VehicleCountPerDayAnalyser implements VehicleDataAnalyser {

    @Override
    public VehicleDataAnalysisResult analyse(List<VehicleDetails>
                                                       vehicleDetails) {
        List<IndividualIntervalAnalysisResult> individualIntervalResults =
                new ArrayList<>();

        for (int day = 1; day <= VehicleSurveyConfigConstants.NUMBER_OF_SURVEY_DAYS;
             day++) {
            int southCount = countEntriesForTheDayForDirection(vehicleDetails, day, Direction.SOUTH);
            int northCount = countEntriesForTheDayForDirection(vehicleDetails, day, Direction.NORTH);
            int total = southCount + northCount;
            IndividualIntervalAnalysisResult individualResult = new
                    IndividualIntervalAnalysisResult("Day ", day,
                    southCount,
                    northCount,total,
                    "00:00:00", "23:59:59");
            individualIntervalResults.add(individualResult);
        }
        VehicleDataAnalysisResult result = new VehicleDataAnalysisResult
                ("Analysis For Each Day",
                        individualIntervalResults, 0,
                        null);
        return result;
    }
    private int countEntriesForTheDayForDirection(List<VehicleDetails>
                                                         vehicleDetails, int day, Direction direction) {
        return vehicleDetails.stream().filter(entry -> entry.getDayOfTheSurvey() == day && entry.getDirection() == direction).collect(Collectors.toList()).size();
    }
}
