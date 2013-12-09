package model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import utils.Sorter;

/**
 * This class represents the texts. All relevant data stored here
 * @author Lars Petter
 *
 */



public class Text {
	private String text;
	private Map<String, Double> tfValues;
	private ArrayList<String> tokens;
	private Map<String, Double> tfIdf;
	private HashMap<Double, String> ldaMap;
	private String name;
	private int words;


	public HashMap<Double, String> getLdaMap() {
		return ldaMap;
	}

	public void setLdaMap(HashMap<Double, String> ldaMap) {
		this.ldaMap = ldaMap;
	}

	@SuppressWarnings("static-access")
	public void sortTfIDF(){

		Map<String, Double> sortedMap = new LinkedHashMap<String, Double>(tfIdf);

		Sorter sorter = new Sorter();
		sortedMap = sorter.sortMapByValuesAscending(tfIdf);
		
//				for(String s  : sortedMap.keySet()){
//		
//						System.out.println(s + " Value tfidf: " + sortedMap.get(s));
//					}





	}

	public Map<String, Double> getTfIdf() {
		return tfIdf;
	}


	public void setTfIdf(Map<String, Double> tfIdf) {
		this.tfIdf = tfIdf;
	}


	public ArrayList<String> getTokens() {
		return tokens;
	}


	public void setTokens(ArrayList<String> tokens) {
		this.tokens = tokens;
	}


	public Map<String, Double> getTfValues() {
		return tfValues;
	}


	public void setTfValues(Map<String, Double> tfValues) {
		this.tfValues = tfValues;
	}


	public Text(String Text){
		this.text = Text;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}
	public void printLdaMap(){
		Set<Map.Entry<Double, String>> set = ldaMap.entrySet(); 
		DecimalFormat df = new DecimalFormat("0.00"); 
		for (Map.Entry<Double, String> me : set) { 
			System.out.println("Distribution :"+df.format(me.getKey()) +" Topic : "+ me.getValue());

		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWords() {
		return words;
	}

	public void setWords(int words) {
		this.words = words;
	}
}
