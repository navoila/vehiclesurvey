package com.nav.aconex.parser;

import com.nav.aconex.exception.InvalidVehicleDataException;
import org.junit.Test;

import static org.junit.Assert.*;

public class VehicleDataUtilTest {
    private static final double DELTA = 0D;

    @Test
    public void shouldParseMilliSecondsFromMidnightWhenValidEntryPassed()
            throws
            Exception {
        //given
        String validEntry = "A1234567";
        //when
        int millisecondsFromMidnight = VehicleDataUtil
        .parseMilliSecondsFromMidnight
                (validEntry);
        //then
        assertEquals(1234567,millisecondsFromMidnight);
    }

    @Test(expected = InvalidVehicleDataException.class )
    public void shouldThrowExceptionWhenInvalidEntryPassed()
            throws InvalidVehicleDataException {
        //given
        String invalidDataInput = "I AM NOT VALID";
        //when
        int millisecondsFromMidnight = VehicleDataUtil
                .parseMilliSecondsFromMidnight
                        (invalidDataInput);
        //then
        //should throw exception
    }

    @Test(expected = InvalidVehicleDataException.class )
    public void shouldThrowExceptionWhenNumberOverMaxMilliSecPerDayPassed()
            throws InvalidVehicleDataException {
        //given
        String invalidDataInput = "B96400000";
        //when
        int millisecondsFromMidnight = VehicleDataUtil
                .parseMilliSecondsFromMidnight
                        (invalidDataInput);
        //then
        //should throw exception
    }

    @Test(expected = InvalidVehicleDataException.class )
    public void shouldThrowExceptionWhenNumberLessThanZeroPassed()
            throws InvalidVehicleDataException {
        //given
        String invalidDataInput = "B-123450";
        //when
        int millisecondsFromMidnight = VehicleDataUtil
                .parseMilliSecondsFromMidnight
                        (invalidDataInput);
        //then
        //should throw exception
    }

    @Test
    public void
    shouldRoundTo2DecPlacesWhenNumberWithMultipleDecDigitsPassed()
            throws Exception {
        //given
        double validValueWithMultipleDecimalPlaces = 12345.23423434534;
        double expectedResult = 12345.23;
        //when
        double roundedValue = VehicleDataUtil.round
                (validValueWithMultipleDecimalPlaces,2);
        //then
        assertEquals(expectedResult,roundedValue, DELTA);
    }

    @Test
    public void
    shouldNotRoundWhenWholeNumberPassed()
            throws Exception {
        //given
        double validValueWithMultipleDecimalPlaces = 12345;
        double expectedResult = 12345;
        //when
        double roundedValue = VehicleDataUtil.round
                (validValueWithMultipleDecimalPlaces,2);
        //then
        assertEquals(expectedResult,roundedValue, DELTA);
    }

    @Test
    public void shouldCalculateSpeedinKphAccuratelyWhenValidDatPassed() throws
            Exception {
        //given
        int frontAxleTimeInMillis = 86081865;
        int rearAxleTimeInMillis = 86081979;
        int expectedSpeedInKph = 78;
        //when
        int calculatedKph = VehicleDataUtil.calculateSpeedinKph
                (frontAxleTimeInMillis,
                rearAxleTimeInMillis);
        //then
        assertNotNull(calculatedKph);
        assertEquals(expectedSpeedInKph,calculatedKph);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowErrorWhenFrontAxleTimeGreaterThanRearAxleTime()
            throws
            Exception {
        //given
        int frontAxleTimeInMillis = 86081979;
        int rearAxleTimeInMillis = 86081865;
        //when
        int calculatedKph = VehicleDataUtil.calculateSpeedinKph
                (frontAxleTimeInMillis,
                        rearAxleTimeInMillis);
        //then
        //should throw error
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowErrorWhenFrontAxleTimeEqualsRearAxleTime()
            throws
            Exception {
        //given
        int frontAxleTimeInMillis = 86081979;
        int rearAxleTimeInMillis = 86081979;
        //when
        int calculatedKph = VehicleDataUtil.calculateSpeedinKph
                (frontAxleTimeInMillis,
                        rearAxleTimeInMillis);
        //then
        //should throw error
    }
}