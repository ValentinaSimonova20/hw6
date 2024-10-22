package org.example;

import lombok.NonNull;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

public class PrefixTree {

    Node root;

    public PrefixTree() {
        this.root = new Node("");
        loadMorseSymbols(this);
    }

    public void add(String symbol, String path) {
        Node current = root;
        for (char curChar: path.toCharArray()) {
            current = current.addSymbol(curChar);
        }
        current.value = symbol;
    }

    /**
     * Декодирует один символ
     */
    public String decode(String word) {
        Node currentNode = root;
        for(char curChar: word.toCharArray()) {
            if(curChar == '.') {
                currentNode = currentNode.left;
            } else {
                currentNode = currentNode.right;
            }
        }
        return currentNode.value;
    }


    /**
     * Кодирует один символ
     */
    public String encode(String letter) {
        return findPath(letter, root);
    }

    private String findPath(String letter, Node element) {
        if(element == null) {
            return "";
        }
        if(element.value.equals(letter)) {
            return String.join("", element.path);
        }
        return findPath(letter, element.left) + findPath(letter, element.right);

    }

    static class Node {

        Node parent;

        Node left;

        Node right;

        @NonNull
        String value;

        List<String> path;

        public Node(String s) {
            this.value = s;
            path = new ArrayList<>();
        }


        Node addSymbol(char curChar) {
            if(curChar == '.') {
                if(this.left == null) {
                    this.left = createNewNode(this, String.valueOf(curChar));
                }
                return this.left;
            } else {
                if(this.right == null) {
                    this.right = createNewNode(this, String.valueOf(curChar));
                }
                return this.right;
            }
        }

        private Node createNewNode(Node currentNode, String currentSymbol) {
            Node newNode= new Node("");
            newNode.parent = currentNode;
            newNode.path.addAll(currentNode.path);
            newNode.path.add(currentSymbol);
            return newNode;
        }
    }

    @SneakyThrows
    private static void loadMorseSymbols(PrefixTree tree) {
        morseSymbolsStorage.forEach(tree::add);
    }

    static Map<String, String> morseSymbolsStorage = Map.ofEntries(
            entry("a", ".-"),
            entry("b", "-..."),
            entry("c", "-.-."),
            entry( "d", "-.."),
            entry("e","."),
            entry("f", "..-."),
            entry( "g", "--."),
            entry( "h", "...."),
            entry("i", ".."),
            entry("j", ".---"),
            entry("k", "-.-"),
            entry("l", ".-.."),
            entry("m", "--"),
            entry("n", "-."),
            entry("o", "---"),
            entry("p", ".--."),
            entry("q", "--.-"),
            entry("r", ".-."),
            entry("s", "..."),
            entry("t", "-"),
            entry("u", "..-"),
            entry("v", "...-"),
            entry("w", ".--"),
            entry("x", "-..-"),
            entry("y", "-.--"),
            entry("z", "--.."),
            entry("1", ".----"),
            entry("2", "..---"),
            entry("3", "...--"),
            entry("4", "....-"),
            entry("5", "....."),
            entry("6", "....-"),
            entry("7", "--..."),
            entry("8", "---.."),
            entry("9", "----."),
            entry("0", "-----")
    );
}
