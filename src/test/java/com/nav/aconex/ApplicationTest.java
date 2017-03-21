package com.nav.aconex;

import com.nav.aconex.exception.InvalidVehicleDataException;
import org.junit.Test;
import org.mockito.Mockito;

public class ApplicationTest {

    @Test
    public void testApplicationMainRunsVehicleSurveyor() throws Exception{
        //given
        VehicleSurveyor surveyor = Mockito.mock(VehicleSurveyor.class);
        Application.setSurveyor(surveyor);
        //when
        Application.main(new String[] {});
        //then
        Mockito.verify(surveyor).run();
    }

    @Test
    public void testApplicationMainHandlesExceptionFromRunningVehicleSurveyor()
            throws Exception{
        //given
        VehicleSurveyor surveyor = Mockito.mock(VehicleSurveyor.class);
        InvalidVehicleDataException invalidVehicleDataException =
                Mockito.mock(InvalidVehicleDataException.class);
        Mockito.doThrow(invalidVehicleDataException).when(surveyor).run();
        Application.setSurveyor(surveyor);
        //when
        Application.main(new String[] {});
        //then
        Mockito.verify(surveyor).run();
        Mockito.verify(invalidVehicleDataException).printStackTrace();
    }

}