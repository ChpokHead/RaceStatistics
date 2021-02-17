package com.chpok.formula1.validation;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.Test;

class ValidatorTest {
    private final Validator validator = new Validator();
    
    @Test
    void validateShouldThrowIllegalArgumentExceptionIfStartDataFilePathIsNull() {
        final String startDataFilePath = null;
        final String endDataFilePath = "/end.log";
        final String abbreviationsDataFilePath = "/abbreviations.txt";
        final String expected = "Start data file path is null!";
        
        assertThatThrownBy(() -> validator.validate(startDataFilePath, endDataFilePath, abbreviationsDataFilePath)).isInstanceOf(IllegalArgumentException.class).hasMessage(expected);
    }
    
    @Test
    void validateShouldThrowIllegalArgumentExceptionIfEndDataFilePathIsNull() {
        final String startDataFilePath = "/start.log";
        final String endDataFilePath = null;
        final String abbreviationsDataFilePath = "/abbreviations.txt";
        final String expected = "End data file path is null!";
        
        assertThatThrownBy(() -> validator.validate(startDataFilePath, endDataFilePath, abbreviationsDataFilePath)).isInstanceOf(IllegalArgumentException.class).hasMessage(expected);
    }
    
    @Test
    void validateShouldThrowIllegalArgumentExceptionIfAbbreviationsDataFilePathIsNull() {
        final String startDataFilePath = "/start.log";
        final String endDataFilePath = "/end.log";
        final String abbreviationsDataFilePath = null;
        final String expected = "Abbreviations data file path is null!";
        
        assertThatThrownBy(() -> validator.validate(startDataFilePath, endDataFilePath, abbreviationsDataFilePath)).isInstanceOf(IllegalArgumentException.class).hasMessage(expected);
    }
    
    @Test
    void validateShouldThrowIllegalArgumentExceptionIfStartDataFilePathIsEmpty() {
        final String startDataFilePath = "";
        final String endDataFilePath = "/end.log";
        final String abbreviationsDataFilePath = "/abbreviations.txt";
        final String expected = "Start data file path is empty!";
        
        assertThatThrownBy(() -> validator.validate(startDataFilePath, endDataFilePath, abbreviationsDataFilePath)).isInstanceOf(IllegalArgumentException.class).hasMessage(expected);
    }
    
    @Test
    void validateShouldThrowIllegalArgumentExceptionIfEndDataFilePathIsEmpty() {
        final String startDataFilePath = "/start.log";
        final String endDataFilePath = "";
        final String abbreviationsDataFilePath = "/abbreviations.txt";
        final String expected = "End data file path is empty!";
        
        assertThatThrownBy(() -> validator.validate(startDataFilePath, endDataFilePath, abbreviationsDataFilePath)).isInstanceOf(IllegalArgumentException.class).hasMessage(expected);
    }

    @Test
    void validateShouldThrowIllegalArgumentExceptionIfAbbreviationsDataFilePathIsEmpty() {
        final String startDataFilePath = "/start.log";
        final String endDataFilePath = "/end.log";
        final String abbreviationsDataFilePath = "";
        final String expected = "Abbreviations data file path is empty!";
        
        assertThatThrownBy(() -> validator.validate(startDataFilePath, endDataFilePath, abbreviationsDataFilePath)).isInstanceOf(IllegalArgumentException.class).hasMessage(expected);
    }
    
    @Test
    void validateShouldThrowIllegalArgumentExceptionIfStartDataFilePathContainsOnlySpacesOrTabs() {
        final String startDataFilePath = "      ";
        final String endDataFilePath = "/end.log";
        final String abbreviationsDataFilePath = "/abbreviations.txt";
        final String expected = "Start data file path contains only spaces or tabs!";
        
        assertThatThrownBy(() -> validator.validate(startDataFilePath, endDataFilePath, abbreviationsDataFilePath)).isInstanceOf(IllegalArgumentException.class).hasMessage(expected);
    }
    
    @Test
    void validateShouldThrowIllegalArgumentExceptionIfEndDataFilePathContainsOnlySpacesOrTabs() {
        final String startDataFilePath = "/start.log";
        final String endDataFilePath = "         ";
        final String abbreviationsDataFilePath = "/abbreviations.txt";
        final String expected = "End data file path contains only spaces or tabs!";
        
        assertThatThrownBy(() -> validator.validate(startDataFilePath, endDataFilePath, abbreviationsDataFilePath)).isInstanceOf(IllegalArgumentException.class).hasMessage(expected);
    }
    
    @Test
    void validateShouldThrowIllegalArgumentExceptionIfAbbreviationsDataFilePathContainsOnlySpacesOrTabs() {
        final String startDataFilePath = "/start.log";
        final String endDataFilePath = "/end.log";
        final String abbreviationsDataFilePath = "        ";
        final String expected = "Abbreviations data file path contains only spaces or tabs!";
        
        assertThatThrownBy(() -> validator.validate(startDataFilePath, endDataFilePath, abbreviationsDataFilePath)).isInstanceOf(IllegalArgumentException.class).hasMessage(expected);
    }
    
    @Test
    void validateShouldNotThrowExceptionWhenEveryFilePathIsCorrect() {
        final String startDataFilePath = "/start.log";
        final String endDataFilePath = "/end.log";
        final String abbreviationsDataFilePath = "/abbreviations.txt";
        
        assertDoesNotThrow(() -> validator.validate(startDataFilePath, endDataFilePath, abbreviationsDataFilePath));
    }
}
