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
 * The {@code SmallCatg} class provides methods to generate small category expenditure 
 * based on big category.
 */
public class SmallCatg {

	private ReadData rd;
	private BreadthFirstDirectedPaths bfs;
	
	/**
	 * SmallCatg constructor get ReadData. 
	 * @param rd-The ReadData constructor from ReadData.java.
	 */
	public SmallCatg(ReadData rd){
		this.rd = rd;
	}
	/**
	 * Get all small category of given big category.
	 * @param v-The given position of big category.
	 * @return-All small category of given big category.
	 */
	public Vector<Double> smaller(int v){
		//create a variable to store the result
		Vector<Double> result = new Vector<Double>();
		
		//get the graph of the data
		Digraph g = rd.Graph();
		//get the value of the data
		Vector<Double> vo = rd.Value();
		
		//initialize dfs
		bfs = new BreadthFirstDirectedPaths(g, v);
		
		//for all adjacent nodes of big category
		Iterable<Integer> small = g.adj(v);
		for (Integer item:small){
			if (bfs.hasPathTo(item)){
				//store the value of small category
				result.add(vo.get(item));
			}	
		}
		return result;
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
		
		SmallCatg sc = new SmallCatg(f);
		
		System.out.println(f.indexes());
		//print the test result
		System.out.println(sc.smaller(97));
		
	}
	

}
