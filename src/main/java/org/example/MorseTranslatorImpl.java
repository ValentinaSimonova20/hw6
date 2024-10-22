package org.example;


public class MorseTranslatorImpl implements MorseTranslator{

    private final PrefixTree tree;

    public MorseTranslatorImpl() {
        this.tree = new PrefixTree();
    }

    @Override
    public String decode(String morseCode) {
        StringBuilder result = new StringBuilder();
        for(String word : morseCode.split("/")) {
            for(String symbol : word.split(" ")) {
                result.append(tree.decode(symbol));
            }
            result.append(" ");
        }
        return result.toString().trim();
    }

    @Override
    public String encode(String source) {
        StringBuilder result = new StringBuilder();
        for(String word : source.toLowerCase().split(" ")) {
            for(String symbol : word.split("")) {
                result.append(tree.encode(symbol)).append(" ");
            }
            result.append("/ ");
        }

        return result.substring(0, result.length() - 3);
    }


}
