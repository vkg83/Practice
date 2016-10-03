package com.vkg.pactice.tpt.csv;

import java.io.File;
import java.io.FileNotFoundException;

public class PersonCsvReader extends CsvReader<Person> {
    public PersonCsvReader(final File file) throws FileNotFoundException {
        super(file);
    }

    @Override
    protected Person readRow(final String[] columns) {
        final int id = Integer.parseInt(columns[0]);
        final String name = columns[1];
        final double salary = Double.parseDouble(columns[2]);
        return new Person(id, name, salary);
    }
}
