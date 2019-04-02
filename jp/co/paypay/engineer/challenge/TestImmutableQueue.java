package jp.co.paypay.engineer.challenge;

import java.util.Arrays;

/**
 * ImmutableQueueのテストクラス。
 */
public class TestImmutableQueue {

	/**
	 * @param args
	 */
	public static void main(final String[] args) {

		final Queue<Integer> queue1 = new ImmutableQueue<>(Arrays.asList(1, 2, 3, 4, 5));

		System.out.println("生成後:" + queue1.toString());

		final Queue<Integer> queue2 = queue1.enQueue(0);

		System.out.println("enQueueで取得した新しいQueue:" + queue2.toString());
		System.out.println("enQueue後:" + queue1.toString());

		final Queue<Integer> queue3 = queue1.deQueue();

		System.out.println("deQueueで取得した新しいQueue:" + queue3.toString());
		System.out.println("deQueue後:" + queue1.toString());

		System.out.println("先頭要素:" + queue1.head());
		System.out.println("isEmpty:" + queue1.isEmpty());

	}

}
