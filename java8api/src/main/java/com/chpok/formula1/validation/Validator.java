package com.chpok.formula1.validation;

public class Validator implements ValidationProvider{
    @Override
    public void validate(String startDataFilePath, String endDataFilePath, String abbreviationsFilePath) {
        validateFilePathIsNull(startDataFilePath, "Start data file path is null!");
        validateFilePathIsNull(endDataFilePath, "End data file path is null!");
        validateFilePathIsNull(abbreviationsFilePath, "Abbreviations data file path is null!");
        
        validateFilePathIsEmpty(startDataFilePath, "Start data file path is empty!");
        validateFilePathIsEmpty(endDataFilePath, "End data file path is empty!");
        validateFilePathIsEmpty(abbreviationsFilePath, "Abbreviations data file path is empty!");
        
        validateFilePathContainsOnlySpacesOrTabs(startDataFilePath, "Start data file path contains only spaces or tabs!");
        validateFilePathContainsOnlySpacesOrTabs(endDataFilePath, "End data file path contains only spaces or tabs!");
        validateFilePathContainsOnlySpacesOrTabs(abbreviationsFilePath, "Abbreviations data file path contains only spaces or tabs!");
    }
    
    private void validateFilePathIsNull(String filePath, String exceptionMessage) {
        if (filePath == null) {
            throw new IllegalArgumentException(exceptionMessage);
        }
    }
    
    private void validateFilePathIsEmpty(String filePath, String exceptionMessage) {
        if (filePath.isEmpty()) {
            throw new IllegalArgumentException(exceptionMessage);
        }
    }
    
    private void validateFilePathContainsOnlySpacesOrTabs(String filePath, String exceptionMessage) {
        if (filePath.trim().isEmpty()) {
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

}
