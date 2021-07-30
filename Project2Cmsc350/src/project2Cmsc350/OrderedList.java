/* Name: Lang, Jordan
 * Project Name: Project2Cmis350
 * Date: 04/4/2021
 * Description: This class determines whether the supplied list is in strictly ascending order.
*/
package project2Cmsc350;

import java.util.Iterator;
import java.util.Comparator;

public class OrderedList {

	// accept list that contains elements that implement Comparable
	public static <order extends Comparable<? super order>> boolean checkSorted(Iterator<order> list) {
		return checkSorted(list, new Comparator<order>() {
			public int compare(order l1, order l2) {
				return l1.compareTo(l2);
			}
		});
	}

	// Overload and use comparator interface
	public static <order> boolean checkSorted(Iterator<order> iterator, Comparator<order> comparator) {
		order cur, pre = iterator.next();
		while (iterator.hasNext()) {
			cur = iterator.next();
			if (comparator.compare(pre, cur) < 0) {
				return false;
			}
			pre = cur;
		}
		return true;
	}
}
