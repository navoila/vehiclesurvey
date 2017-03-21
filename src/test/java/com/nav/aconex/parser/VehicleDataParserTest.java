package com.nav.aconex.parser;

import com.nav.aconex.constants.Direction;
import com.nav.aconex.exception.InvalidVehicleDataException;
import com.nav.aconex.io.reader.VehicleDataFileReader;
import com.nav.aconex.model.VehicleDetails;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class VehicleDataParserTest {
    private VehicleDataParser classUnderTest;

    @Mock
    private VehicleDataFileReader vehicleDataFileReader;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        classUnderTest = new VehicleDataParser(vehicleDataFileReader);
    }

    @Test
    public void
    shouldCreateVehicleDetailsWithCorrectDetailsWhenNorthEntriesPassed()
            throws Exception {
        //given - see @setUp
        given(vehicleDataFileReader.readData()).willReturn(new ArrayList<String>
                (Arrays.asList("A86081865", "A86081979")));
        //when
        List<VehicleDetails> vehicleDetailsEntries = classUnderTest.parse();
        //then
        assertNotNull(vehicleDetailsEntries);
        assertTrue(vehicleDetailsEntries.size() == 1);
        VehicleDetails vehDetails = vehicleDetailsEntries.get(0);
        assertEquals(Integer.valueOf(1), vehDetails.getCounter());
        assertEquals(Integer.valueOf(1), vehDetails.getDayOfTheSurvey());
        assertEquals(Direction.NORTH, vehDetails.getDirection());
        assertEquals(Integer.valueOf(86081865), vehDetails
                .getFrontAxleMilliSecondsAfterMidnight());
        assertEquals(Integer.valueOf(86081979), vehDetails
                .getRearAxleMilliSecondsAfterMidnight());
        assertEquals(Integer.valueOf(78), vehDetails.getSpeedInKph());
    }

    @Test
    public void
    shouldCreateVehicleDetailsForBothDaysWhenVehiclesOn2DayNorthEntriesPassed()
            throws Exception {
        //given - see @setUp
        given(vehicleDataFileReader.readData()).willReturn(new ArrayList<String>
                (Arrays.asList("A86081865", "A86081979", "A56081865",
                        "A56081985")));
        //when
        List<VehicleDetails> vehicleDetailsEntries = classUnderTest.parse();
        //then
        assertNotNull(vehicleDetailsEntries);
        assertTrue(vehicleDetailsEntries.size() == 2);
        VehicleDetails vehDetails = vehicleDetailsEntries.get(0);
        assertEquals(Integer.valueOf(1), vehDetails.getDayOfTheSurvey());
        vehDetails = vehicleDetailsEntries.get(1);
        assertEquals(Integer.valueOf(2), vehDetails.getDayOfTheSurvey());

    }

    @Test
    public void
    shouldCreateVehicleDetailsForBothDaysWhenVehiclesOn2DaySouthEntriesPassed()
            throws Exception {
        //given - see @setUp
        given(vehicleDataFileReader.readData()).willReturn(new ArrayList<String>
                (Arrays.asList("A86081865", "B86081890", "A86081979",
                        "B86081899", "A1081865", "B1081880", "A1081980",
                        "B1081865990")));
        //when
        List<VehicleDetails> vehicleDetailsEntries = classUnderTest.parse();
        //then
        assertNotNull(vehicleDetailsEntries);
        assertTrue(vehicleDetailsEntries.size() == 2);
        VehicleDetails vehDetails = vehicleDetailsEntries.get(0);
        assertEquals(Integer.valueOf(1), vehDetails.getDayOfTheSurvey());
        vehDetails = vehicleDetailsEntries.get(1);
        assertEquals(Integer.valueOf(2), vehDetails.getDayOfTheSurvey());

    }

    @Test
    public void
    shouldCreateVehicleDetailsWithCorrectDetailsWhenSouthEntriesPassed()
            throws Exception {
        //given - see @setUp
        given(vehicleDataFileReader.readData()).willReturn(new ArrayList<String>
                (Arrays.asList("A86081865", "B86081890", "A86081979",
                        "B86081899")));
        //when
        List<VehicleDetails> vehicleDetailsEntries = classUnderTest.parse();
        //then
        assertNotNull(vehicleDetailsEntries);
        assertTrue(vehicleDetailsEntries.size() == 1);
        VehicleDetails vehDetails = vehicleDetailsEntries.get(0);
        assertEquals(Integer.valueOf(1), vehDetails.getCounter());
        assertEquals(Integer.valueOf(1), vehDetails.getDayOfTheSurvey());
        assertEquals(Direction.SOUTH, vehDetails.getDirection());
        assertEquals(Integer.valueOf(86081865), vehDetails
                .getFrontAxleMilliSecondsAfterMidnight());
        assertEquals(Integer.valueOf(86081979), vehDetails
                .getRearAxleMilliSecondsAfterMidnight());
        assertEquals(Integer.valueOf(78), vehDetails.getSpeedInKph());
    }

    @Test(expected = InvalidVehicleDataException.class)
    public void
    shouldThrowErrorWhenIncorrectSouthEntriesPassed()
            throws Exception {
        //given - see @setUp
        given(vehicleDataFileReader.readData()).willReturn(new ArrayList<String>
                (Arrays.asList("A86081865", "B86081890", "B86081979",
                        "A86081899")));
        //when
        List<VehicleDetails> vehicleDetailsEntries = classUnderTest.parse();
        //then
        //should throw error
    }

    @Test(expected = InvalidVehicleDataException.class)
    public void
    shouldThrowErrorWhenFrontAxleTimeIsGreaterThanRearAxleTime()
            throws Exception {
        //given - see @setUp
        given(vehicleDataFileReader.readData()).willReturn(new ArrayList<String>
                (Arrays.asList("A86081899", "B86081890", "B86081979",
                        "A86081865")));
        //when
        List<VehicleDetails> vehicleDetailsEntries = classUnderTest.parse();
        //then
        //should throw error
    }

    @Test
    public void
    shouldReturnEmptyListWhenOneEntryPassed()
            throws Exception {
        //given - see @setUp
        given(vehicleDataFileReader.readData()).willReturn(new ArrayList<String>
                (Arrays.asList("A86081865")));
        //when
        List<VehicleDetails> vehicleDetailsEntries =  classUnderTest.parse();
        //then
        assertNotNull(vehicleDetailsEntries);
        assertTrue(vehicleDetailsEntries.size()==0);
    }

    @Test
    public void
    shouldReturnEmptyListWhenNoEntriesPassed()
            throws Exception {
        //given - see @setUp
        given(vehicleDataFileReader.readData()).willReturn(new ArrayList<String>
                (Arrays.asList()));
        //when
        List<VehicleDetails> vehicleDetailsEntries =  classUnderTest.parse();
        //then
        assertNotNull(vehicleDetailsEntries);
        assertTrue(vehicleDetailsEntries.size()==0);
    }

}