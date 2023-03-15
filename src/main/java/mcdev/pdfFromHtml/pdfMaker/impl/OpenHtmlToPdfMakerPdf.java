package mcdev.pdfFromHtml.pdfMaker.impl;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.w3c.dom.Document;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder.PdfAConformance;
import com.openhtmltopdf.svgsupport.BatikSVGDrawer;

import mcdev.pdfFromHtml.pdfMaker.IMakerPdf;


public class OpenHtmlToPdfMakerPdf implements IMakerPdf {

    @Override
    public void createPdf(String html, String staticAssetsForder, boolean usePDFA, boolean accessiblePdf, String dest) throws IOException {
        try (OutputStream os = new FileOutputStream(dest)) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            System.out.println("Base URI: " + staticAssetsForder);
            builder.withW3cDocument(html5ParseDocument(html), "file:" + staticAssetsForder);
            builder.useSVGDrawer(new BatikSVGDrawer());
            loadFonts(builder);
            if(accessiblePdf)
            	builder.usePdfUaAccessbility(true);
            if(usePDFA) {

//            	builder.usePdfAConformance(PdfAConformance.PDFA_1_A);
//            	builder.usePdfAConformance(PdfAConformance.PDFA_1_B);
//            	builder.usePdfAConformance(PdfAConformance.PDFA_2_A);
//            	builder.usePdfAConformance(PdfAConformance.PDFA_2_B);
//            	builder.usePdfAConformance(PdfAConformance.PDFA_2_U);
//            	builder.usePdfAConformance(PdfAConformance.PDFA_3_A);
//            	builder.usePdfAConformance(PdfAConformance.PDFA_3_B);
            	builder.usePdfAConformance(PdfAConformance.PDFA_3_U);
            	
				try (InputStream in = OpenHtmlToPdfMakerPdf.class.getResourceAsStream("/");
						BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
					String resource;

					while ((resource = br.readLine()) != null) {
						System.out.println("res: " + resource);
					}
				}
            	
           		try (InputStream colorProfile = OpenHtmlToPdfMakerPdf.class.getResourceAsStream("/colorspace/sRGB.icc")) {
                    byte[] colorProfileBytes = IOUtils.toByteArray(colorProfile);
                    builder.useColorProfile(colorProfileBytes);
                }
            }
            // set output to an output stream set 
            builder.toStream(os);
            // Run the XHTML/XML to PDF conversion and 
            builder.run();
            //prints the message if the PDF is created successfully
            System.out.println("PDF created");
        }
    }
    
    private static void loadFonts(PdfRendererBuilder builder) {
//    	builder.useFont(new File(baseUri + "fonts/DMSans-Bold.ttf"), "DMSans");
//    	builder.useFont(new File(baseUri + "fonts/DMSans-BoldItalic.ttf"), "DMSans");   
//    	builder.useFont(new File(baseUri + "fonts/DMSans-Italic.ttf"), "DMSans");   
//    	builder.useFont(new File(baseUri + "fonts/DMSans-Medium.ttf"), "DMSans");   
//    	builder.useFont(new File(baseUri + "fonts/DMSans-MediumItalic.ttf"), "DMSans");   
//    	builder.useFont(new File(baseUri + "fonts/DMSans-Regular.ttf"), "DMSans");   
    }
    
	private static Document html5ParseDocument(String inputHTML) throws IOException {
		org.jsoup.nodes.Document doc;
		doc = Jsoup.parse(inputHTML);
		return new W3CDom().fromJsoup(doc);
	}

    

}
