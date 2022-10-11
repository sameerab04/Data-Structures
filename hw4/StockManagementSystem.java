package edu.pitt.cs.as4;

import java.util.Scanner;
import java.util.NoSuchElementException;
import java.io.IOException;

/**
 * A class that represents a stock management system
 *
 * @author Jon Rutkauskas
 * @author Brian Nixon
 * @version 1.0
 */
public class StockManagementSystem {
	public ListInterface<Category> categories;

	// constructor. instantiates categories with an ArrayList
	public StockManagementSystem() {
		// TODO: Complete this method
		categories = new ArrayList<Category>();
	}

	// creates a product and adds it to an existing category
	public void createAndAddProduct(String categoryName, String productName, int quantity, double price) {
		// TODO: Complete this method
		Product newProduct = new Product(productName, quantity, price);
		for (int i = 0; i < categories.getSize(); i++) {
			if (categories.get(i).getCategoryName().equals(categoryName)) {
				categories.get(i).addProduct(newProduct);
			}
		}
	}

	// returns the number of items (Sum of all quantities) in a category
	public int getNumberOfStockedItemsInCategory(String categoryName) {
		// TODO: Complete this method
		int items = 0;
		for (int i = 0; i < categories.getSize(); i++) {
			if (categories.get(i).getCategoryName().equals(categoryName)) {
				items += categories.get(i).getTotalQuantityOfStock();
			}
		}
		return items;
	}

	// returns the number of stocked items (sum of all quantities) across multiple
	// categories (given as a list)
	public int getNumberOfStockedItemsInCategories(ListInterface<String> categoryNames) {
		// TODO: Complete this method
		int stockedItems = 0;
		for (int i = 0; i < categories.getSize(); i++) {
			for (int j = 0; j < categoryNames.getSize(); j++) {
				if (categories.get(i).getCategoryName().equals(categoryNames.get(j))) {
					stockedItems += categories.get(i).getTotalQuantityOfStock();
				}
			}
		}
		return stockedItems;
	}

	// returns the stocked quantity of a specific item
	public int getQuantityOfItemByName(String productName) {
		// TODO: Complete this method
		int items = 0;
		try {
			for (int i = 0; i < categories.getSize(); i++) {
				items += categories.get(i).findProductByName(productName).getQuantityInStock();
			}
		} catch (NullPointerException e) {
			System.out.println("product not found, please try again");
		}
		return items;
	}

	// sets the stocked quantity of a specific item
	public void setQuantityOfItemByName(String productName, int newQuantity) {
		// TODO: Complete this method
		try{


		for (int i = 0; i < categories.getSize(); i++) {
			categories.get(i).findProductByName(productName).setQuantityInStock(newQuantity);
			}
		}catch(NullPointerException e){
			System.out.println("product does not exist");
		}
	}

	// removes a product from the system
	public Product removeProductByName(String productName) {
		// TODO: Complete this method
		Product removedProduct = null;
		for (int i = 0; i < categories.getSize(); i++) {

			removedProduct = categories.get(i).removeProductByName(productName);
		}
		return removedProduct;
	}

	// removes a category from the system
	public Category removeCategoryByName(String categoryName) {
		// TODO: Complete this method
		Category result = null;
		try {
			for (int i = 0; i < categories.getSize(); i++) {
				if (categories.get(i).getCategoryName().equals(categoryName)) {
					result = categories.remove(i);
				}
			}
		} catch (NullPointerException e) {
			System.out.println("Category does not exist");
		}

		return result;
	}

	// creates and adds new category
	public void createCategory(String categoryName) {
		// TODO: Complete this method
		Category newCatogory = new Category(categoryName);
		categories.add(newCatogory);
	}

	// calculates and returns the total value of all items in a given category
	public double totalValueOfItemsInCategory(String categoryName) {
		// TODO: Complete this method
		double value = 0;
		for (int i = 0; i < categories.getSize(); i++) {
			if (categories.get(i).getCategoryName().equals(categoryName)) {
				value += categories.get(i).getTotalValue();
			}
		}
		return value;
	}

	// a scanner to get input from the user
	private static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		// interactivity

		StockManagementSystem s = new StockManagementSystem();
		preStock(s);
		int selection = -1;

		while (selection != 0) {
			System.out.println("\n=================================");
			System.out.println("Stock Management System Main Menu");
			System.out.println("1. Print stocked items");
			System.out.println("2. Create category");
			System.out.println("3. Create Product");
			System.out.println("4. Delete Product");
			System.out.println("5. Delete Category");
			System.out.println("6. Manage Quantity of Item");
			System.out.println("7. Get amount of items stocked in multiple categories");
			System.out.println("8. Get Total Value of all products in a category");
			System.out.println("0. Quit");
			System.out.print("Selection: ");

			try {
				selection = input.nextInt();
			} catch (NoSuchElementException e) {
				selection = -1;
			} catch (IllegalStateException e) {
				selection = -1;
			}
			input.nextLine();

			switch (selection) {
			case 1:
				print(s);
				break;
			case 2:
				createCat(s);
				break;
			case 3:
				createProd(s);
				break;
			case 4:
				deleteProd(s);
				break;
			case 5:
				deleteCat(s);
				break;
			case 6:
				setQty(s);
				break;
			case 7:
				getQtyAcrossCats(s);
				break;
			case 8:
				getPriceCategory(s);
				break;
			case 0:
				break;
			default:
				// Invalid, just ignore and let loop again
				break;
			}
		}
	}

