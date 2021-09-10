package com.company;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HuffmanCode {
    public static void run(){
        Scanner input = new Scanner(System.in);
        String inputText = "";
        String userInput = "";
        ArrayList<Character> charList = new ArrayList<>();
        ArrayList<Integer> frequencyList = new ArrayList<>();

        while(true) {
            System.out.println("------------------------------------");
            System.out.println("      Huffman Coding Simulator");
            System.out.println("------------------------------------");
            System.out.println("Enter your message to encode (enter exit to quit)");
            inputText = input.nextLine();
            try{
                if (!inputText.toLowerCase().equals("exit")){
                    for (char c: inputText.toCharArray()){
                        if (charList.contains(c)){
                            frequencyList.set(charList.indexOf(c), frequencyList.get(charList.indexOf(c))+1);
                        }
                        else{
                            charList.add(c);
                            frequencyList.add(1);
                        }
                    }

                    ArrayList<CharacterNode> huffmanNodes = generateHuffmanNodes(charList, frequencyList);
                    charList.clear();
                    frequencyList.clear();

                    userInput = "";
                    while (!userInput.equals("5")){
                        printActionMenu();
                        userInput = input.nextLine();
                        System.out.println();
                        switch(userInput){
                            case "1":
                                printHuffmanTable(huffmanNodes);
                                break;
                            case "2":
                                printSizeComparison(huffmanNodes);
                                break;
                            case "3":
                                printASCIIText(inputText);
                                break;
                            case "4":
                                printHuffmanText(inputText, huffmanNodes);
                                break;
                            case "5":
                                break;
                            default:
                                System.out.println("Invalid input, please select a valid option");
                                break;
                        }
                    }
                }
                else{
                    input.close();
                    break;
                }
            }
            catch (Exception e){
                System.out.println("Cannot process empty input, try again.");
            }
        }
    }


    public static void printActionMenu(){
        System.out.print("\n" +
        "Select An Action:\n" +
        "1. Show Huffman Table\n" +
        "2. Show Size Comparison With ASCII\n" +
        "3. Display Text In ASCII\n" +
        "4. Display Text In Huffman\n" +
        "5. Insert New Text\n" +
        "\n"+
        "Enter Your Choice: ");
    }

    public static ArrayList<CharacterNode> generateHuffmanNodes(ArrayList<Character> charList, ArrayList<Integer> frequencyList){
        PriorityQueue<Node> nodeList = new PriorityQueue<>(new NodeSorter());
        ArrayList<CharacterNode> charNodeList = new ArrayList<>();
        Node node1;
        Node node2;

        for (int i = 0; i < charList.size(); i++){
            charNodeList.add(new CharacterNode(charList.get(i), frequencyList.get(i)));
            nodeList.add(charNodeList.get(i));
        }

        while (nodeList.size() > 1){
            node1 = nodeList.remove();
            node2 = nodeList.remove();
            nodeList.add(new Node((node1.getValue()+node2.getValue()), node1, node2));
        }
        
        if (charNodeList.size() > 1){
            nodeList.peek().getLeftChild().generateHuffmanCode("0");
            nodeList.peek().getRightChild().generateHuffmanCode("1");
        }
        else{
            charNodeList.get(0).setHuffmanCode("1");
        }

        return charNodeList;
    }


    public static void printSizeComparison(ArrayList<CharacterNode> huffmanNodes){
        int huffmanTextSize = 0;
        int huffmanTableSize = 0;
        int inputTextSize = 0;
        for(CharacterNode n : huffmanNodes){
            huffmanTextSize += (n.getValue() * n.getHuffmanCode().length());
            huffmanTableSize += (8 + n.getHuffmanCode().length());
            inputTextSize += (n.getValue() * 8);
        }

        System.out.println("------------------------------------");
        System.out.println("           Size Comparison");
        System.out.println("------------------------------------");

        System.out.println("Text Size Using Huffman Coding:");
        System.out.println(huffmanTextSize + " bits");
        System.out.println("Huffman Table Size:");
        System.out.println(huffmanTableSize + " bits");
        System.out.println();
        System.out.println("Message Size Using ASCII:");
        System.out.println(inputTextSize + " bits");
        System.out.println("Message Size Using Huffman Coding (Text + Table):");
        System.out.println((huffmanTableSize + huffmanTextSize) + " bits");
    }


    public static void printHuffmanTable(ArrayList<CharacterNode> huffmanNodes){
        System.out.println("------------------------------------");
        System.out.println("         Huffman Code Table");
        System.out.println("------------------------------------");
        System.out.println("Character | Frequency | ASCII   | Huffman Code");
        for(CharacterNode n : huffmanNodes){
            System.out.printf("%-10s| %-10s|%08d |%s\n", n.getCharacter(), n.getValue(), binary(Character.toString(n.getCharacter()).getBytes(Charset.forName("US-ASCII"))[0]), n.getHuffmanCode());
        }
    }


    public static void printASCIIText(String inputText){
        int textCount = 0;
        byte[] byteArray = inputText.getBytes(Charset.forName("US-ASCII"));

        System.out.println("------------------------------------");
        System.out.println("         ASCII Code Text");
        System.out.println("------------------------------------");

        for (int b : byteArray){
            System.out.printf("%08d ", binary(b));
            textCount++;
            if (textCount % 5 == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }

    public static int binary(int b){
        int i = b;
        String s = "";

        while (i != 0){
            if (i%2 == 0){
                s = "0" + s;
            }
            else{
                s = "1" + s;
            }
            i = i/2;
        }
        return Integer.parseInt(s);
    }

    public static void printHuffmanText(String inputText, ArrayList<CharacterNode> huffmanNodes){
        Hashtable<Character, String> dict = new Hashtable<>();
        int textCount = 0;

        for (CharacterNode node : huffmanNodes){
            dict.put(node.getCharacter(), node.getHuffmanCode());
        }

        System.out.println("------------------------------------");
        System.out.println("         Huffman Code Text");
        System.out.println("------------------------------------");

        for (char c: inputText.toCharArray()){
            System.out.printf("%s ", dict.get(c));
            textCount++;
            if (textCount % 5 == 0) {
                System.out.println();
            }
        }

        System.out.println();
    }
}
