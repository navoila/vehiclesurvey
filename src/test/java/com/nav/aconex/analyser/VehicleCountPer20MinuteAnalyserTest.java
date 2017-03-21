package com.nav.aconex.analyser;

import com.nav.aconex.VehicleSurveyorTest;
import com.nav.aconex.analyser.result.VehicleDataAnalysisResult;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class VehicleCountPer20MinuteAnalyserTest {
    private VehicleCountPer20MinuteAnalyser classUnderTest;

    @Test
    public void shouldReturnValidResultWhenAnalysisRun() throws Exception {
        //given
        classUnderTest= new VehicleCountPer20MinuteAnalyser();
        //when
        VehicleDataAnalysisResult result =classUnderTest.analyse
                (VehicleSurveyorTest
                .createDummyVehicleDetailsList());
        //then
        assertNotNull(result);
    }

}