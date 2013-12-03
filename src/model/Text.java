package model;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * This class represents the texts. All relevant data stored here
 * @author Lars Petter
 *
 */



public class Text {
	private String text;
	private Map<String, Integer> tfValues;
	private LinkedHashSet<String> tokens;
	
	
	public LinkedHashSet<String> getTokens() {
		return tokens;
	}


	public void setTokens(LinkedHashSet<String> tokens) {
		this.tokens = tokens;
	}


	public Map<String, Integer> getTfValues() {
		return tfValues;
	}


	public void setTfValues(Map<String, Integer> tfValues) {
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
}
