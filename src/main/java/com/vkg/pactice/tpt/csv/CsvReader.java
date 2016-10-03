package com.vkg.pactice.tpt.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class CsvReader<T> {
    private BufferedReader reader;

    public CsvReader(final File file) throws FileNotFoundException {
        this(new BufferedReader(new FileReader(file)));
    }

    public CsvReader(final BufferedReader reader) {
        this.reader = reader;
    }

    public List<T> read() throws IOException {
        String line;
        List<T> result = new ArrayList<T>();
        do {
            line = reader.readLine();
            if (line != null) {
                T obj = readRow(line.split("\\s*,\\s*"));
                result.add(obj);
            }
        } while (line != null);

        return result;
    }

    protected abstract T readRow(String[] columns);
}
