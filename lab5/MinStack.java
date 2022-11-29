package cs445.lab5;

public class MinStack<T extends Comparable<T>> extends LinkedStack<T> {
	// The private, second stack
	private StackInterface<T> minimums;

	public MinStack() {
		// TODO: instantiate the stack of minimums
		minimums = new Stack<T>();

	}

	@Override
	public void push(T newEntry) {
		// TODO: Use the stack of minimums to keep track of the lowest values on the stack.  Remember that peeking or popping from an empty stack results in an EmptyStackException


		// TODO: Use 'super' to push to the main stack


	}

	@Override
	public T pop() {
		// TODO: Get the top-most entry from the main stack.


		// TODO: Compare the entry to the entry on top of the stack of minimums (if it isn't empty) to determine if you need to
		// pop from there as well


		// TODO: Return the popped entry

	}

	// getMin returns the lowest value on the stack in O(1) time
	public T getMin() {
		// TODO: Complete this method

	}
}
