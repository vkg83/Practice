package com.vkg.pactice.tpt;

import com.vkg.pactice.tpt.csv.CsvReader;
import com.vkg.pactice.tpt.csv.Person;
import com.vkg.pactice.tpt.csv.PersonCsvReader;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class CsvReaderClient {
    public static void main(String[] args) throws IOException, URISyntaxException {
        File file = new File(CsvReaderClient.class.getResource("/person.csv").toURI());
        CsvReader<Person> reader = new PersonCsvReader(file);
        final List<Person> personList = reader.read();
        personList.forEach(System.out::println);
    }
}
