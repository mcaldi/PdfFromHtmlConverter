package mcdev.pdfFromHtml.pdfMaker.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Paths;

import org.jsoup.Jsoup;

import mcdev.pdfFromHtml.pdfMaker.IMakerPdf;

public class FyingsaucerMakerPdf implements IMakerPdf {

    @Override
    public void createPdf(String html, String staticAssetsForder, boolean usePDFA, boolean accessiblePdf, String dest) throws IOException {        try (OutputStream os = new FileOutputStream(dest)) {
    	
			if (usePDFA)
				System.err.println("WARNING - cannot create a PDFA - not implemented");
			if (accessiblePdf)
				System.err.println("WARNING - cannot create an accessibile PDF - not implemented");
			
        	org.xhtmlrenderer.pdf.ITextRenderer renderer = new org.xhtmlrenderer.pdf.ITextRenderer();
            org.xhtmlrenderer.layout.SharedContext sharedContext = renderer.getSharedContext();
            sharedContext.setPrint(true);
			sharedContext.setInteractive(false);
			renderer.setDocumentFromString(getXhtml(html).html(), "file:" + staticAssetsForder);
			renderer.layout();
			renderer.createPDF(os);
        } 
    }
    
	
	private static org.jsoup.nodes.Document getXhtml(String html) {
		org.jsoup.nodes.Document result = Jsoup.parse(html);
		result.outputSettings().syntax(org.jsoup.nodes.Document.OutputSettings.Syntax.xml);
		return result;
	}

    

}
