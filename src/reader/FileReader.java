package reader;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import model.Text;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

public class FileReader {

	public FileReader(){
		
	}
	
	
public Text readPDF(String file){
 
	try {
		PDDocument doc = PDDocument.load(file);
		PDFTextStripper stripper = new PDFTextStripper();
		String input = stripper.getText(doc);
		Text resultText = new Text(input);
		
		
		return resultText;
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}

}
