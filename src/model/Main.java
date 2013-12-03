package model;

import java.util.Map;

import reader.FileReader;



public class Main {
	public static void main(String[] args) {
		
		
		
		// TODO Auto-generated method stub
		FileReader f = new FileReader();
		Analyzer a = new Analyzer();
		
		String[] filesToRead = new String[1];
		filesToRead[0] = "networks-book-ch14.pdf";
		
		
		for(int i = 0; i < filesToRead.length; i++){
		Text t = f.readPDF(filesToRead[i]);
		Map<String, Integer> map = a.tokenize(t);
		
		for(String s : map.keySet()){
			System.out.println(s + " " + map.get(s));
		}
		
		
//		System.out.println(t.getText());
		
		}
	}
}
