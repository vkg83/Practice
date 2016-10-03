package com.vkg.pactice.other;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OutOfDateSoftware {
    public static void main(String[] args) throws IOException {
        OutOfDateSoftware ood = new OutOfDateSoftware();
        BufferedReader reader = new BufferedReader(new FileReader("/Users/guptav/Documents/workspaceLearn/Practice/src/main/resources/input.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/guptav/Documents/workspaceLearn/Practice/src/main/resources/output.txt"));
        ood.find(reader, writer);

        writer.close();
    }

    void find(final BufferedReader reader, final BufferedWriter writer) throws IOException {
        Software sw;
        Map<String, List<Software>> swMap = new HashMap<>();
        while ((sw = readSoftware(reader)) != null) {
            List<Software> tmp = swMap.get(sw.getName());
            if (tmp == null) {
                tmp = new ArrayList<>();
                swMap.put(sw.getName(), tmp);
            }

            tmp.add(sw);
        }
        for (String swName : swMap.keySet()) {
            if(containsOutOfDate(swMap.get(swName))) {
                writer.write(swName);
                writer.write("\n");
            }
        }
    }

    private boolean containsOutOfDate(final List<Software> softwares) {
        Collections.sort(softwares, (sw1, sw2)-> sw2.getVersion().compareTo(sw1.getVersion()));
        Software latest = softwares.get(0);

        int oodCount = 0;
        for(int i = 1; i < softwares.size(); i++) {
            if(softwares.get(i).getVersion().compareTo(latest.getVersion()) < 0) {
                oodCount++;
            }
        }
        return oodCount >= 2;
    }

    Software readSoftware(final BufferedReader reader) throws IOException {
        String line = reader.readLine();
        if(line == null) return null;

        String words[] = line.split(", ");
        return new Software(words[0], words[1], words[2], new Version(words[3]));
    }
}

class Software {

    private String name;
    private Version version;

    public Software(final String location, final String type, final String name, final Version version) {
        this.name = name;
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public Version getVersion() {
        return version;
    }
}

class Version implements Comparable<Version> {
    ArrayList<Integer> numbers;

    public Version(final String version) {
        numbers = new ArrayList<>();
        String[] nums = version.split("\\.");
        for (int i = 0; i < nums.length; i++) {
            numbers.add(Integer.parseInt(nums[i].trim()));
        }
    }

    @Override
    public int compareTo(final Version o) {
        int level = 0;
        while(numbers.size() > level && o.numbers.size() > level) {
            int diff = numbers.get(level) - o.numbers.get(level);
            if(diff != 0) {
                return diff;
            }
            level++;
        }

        if(numbers.size() > level) {
            return 1;
        } else if(o.numbers.size() > level) {
            return -1;
        }
        return 0;
    }
}
