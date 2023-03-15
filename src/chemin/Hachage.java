package chemin;

//https://www.geeksforgeeks.org/implementing-our-own-hash-table-with-separate-chaining-in-java/
//Geeksfo




public class Hachage<K, V> {
    K key;
    V value;
    public int code;


    Hachage<K, V> next;

    public Hachage (K key, V value, int code) {
        this.key = key;
        this.value = value;
        this.code = code;
    }

    public static void main(String[] args) {
        Map<String, Integer> map = new Map<>();
        map.add("this", 1);
        map.add("coder", 2);
        map.add("this", 4);
        map.add("hi", 5);
        System.out.println(map.size());
        System.out.println(map.remove("this"));
        System.out.println(map.remove("this"));
        System.out.println(map.size());
        System.out.println(map.isEmpty());
        System.out.println(map.get("hi"));
    }

}

class Map<K, V> {

    Map head;


    public DoubleLinkedList<Hachage<K, V>> liste;

    public int nb_value;

    public int size;

    public Map() {
        liste = new DoubleLinkedList<>();
        nb_value = 10;
        size = 0;

        for (int i = 0; i < nb_value; i++)
            liste.append(null);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int code(K key) {
        int compteur = 0;
        for (int i = 0; i < (key.toString().length()); i++) {
            compteur++;
        }
        return compteur;
    }

    public int index(K key) {
        int code = code(key);
        int index = code % nb_value;
        if (index < 0) {
            index = index * -1;
        }
        return index;

    }

    public V remove(K key)
    {

        int index = index(key);
        int code = code(key);

        Hachage<K, V> head = liste.index(index);

        Hachage<K, V> prev = null;

        while (head != null) {
            if (head.key.equals(key) && code == head.code)
                break;

            prev = head;
            head = head.next;
        }
        if (head == null)
            return null;

        size--;

        if (prev != null)
            prev.next = head.next;
        else
            liste.replace(index, head.next);


        return head.value;
    }



    public V get(K key)
    {

        int index = index(key);
        int code = code(key);

        Hachage<K, V> head = liste.index(index);

        while (head != null) {
            if (head.key.equals(key) && head.code == code)
                return head.value;
            head = head.next;
        }

        return null;
    }

    public void add(K key, V value)
    {
        int index = index(key);
        int code = code(key);
        Hachage<K, V> head = liste.index(index);


        while (head != null) {
            if (head.key.equals(key) && head.code == code) {
                head.value = value;
                return;
            }
            head = head.next;
        }

        size++;

        head = liste.index(index);
        Hachage<K, V> newNode = new Hachage<>(key, value, code);
        newNode.next = head;
        liste.replace(index, newNode);

        if ((1.0 * size) / nb_value >= 0.7) {
            DoubleLinkedList<Hachage<K, V> > temp = liste;
            liste = new DoubleLinkedList<>();
            nb_value = 2 * nb_value;
            size = 0;
            for (int i = 0; i < nb_value; i++)
                temp.append(null);

            for (int i = 0; i < size; i++) {
                Hachage<K, V> headNode = head;
                while (headNode != null) {
                    add(headNode.key, headNode.value);
                    headNode = headNode.next;
                }
            }
        }
    }
}