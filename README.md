# algorithm-primary

Leetcode problems in data stucture & algorithm course by [Chengyun Zuo](https://github.com/algorithmzuo/algorithm-primary)

- [class01](#class01)
- [class02](#class02)
- [class03](#class03)
- [class04](#class04)
  - [lc206](#lc206)
  - [lc25](#lc25)
  - [lc2](#lc2)
  - [lc21](#lc21)
- [class05](#class05)
- [class06](#class06)
- [class07](#class07)
- [class08](#class08)

## class01

@打印 int 的 32 位

## class02

## class03

## class04

### lc206

@反转单链表

下头<u>下前</u>头

指针 pre next 最初指向 null  
head 指向反转后的链表头

while head 不为空  
{
下=头下(提前记录下一个要处理的节点)  
头下=前(反转链)  
前=头(用 pre 记录已完成反转的部分的最新节点 它将作为下一次反转的目标)  
头=下(head 到达之前记录的下一个要处理的节点)  
}  
return 前

```java
public ListNode reverseList(ListNode head) {
    ListNode pre = null;
    ListNode next = null;
    while(head != null){
        next = head.next;
        head.next = pre;
        pre = head;
        head = next;
    }
    return pre;
}
```

### lc25

@k 个节点一组 逆序单链表

```java
public ListNode reverseKGroup(ListNode head, int k) {
  ListNode start = head;
  ListNode end = getGroupEnd(start, k);
  if (end == null) {// 一组也凑不齐
    return head;
  }
  head = end;// 第一组逆序后的头为整个链最后的头 不变
  reverse(start, end);// 完成组内反转 但start和end指向的节点不变
  ListNode lastEnd = start;// 上一组的结尾节点即start
  while (lastEnd.next != null) {
    start = lastEnd.next;// 找到下一组的start
    end = getGroupEnd(start, k);
    if (end == null) {
      return head;
    }
    reverse(start, end);
    lastEnd.next = end;// 反转后end为当前组的开头 被上一组的结尾指向
    lastEnd = start;// start反转后为当前组的结尾 也即此后的上一组的结尾
  }
  return head;
}

public static ListNode getGroupEnd(ListNode start, int k) {// start开始数k个 返回组内最后一个 不够k个会返回null
  while (--k != 0 && start != null) {
    start = start.next;
  }
  return start;
}

public static void reverse(ListNode start, ListNode end) {// start到end逆序
  end = end.next;// 记录逆序后要连向的位置
  ListNode pre = null;
  ListNode next = null;
  ListNode head = start;
  while (head != end) {
    next = head.next;
    head.next = pre;
    pre = head;
    head = next;
  }
  start.next = end;// 原头为逆序后的尾 连向记录好的位置
}
```

## class05

## class06

## class07

## class08
