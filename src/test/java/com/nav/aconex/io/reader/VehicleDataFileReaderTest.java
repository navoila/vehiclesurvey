package com.nav.aconex.io.reader;

import com.nav.aconex.exception.InvalidVehicleDataException;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class VehicleDataFileReaderTest {
    private VehicleDataFileReader classUnderTest;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void shouldReadDataFromDefaultFile() throws Exception {
        //given
        classUnderTest = new VehicleDataFileReader();
        //when
        List<String> vehicleData = classUnderTest.readData();
        //then
        assertNotNull(vehicleData);
        assertFalse(vehicleData.isEmpty());
    }

    @Test
    public void shouldReadDataFromSpecifiedFileWhenFileExists() throws
            Exception {
        //given
        classUnderTest = new VehicleDataFileReader("data1.txt");
        //when
        List<String> vehicleData = classUnderTest.readData();
        //then
        assertNotNull(vehicleData);
        assertFalse(vehicleData.isEmpty());
    }

    @Test(expected = InvalidVehicleDataException.class)
    public void shouldThrowExceptionWhenDataIsInvalidInFile() throws
            InvalidVehicleDataException {
        //given
        classUnderTest = new VehicleDataFileReader("invalid-data-file.txt");
        //when
        List<String> vehicleData = classUnderTest.readData();
        System.out.println(vehicleData);
        //then
        //throws exception

    }

    @Test(expected = InvalidVehicleDataException.class)
    public void shouldThrowExceptionWhenDataFileEmpty() throws
            InvalidVehicleDataException {
        //given
        classUnderTest = new VehicleDataFileReader("empty-data-file.txt");
        //when
        List<String> vehicleData = classUnderTest.readData();
        System.out.println(vehicleData);
        //then
        //throws exception

    }

}