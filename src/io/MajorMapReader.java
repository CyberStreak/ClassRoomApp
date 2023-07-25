package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MajorMapReader {
    private final File majorMapFile;

    public MajorMapReader(File majorMapFile) {
        this.majorMapFile = majorMapFile;
    }

    public Map<String, String> readMap() {
        Map<String, String> majorMap = new HashMap<>();

        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(majorMapFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (fileScanner != null) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();

                String[] tokens = line.split("\t");

                String code =  tokens[0].trim();
                String description = tokens[1].trim();

                majorMap.put(code, description);
            }
        }
        return majorMap;
    }
}
