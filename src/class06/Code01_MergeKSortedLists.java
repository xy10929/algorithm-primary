package class06;
//lc23
import java.util.Comparator;
import java.util.PriorityQueue;

public class Code01_MergeKSortedLists {

	public static class ListNode {
		public int val;
		public ListNode next;
	}

	public ListNode mergeKLists(ListNode[] lists) {
		if (lists == null) {
			return null;
		}
		PriorityQueue<ListNode> heap = new PriorityQueue<>(new ListNodeComparator());
		for (int i = 0; i < lists.length; i++) {
			if (lists[i] != null) {
				heap.add(lists[i]);//抓取每个链表的头结点
			}
		}
		if (heap.isEmpty()) {//所以链表都为空
			return null;
		}
		ListNode head = heap.poll();//从小根堆弹出 作为返回链表的头
		ListNode pre = head;//已穿好的部分的结尾
		if (pre.next != null) {
			heap.add(pre.next);//弹出节点后将其next节点加入小根堆
		}
		while (!heap.isEmpty()) {
			ListNode cur = heap.poll();
			pre.next = cur;//更新pre
			pre = cur;
			if (cur.next != null) {
				heap.add(cur.next);
			}
		}
		return head;
	}

	public static class ListNodeComparator implements Comparator<ListNode> {

		@Override
		public int compare(ListNode L1, ListNode L2) {
			return L1.val - L2.val;//如果第一个参数较小 且要把它排在前 则需返回负数
		}
	}
}
