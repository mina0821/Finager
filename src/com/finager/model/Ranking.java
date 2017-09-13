package com.finager.model;
/**
 * Project Information*
 * -------------------* 
 * Name: Finager*
 * Course Code: CS 2XB3* 
 * Lab Section: 01* 
 * The {@code Ranking} class provides methods to sort the final consumption.
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Vector;

public class Ranking {
	
	private Vector<String> data;
	private ProVal[] ranked;
	/**
	 * Sort the final consumption by quick sort
	 * @param data-The information of predicted expenditure of all category from output Vector of string.
	 * @param catg-The category name
	 */
	public Ranking (Vector<String> data, String catg){
		this.data = data;
		this.ranked = new ProVal[14];
		
		int j = 0;
		//search only for final consumption
		for (int i = 0; i<data.size();i++){
			//split the line
			String[] input = data.get(i).split(",");
			if (catg.equals(input[5])){
				Double val = Double.parseDouble(input[1]);
				ProVal temp = new ProVal (input[2],val);
				ranked[j] = temp;
				j++;
			}
			
		}
		
		//sort by value
		Quick.sort(ranked);
	}
	/**
	 * Use BinarySearchST search the ranked value of given province.
	 * @param prov-the province name
	 * @return-the ranked value of given province.
	 */
	public Double find (String prov){
		BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>();
		
		//put value into the tree
        for (int i = 0; i<14; i++) {
            st.put(ranked[i].getProv(),i);
        }
        
        //search for the index of given provinces
        Integer inx = st.get(prov);
        //return the value
        return ranked[inx].getVal();
	}
	
	/**
	 * Get the final consumption value after quick sort
	 * @return-the final consumption value after quick sort
	 */
	public ProVal[] getRank(){
		return this.ranked;
	}
	

	
	
	/**
	 * Test 
	 * @param args-main args
	 * @throws IOException if stream to aFile cannot be written to or closed.
	 */
	//test
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
		
		//only contains final consumption
		/*Ranking r = new Ranking(tr2);
		ProVal[] tr3 = r.getRank();
		
		System.out.println(Arrays.toString(tr3));
		System.out.println(r.find("Canada"));*/
	
	}

}
