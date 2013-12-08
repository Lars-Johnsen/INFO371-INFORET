package model;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class CosineSimilarity {

	/**
	 * calculate the cosine similarity between feature vectors of two clusters
	 *
	 * The feature vector is represented as HashMap<String, Double>.
	 *
	 * @param firstFeatures The feature vector of the first cluster
	 * @param secondFeatures The feature vector of the second cluster
	 * @return the similarity measure
	 */
	public static Double calculateCosineSimilarity(HashMap<String, Double> firstFeatures, HashMap<String, Double> secondFeatures) {
//		for(String s : firstFeatures.keySet()){
//			System.out.println("VAALUE"  + firstFeatures.get(s));
//		}

		Double similarity = 0.0;
		Double sum = 0.0;        // the numerator of the cosine similarity
		Double fnorm = 0.0;        // the first part of the denominator of the cosine similarity
		Double snorm = 0.0;        // the second part of the denominator of the cosine similarity
		Set<String> fkeys = firstFeatures.keySet();
		Iterator<String> fit = fkeys.iterator();
		while (fit.hasNext()) {
			String featurename = fit.next();
			

			boolean containKey = secondFeatures.containsKey(featurename);
//			System.out.println(featurename);
//			System.out.println(containKey);				                       	 
			if (containKey) {				
//				System.out.println("FirstFeature " +firstFeatures.get(featurename));
//				System.out.println("SecondFeature " + secondFeatures.get(featurename));
				sum = sum + (firstFeatures.get(featurename) * secondFeatures.get(featurename));
			}
		}
		fnorm = calculateNorm(firstFeatures);
		

		snorm = calculateNorm(secondFeatures);
//		System.out.println("SNORM " + snorm);
//		System.out.println("SUM " + sum);
//		System.out.println();
		similarity = sum / (fnorm * snorm);
		
//		Set<Map.Entry<String, Double>> set1 = firstFeatures.entrySet(); 
//		for (Map.Entry<String, Double> me : set1) { 
//			System.out.println("Term: " + me.getKey() +" value : "+ me.getValue());
//
//		}
//		System.out.println("-----------------------------------------------------------------");
//		Set<Map.Entry<String, Double>> set = secondFeatures.entrySet(); 
//		for (Map.Entry<String, Double> me : set) { 
//			System.out.println("Term: " +me.getKey() +" value : "+ me.getValue());
//
//		}
		
		return similarity;
	}

	/**
	 * calculate the norm of one feature vector
	 *
	 * @param feature of one cluster
	 * @return
	 */
	private static Double calculateNorm(HashMap<String, Double> feature) {
		Double norm = 0.0;
		Set<String> keys = feature.keySet();
		Iterator<String> it = keys.iterator();
		while (it.hasNext()) {
			String featurename = it.next();
			norm = norm + Math.pow(feature.get(featurename), 2);
		}
		return Math.sqrt(norm);
	}

}