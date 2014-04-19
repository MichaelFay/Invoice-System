package com.cinco;

@SuppressWarnings("unchecked")
public class ArrayADT {

	private Invoice[] array;
	private int typeCompare = 1;

	public ArrayADT(int comp) {
		this.typeCompare = comp;
		array = new Invoice[0];

	}

	public void clear() {
		Invoice temp[] = new Invoice[0];
		array = temp;
	}

	protected void addToStart(Invoice t) {
		addIndex(0, t);
	}

	protected void addToEnd(Invoice t) {
		addIndex(array.length, t);

	}

	private void addIndex(int index, Invoice insertion) {
		this.array = copyAllWithInsert(array, index, insertion);

	}

	protected void add(Invoice t) {
		// addToStart(t);
		addInvoice(t);
	}

	protected Invoice get(int index) {

		if (this.array.length <= index)
			return null;

		return this.array[index];

	}

	protected Invoice[] copyAll(Invoice[] source) {
		return source.clone();
	}

	protected Invoice[] copyAllWithInsert(Invoice[] source, int index,
			Invoice insertion) {

		if (insertion == null)
			System.out.println("wtf");

		Invoice temp[] = new Invoice[source.length + 1];

		for (int i = 0; i < index; i++) {
			temp[i] = source[i];
		}

		temp[index] = insertion;

		for (int i = index + 1; i <= source.length; i++) {
			if (temp.length < (index) || source.length < (index)
					|| source == null || temp == null)
				break;

			temp[i] = source[i - 1];
		}

		return temp;
	}

	protected void remove(Invoice[] source, int index, Invoice deletion) {

		Invoice[] temp = new Invoice[array.length - 1];

		for (int i = 0; i < index; i++)
			temp[i] = source[i];

		for (int i = index++; i < source.length + 1; i++)
			temp[i] = source[i];

	}

	public void print() {
		StringBuilder sb = new StringBuilder();

		if (array.length == 0 || array == null)
			System.out.println("The list is empty");

		else {
			sb.append(get(0) + "->");

			for (int i = 1; i < array.length; i++)
				sb.append("" + get(i) + "->");

			System.out.println(sb.toString());
		}

	}

	public void addInvoice(Invoice t) {

		if (array.length != 0) {
			if (typeCompare == 0)
				sortAlpha(t);
			else if (typeCompare == 1)
				sortHighToLow(t);
			else if (typeCompare == 2)
				typeAlpha(t);
		} 
		else if(array.length == 0)
			addToStart(t);

	}

	private void sortAlpha(Invoice t) {

		if (array.length < 1) {
			addToEnd(t);
			return;
		}

		Boolean isFalse = null, wasTrue = null;

		for (int i = 0; get(i) != null && i < array.length; i++) {

			String tempS0 = ((Invoice) get(i)).getConsumerName(), tempS1 = t
					.getConsumerName();

			int tmpN = tempS0.compareTo(tempS1);

			if (tmpN < 0) {
				// a is before
				wasTrue = true;

			} else if (tmpN > 0) {
				if (wasTrue == null) {
					addToStart(t);
					return;
				}

				isFalse = true;
			} else if (tmpN == 0) {
				wasTrue = true;
				isFalse = true;
			}

			if (isFalse != null && wasTrue != null)
				if (isFalse == true && wasTrue == true) {
					addIndex(i, t);
					System.out.println("Hello" + array.length);
					return;

				}
		}
		addToEnd(t);

	}

	private void sortHighToLow(Invoice t) {

		if (array.length < 1) {
			addToEnd(t);
			return;
		}

		Boolean isFalse = null, wasTrue = null;

		for (int i = 0; get(i) != null && i < array.length; i++) {

			Double tempD0 = ((Invoice) get(i)).getGrandTotal(), tempD1 = t
					.getGrandTotal();

			double tmpN = tempD0 - tempD1;

			// System.out.println(tmpN);

			if (tmpN > 0) {
				// a is before
				wasTrue = true;

			} else if (tmpN < 0) {
				if (wasTrue == null) {
					addToStart(t);
					return;
				}

				isFalse = true;
			} else if (tmpN == 0) {
				wasTrue = true;
				isFalse = true;
			}

			if (isFalse != null && wasTrue != null)
				if (isFalse == true && wasTrue == true) {
					addIndex(i, t);
					// System.out.println("Hello" + array.length);
					return;

				}
		}
		addToEnd(t);

	}

	private void typeAlpha(Invoice t) {
		

		int cgs = FCGS();

		Boolean isFalse = null, wasTrue = null;

		if (t.getConsumer().getGov() == false) {
			for (int i = 0; i <= cgs; i++) {

				String tempS0 = ((Invoice) get(i)).getConsumerName(), tempS1 = t
						.getConsumerName();

				int tmpN = tempS0.compareTo(tempS1);

				if (tmpN < 0) {
					// a is before
					wasTrue = true;

				} else if (tmpN > 0) {
					if (wasTrue == null) {
						addToStart(t);
						return;
					}

					isFalse = true;
				} else if (tmpN == 0) {
					wasTrue = true;
					isFalse = true;
				}

				if (isFalse != null && wasTrue != null)
					if (isFalse == true && wasTrue == true) {
						addIndex(i, t);
						System.out.println("Hello" + array.length);
						return;

					}

			}
			addToStart(t);
		}

		else if (t.getConsumer().getGov() == true) {
			for (int i = cgs; get(i) != null && i < this.array.length; i++) {

				String tempS0 = ((Invoice) get(i)).getConsumerName(), tempS1 = t
						.getConsumerName();

				int tmpN = tempS0.compareTo(tempS1);

				if (tmpN < 0) {
					// a is before
					wasTrue = true;

				} else if (tmpN > 0) {
					if (wasTrue == null) {
						addToStart(t);
						return;
					}

					isFalse = true;
				} else if (tmpN == 0) {
					wasTrue = true;
					isFalse = true;
				}

				if (isFalse != null && wasTrue != null)
					if (isFalse == true && wasTrue == true) {
						addIndex(i, t);
						//System.out.println("Hello" + array.length);
						return;

					}
			}
			addToEnd(t);

		}
	}

	protected int size() {
		return this.array.length;
	}

	protected int FCGS() {
		Boolean isGov = null, wasCorp = null;

		if (get(0) == null)
			return -1;

		for (int i = 0; i < this.array.length; i++) {
			Invoice temp = get(i);
			if (temp.getConsumer().getGov() == false)
				wasCorp = true;
			else if (temp.getConsumer().getGov() == true)
				isGov = true;

			if (i == 0)
				if (isGov = true)
					return i;
			if (i != 0)
				if (isGov = true && wasCorp == true)
					return i;

		}

		return 0;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		if (array.length == 0 || array == null)

			System.out.println("The list is empty");

		else {
			sb.append(((Invoice) get(0)).getInvoiceCode() + "->");

			for (int i = 1; i < array.length; i++)
				sb.append("" + ((Invoice) get(0)).getInvoiceCode() + "->");

			System.out.println(sb.toString());
		}

		return sb.toString();
	}
}
