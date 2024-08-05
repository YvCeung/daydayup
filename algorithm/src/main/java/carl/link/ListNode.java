package carl.link;

public class ListNode {
    //结点的值
    int val;

    //下个节点
    ListNode next;

    //节点的构造函数(无参)
    public ListNode() {
    }

    //节点的构造函数（有一个参数）
    public ListNode(int val) {
        this.val = val;
    }

    //节点的构造函数（有两个参数）
    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}




class ListNode0 {
    // 结点的值
    int val;

    // 下一个结点
    ListNode0 next;

    // 节点的构造函数(无参)
    public ListNode0() {
    }

    // 节点的构造函数(有一个参数)
    public ListNode0(int val) {
        this.val = val;
    }

    // 节点的构造函数(有两个参数)
    public ListNode0(int val, ListNode0 next) {
        this.val = val;
        this.next = next;
    }
}
