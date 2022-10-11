package edu.pitt.cs.as1;

public class VendingItem implements VendingItemInterface {

	private final double PRICE;
	private final String NAME;

	VendingItem(double price, String name){
		PRICE = price;
		NAME = name;

	}

	public double getPrice() {
		return PRICE;
	}


	public String getName() {
		return NAME;
	}

	public String toString(){
		String name = NAME;
		return name;
	}


}
