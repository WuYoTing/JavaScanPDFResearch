package com.example.app;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

import java.io.*;
import java.util.List;

/**
 * Example of use pdfbox to read only one word
 */
public class GetCharLocationAndSize extends PDFTextStripper {
    // File Path
    public static final String PDF = "";

    public GetCharLocationAndSize() throws IOException {
    }

    /**
     * @throws IOException If there is an error parsing the document.
     */
    public static void main(String[] args) throws IOException {
        PDDocument document = null;
        try {
            document = PDDocument.load(new File(PDF));
            PDFTextStripper stripper = new GetCharLocationAndSize();
            stripper.setSortByPosition(true);
            stripper.setStartPage(0);
            stripper.setEndPage(document.getNumberOfPages());

            Writer dummy = new OutputStreamWriter(new ByteArrayOutputStream());
            System.out.println("-----------");
            stripper.writeText(document, dummy);
        } finally {
            if (document != null) {
                document.close();
            }
        }
    }

    /**
     * Override the default functionality of PDFTextStripper.writeString()
     */
    @Override
    protected void writeString(String string, List<TextPosition> textPositions) throws IOException {
        float xMin = 0, xMax = 0, yMin = 0, yMax = 0, totalWidth = 0, totalHeight = 0;

        for (int i = 0; i < textPositions.size(); i++) {
            String text = textPositions.get(i).getUnicode();
            Float xPosition = textPositions.get(i).getXDirAdj();
            Float yPosition = textPositions.get(i).getYDirAdj();
            Float height = textPositions.get(i).getHeightDir();
            Float width = textPositions.get(i).getWidthDirAdj();

            if (i == 0) {
                totalHeight = 2 * height;
                totalWidth += 2 * width;
                xMin = xPosition - width;
                yMax = yPosition + height;
                yMin = yPosition - height;
            } else if (i == textPositions.size() - 1) {
                xMax = textPositions.get(i).getXDirAdj() + textPositions.get(i).getWidthDirAdj();
            } else {
                totalWidth += textPositions.get(i).getWidthDirAdj();
            }
            System.out.println(
                    text + " [(X=" + xPosition + ",Y=" + yPosition + ") height=" + height + " width=" + width + "]"
            );
        }

        System.out.print("Width Range : ");
        System.out.printf("%.5f ~ ", xMin);
        System.out.printf("%.5f \n", xMax);

        System.out.print("Height Range : ");
        System.out.printf("%.5f ~ ", yMin);
        System.out.printf("%.5f \n", yMax);

        System.out.print("Total Width : ");
        System.out.printf("%.5f \n", totalWidth);
        System.out.print("Total height : ");
        System.out.printf("%.5f \n", totalHeight);
        System.out.println("-----------");
    }
}
