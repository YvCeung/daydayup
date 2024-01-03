package linkNode;

public class removeElements203 {


    public static void main(String[] args) {
        removeElements203 re = new removeElements203();
        ListNode si = new ListNode(1,null);
        ListNode san = new ListNode(2,si);
        ListNode er = new ListNode(2,san);
        ListNode head = new ListNode(1,er);
        re.removeElements03(head,2);
    }

    /**
     * 添加虚节点
     * @param head
     * @param val
     * @return
     */
    public  ListNode removeELements11(ListNode head, int val){
        if (head == null) {
            return head;
        }
        //因为删除可能设计到的头节点，所以设置dummy节点，同意操作
        ListNode dummy = new ListNode(-1, head);
        ListNode pre = dummy;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return dummy.next;
    }

    /**
     * 不添加虚节点
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements12(ListNode head, int val) {
        //单独处理头节点
        //这里注意是while ， 不要用if 。预防 “1 1 1 1 1 1 1 1”这种情况
        while (head != null && head.val == val) {
            head = head.next;
        }
        //已经为null，提前退出
        if (head == null) {
            return head;
        }
        //已确定head.val != val
        //没有虚拟节点，head为要删除节点的前一个节点
        ListNode pre = head;
        //head.next 为要删除的当前节点
        ListNode cur = head.next;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    /**
     * 不添加虚拟节点
     * cur.next 为要删除的节点
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements13(ListNode head, int val) {
        while (head != null && head.val == val) {
            head = head.next;
        }
        ListNode cur = head;
        while (cur != null) {
            while (cur != null) {
                while (cur.next != null && cur.next.val == val) {
                    cur.next = cur.next.next;
                }
                cur = cur.next;
            }
        }
        return head;
    }





































































































    /**
     * 添加虚节点方式      cur指针所指的为要移除元素！！！
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements01(ListNode head, int val) {
        if (head == null) {
            return head;
        }
        // 因为删除可能涉及到头节点，所以设置dummy节点，统一操作
        ListNode dummy = new ListNode(-1, head);
        ListNode pre = dummy;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return dummy.next;
    }
    /**
     * 不添加虚拟节点方式
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements02(ListNode head, int val) {
        while (head != null && head.val == val) {
            head = head.next;
        }
        // 已经为null，提前退出
        if (head == null) {
            return head;
        }
        // 已确定当前head.val != val
        ListNode pre = head;
        ListNode cur = head.next;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }
    /**
     * 不添加虚拟节点and pre Node方式
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements03(ListNode head, int val) {
        while(head!=null && head.val==val){
            head = head.next;
        }
        ListNode curr = head;
        while(curr!=null){
            //在这里不能用if，要用while，不然会漏删目标值 ！！！ eg：1 &2 2 2 &2 1 （数字前有符号的会漏掉）
            while (curr.next!=null && curr.next.val == val){
                curr.next = curr.next.next;
            }
            curr = curr.next;
        }
        return head;
    }
}










