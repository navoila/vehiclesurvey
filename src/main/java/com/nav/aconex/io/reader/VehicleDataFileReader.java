package com.nav.aconex.io.reader;

import com.nav.aconex.constants.VehicleSurveyConfigConstants;
import com.nav.aconex.exception.InvalidVehicleDataException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class VehicleDataFileReader implements DataReader {

    private String dataFilePath = VehicleSurveyConfigConstants.DEFAULT_DATA_FILE_PATH;

    public VehicleDataFileReader() {
    }

    public VehicleDataFileReader(String dataFilePath) {
        this.dataFilePath = dataFilePath;
    }

    @Override
    public List<String> readData() throws
            InvalidVehicleDataException{
        InputStream in = getClass().getResourceAsStream("/" + dataFilePath);
        BufferedReader input = new BufferedReader(new InputStreamReader(in));
        Pattern regexPattern = Pattern.compile(VehicleSurveyConfigConstants.INPUT_DATA_ITEM_VALID_REGEX_PATTERN);
        List<String> dataEntries = input.lines().collect(Collectors.toList());
        if(dataEntries.isEmpty() || !(dataEntries.stream().allMatch(regexPattern
                .asPredicate())))
            throw new InvalidVehicleDataException("Invalid entries found in input " +
                    "data");
        return dataEntries;
    }
}
