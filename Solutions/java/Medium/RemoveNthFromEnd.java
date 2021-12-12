public class RemoveNthFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        length -= n;
        head = dummy;
        while (length > 0) {
            length--;
            head = head.next;
        }
        head.next = head.next.next;
        return dummy.next;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
