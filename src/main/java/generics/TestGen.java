package generics;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

//Learning Generics
@Data
@AllArgsConstructor
class Employee{
    private int empId;
    private String empName;
}

@Data
@AllArgsConstructor
class Student{
    private int stId;
    private String stName;

}

class MyGen<T>{
    T obj;
    public void add(T obj){
        this.obj = obj;
    }

    public T get(){
        return obj;
    }
}

class MyGenList<T>  {
    List<T> list = new ArrayList<>();

    public void addToList(T obj){
        this.list.add(obj);
    }

    public List<T> getList(){
        return list;
    }

    public  <E> void printArray(List<E> elements) {

        elements.stream().forEach(element -> System.out.println(element));
     }

    public <pacoo2> void printArray2(List<pacoo2> elements) {
        elements.stream().forEach(element -> System.out.println(element));
    }

    public <String> void printArray3(List<String> elements) {
        elements.stream().forEach(element -> System.out.println(element));
    }

    //Here String a just a keyword like foo bar not the actual String class
}

class Box<T> {
    private T contents;

    public Box(T contents) {
        this.contents = contents;
    }

    public T getContents() {
        return contents;
    }

    public void setContents(T contents) {
        this.contents = contents;
    }
}
class Pair<T, U> {
    private T first;
    private U second;

    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public void setSecond(U second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return "Pair [first="+first + ", second="+second + "]";
    }
}

class Stack<T> {
    private ArrayList<T> elements;

    public Stack() {
        elements = new ArrayList<>();
    }

    public void push(T item) {
        elements.add(item);
    }

    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty.");
        }
        return elements.remove(elements.size() - 1);
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public int size() {
        return elements.size();
    }
}

 class LinkedList<T> {
    private Node<T> head;

    private static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public void insert(T data) {
        Node<T> newNode = new Node<>(data);

        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public void delete(T data) {
        if (head == null) {
            return;
        }

        if (head.data.equals(data)) {
            head = head.next;
            return;
        }

        Node<T> current = head;
        Node<T> prev = null;

        while (current != null && !current.data.equals(data)) {
            prev = current;
            current = current.next;
        }

        if (current == null) {
            return;
        }

        prev.next = current.next;
    }

    public void display() {
        Node<T> current = head;

        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }

        System.out.println();
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();

        list.insert(10);
        list.insert(20);
        list.insert(30);

        list.display(); // Output: 10 20 30

        list.delete(20);

        list.display(); // Output: 10 30

        LinkedList<String> stringList = new LinkedList<>();

        stringList.insert("Hello");
        stringList.insert("World");

        stringList.display(); // Output: Hello World
    }
}



public class TestGen {

    public static void main(String[] args) {
        MyGen<Student> myGenSt = new MyGen<Student>();
        myGenSt.add(new Student(1,"saurabh student"));
       // System.out.println(myGenSt.get());

        MyGen<Employee> myGenEmp = new MyGen<Employee>();
        myGenEmp.add(new Employee(2,"saurabh Employee"));
       // System.out.println(myGenEmp.get());

        Student student1 = new Student(1, "Saurabh");
        Student student2 = new Student(2, "John");
        Student student3 = new Student(3, "Emily");
        Student student4 = new Student(4, "Michael");
        Student student5 = new Student(5, "Sarah");

        MyGenList<Student> myGenList = new MyGenList<>();
        myGenList.addToList(student1);
        myGenList.addToList(student2);
        myGenList.addToList(student3);
        myGenList.addToList(student4);
        myGenList.addToList(student5);

        myGenList.getList().stream().forEach(System.out::println);

        //BOX class
        Box<String> box = new Box<>("Hello, World");
        Box<Integer> box2 = new Box<>(10);
        System.out.println(box.getContents());

        //Pair Class
        Pair pair = new Pair("John Doe", 25);
        Pair pair2 = new Pair(10,20);
        Pair pair3 = new Pair("Saurabh","Kumar");

        System.out.println(pair.getFirst());
        System.out.println(pair.getSecond());
        System.out.println(pair2.getFirst());
        System.out.println(pair2.getSecond());
        System.out.println(pair3.getFirst());
        System.out.println(pair3.getSecond());

        Stack<Integer> stack = new Stack<>();
        stack.push(10);
        stack.push(30);
        stack.pop();

        Stack<String> stack2 = new Stack<>();
        stack2.push("using");
        stack2.push("C");
        System.out.println(stack);
        System.out.println(stack2);

     }
  }
