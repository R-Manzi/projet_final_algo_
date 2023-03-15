//https://www.geeksforgeeks.org/priority-queue-using-linked-list/   user: Arnab Kundu
package chemin;

public class PriorityQueue<T extends Comparable<T>> {

    private static Node<T> head;

    static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        public Node(T d) {
            this.data = d;
        }

        public T getValue() {
            return data;
        }

        public Node<T> getNext() {
            return next;
        }
    }

    public void push(T d) {
        Node<T> node = new Node<>(d);
        Node<T> temp = head;

        if (head == null) {
            head = node;
            return;
        }

        if (head.data.compareTo(d) > 0) {
            node.next = head;
            head = node;
        } else {
            while (temp.next != null && temp.next.data.compareTo(d) < 0) {
                temp = temp.next;
            }
            node.next = temp.next;
            temp.next = node;
        }
    }

    public void pop() {
        if (head != null) {
            if (head.next == null) {
                head = null;
            } else {
                head = head.next;
                head.prev = null;
            }
        }
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void print() {
        Node<T> node = head;
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
        System.out.println();
    }

    public boolean contains(T value) {
        Node<T> currentNode = head;
        while (currentNode != null) {
            if (currentNode.getValue().equals(value)) {
                return true;
            }
            currentNode = currentNode.getNext();
        }
        return false;
    }

    public static void main(String[] args) {

        PriorityQueue<Integer> p = new PriorityQueue<>();

        p.isEmpty();
        p.push(15);
        p.push(20);
        p.push(14);
        p.push(4);
        p.push(82);
        p.push(1);
        p.print();
        p.pop();
        p.print();
        p.isEmpty();
    }
}
