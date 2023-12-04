
import java.io.PrintStream;
import java.util.NoSuchElementException;

class StringStackImpl<T> implements StringStack<T>{
	
	private Node<T> top;
	private int size=0;
	
	private class Node<T>{

		private T data;
		private Node<T> next;
		
		public Node(T data){
			this.data=data;
			this.next=null;
		}
	}
	

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void push(T item) {

		Node<T> n = new Node<>(item);
		n.next = top;
		top = n;

		size++;
	}

	@Override
	public T pop() throws NoSuchElementException{
		
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		
		T x = top.data;
		top = top.next;
		size = size - 1;
		
		return x;
	}

	@Override
	public T peek() throws NoSuchElementException {
		
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		
		return top.data;
		
	}

	@Override
	public void printStack(PrintStream stream) {
		if(isEmpty()){
			System.out.print("Empty list!");
		}else{
			
			Node<T> node = top;
			
			while(node != null) {
				stream.println(node.data);
				stream.flush();
				node = node.next;
			}
		}
	}

	@Override
	public int size(){
		if (isEmpty()) {
			return 0;
		}else {
			return size;
		}
	}

}
