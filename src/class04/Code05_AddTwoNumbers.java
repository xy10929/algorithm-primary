package class04;

//lc2
public class Code05_AddTwoNumbers {

	// 不要提交这个类
	public static class ListNode {
		public int val;
		public ListNode next;

		public ListNode(int val) {
			this.val = val;
		}

		public ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}
	}

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		int len1 = listLength(l1);
		int len2 = listLength(l2);
		ListNode l = len1 > len2 ? l1 : l2;
		ListNode s = l == l1 ? l2 : l1;
		ListNode curL = l;
		ListNode curS = s;
		int carry = 0;// 进位
		int num = 0;// 和
		ListNode last = curL;// 跟踪最后一个节点 便于往后加节点
		while (curS != null) {
			num = curS.val + curL.val + carry;
			curL.val = num % 10;
			carry = num / 10;
			last = curL;
			curS = curS.next;
			curL = curL.next;
		}
		while (curL != null) {
			num = curL.val + carry;
			curL.val = num % 10;
			carry = num / 10;
			last = curL;
			curL = curL.next;
		}
		if (carry != 0) {
			last.next = new ListNode(1);
		}
		return l;
	}

	public int listLength(ListNode head) {
		int ans = 0;
		while (head != null) {
			head = head.next;
			ans++;
		}
		return ans;
	}

}