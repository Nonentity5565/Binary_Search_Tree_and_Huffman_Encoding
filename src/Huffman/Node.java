package com.company;

public class Node {
    private String huffmanCode;
    private int value;
    private Node leftChild;
    private Node rightChild;

    // Constructors
    public Node(int value){
        this.value = value;
        this.leftChild = null;
        this.rightChild = null;
    }

    public Node(int value, Node leftChild, Node rightChild){
        this.value = value;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    // Setter
    public void setHuffmanCode(String huffmanCode){
        this.huffmanCode = huffmanCode;
    }

    // Getter
    public String getHuffmanCode() {
        return huffmanCode;
    }

    public Integer getValue() {
        return value;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    // Method
    public void generateHuffmanCode(String huffmanCode){
        this.setHuffmanCode(huffmanCode);
        if (this.leftChild != null){
            this.leftChild.generateHuffmanCode(huffmanCode + "0");
        }
        if (this.rightChild != null){
            this.rightChild.generateHuffmanCode(huffmanCode + "1");
        }
    }
}