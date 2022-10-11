package cs445.lab5;

public class Lab5Tester {
	public static void main(String[] args) {
		if(args.length != 1) {
			System.out.println("You must enter exactly one command line argument.");
			System.out.println("Valid options are either 'reverse' or 'minstack'.");
			return;
		}
		
		// Test reversing a queue
		if(args[0].equalsIgnoreCase("reverse")) {
			// Fill queue with numbers 1-10 in order
			QueueInterface<Integer> queue = new LinkedQueue<Integer>();
			System.out.print("Queue contains: ");
			for(int i = 1; i <= 10; i++) {
				queue.enqueue(new Integer(i));
				System.out.print(i + "  ");
			}
			System.out.println();
			
			// Reverse the queue
			QueueReverser.reverseQueue(queue);
			
			// Print contents of queue
			System.out.print("After reversal, queue contains: ");
			while(!queue.isEmpty()) {
				System.out.print(queue.dequeue() + "  ");
			}
			System.out.println();
		}
		
		// Test the minstack
		else if(args[0].equalsIgnoreCase("minstack")) {
			MinStack<Integer> st = new MinStack<Integer>();
			int[] order = {8,5,2,6,1,3,1,5}; // Order of numbers to test
			// Test pushes
			System.out.println("Testing pushing:");
			for(int i = 0; i < order.length; i++) {
				st.push(order[i]);
				System.out.println("Pushed: " + order[i] + "; Now min is: " + st.getMin());
			}
			
			// Test popping
			System.out.println("\nTesting popping");
			while(!st.isEmpty()) {
				Integer elem = st.pop();
				System.out.println("Popped: " + elem + "; Now min is: " + (st.isEmpty() ? "EMPTY STACK" :st.getMin()));
			}
		}
		
		else {
			System.out.println("Unrecognized command line argument.");
		}
	}
}