package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Analyzer {
	

	  // this list is taken from the TextMine project
	  private static final String DEFAULT_STOPWORDS = 
	    "a about add ago after all also an and another any are as at be " +
	    "because been before being between big both but by came can come " +
	    "could did do does due each else end far few for from get got had " +
	    "has have he her here him himself his how if in into is it its " +
	    "just let lie like low make many me might more most much must " +
	    "my never no nor not now of off old on only or other our out over " +
	    "per pre put re said same see she should since so some still such " +
	    "take than that the their them then there these they this those " +
	    "through to too under up use very via want was way we well were " +
	    "what when where which while who will with would yes yet you your";
	
	 
	
	
	public Analyzer(){

	}
	public void findTermFrequency(){
		
	}
	public void findDocumentFrequency(){
		
		
	}
	public Map<String, Integer> tokenize(Text t){
		
		String[] StopWords = DEFAULT_STOPWORDS.split(" ");
		ArrayList<String> stopArrayList = new ArrayList<String>(Arrays.asList(StopWords));
		
		
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		String[] tokens = t.getText().replace(",","")
				.replace(".","")
				.replace(";", "")
				.replace("(", "")
				.replace(")", "")
				.replace("<", "")
				.replace(">", "")
				.replace("[", "")
				.replace("]", "")
				.replace("\"", "")
				.replace('"', ' ')
				.replace(":", "")
				.toLowerCase()
				.split(" ");
		ArrayList<String> tokenArrayList = new ArrayList<String>(Arrays.asList(tokens));
			
		
		for(String word: tokenArrayList){
			if(!stopArrayList.contains(word)){
				if(!map.containsKey(word)){
					map.put(word.toLowerCase(), 0);
				}
				map.put(word, map.get(word)+1);
			}
			else{
//				System.out.println("StoppOrd");
//				System.out.println(word);
			}
	}
		return map;
	
	
	
}
}
