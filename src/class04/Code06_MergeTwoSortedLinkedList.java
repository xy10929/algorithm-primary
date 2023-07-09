package class04;

//lc21
public class Code06_MergeTwoSortedLinkedList {

	// 不要提交这个类
	public static class ListNode {
		public int val;
		public ListNode next;
	}

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if (l1 == null || l2 == null) {
			return l1 == null ? l2 : l1;
		}
		ListNode head = l1.val < l2.val ? l1 : l2;// 较小的为head
		ListNode cur1 = head.next;
		ListNode cur2 = head == l1 ? l2 : l1;
		ListNode pre = head;// 已连好的串的结尾
		while (cur1 != null && cur2 != null) {
			if (cur1.val < cur2.val) {
				pre.next = cur1;
				cur1 = cur1.next;
			} else {
				pre.next = cur2;
				cur2 = cur2.next;
			}
			pre = pre.next;
		}
		pre.next = cur1 != null ? cur1 : cur2;// 一个链已连完 另一个链的剩余部分直接接上
		return head;
	}

}