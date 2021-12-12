package com.company.yavlash.reader;

import com.company.yavlash.exception.TextException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TextReader {
    private static final Logger logger = LogManager.getLogger();

    public String readText(String filePath) throws TextException {
        if (getClass().getClassLoader().getResource(filePath) == null) {
            logger.log(Level.ERROR, "File {} does not exist in specified directory.", filePath);
            throw new TextException("File " + filePath + " does not exist in specified directory.");
        }
        String fileText;
        try {
            Path pathToFile = Paths.get(getClass().getClassLoader().getResource(filePath).toURI());
            fileText = Files.readString(pathToFile);
        } catch (IOException | URISyntaxException exception) {
            logger.log(Level.ERROR, "Error of reading file \"{}\": {}", filePath, exception);
            throw new TextException("Error of reading file \"" + filePath + "\": ", exception);
        }
        return fileText;
    }
}