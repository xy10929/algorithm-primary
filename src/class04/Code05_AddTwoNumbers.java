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
		ListNode l = len1 > len2 ? l1 : l2;// 最后返回长链表的头
		ListNode s = l == l1 ? l2 : l1;
		int sum = 0;
		int carry = 0;
		ListNode curL = l;// 遍历时的链表指针
		ListNode curS = s;
		ListNode cur = l;
		while (curS != null) {// 尚未遍历完短链表
			sum = curS.val + curL.val + carry;
			curL.val = sum % 10;
			carry = sum / 10;
			cur = curL;// 记录目前的结果链表的末端
			curL = curL.next;
			curS = curS.next;
		}
		while (curL != null) {// 已遍历完短链表
			sum = curL.val + carry;
			curL.val = sum % 10;
			carry = sum / 10;
			cur = curL;
			curL = curL.next;
		}
		if (carry == 1) {
			cur.next = new ListNode(1);
		}
		return l;
	}

	public int listLength(ListNode n) {
		int ans = 1;
		while (n.next != null) {
			ans++;
			n = n.next;
		}
		return ans;
	}

}