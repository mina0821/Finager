package com.finager.model;
/**
 * Project Information*
 * -------------------* 
 * Name: Finager*
 * Course Code: CS 2XB3* 
 * Lab Section: 01* 
 * The {@code ProVal} class provides methods to construct a comparable ProVal.
 */

public class ProVal implements Comparable<ProVal>{
	private String prov;
	private Double val;
	/**
	 * ProVal constructor
	 * @param prov-the province name
	 * @param val-the value
	 */
	public ProVal (String prov, Double val){
		this.prov = prov;
		this.val = val;
	}
	/**
	 * Get the value
	 * @return-the value
	 */
	public Double getVal(){
		return this.val;
	}
	/**
	 * Get the province name
	 * @return-the province name
	 */
	public String getProv(){
		return this.prov;
	}
    /**
     * compare this ProVal value with other
     */
	@Override
	public int compareTo(ProVal other) {
		return this.val.compareTo(other.getVal());
	}
    /**
     * Make this ProVal toString 
     */
	public String toString() {
		return "ProVal [prov=" + prov + ", val=" + val + "]";
	}
	
	public static void main(String[] args){
		ProVal p = new ProVal("Ontario",2017.0);
		System.out.println(p.getProv().equals("Ontario"));
		System.out.println(p.getVal()==2017.0);
		System.out.println(p.toString());
	}


}
