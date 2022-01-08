package com.example.app;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.parser.PdfCanvasProcessor;
import com.itextpdf.kernel.pdf.canvas.parser.listener.IPdfTextLocation;
import com.itextpdf.kernel.pdf.canvas.parser.listener.RegexBasedLocationExtractionStrategy;
import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;

import java.io.IOException;

public class itext7 {

  public static final String SRC = "";

  public static void main(String[] args) throws IOException {
    String a = manipulatePdf();
  }

  private static String manipulatePdf() throws IOException {
    PdfDocument pdfDoc = new PdfDocument(new PdfReader(SRC));

    System.out.println(pdfDoc.getNumberOfPages());
    for (int i = 1; i <= pdfDoc.getNumberOfPages(); i++) {
      PdfPage page = pdfDoc.getPage(i);
      // new Regex Based Strategy with .*
      RegexBasedLocationExtractionStrategy strategy = new RegexBasedLocationExtractionStrategy(".*");
      // New Stream Processor that will send its output to the designated render listener
      PdfCanvasProcessor canvasProcessor = new PdfCanvasProcessor(strategy);
      // Creates a new PDF Content Stream Processor that will send its output to the designated render listener.
      canvasProcessor.processPageContent(page);
      // Returns the Rectangles that have been processed so far
      Collection<IPdfTextLocation> resultantLocations = strategy.getResultantLocations();

      System.out.println("---------------------------");
      for (IPdfTextLocation location : resultantLocations) {
        Rectangle rectangle = location.getRectangle();
        System.out.println(location.getPageNumber());
        System.out.println(location.getText());
        System.out.printf("X : %f \n", rectangle.getX());
        System.out.printf("Y : %f \n", rectangle.getY());
        System.out.printf("Width : %f \n", rectangle.getWidth());
        System.out.printf("Height : %f \n", rectangle.getHeight());
        System.out.println("---------------------------");
      }
    }
    pdfDoc.close();
    return "hello";
  }
}
