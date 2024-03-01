public class ListNode {
    int value;
    ListNode next;

    public void print() {
        System.out.println(value);
        ListNode tmp = next;
        while(tmp!=null) {
            System.out.println(tmp.value);
            tmp = tmp.next;
        }
    }
}