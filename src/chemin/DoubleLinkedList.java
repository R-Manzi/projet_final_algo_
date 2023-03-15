package chemin;//https://www.geeksforgeeks.org/introduction-and-insertion-in-a-doubly-linked-list/
//GeeksforGeeks
//Utiliser pour faire le construc
import java.util.function.Consumer;

public class DoubleLinkedList<T> {
    public Node current;

    public DoubleLinkedList next;
    public Node head;

    public void insert(T obj) {
    }

    public T get(T index) {

        return index;
    }

    public T getFirst() {

        return index(0);
    }

    public void removeFirst() {
        remove_first();
    }

    public void forEach(Consumer<T> consumer) {
        Node currentNode = head;
        while (currentNode != null) {
            consumer.accept(currentNode.data);
            currentNode = currentNode.next;
        }
    }


    public class Node {

        public T data;
        public Node next;
        Node prev;

        public Node(T data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        DoubleLinkedList<String> list = new DoubleLinkedList();
        list.push("hello");
        list.push("bonjour");
        list.push("salut");
        list.append("Hooo");
        list.print();
        list.insert(1, "haha");
        list.print();
        list.insert(4, "Nice");
        list.print();
        list.replace(0, "allo");
        list.print();

        DoubleLinkedList<Integer> list_int = new DoubleLinkedList();
        list_int.remove_first();
        list_int.push(5);
        list_int.push(6);
        list_int.push(7);
        list_int.push(8);
        list_int.append(5);
        list_int.print();
        list_int.append(4);
        list_int.append(3);
        list_int.append(2);
        list_int.print();
        list_int.insert(40, 90);
        list_int.print();
        if (list_int.index( 2) == 6) {
            System.out.println("good");
        }
        list_int.remove_index(0);
        list_int.print();
        list_int.remove_index(2);
        list_int.print();
        list_int.remove_index(5);
        list_int.print();
        list_int.remove_first();
        list_int.print();
        list_int.remove_last();
        list_int.print();




    }


    public boolean isEmpty() {
        if (head == null) {
            return true;
        }
        return false;
    }

    public void push(T x) {
        Node node = new Node(x);
        node.next = head;
        node.prev = null;

        if (head != null) {
            head.prev = node;
        }

        node.data = x;
        head = node;

    }

    public void append(T x) {

        Node node = new Node(x);
        Node last = head;
        node.next = null;

        if (head == null) {
            node.prev = null;
            node.data = x;
            head = node;
            return;
        }

        while (last.next != null) {
            last = last.next;
        }

        last.next = node;
        node.prev = last;
        node.data = x;

    }

    public void remove_index(int index) {
        Node node = head;
        Node last = node;

        if (index == 0) {
            remove_first();
        }

        int i = 0;
        while (i < index) {
            try{
                last = node;
                node = node.next;
            }
            catch(Exception e) {
                System.out.println("Index trop élevé!!");
                return;
            }
            i++;
        }

        if (node == null) {
            System.out.println("Index trop élevé!!");
            return;
        }

        node.prev = last;
        last.next = node.next;

        node = null;
    }

    public void remove_first() {

        if (head != null) {
            if (head.next == null) {
                head = null;
            }else {
                head = head.next;
                head.prev = null;
            }
        }
    }

    public T remove_last() {

        Node node = head;
        Node last = node;
        while (node.next != null) {
            last = node;
            node = node.next;
        }
        node.prev = last;
        last.next = null;
        last = null;
        return node.data;
    }

    public void print() {
        Node node = head;
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;

        }
        System.out.println();
    }


    public T index (int i) {
        Node node = head;
        int index = 0;
        while (node != null) {
            if (i == index) {
                return node.data;
            }
            index ++;
            node = node.next;
        }
        return null;
    }


    public void replace(int index, T x) {
        Node node = new Node(x);
        Node last = head;
        int i = 0;
        while (i < index) {
            last = last.next;
            i++;
        }

        node.next = last.next;
        node.prev = last.prev;
        node = last;

        node.data = x;

    }

    public int len() {
        Node node = head;
        int i = 0;
        while (node != null) {
            node = node.next;
            i += 1;
        }
        return i;
    }

    public int get_index(T x) {
        Node node = head;
        int index = 0;
        while (node.data != x) {
            index ++;
            node = node.next;
        }
        if (index > 0 && node == null) {
            return -1;
        }
        return index;

    }

    public void insert(int index, T x) {
        Node node = new Node(x);
        Node last = head;

        int i = 0;
        while (i < index - 1) {
            try{
                last = last.next;
            }
            catch(Exception e) {
                System.out.println("Index trop élevé!!");
                return;
            }
            i++;
        }

        node.next = last.next;
        last.next = node;
        node.prev = last;

        if (node.next != null) {
            node.next.prev = node;
        }

        node.data = x;

    }


}


