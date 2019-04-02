package jp.co.paypay.engineer.challenge;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An immutable Queue.
 * <p>
 * Order elements in a FIFO (first-in-first-out) manner.
 *
 * @author LI ZONGYU
 * @param <T> the type of elements held in this Queue
 */
public final class ImmutableQueue<T> implements Queue<T> {

	/*
	 * Node for holds the element. Root node's element is always null.
	 */
	private final QueueNode<T> elementsNode;

	/**
	 * Default constructor. Create an empty Queue.
	 */
	public ImmutableQueue() {
		this.elementsNode = new QueueNode<>(null, null);
	}

	/**
	 * Constructs a Queue containing the elements of the specified collection, in
	 * the order they are returned by the collection's iterator.
	 *
	 * @param collection
	 */
	public ImmutableQueue(final Collection<T> collection) {

		// Create a root node always null.
		this.elementsNode = new QueueNode<>(null, null);

		final Iterator<T> iterator = collection.iterator();

		while (iterator.hasNext()) {
			this.add(iterator.next(), this.elementsNode);
		}
	}

	/**
	 * Private Constructor to create a Queue containing the elements of a node.
	 *
	 * @param queueNode
	 */
	private ImmutableQueue(final QueueNode<T> queueNode) {

		this.elementsNode = queueNode;
	}

	/**
	 * Private method to add the element into queue's node.
	 *
	 * @param t          the element to add
	 * @param targetNode a node
	 */
	private void add(final T t, final QueueNode<T> targetNode) {

		QueueNode<T> tail = targetNode;

		while (tail.next != null) {
			tail = tail.next;
		}
		final QueueNode<T> newNode = new QueueNode<>(t, null);
		tail.next = newNode;
	}

	/**
	 * Inserts the element into the head of this queue, and returns a new queue.
	 *
	 * @param t the element to add
	 * @return the new queue.
	 */
	@Override
	public Queue<T> enQueue(final T t) {

		if (t == null) {
			throw new NullPointerException();
		}

		final QueueNode<T> newRootNode = new QueueNode<>(null, null);

		// Add the parameter to the head
		add(t, newRootNode);

		QueueNode<T> tail = this.elementsNode;

		while (tail.next != null) {
			add(tail.next.element, newRootNode);
			tail = tail.next;
		}

		return new ImmutableQueue<>(newRootNode);
	}

	/**
	 * Removes the element at the head of this queue, and returns a new queue.
	 *
	 * @return the new queue.
	 */
	@Override
	public Queue<T> deQueue() {

		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		final QueueNode<T> newRootNode = new QueueNode<>(null, null);

		QueueNode<T> tail = this.elementsNode.next;

		while (tail.next != null) {
			add(tail.next.element, newRootNode);
			tail = tail.next;
		}

		return new ImmutableQueue<>(newRootNode);
	}

	/**
	 * Retrieves, but does not remove, the head of this queue, or returns null if
	 * this queue is empty.
	 *
	 * @return a new queue.
	 */
	@Override
	public T head() {
		if (isEmpty()) {
			return null;
		}
		return this.elementsNode.next.element;
	}

	/**
	 * Returns true if this queue contains no elements.
	 *
	 * @return true if this queue contains no elements
	 */
	@Override
	public boolean isEmpty() {
		return this.elementsNode.next == null;
	}

	/**
	 * Convert this queue's elements to a string like [1,2,3,4,5].
	 *
	 * @return the string of elements.
	 */
	@Override
	public String toString() {

		if (isEmpty()) {
			return "[]";
		}

		QueueNode<T> tail = this.elementsNode.next;

		final StringBuilder sb = new StringBuilder();

		sb.append("[");

		while (tail != null) {
			sb.append(String.valueOf(tail.element)).append(",");
			tail = tail.next;
		}

		// Remove last separator
		sb.setLength(sb.length() - 1);

		return sb.append("]").toString();
	}

	/**
	 * Node to store one element and next node.
	 */
	private static class QueueNode<T> {

		T element;
		QueueNode<T> next;

		public QueueNode(final T t, final QueueNode<T> next) {

			this.element = t;
			this.next = next;
		}
	}

}
