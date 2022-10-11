package edu.pitt.cs.as1;

public class Shelf<T> implements ShelfInterface<T> {

	private final int ROWS;
	private final int COLUMNS;
	private final int QUANTITY;
	private T[][][] shelf;

	@SuppressWarnings("unchecked")
	public Shelf(int rows, int columns, int quantity) {
		ROWS = rows;
		COLUMNS = columns;
		QUANTITY = quantity;
		shelf = (T[][][]) new Object[ROWS][COLUMNS][QUANTITY];
	}

	public T get(int row, int column, int position) {
		return shelf[row][column][position];
	}

	public String getName(int row, int column, int position) {

		Object object = shelf[row][column][position];
		return object.toString();
	}

	@SuppressWarnings("unchecked")
	public void add(int row, int column, int position, Object o) {
		if (o != null) {
			shelf[row][column][position] = (T) o;
		}
	}

	public void remove(int row, int column) {
		shelf[row][column][0] = null;
		for (int i = 0; i < QUANTITY - 1; i++) {
			shelf[row][column][i] = shelf[row][column][i + 1];
		}
		shelf[row][column][QUANTITY - 1] = null;
	}

	public boolean contains(T entry) {

		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				if (entry == null) {
					return false;
				}
				if (shelf[i][j][0] == entry) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean isEmpty(int row, int column, int position) {
		boolean isEmpty = false;

		if (shelf[row][column][position] == null) {
			isEmpty = true;
		}
		return isEmpty;
	}

	public T[][][] toArray() {
		@SuppressWarnings("unchecked")
		T[][][] result = (T[][][]) new Object[ROWS][COLUMNS][QUANTITY];
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				for (int k = 0; k < QUANTITY; k++) {
					result[i][j][k] = shelf[i][j][k];
				}
			}
		}
		return result;
	}

}