// interactive functions implemented for students
	private static void createCat(StockManagementSystem s) {
		System.out.print("New Category Name: ");
		String name = input.nextLine();
		s.createCategory(name);

	}

	private static void createProd(StockManagementSystem s) {
		System.out.print("Category to add Product into: ");
		String cat = input.nextLine();
		System.out.print("New Product Name: ");
		String name = input.nextLine();
		System.out.print("Quantity of this item: ");
		int qty = input.nextInt();
		input.nextLine();
		System.out.print("Price of this item: ");
		double price = input.nextDouble();
		input.nextLine();
		s.createAndAddProduct(cat, name, qty, price);

	}

	private static void deleteProd(StockManagementSystem s) {

		System.out.print("Name of Product to Delete: ");
		String name = input.nextLine();
		Product p = s.removeProductByName(name);
		System.out.println("Removed: " + p);

	}

	private static void deleteCat(StockManagementSystem s) {
		System.out.print("Name of Category to Delete: ");
		String name = input.nextLine();

		Category c = s.removeCategoryByName(name);
		try {
		System.out.println("Removed " + name + " category with " + c.getTotalQuantityOfStock() + " items");
		}catch(NullPointerException e) {
			System.out.println("Category does not exist, please try again");
		}
	}

	private static void setQty(StockManagementSystem s) {
		System.out.print("Product Name: ");
		String name = input.nextLine();
		int i = s.getQuantityOfItemByName(name);
		System.out.println("Current quantity of " + name + " is: " + i);
		System.out.print("Enter new quantity of this item (leave blank to leave unchanged): ");
		String str = input.nextLine();
		if (!str.equals("")) {
			int qty = Integer.parseInt(str);
			s.setQuantityOfItemByName(name, qty);
		}

	}

	private static void getQtyAcrossCats(StockManagementSystem s) {
		ListInterface<String> list = new ArrayList<String>();
		String str = "initialString";
		System.out.println("Enter names of categories, pressing ENTER after each category; Type 'done' to finish: ");
		while (!str.equals("done")) {
			str = input.nextLine();
			if (!str.equals("done"))
				list.add(str);
		}
		int qty = s.getNumberOfStockedItemsInCategories(list);
		System.out.println("Total number of items stocked in those categories is: " + qty);
	}

	private static void print(StockManagementSystem s) {
		System.out.println("=========== All Items ===========");
		for (int i = 0; i < s.categories.getSize(); i++) {
			Category c = s.categories.get(i);
			System.out.println(c.getCategoryName() + " (" + c.getTotalQuantityOfStock() + " items):");
			ListInterface<Product> list = c.getAllProducts();
			for (int j = 0; j < list.getSize(); j++) {
				Product p = list.get(j);
				System.out.println("\t" + (j + 1) + ". " + p.getItemName() + " (" + p.getQuantityInStock() + " @ $"
						+ p.getPrice() + " each) ");
			}
		}
	}

	private static void getPriceCategory(StockManagementSystem s) {
		System.out.print("Name of Category: ");
		String name = input.nextLine();
		double d = s.totalValueOfItemsInCategory(name);
		System.out.println("Value of all items in " + name + ": $" + d);
	}

	private static void preStock(StockManagementSystem s) {
		System.out.print("Do you want to pre-stock the system with default values? (y/n): ");
		String entered = input.nextLine();
		if (entered.equals("y")) {
			Category food = new Category("food");
			Product chips = new Product("chips", 13, 1.49);
			Product apple = new Product("apple", 38, 0.79);
			Product soup = new Product("soup", 18, 1.99);
			Product bread = new Product("bread", 20, 1.87);
			food.addProduct(chips);
			food.addProduct(apple);
			food.addProduct(soup);
			food.addProduct(bread);
			s.categories.add(food);

			Category bev = new Category("beverage");
			Product beer = new Product("beer", 36, 5.99);
			Product wine = new Product("wine", 14, 9.99);
			Product vodka = new Product("vodka", 33, 16.50);
			Product oj = new Product("orange juice", 19, 2.49);
			Product coffee = new Product("coffee", 8, 3);

			bev.addProduct(beer);
			bev.addProduct(wine);
			bev.addProduct(vodka);
			bev.addProduct(oj);
			bev.addProduct(coffee);
			s.categories.add(bev);

			Category elec = new Category("electronics");
			elec.addProduct(new Product("mini-dp to hdmi", 2, 13.99));
			elec.addProduct(new Product("cd player", 8, 19.99));
			elec.addProduct(new Product("cell phone", 5, 99));
			elec.addProduct(new Product("tv remote", 6, 13));
			elec.addProduct(new Product("lamp", 19, 10));
			s.categories.add(elec);
		}
	}

}
