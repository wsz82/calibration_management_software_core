package spio2023.cms.core.engine;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CalibrationLogger {
    private static final Path outputFile = Paths.get("target", "test-calibration");
    private BufferedWriter writer;

    public CalibrationLogger(String fileName) {
        fileName += ".txt";
        try {
            var file = outputFile.resolve(fileName).toFile();
            if (file.exists()) {
                file.delete();
            }
            file.getParentFile().mkdirs();
            file.createNewFile();
            var fileWriter = new FileWriter(file, true);
            writer = new BufferedWriter(fileWriter);
            System.out.println("Printing to output file: " + file);
        } catch (IOException e) {
            System.err.println("Error initializing CalibrationLogger: " + e.getMessage());
        }
    }

    public void log(Object message) {
        try {
            writer.write(message.toString());
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }

    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            System.err.println("Error closing log file: " + e.getMessage());
        }
    }

}