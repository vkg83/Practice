package com.vkg.tools.formatter.client;

import com.vkg.tools.formatter.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;

/**
 * Created by Vishnu on 12/14/2018.
 */
public class ExcelClient {
    public static void main(String[] args) throws IOException, InvalidFormatException {
        CompositeObject person = new CompositeObject("person");
        person.addItem(new TextField("name", "Vishnu"));
        person.addItem(new NumberField("age", 35));
        person.addItem(new TextField("phone", "9766045435").multi(4));
        person.addItem(new NumberField("salary", 120000));
        CompositeObject address = new CompositeObject("address");
        address.addItem(new TextField("line", "Lane 5").multi());
        address.addItem(new TextField("city", "Pune"));
        address.addItem(new TextField("state", "Maharashtra"));
        person.addItem(address.multi());
        person.addItem(new TextField("designation"));
        ExcelWriter visitor = new ExcelWriter();
        visitor.addSheet("Persons", person.multi());
        CompositeObject v = new CompositeObject("validation");
        v.setConfig(new ExcelVisitorConfig(true, true));
        v.addItem(new TextField("tabId"));
        //CompositeObject rule = new CompositeObject("rules");
        v.addItem(new TextField("name").multi());
        v.addItem(new TextField("type").multi());
        v.addItem(new TextField("param").multi());
        v.addItem(new TextField("enabled", "true").multi());
        v.addItem(new TextField("errorLevel", "E").multi());
        //CompositeArray a = rule.multi();
        //a.setConfig(new ExcelVisitorConfig(false, true));
        //v.addItem(rule.multi());
        visitor.addSheet("Validations", v.multi(5), false);
        visitor.write("C:\\Users\\Vishnu Kant Gupta\\Desktop\\sample.xlsx");
    }
}
