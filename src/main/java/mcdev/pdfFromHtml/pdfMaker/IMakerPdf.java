package mcdev.pdfFromHtml.pdfMaker;

import java.io.IOException;

public interface IMakerPdf {
    void createPdf(String html, String staticAssetsForder, boolean usePDFA, boolean accessiblePdf, String dest) throws IOException;
}
