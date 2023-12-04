
import java.io.PrintStream;
import java.util.NoSuchElementException;


public class StringQueueImpl<T> implements StringQueue<T>{

    private Node<T> front = null;
    private Node<T> rear = null;
    
    private int size = 0;

    private class Node<T>{

		private T data;
		private Node<T> next;
		
		public Node(T data){
			this.data = data;
			this.next = null;
		}
	}
	
	@Override
	public boolean isEmpty() {
		return front == null;
	}

	@Override
	public void put(T item) {
		if( isEmpty()) {
			rear = new Node<T>(item);
			front = rear;
		}else {
			rear.next = new Node<T>(item);
			rear = rear.next;
		}
		size++;
	}

	@Override
	public T get() throws NoSuchElementException {
		if( isEmpty()) {
			throw new NoSuchElementException();
		}else {
			
			T d = front.data;
			front = front.next;
			size = size - 1;
			
			return d;
		}
		
	}

	@Override
	public T peek() throws NoSuchElementException {
		if( isEmpty()) {
			throw new NoSuchElementException();
		}else {
			return front.data;
		}
	}

	@Override
	public void printQueue(PrintStream stream) {
		if(isEmpty()){
			System.out.print("Empty list!");
		}else{
			
			Node<T> node = front;
			
			while(node != null) {
				stream.println(node.data);
				stream.flush();
				node = node.next;
			}
		}
	}

	@Override
	public int size() {
		if (isEmpty()) {
			return 0;
		}else {
			return size;
		}
	}
    

}
