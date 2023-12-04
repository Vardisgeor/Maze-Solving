
import java.io.PrintStream;
import java.util.NoSuchElementException;

public class StringQueueWithOnePointer<T> implements StringQueue<T> {

	private Node<T> rear = null;
    
    private int size = 0;

    private class Node<T>{

		private T data;
		private Node<T> next;
		
		public Node(T data){
			this.data = data;
		}
	}

	@Override
	public boolean isEmpty() {
		return size==0;
	}

	@Override
	public void put(T item) {

		Node<T> newNode = new Node<T>(item);

		if( isEmpty()) {
			rear = newNode;
		}else {

			if(rear.next == null){
				rear.next = newNode; 
				newNode.next = rear;
			}else{
				newNode.next = rear.next;
				rear.next = newNode;
			}
		}

		rear = newNode;
		size++;
	}

	@Override
	public T get() throws NoSuchElementException {
		if( isEmpty()) {
			throw new NoSuchElementException();
		}else{
			
			T x;
			if(size > 1){
				rear.next = rear.next.next;
				x = rear.next.data;
			}else{
				x=rear.data;
			}

			if(rear.next == null){
				rear = null;
			}
			size = size-1;
			return x;
		}
		
	}

	@Override
	public T peek() throws NoSuchElementException {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		if(rear.next == null){
			return rear.data;
		}else{
			return rear.next.data;
		}
	}

	@Override
	public void printQueue(PrintStream stream) {
		if(isEmpty()){
			System.out.print("Empty Queue!");
		}else{
			
			Node<T> node = rear.next;
			
			while(node != rear  ) {
				stream.println(node.data);
				stream.flush();

				node = node.next;
			}
			stream.println(node.data);
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
