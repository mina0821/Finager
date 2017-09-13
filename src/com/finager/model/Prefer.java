package com.finager.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Vector;

//use ReadData module
/**
 * Project Information*
 * -------------------* 
 * Name: Finager*
 * Course Code: CS 2XB3* 
 * Lab Section: 01* 
 * The {@code Prefer} class provides methods to generate expenditure by user preferences.
 */
public class Prefer {
	private Double[] pref;
	private ReadData rd;
	/**
	 * Prefer constructor use to calculate the big category expenditure adjust by user preferences.
	 * @param user-The user's preference for each big category.
	 * @param read-The ReadData constructor from ReadData.java.
	 */
	public Prefer(Double[] user, ReadData read){
		pref = user;
		rd = read;
		Replace();
	}
	/**
	 * Get the adjust expenditure of given category position.
	 * @param catg-The given category position.
	 * @return-The adjust expenditure of given category position.
	 */
	public Double Value(int catg){
		return pref[catg];
	}
	/**
	 * Get expenditure adjust by user preferences.
	 * @return-The expenditure adjust by user preferences.
	 */
	public Double[] vals(){
		return this.pref;
	}
	/**
	 * Calculate the big category expenditure adjust by user preferences.
	 * And adjust ratio K.
	 */
	private void Replace(){
		
		for(int i = 0; i<pref.length;i++){
			//find the corresponding big category
			int catg = rd.Index2Catg(i);
			//find the corresponding value
			Double val = rd.Value().get(catg);
			pref [i] = pref [i] * val; 
		}
		//calculate the new overall expenditure
		double newData = 0;
		for (double entry : pref) {
		    newData += entry;
		}
		double oldData = rd.Value().get(0);
		
		//adjust the ratio
		double kRatio = oldData / newData;
		
		for(int i = 0; i<pref.length; i++){
			pref[i] = pref[i]*kRatio;
		}
	}
	/**
     * Test 
     * @param args-main method
     * @throws IOException if stream to aFile cannot be written to or closed.
     */
	public static void main(String[] args) throws IOException {
		// readData fake data
		Vector<String> trial = new Vector<String>();
		String fName = "WebContent/WEB-INF/expenditure.csv";
		File file = new File(fName);
		// opens and reads requested file
		FileReader fileReader = new FileReader(file);
		BufferedReader br = new BufferedReader(fileReader);
		String line;
		while ((line = br.readLine()) != null) {
			trial.add(line);
		}
		br.close();
		
		//predict the data for future first
		Entry a = new Entry(trial,2016);
		Vector<String> tr2 = a.getoutput();
		

		// partition the data set, get the predicted average expenditure of selected provinces
		Partition pat = new Partition(tr2, "Ontario");
		Vector<String> d3 = pat.partitionoutput();

		// read data from selected provinces
		ReadData f = new ReadData(d3);
		
		Double[] pref = new Double[13];
		for (int i = 0; i < pref.length; i++) {
			pref[i] = 0.5;
		}
		Prefer prefs = new Prefer(pref, f);
		for (int i = 0; i < prefs.vals().length; i++) {
			System.out.println(prefs.vals()[i]);
		}
		
	}

}

