package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import utils.Sorter;


public class Analyzer {
	private ArrayList<String> tokenList;
	private ArrayList<Text> textList;
	Map<String, Integer> documentFrequency;

	Map<String, Double> InvertedDocumentFrequency;


	// this list is taken from the TextMine project
	private static final String DEFAULT_STOPWORDS =  "";

	//			"a about add ago after all also an and another any are as at be " +
	//					"because been before being between big both but by came can come " +
	//					"could did do does due each else end far few for from get got had " +
	//					"has have he her here him himself his how if in into is it its " +
	//					"just let lie like low make many me might more most much must " +
	//					"my never no nor not now of off old on only or other our out over " +
	//					"per pre put re said same see she should since so some still such " +
	//					"take than that the their them then there these they this those " +
	//					"through to too under up use very via want was way we well were " +
	//					"what when where which while who will with would yes yet you your";




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
			text.setWords(tokens.size());
			text.setTfValues(findTermFrequency(tokens));

		}
		findDocumentFrequency();

		InvertedDocumentFrequency = invertedDocumentFrequency(textList, documentFrequency);
		calculateTFIDF();
		for(Text ta : textList){

			ta.sortTfIDF();
		}
		Set<Map.Entry<Text, Double>> set = calculateCosineSimilarity(textList, "life learning").entrySet();
		for (Map.Entry<Text, Double> me : set) { 
			System.out.println("Filnavn: "+me.getKey().getName() +" Value: "+ me.getValue());
		}
	}

	/**
	 * Method for finding the term frequency of a term. 
	 * Removes the stopwords. 
	 * @param tokens
	 * @return Map of the tokens with the number of times they appear.
	 */
	public Map<String, Double> findTermFrequency(ArrayList<String> tokens){

		Map<String, Integer> map = new HashMap<String, Integer>();
		String[] StopWords = DEFAULT_STOPWORDS.split(" ");
		ArrayList<String> stopArrayList = new ArrayList<String>(Arrays.asList(StopWords));

		for(String word: tokens){
			int i=0;
			if(!stopArrayList.contains(word)){

				if(!map.containsKey(word)){

					addToTokenList(word);
					map.put(word, 0);
				}

				map.put(word, map.get(word)+1);
				int lol = +map.get(word)+1;


			}
		}
		Set<Entry<String, Integer>> set = map.entrySet(); 
		Map<String, Double> map2 = new HashMap<String, Double>();
		for (Map.Entry<String, Integer> me : set) { 
			double value = me.getValue();
			double antallOrd = tokens.size();
			double snitt = value/antallOrd;
			map2.put(me.getKey(),snitt);
		}
		return map2;
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
				.replace("\r", "")
				//				.replace("1", "")
				//				.replace("2", "")
				//				.replace("3", "")
				//				.replace("4", "")
				//				.replace("5", "")
				//				.replace("6", "")
				//				.replace("7", "")
				//				.replace("8", "")
				//				.replace("9", "")

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

	public Map<String, Double> invertedDocumentFrequency(ArrayList<Text> textList, Map<String, Integer> documentFrequency){
		Map<String, Double> invertedDocFreq = new HashMap<String, Double>();
		for(String s : documentFrequency.keySet()){
			double textListSize = textList.size();
			double docFreq = documentFrequency.get(s);
			double divided = textListSize/docFreq;
			double inverted =   Math.log(divided);
			invertedDocFreq.put(s, inverted);

		}
		return invertedDocFreq;
	}
	/**
	 * Method for calculating tf-idf for each Term. Multiplying the term frequency and the inverted document frequency.
	 *	
	 */

	public void calculateTFIDF(){

		for (Text t :  textList){
			Map<String, Double> tfIdfValues = new HashMap<String, Double>();
			//	
			Map<String, Double> tf = t.getTfValues();
			for(String s : tf.keySet()){

				double realtf = tf.get(s);
				Double tfidf =  (realtf * (0+InvertedDocumentFrequency.get(s))); 
				tfIdfValues.put(s,tfidf);


			}

			t.setTfIdf(tfIdfValues);

		}


	}
	public void calculateLDA() throws FileNotFoundException{
		for (Text t :  textList){

			PrintWriter out = new PrintWriter("haha.txt");
			out.println(t.getText().replace("\n", " "));
			out.close();
			TopicModellingLDA lda = new TopicModellingLDA();
			try {
				t.setLdaMap(lda.lda(5, 8, 400, new File("haha.txt")));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public HashMap<Text, Double> calculateCosineSimilarity(ArrayList<Text> textCollection, String Query){
		CosineSimilarity cosim = new CosineSimilarity();
		Text QueryText = new Text(Query);
		ArrayList<String> QueryTokens = tokenize(QueryText);


		HashMap<String, Double> TfIdfQuery = new HashMap<String, Double>();
		Map<String, Integer> TermFrequency = new HashMap<String, Integer>();

		String[] StopWords = DEFAULT_STOPWORDS.split(" ");
		ArrayList<String> stopArrayList = new ArrayList<String>(Arrays.asList(StopWords));


		for(String word: QueryTokens){

			if(!stopArrayList.contains(word)){

				if(!TermFrequency.containsKey(word)){


					TermFrequency.put(word, 0);
				}
				TermFrequency.put(word, TermFrequency.get(word)+1);

			}
		}

		Set<Entry<String, Integer>> set = TermFrequency.entrySet(); 
		Map<String, Double> map2 = new HashMap<String, Double>();
		for (Map.Entry<String, Integer> me : set) { 

			double value = me.getValue();
			double antallOrd = QueryTokens.size();
			double snitt = value/antallOrd;

			map2.put(me.getKey(),snitt);
		}
		for(String s: map2.keySet()){
			double termFr = map2.get(s);
			double idf =0;
			try {
				idf = 0 + InvertedDocumentFrequency.get(s);			

			} catch (NullPointerException e) {
				idf=0;
			}
			double tfIdf = termFr * idf;
			TfIdfQuery.put(s, (tfIdf));

		}

		HashMap<Text, Double> map = new HashMap<Text, Double>();
		for(Text t : textCollection){
			HashMap<String, Double> h1 = (HashMap<String, Double>) t.getTfIdf();
			map.put(t, cosim.calculateCosineSimilarity(h1,TfIdfQuery));
		}


		HashMap<Text, Double> map1 = (HashMap<Text, Double>) Sorter.sortMapByValuesDescending(map);
		QueryText.setTfIdf(TfIdfQuery);
		return map1;
	}
}
