package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Analyzer {
	private ArrayList<String> tokenList;
	private ArrayList<Text> textList;
	Map<String, Integer> documentFrequency;

	Map<String, Double> InvertedDocumentFrequency;


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




	public Analyzer(ArrayList<Text> docs){

		this.tokenList = new ArrayList<String>();

		this.textList = docs;

		this.documentFrequency = new HashMap<String, Integer>();

		InvertedDocumentFrequency = new HashMap<String, Double>();

		// First sends the docments to be tokenized. Then Stores Calculates the term frequency of the document
		//Stores all the values regarding the document in the text class. 
		for(Text text : textList){

			ArrayList<String> tokens = tokenize(text);
			text.setTokens(tokens);
			text.setTfValues(findTermFrequency(tokens));

		}
		findDocumentFrequency();
		invertedDocumentFrequency();
		calculateTFIDF();
		for(Text ta : textList){
			ta.sortTfIDF();
		}
	}

	/**
	 * Method for finding the term frequency of a term. 
	 * Removes the stopwords. 
	 * @param tokens
	 * @return Map of the tokens with the number of times they appear.
	 */
	public Map<String, Integer> findTermFrequency(ArrayList<String> tokens){

		Map<String, Integer> map = new HashMap<String, Integer>();
		String[] StopWords = DEFAULT_STOPWORDS.split(" ");
		ArrayList<String> stopArrayList = new ArrayList<String>(Arrays.asList(StopWords));

		for(String word: tokens){

			if(!stopArrayList.contains(word)){

				if(!map.containsKey(word)){

					addToTokenList(word);
					map.put(word, 0);
				}
				map.put(word, map.get(word)+1);

			}
			//			

		}
		return map;
	}

	/**
	 * Method for finding the document frequency.
	 * Takes a unique token and counts how many documents it exits in.
	 */
	public void findDocumentFrequency(){

		for(String uniqueToken : tokenList){
			for(Text t : textList){
				if(t.getTokens().contains(uniqueToken)){
					if(!documentFrequency.containsKey(uniqueToken)){
						documentFrequency.put(uniqueToken, 0);
					}
					documentFrequency.put(uniqueToken, documentFrequency.get(uniqueToken)+1);
				}
			}
		}

		//		for(String s : documentFrequency.keySet()){
		//						System.out.println(s + " " + documentFrequency.get(s));
		//		}

	}






	/**
	 * Method for tokenization of the texts. Removes special characters and splits the words based on 
	 * space.
	 * @param t Text t is the text itself. 
	 * @return	ArrayList<String> Returns an arrayList of Tokens.
	 */
	public ArrayList<String> tokenize(Text t){
		String[] tokens = t.getText()
				.replace(",","")
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
				.replace("/" , "")
				.replace("?", "")
				.replace("\n", "")
				.replace("-", "")
				.toLowerCase()
				.split(" ");
		ArrayList<String> tokenHashSet = new ArrayList<String>(Arrays.asList(tokens));



		return tokenHashSet;

		/**
		 * Method for adding the unique tokens to the list.
		 * For use in analyzing	
		 */


	}

	public void addToTokenList(String token){
		if(!tokenList.contains(token)){
			tokenList.add(token);
		}
	}
	/**
	 * This method finds the inverted document frequency. Divide the total number of documents by the document frequency.
	 */

	public void invertedDocumentFrequency(){
		for(String s : documentFrequency.keySet()){
			Double inverted =   ((Math.log(textList.size() / documentFrequency.get(s))));
			InvertedDocumentFrequency.put(s, inverted);

		}
	}
	/**
	 * Method for calculating tf-idf for each Term. Multiplying the term frequency and the inverted document frequency.
	 *	
	 */

	public void calculateTFIDF(){

		for (Text t :  textList){
			Map<String, Double> tfIdfValues = new HashMap<String, Double>();
			//	
			Map<String, Integer> tf = t.getTfValues();
			for(String s : tf.keySet()){
				try{

					Double tfidf = (double) tf.get(s) * (InvertedDocumentFrequency.get(s)); 
					tfIdfValues.put(s,tfidf);
				}
				catch(NullPointerException e){
					
				}

			}
			t.setTfIdf(tfIdfValues);


	

		}


	}
}
