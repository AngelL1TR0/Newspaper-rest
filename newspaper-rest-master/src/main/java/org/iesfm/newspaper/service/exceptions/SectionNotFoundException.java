package org.iesfm.newspaper.service.exceptions;

public class SectionNotFoundException extends Exception {

    private final int sectionId;

    public SectionNotFoundException(int sectionId) {
        super();
        this.sectionId = sectionId;
    }

    public int getSectionId() {
        return sectionId;
    }
}
