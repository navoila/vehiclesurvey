package com.nav.aconex.analyser;

import org.junit.Test;

import static org.junit.Assert.*;

public class VehicleDataAnalyserFactoryTest {
    private VehicleDataAnalyserFactory classUnderTest;

    @Test
    public void shouldGetCorrectAnalyser() throws Exception {
        //given
        classUnderTest = new VehicleDataAnalyserFactory();
        //when
        VehicleDataAnalyser analyser = classUnderTest.getAnalyser
                (VehicleDataAnalyserType
                .VEHICLE_COUNT_PER_15_MINUTE);
        //then
        assertNotNull(analyser);
        assertTrue(analyser instanceof VehicleCountPer15MinuteAnalyser);
    }

}