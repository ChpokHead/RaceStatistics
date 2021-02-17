package com.chpok.formula1.reader;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class FileReaderImplTest {
    private static final String ABBREVIATIONS_DATA_FILE_PATH = "/abbreviations.txt";
    
    private final FileReaderImpl reader = new FileReaderImpl();
    
    @Test
    void readFileShouldReturnFileByLinesIfFilenameIsCorrect() {
        final List<String> expected = Arrays.asList("DRR_Daniel Ricciardo_RED BULL RACING TAG HEUER",
                "SVF_Sebastian Vettel_FERRARI", 
                "LHM_Lewis Hamilton_MERCEDES", 
                "VBM_Valtteri Bottas_MERCEDES");
        final List<String> actual = reader.readFile(ABBREVIATIONS_DATA_FILE_PATH);
        
        assertThat(actual.toArray()).isEqualTo(expected.toArray());
    }
    
}
