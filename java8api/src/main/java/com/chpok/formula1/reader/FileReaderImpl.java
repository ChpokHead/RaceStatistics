package com.chpok.formula1.reader;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.chpok.formula1.exception.FileReaderException;

public class FileReaderImpl implements FileReader{
    private final static Logger LOGGER = LogManager.getLogger();
    
    @Override
    public List<String> readFile(String filename) {
        List<String> fileLines = new ArrayList<>();

        try {
            URI uri = FileReaderImpl.class.getResource(filename).toURI();
            Files.lines(Paths.get(uri)).forEach(fileLines::add);
        } catch (IOException | URISyntaxException e) {
            LOGGER.error("Exception catched", e);
            
            throw new FileReaderException();
        }
        
        return fileLines;
    }
    
}
