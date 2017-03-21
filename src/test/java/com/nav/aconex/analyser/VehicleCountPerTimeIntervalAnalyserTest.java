package com.nav.aconex.analyser;

import com.nav.aconex.VehicleSurveyorTest;
import com.nav.aconex.analyser.result.VehicleDataAnalysisResult;
import com.nav.aconex.model.VehicleDetails;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class VehicleCountPerTimeIntervalAnalyserTest {
    private VehicleCountPerTimeIntervalAnalyser classUnderTest;

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowErrorWhenIntervalMinutesIsSetAs0()
            throws
            Exception {
        //given
        classUnderTest = new VehicleCountPerTimeIntervalAnalyser(0,"hello" );
        //when
        VehicleDataAnalysisResult result = classUnderTest.analyse(null);
        //then throws error
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowErrorWhenMinsInDayNonDivisibleByInterval()
            throws Exception {
        //given
        classUnderTest = new VehicleCountPerTimeIntervalAnalyser(11,"hello" );
        //when
        VehicleDataAnalysisResult result = classUnderTest.analyse(null);
        //then throws error
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowErrorWhenInvalidVehicleDetailsPasedIn()
            throws Exception {
        //given
        classUnderTest = new VehicleCountPerTimeIntervalAnalyser(60,"hello" );
        //when
        VehicleDataAnalysisResult result = classUnderTest.analyse(null);
        //then
        //throws exception
    }

    @Test
    public void shouldReturnValidResultsWhenMinsInDayDivisibleByInterval()
            throws Exception {
        //given
        classUnderTest = new VehicleCountPerTimeIntervalAnalyser(60,"hello" );
        //when
        List<VehicleDetails> dummyVehicleDetails= new ArrayList<>();
        dummyVehicleDetails.add(VehicleSurveyorTest
                .createDummyVehicleDetails());
        VehicleDataAnalysisResult result = classUnderTest.analyse
                (dummyVehicleDetails);
        //then
    assertNotNull(result);
    assertEquals("hello",result.getAnalysisDescription());
    }

}