import java.util.InputMismatchException;
import java.util.Scanner;

public class BST {
    public static class Node {
        Double key;
        Node left;
        Node right;

        public Node(Double key) {
            this.key = key;
            this.left = null;
            this.right = null;
        }
    }

    public static Node root;

    /*******insert function*********/
    public static void insert(Double key) {
        System.out.println("Inserting " + key);
        root = insertKey(new Node(key), root);
    }

    public static Node insertKey(Node newNode, Node currentRoot) {
        if (currentRoot != null) {
            System.out.println("Current Node: " + currentRoot.key);
        }
        /*executes if tree is new/empty*/
        if (currentRoot == null) {
            System.out.println("Current Node: Null");
            System.out.println("Assigning " + newNode.key + " here.");
            currentRoot = newNode;
            return currentRoot;
        }
        if (newNode.key < currentRoot.key) {
            System.out.println(newNode.key + " < " + currentRoot.key);
            if (currentRoot.left == null) {
                System.out.println("Left child of node " + currentRoot.key + " is null. Assigning " + newNode.key + " here!");
                currentRoot.left = newNode;
            } else {
                System.out.println("left child of node " + currentRoot.key + " is not null. Recurring.");
                insertKey(newNode, currentRoot.left);
            }
        }
        else if (newNode.key > currentRoot.key) {
            System.out.println(newNode.key + " > " + currentRoot.key);
            if (currentRoot.right == null) {
                System.out.println("Right child of node " + currentRoot.key + " is null. Assigning " + newNode.key + " here!");
                currentRoot.right = newNode;
            } else {
                System.out.println("right child of node " + currentRoot.key + " is not null. Recurring.");
                insertKey(newNode, currentRoot.right);
            }
        }
        else if (newNode.key.equals(currentRoot.key)) {
            System.out.println("Cannot insert duplicate!");
        }
        return currentRoot;
    }

    /*******search function*********/
    public static void search(Double key) {
        if (root == null) {
            System.out.println("No tree!");
        } else {
            System.out.println("Searching for " + key);
            root = searchKey(key, root);
        }
    }

    public static Node searchKey(Double key, Node currentRoot) {
        if (currentRoot != null) {
            System.out.println("Current Node: " + currentRoot.key);
        }
        if (currentRoot == null) {
            System.out.println("No more nodes! cannot find node with key " + key + " in this tree!");
        }
        else if (currentRoot.key == key) {
            System.out.println("Found match for key " + key);
        }
        else if (key < currentRoot.key) {
            System.out.println(key + " < " + currentRoot.key + ". Recurring.");
            searchKey(key, currentRoot.left);
            }
        else if (key > currentRoot.key) {
            System.out.println(key + " > " + currentRoot.key + ". Recurring.");
            searchKey(key, currentRoot.right);
            }
        return currentRoot;
    }

    /******delete function*******/
    public static Boolean isLeaf(Node node) {
        return node.left == null && node.right == null;
    }

    public static Node minimumRight(Node node) {
        if (node.left != null) {
            minimumRight(node.left);
        }
        return node;
    }

    public static void delete(Double key) {
        if (root == null) {
            System.out.println("No tree!");
        } else {
            System.out.println("Attempting to delete " + key);
            root = deleteNode(key, root);
        }
    }

    public static Node deleteNode(Double key, Node currentRoot) {
        if (currentRoot == null) {
            System.out.println(key + " is not in the tree!");
            return null;
        }
        if (key < currentRoot.key) {
            System.out.println("Current Node: " + currentRoot.key);
            System.out.println(key + " < " + currentRoot.key + " Recurring.");
            currentRoot.left = deleteNode(key, currentRoot.left);
        }
        else if (key > currentRoot.key) {
            System.out.println("Current Node: " + currentRoot.key);
            System.out.println(key + " > " + currentRoot.key + " Recurring.");
            currentRoot.right = deleteNode(key, currentRoot.right);
        }
        else {
            System.out.println("Current Node: " + currentRoot.key);
            if (isLeaf(currentRoot)) {
                System.out.println("Current Node: " + currentRoot.key);
                System.out.println(currentRoot.key + " is a leaf node! deleting.");
                currentRoot = null;
            }
            else if (currentRoot.right == null) {
                System.out.println("Replacing " + key + " with left child " + currentRoot.left.key);
                Node yeet = currentRoot;
                currentRoot = currentRoot.left;
                yeet = null;
            }
            else if (currentRoot.left == null) {
                System.out.println("Replacing " + key + " with right child " + currentRoot.right.key);
                Node yeet = currentRoot;
                currentRoot = currentRoot.right;
                yeet = null;
            }
            else {
                System.out.println(currentRoot.key + " has two children!");
                System.out.println("replacing with minimum from right child.");
                Node yeet = minimumRight(currentRoot.right);
                currentRoot.key = yeet.key;
                currentRoot.right = deleteNode(yeet.key, currentRoot.right);
            }
        }
        return currentRoot;
    }

    /******traversal functions*******/
    public static void inorderPrint() {
        System.out.println("Printing Inorder Traversal");
        inorder(root);
        System.out.println();
    }
    public static void inorder(Node root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.key + " ");
        inorder(root.right);
    }

    public static void postorderPrint() {
        System.out.println("Printing Postorder Traversal");
        postorder(root);
        System.out.println();
    }
    public static void postorder(Node root) {
        if (root == null) {
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.key + " ");
    }

    public static void preorderPrint() {
        System.out.println("Printing Preorder Traversal");
        preorder(root);
        System.out.println();
    }
    public static void preorder(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.key + " ");
        preorder(root.left);
        preorder(root.right);
    }

    /******CLI commands*******/
    static Boolean flag = true;
    static Scanner userInput = new Scanner(System.in);

    static void keyEntry(int type) {
        String[] tempArray;
        System.out.println("You can enter multiple values separated by a space.");
        System.out.print("Enter desired value: ");
        while (true) {
            String temp = userInput.nextLine();
            tempArray = temp.split(" ");
            try {
                for (String item: tempArray) {
                    Double transformed = Double.parseDouble(item);
                }
                break;
            }
            catch (Exception e) {
                System.out.println("Please enter numbers only.");
            }
        }
        for (String item: tempArray) {
            Double transformed = Double.parseDouble(item);
            if (type == 0) {
                insert(transformed);
            }
            else if (type == 1) {
                delete(transformed);
            }
            else if (type == 2) {
                search(transformed);
            }
            System.out.println();
        }
    }

    /******driver*******/
    public static void main(String[] args) {
        System.out.println("Welcome to Binary Search Tree Demo");
        String menuChoice;
        while (flag) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("0: Insert");
            System.out.println("1: Delete");
            System.out.println("2: Search");
            System.out.println("3: Inorder Traversal");
            System.out.println("4: Preorder Traversal");
            System.out.println("5: Postorder Traversal");
            System.out.println("6: Quit");
            menuChoice = userInput.nextLine();

            switch (menuChoice) {
                case "0":
                    keyEntry(0);
                    break;
                case "1":
                    keyEntry(1);
                    break;
                case "2":
                    keyEntry(2);
                    break;
                case "3":
                    inorderPrint();
                    break;
                case "4":
                    preorderPrint();
                    break;
                case "5":
                    postorderPrint();
                    break;
                case "6":
                    flag = false;
                    System.out.println("Closing.");
                    break;
                default:
                    System.out.println("Please enter one of the given options.");
            }
        }
    }
}
