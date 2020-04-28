package classicEasy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
//https://www.codingame.com/training/easy/order-of-succession
public class OrDerOFSucceSsion {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        ArrayList<Person> personList = new ArrayList<>();
        for(int i = 0; i < n; i++){
            String name = in.next();
            String parent = in.next();
            int dateOfBirth = in.nextInt();
            int dateOfDeath = 0;
            if (in.hasNextInt()) dateOfDeath = in.nextInt();
            else in.next();
            String religion = in.next();
            String gender = in.next();
            personList.add(new Person(name, parent, dateOfBirth, dateOfDeath == 0 && religion.equals("Anglican"), gender.equals("M")));
        }
        Person rootPerson = personList.stream().filter(p -> p.parent.equals("-")).findFirst().orElse(null);
        Node root = new Node(rootPerson);
        root.buildChideList(personList);
        root.buildSuccessorList(new ArrayList<>()).forEach(System.out::println);
    }
}

class Node {
    Person data;
    List<Node> children;

    public Node(Person data) {
        this.data = data;
    }
    //Recursive adding children nodes to parent node
    public void buildChideList(List<Person> personList) {
        children = personList.stream()
                .filter(p -> p.parent.equals(this.data.name))
                .sorted()
                .map(Node::new)
                .collect(Collectors.toList());
        children.forEach(c -> c.buildChideList(personList));
    }
    //Recursive node adding to Successor List - answer
    public List<String> buildSuccessorList(List<String> successorList) {
        if (data.isValidSuccessor) successorList.add(data.name);
        for(Node child: children){
            successorList = child.buildSuccessorList(successorList);
        }
        return successorList;
    }
}

class Person implements Comparable<Person> {
    String name;
    String parent;
    int yearOfBirth;
    boolean isValidSuccessor; //True if date of dead is "-" and religion is Anglican
    boolean isMaleGender;

    public Person(String name, String parent, int yearOfBirth, boolean isValidSuccessor, boolean isMaleGender) {
        this.name = name;
        this.parent = parent;
        this.yearOfBirth = yearOfBirth;
        this.isValidSuccessor = isValidSuccessor;
        this.isMaleGender = isMaleGender;
    }

    @Override
    public int compareTo(Person comparable) { //Sort in successor rules order
        return Comparator.comparing((Person p1) -> p1.isMaleGender)
                .reversed()
                .thenComparing((Person p1) -> p1.yearOfBirth)
                .compare(this, comparable);
    }
}