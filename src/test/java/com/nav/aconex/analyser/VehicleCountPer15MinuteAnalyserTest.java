package com.nav.aconex.analyser;

import com.nav.aconex.VehicleSurveyorTest;
import com.nav.aconex.analyser.result.VehicleDataAnalysisResult;
import org.junit.Test;

import static org.junit.Assert.*;

public class VehicleCountPer15MinuteAnalyserTest {
    private VehicleCountPer15MinuteAnalyser classUnderTest;

    @Test
    public void shouldReturnValidResultWhenAnalysisRun() throws Exception {
        //given
        classUnderTest= new VehicleCountPer15MinuteAnalyser();
        //when
        VehicleDataAnalysisResult result =classUnderTest.analyse
                (VehicleSurveyorTest
                .createDummyVehicleDetailsList());
        //then
        assertNotNull(result);
    }

}