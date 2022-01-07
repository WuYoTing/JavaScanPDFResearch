package com.example.app;

import com.itextpdf.kernel.pdf.PdfArray;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfCanvasProcessor;
import com.itextpdf.kernel.pdf.canvas.parser.listener.LocationTextExtractionStrategy;
import com.itextpdf.forms.PdfAcroForm;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class itext7 {

  public static final String DEST = "C:\\Users\\yung\\Desktop\\記憶體發票.txt";

  public static final String SRC = "C:\\Users\\yung\\Desktop\\文件\\記憶體發票.pdf";

  public static void main(String[] args) throws IOException {
    File file = new File(DEST);
    file.getParentFile().mkdirs();

    new itext7().manipulatePdf(DEST);
  }

  protected void manipulatePdf(String dest) throws IOException {
    PdfDocument pdfDoc = new PdfDocument(new PdfReader(SRC));
    // Create a text extraction renderer
    LocationTextExtractionStrategy strategy = new LocationTextExtractionStrategy();
    // Note: if you want to re-use the PdfCanvasProcessor, you must call PdfCanvasProcessor.reset()
    PdfCanvasProcessor parser = new PdfCanvasProcessor(strategy);
    parser.processPageContent(pdfDoc.getFirstPage());
    String actualText = strategy.getResultantText();
    System.out.println(actualText);
    pdfDoc.close();

  }
}
