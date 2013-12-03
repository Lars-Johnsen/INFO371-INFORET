package model;

import reader.FileReader;



public class Main {
	public static void main(String[] args) {
		
		
		
		// TODO Auto-generated method stub
		FileReader f = new FileReader();
		Analyzer a = new Analyzer();
		
		String[] filesToRead = new String[1];
		filesToRead[0] = "109003349.pdf";
		
		
		for(int i = 0; i < filesToRead.length; i++){
		Text t = f.readPDF(filesToRead[i]);
		System.out.println(t.getText());
		
		}
	}
}
