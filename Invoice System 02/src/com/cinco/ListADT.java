package com.cinco;

import java.util.Iterator;

public class ListADT<T> implements Iterable<T> {

	private LHADT<T> firstNode;
	private int size = 0;
	// private Stack<T> s = null;
	private int typeCompare = 0;

	public ListADT(int size) {
		this.typeCompare = size;
	}

	public void clear() {
		firstNode = null;
	}

	public void addToStart(T t) {
		if (t == null)
			return;


		insert(0, t);
		
		size++;
		
	}

	public void addToEnd(T t) {
		if (t == null)
			return;

		if (firstNode == null)
			addToStart(t);

		else {
			LHADT<T> tmp = firstNode;

			while (tmp.getNext() != null)
				tmp = tmp.getNext();

			LHADT<T> a = new LHADT<T>(t);
			tmp.setNext(a);

			size++;
		}

	}

	public void remove(int position) {
		if (position > size || position < 0)
			return;

		if (position == 0)
			firstNode = firstNode.getNext();

		else {
			LHADT<T> temp = firstNode;

			for (int i = 0; i < position - 1; i++)
				temp = temp.getNext();

			if (temp.getNext() == null)
				temp = null;

			else
				temp.setNext(temp.getNext().getNext());

		}

		size--;

	}

	@SuppressWarnings("unused")
	private LHADT<T> getNode(int position) {

		LHADT<T> temp = firstNode;

		for (int i = 0; i < position; i++)
			temp = temp.getNext();

		return temp;
	}

	public T get(int position) {
		if (position < 0 || position > size)
			return null;

		LHADT<T> temp = firstNode;

		for (int i = 0; i < position; i++) {
			if (temp.getNext() != null)
				temp = temp.getNext();

		}

		return temp.getObject();
	}

	public void print() {
		StringBuilder sb = new StringBuilder();

		if (firstNode == null)
			System.out.println("The list is empty");

		else {
			sb.append(firstNode.getObject().toString());
			// sb.append(firstNode.getTruck().getLicensePlate() + "->");

			while (firstNode.getNext() != null) {
				firstNode = firstNode.getNext();
				sb.append("\n" + firstNode.getObject().toString());
				// sb.append(""+firstNode.getTruck().getLicensePlate() + "->");
			}
			System.out.println(sb.toString());
		}

	}

	public int size() {
		return this.size;
	}

	public void add(T t) {

		// System.out.println(t.getClass() + " --- " + People.class);
		// System.out.println();

		if (t instanceof Invoice) {
			//System.out.println(t.getClass());
			addInvoice((Invoice) t);

		}

		else if (t instanceof Products) {
			addToEnd(t);
			//System.out.println(t.getClass());
		}

		else if (t instanceof Consumer) {
			addToEnd(t);
			//System.out.println(t.getClass());

		}

		else if (t instanceof People) {
			addToEnd(t);
			//System.out.println(t.getClass());
		}

		else
			addToEnd(t);

		// TODO Auto-generated method stub

	}

	private void addInvoice(Invoice t) {

		if (size != 0){
			if (typeCompare == 0)
				sortAlpha(t);
		}
		else
			addToStart((T) t);
		
		size++;

	}

	private void sortAlpha(Invoice t) {

		Boolean isFalse = null, wasTrue = null;

		for (int i = 0;  i< size; i++) {

			Invoice tempI = (Invoice) get(i);

			String tempS0 = tempI.getConsumerName(), tempS1 = t.getConsumerName();
			
			int tmpN = tempS0.compareTo(tempS1);

			if (tmpN < 0) {
				// a is before
				wasTrue = true;

			} else if (tmpN > 0) {
				if (wasTrue == null)
					addToStart((T) t);

				isFalse = true;
			} else if(tmpN == 0){
				wasTrue = true;
				isFalse = true;
			}
			
			//System.out.println(i);

			//if (isFalse == true && wasTrue == true && isFalse != null && wasTrue != null) {
				insert(i, (T) t);
			//}
		}

	}

	private void insert(int p1, T t) {

		
		if(size != 0 ){
		LHADT temp = getNode(p1);
		
		System.out.println(getNode(p1));

		LHADT<T> newFirst = new LHADT<T>((T) t);

		getNode(p1).setNext(newFirst);
		newFirst.setNext(temp);
		}
		
		else{
			
			LHADT<T> newFirst = new LHADT<T>(t);

			//newFirst.setNext(firstNode);
			firstNode = newFirst;
			System.out.println("HELLO ");
		}
		

	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
