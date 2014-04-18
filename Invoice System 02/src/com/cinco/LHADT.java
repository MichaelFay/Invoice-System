package com.cinco;


public class LHADT<T> {
	
	 private LHADT<T> next;
	 private T item;

	    public LHADT(LHADT<T> item) {
	        this.item = (T) item;
	        this.next = null;
	    }
	    
	    @SuppressWarnings("unchecked")
		public LHADT(T t) {
	        this.item =  t;
	        this.next = null;
	    }
	    
		public T getObject() {
	        return (T) item;
	    }

	    public LHADT<T> getNext() {
	        return next;
	    }

	    public void setNext(LHADT<T> next) {
	        this.next = next;
	    }
	    
	

}
