package com.finager.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

//use ReadData module

import java.util.Vector;
/**
 * Project Information*
 * -------------------* 
 * Name: Finager*
 * Course Code: CS 2XB3* 
 * Lab Section: 01* 
 * The {@code Ratio} class provides method to calculate the ratio K.
 */
public class Ratio {
	
	private double user;
	private ReadData rd;
	/**
	 * Ratio constructor calculate the user's expenditure for one year.
	 * @param income-The user's annual income.
	 * @param saving－The total amount of money user wants to save this year.
	 * @param rd-The ReadData constructor from ReadData.java.
	 */
	public Ratio(double income, double saving, ReadData rd ) {
		this.user = income - saving;
		this.rd = rd;
	}
	/**
	 * Get the overall predicted average expenditure value from file.
	 * @return-The overall predicted average expenditure value from file.
	 */
	public double overall(){
		Vector<Double> v = rd.Value();
		double overall = v.get(0);
		return overall;
	}
	/**
	 * Calculate the ratio K between user's annual income and overall predicted average expenditure.
	 * @return-The ratio K.
	 */
	public double k(){
		double k = this.user/overall();
		return k;
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

		// generate income matching ratio
		Ratio r = new Ratio(50000, 10000, f);
		System.out.println(r.k());
	}

}
