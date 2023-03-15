package mcdev.pdfFromHtml.pdfMaker;

public enum PDFLibraryEnum {
	OPENHTMLTOPDF(0),
	FYING_SAUCER(1);

	private final int value;

    /**
     * @param text
     */
	PDFLibraryEnum(final int value) {
        this.value = value;
    }
	
    public int getValue() {
        return value;
    }

}
