package com.app;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by rentala on 4/1/18.
 */
public class FileHelper {

    public List<String> read(String filePath) {
        List<String> list = new LinkedList<String>();
        try {
            File f = new File(filePath);
            BufferedReader b = new BufferedReader(new FileReader(f));
            String line = "";
            while ((line = b.readLine()) != null) {
                list.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<String> getIndexData(String word) {
        String dataFile = INDEX_DIR+ "/"+word.charAt(0) + "/"+word+".csv";
        if(new File(dataFile).exists()) {
            String line = read(dataFile).get(0);
            List<String> lines = new LinkedList<String>();
            for(String l :line.split(",")) {
                lines.add(l);
            }
            return lines;
        }
        return null;

    }
    public void writeToDisk(HashMap<String, List<String>> map) throws IOException {
        // create base index
        File rootIndex = new File(INDEX_DIR);
        rootIndex.mkdirs();
        // mkdir foreach sorted value
        for(String sortedWord : map.keySet()) {
            // index alphabetically
            String indexDir = INDEX_DIR+"/"+sortedWord.charAt(0);
            File index = new File(indexDir);
            if (!index.exists())
                index.mkdirs();
            // write all anagrams to a data file

            String csvFile = indexDir + "/" +sortedWord + ".csv";
            FileWriter writer = new FileWriter(csvFile);
            writeLine(writer, map.get(sortedWord));
            writer.flush();
            writer.close();
        }
        System.out.println(" Completed writing the map to disk . . ");

    }
    private static final char DEFAULT_SEPARATOR = ',';
    private static final String INDEX_DIR = "../index";

    public static void writeLine(Writer w, List<String> values) throws IOException {
        writeLine(w, values, DEFAULT_SEPARATOR, ' ');
    }
    private static String followCVSformat(String value) {

        String result = value;
        if (result.contains("\"")) {
            result = result.replace("\"", "\"\"");
        }
        return result;

    }
    public static void writeLine(Writer w, List<String> values, char separators, char customQuote) throws IOException {

        boolean first = true;

        if (separators == ' ') {
            separators = DEFAULT_SEPARATOR;
        }

        StringBuilder sb = new StringBuilder();
        for (String value : values) {
            if (!first) {
                sb.append(separators);
            }
            if (customQuote == ' ') {
                sb.append(followCVSformat(value));
            } else {
                sb.append(customQuote).append(followCVSformat(value)).append(customQuote);
            }

            first = false;
        }
        sb.append("\n");
        w.append(sb.toString());


    }
}

