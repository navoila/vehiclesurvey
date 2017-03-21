package com.nav.aconex;

import com.nav.aconex.analyser.VehicleCountPerDayAnalyser;
import com.nav.aconex.analyser.VehicleDataAnalyserFactory;
import com.nav.aconex.analyser.VehicleDataAnalyserType;
import com.nav.aconex.analyser.result.VehicleDataAnalysisResult;
import com.nav.aconex.constants.Direction;
import com.nav.aconex.exception.InvalidVehicleDataException;
import com.nav.aconex.io.reader.VehicleDataFileReader;
import com.nav.aconex.model.VehicleDetails;
import com.nav.aconex.parser.VehicleDataParser;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class VehicleSurveyorTest {
    private VehicleDataParser vehicleDataParser;
    private VehicleDataFileReader vehicleDataFileReader;
    private VehicleDataAnalyserFactory vehicleDataAnalyserFactory;

    private VehicleSurveyor classUnderTest;

    @Before
    public void setUp() throws Exception {
        //given
        classUnderTest = new VehicleSurveyor();
        vehicleDataParser = Mockito.mock(VehicleDataParser.class);
        vehicleDataFileReader = Mockito.mock(VehicleDataFileReader.class);
        vehicleDataAnalyserFactory = Mockito.mock
                (VehicleDataAnalyserFactory.class);
        classUnderTest.setVehicleDataAnalyserFactory(vehicleDataAnalyserFactory);
        classUnderTest.setVehicleDataFileReader(vehicleDataFileReader);
        classUnderTest.setVehicleDataParser(vehicleDataParser);
    }

    @Test(expected = InvalidVehicleDataException.class)
    public void shouldThrowExceptionWhenParserReturnsNullVehicleDetails()
            throws Exception {
        //when
        //by default mocked parser object should return null
        classUnderTest.run();
        //then
        //throw exception
    }

    @Test(expected = InvalidVehicleDataException.class)
    public void  shouldThrowExceptionWhenParserReturnsEmptyVehicleDetails()
            throws Exception {
        //when
        Mockito.doReturn(new ArrayList<VehicleDetails>()).when
                (vehicleDataParser).parse();
        classUnderTest.run();
        //then
        //throw exception
    }

    @Test
    public void  shouldAnalyseResultsWhenParserReturnsValidVehicleDetails()
            throws Exception {
        //when
        List<VehicleDetails> vehicleDetails = new ArrayList<VehicleDetails>();
        vehicleDetails.add(createDummyVehicleDetails());
        Mockito.doReturn(vehicleDetails).when
                (vehicleDataParser).parse();
        Mockito.doReturn(new VehicleCountPerDayAnalyser()).when
                (vehicleDataAnalyserFactory).getAnalyser(Mockito.any
                (VehicleDataAnalyserType.class));
        List<VehicleDataAnalysisResult> results = classUnderTest.run();
        //then
        assertNotNull(results);
        assertEquals(VehicleDataAnalyserType.values().length,results.size());

    }

    public static VehicleDetails createDummyVehicleDetails() {
        return new VehicleDetails(1, Direction.SOUTH,12345678,
                12345768,65, 1);
    }

    public static List<VehicleDetails> createDummyVehicleDetailsList() {
        List<VehicleDetails> vehDetails = new ArrayList<>();
        vehDetails.add(createDummyVehicleDetails());
        return vehDetails;
    }

}