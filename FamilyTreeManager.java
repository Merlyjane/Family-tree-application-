import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class FamilyTreeManager {
    static class Person {
        String name;
        ArrayList<Person> children;

        Person(String name) {
            this.name = name;
            this.children = new ArrayList<>();
        }
    }

    static HashMap<String, Person> familyTree = new HashMap<>();
    static Person root = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Family Tree Manager!");

        while (true) {
            System.out.println("\n1. Add Person\n2. Set Root\n3. Add Relationship\n4. Display Tree\n5. Exit");
            System.out.print("Choose an option: ");
            int choice;

            // Validate menu input
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                continue;
            }

            switch (choice) {
                case 1:  addPerson(scanner);
                case 2: setRoot(scanner);
                case 3:  addRelationship(scanner);
                case 4: displayTree(root, 0);
                case 5: {
                    System.out.println("Exiting...");
                    return;
                }
               default: System.out.println("Invalid option, try again.");
            }
        }
    }

    static void addPerson(Scanner scanner) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        if (familyTree.containsKey(name)) {
            System.out.println("Person already exists in the tree.");
            return;
        }

        familyTree.put(name, new Person(name));
        System.out.println(name + " has been added to the tree.");
    }

    static void setRoot(Scanner scanner) {
        System.out.print("Enter the name of the root person: ");
        String name = scanner.nextLine();

        if (!familyTree.containsKey(name)) {
            System.out.println("Person does not exist. Please add them first.");
            return;
        }

        root = familyTree.get(name);
        System.out.println(name + " is now the root of the tree.");
    }

    static void addRelationship(Scanner scanner) {
        System.out.print("Enter parent name: ");
        String parent = scanner.nextLine();
        System.out.print("Enter child name: ");
        String child = scanner.nextLine();

        if (!familyTree.containsKey(parent) || !familyTree.containsKey(child)) {
            System.out.println("One or both names do not exist. Please add them first.");
            return;
        }

        Person parentNode = familyTree.get(parent);
        Person childNode = familyTree.get(child);

        if (parentNode.children.contains(childNode)) {
            System.out.println("This relationship already exists.");
            return;
        }

        parentNode.children.add(childNode);
        System.out.println("Relationship added: " + parent + " -> " + child);
    }

    static void displayTree(Person root, int depth) {
        if (root == null) {
            System.out.println("Tree is empty or root is not set.");
            return;
        }

        System.out.println("  ".repeat(depth) + root.name);

        for (Person child : root.children) {
            displayTree(child, depth + 1);
        }
    }
}
