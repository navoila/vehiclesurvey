package com.nav.aconex.io.reader;

import java.util.List;

public interface DataReader {
    List<String> readData() throws
            Exception;
}
