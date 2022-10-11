package edu.pitt.cs.as4;

/**
 * A class that represents a category of products in a stock management system
 *
 * @author Jon Rutkauskas
 * @author Brian Nixon
 * @version 1.0
 */
public class Category {
	private ListInterface<Product> products;
	private String categoryName;

	public Category(String categoryName) {
		// TODO: Complete this method
		this.categoryName = categoryName;
		products = new ArrayList<Product>();
	}

	// returns the name of this category
	public String getCategoryName() {
		// TODO: Complete this method
		return categoryName;
	}

	// adds a single product to this category
	public void addProduct(Product prod) {
		// TODO: Complete this method
		products.add(prod);
	}

	// returns a product entry given a string of the product's name
	public Product findProductByName(String productName) {
		// TODO: Complete this method
		Product product = null;

		for (int i = 0; i < products.getSize(); i++) {
			if (products.get(i).getItemName().equals(productName)) {
				product = products.get(i);
			}
		}

		return product;
	}

	// removes a product entry from this category and returns it
	public Product removeProductByName(String productName) {
		// TODO: Complete this method
		Product removedProduct = null;
		try {
			for (int i = 0; i < products.getSize(); i++) {
				if (products.get(i).getItemName().equals(productName)) {
					removedProduct = products.get(i);
					if(products.get(i)==null){
						System.out.println("Product does not exist");
					}
					products.remove(i);
				}
			}

			// return removedProduct; // or throw exception?
		} catch (NullPointerException e) {
			System.out.println("Product does not exist");
		}
		return removedProduct;
	}

	// returns the number of products in this category
	public int getSize() {
		// TODO: Complete this method
		return products.getSize();
	}

	// returns the total number of items stocked in this category (sum of all
	// quantities)
	public int getTotalQuantityOfStock() {
		// TODO: Complete this method
		int totalQuantity = 0;

		for (int i = 0; i < products.getSize(); i++) {
			totalQuantity += products.get(i).getQuantityInStock();
		}

		return totalQuantity;
	}

	// returns the value of all products in the system: Sum(Price * Quantity) for
	// each Product in this category
	public double getTotalValue() {
		// TODO: Complete this method
		int value = 0;
		for (int i = 0; i < products.getSize(); i++) {
			double price = products.get(i).getPrice();
			int quantity = products.get(i).getQuantityInStock();
			double sum = price * quantity;
			value += sum;
		}
		return value;
	}

	// returns a new List containing all products in this category. Do not directly
	// return the private backing List
	public ListInterface<Product> getAllProducts() {
		// TODO: Complete this method
		ListInterface<Product> result = new ArrayList<Product>();
		for (int i = 0; i < products.getSize(); i++) {
			result.add(products.get(i));
		}
		return result;
	}

}
