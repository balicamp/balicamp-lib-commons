package id.co.sigma.common.server.lov;

import java.util.ArrayList;




/**
 * 1 kelompok LOV Provider
 **/
 public abstract class CustomLOVProviderGroup {
	

	
	/**
	 * LOV provider yang tersedia dalam 1 group
	 * 
	 **/
	public abstract ArrayList<BaseLOVProvider> getAllAvailableProvider (); 

}
