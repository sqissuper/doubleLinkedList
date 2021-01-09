import java.util.List;

class ListNode{
    private int val;
    private ListNode next;
    private ListNode prev;

    public ListNode(int val){
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public ListNode getNext() {
        return next;
    }

    public ListNode getPrev() {
        return prev;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    public void setPrev(ListNode prev) {
        this.prev = prev;
    }
}
public class TwoWayLinkList {

    public ListNode head;
    public ListNode last;

    //计数
    public int size(){
        ListNode cur = this.head;
        int count = 0;
        while(cur != null){
            cur = cur.getNext();
            count++;
        }
        return count;
    }

    //打印链表
    public void disPlay(){
        ListNode cur = this.head;
        while(cur != null){
            System.out.print(cur.getVal() + " ");
            cur = cur.getNext();
        }
        System.out.println();
    }
    //头插法
    public void addInsert(int val){
        ListNode nod = new ListNode(val);
        if(this.head == null){
            this.head = nod;
            this.last = nod;
        } else {
            nod.setNext(this.head);
            this.head.setPrev(nod);
            this.head = nod;
        }
    }

    //尾插法
    public void tailInsert(int val){
        ListNode nod = new ListNode(val);
        if(this.head == null){
            this.head = nod;
            this.last = nod;
        } else {
            this.last.setNext(nod);
            nod.setPrev(this.last);
            this.last = nod;
        }
    }

    //任意位置插入
    public void addIndex(int index,int data){
        if(index < 0 || index > size()) return;
        if(index == 0){
            addInsert(data);//头位置头插法
            return;
        }
        if(index == size()){
            tailInsert(data);//末位置尾插法
            return;
        }
        ListNode cur = this.head;
        while (index != 0){
            cur = cur.getNext();
            index--;
        }
        ListNode nod = new ListNode(data);
        nod.setNext(cur);
        nod.setPrev(cur.getPrev());
        nod.getPrev().setNext(nod);
        cur.setPrev(nod);
    }

    //查找是否存在关键字
    public boolean contains(int key){
        ListNode cur = this.head;
        while(cur != null){
            if(cur.getNext().getVal() == key){
                return true;
            }
            cur = cur.getNext();
        }
        return false;
    }

    //找key所在的节点
    public ListNode findNode(int key){
        ListNode cur = this.head;
        while (cur != null) {
            if(cur.getVal() == key){
                return cur;
            }
            cur = cur.getNext();
        }
        return null;
    }

    //删除第一次出现的关键字key
    public void remove(int key){
        ListNode cur = this.findNode(key);
        if(cur == null) return;
        if(cur == this.head){
            this.head = this.head.getNext();
            this.head.setPrev(null);
            return;
        }
        if(cur == this.last){
            cur.getPrev().setNext(null);
            this.last = this.last.getPrev();
            return;
        }
        cur.getPrev().setNext(cur.getNext());
        cur.getNext().setPrev(cur.getPrev());
    }

    //删除所有节点
    public void allKey(int key){
        ListNode cur = this.head;
        while(cur != null){
            if(cur.getVal() == key){
                if(cur == this.head){
                    this.head = this.head.getNext();
                    this.head.setPrev(null);
                }else {
                    cur.getPrev().setNext(cur.getNext());
                    if(cur.getNext() != null){
                        cur.getNext().setPrev(cur.getPrev());
                    } else {
                        this.last = this.last.getPrev();
                    }
                }
            }
            cur = cur.getNext();
        }
    }

    //清空链表
    public void clean(){
        ListNode cur = this.head;
        while(cur != null){
            cur.setPrev(null);
            cur.setNext(null);
            cur = cur.getNext();
        }
        this.head = null;
        this.last = null;
    }

    public static void main(String[] args) {
        TwoWayLinkList myList = new TwoWayLinkList();
        myList.addInsert(1);
        myList.addInsert(2);
        myList.addInsert(1);
        myList.tailInsert(4);
        myList.tailInsert(1);
//        myList.addIndex(0,0);
//        myList.addIndex(6,6);
//        myList.addIndex(2,2);
        myList.disPlay();
        System.out.println();
//        boolean flag = myList.contains(1);
//        System.out.println(flag);
//        myList.remove(0);
//        myList.remove(6);
//        myList.remove(2);
//        myList.disPlay();
//        System.out.println();
        myList.allKey(1);
        myList.disPlay();

    }
}
