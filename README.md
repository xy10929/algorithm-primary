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

## class05

## class06

## class07

## class08
