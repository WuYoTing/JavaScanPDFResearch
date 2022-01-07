package com.example.app;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

import java.io.*;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    // File Path
    public static final String PDF = "";

    public static void main(String[] args) throws IOException {
        try {
            PDDocument document = PDDocument.load(new File(PDF));
            PDFTextStripper textStripper = new PDFTextStripper();
            // Get total page count of the PDF document
            int numberOfPages = document.getNumberOfPages();
            //set the first page to be extracted
            textStripper.setStartPage(1);
            // set the last page to be extracted
            textStripper.setEndPage(numberOfPages);
            String text = textStripper.getText(document);
            System.out.println(text);
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
