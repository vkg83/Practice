package com.vkg.pactice.trees;

import java.util.ArrayList;
import java.util.Arrays;
/*

input: ["zebra", "dog", "duck", "dot"]

Now we will build prefix tree and we will also store count of characters

                root
                /|
         (d, 3)/ |(z, 1)
              /  |
          Node1  Node2
           /|        \
     (o,2)/ |(u,1)    \(e,1)
         /  |          \
   Node1.1  Node1.2     Node2.1
      | \         \            \
(g,1) |  \ (t,1)   \(c,1)       \(b,1)
      |   \         \            \
    Leaf Leaf       Node1.2.1     Node2.1.1
    (dog)  (dot)        \                  \
                         \(k, 1)            \(r, 1)
                          \                  \
                          Leaf               Node2.1.1.1
                          (duck)                       \
                                                        \(a,1)
                                                         \
                                                         Leaf
                                                         (zebra)

Now, for every leaf / word , we find the character nearest to the root with frequency as 1.
The prefix that the path from root to this character corresponds to, is the representation of the word.

 */

public class OrderedTrie {
    class Data {
        int freq;
        char ch;
        TrieNode node;
        Data(char ch) {
            this.ch = ch;
            node = new TrieNode();
        }
    }
    class TrieNode {
        ArrayList<Data> children = new ArrayList<>();

        public TrieNode insert(final char ch) {
            Data data = null;
            for (Data d : children) {
                if(d.ch == ch) {
                    data = d;
                }
            }

            if(data == null) {
                data = new Data(ch);
                children.add(data);
            }

            data.freq++;

            return data.node;
        }
    }
    public ArrayList<String> prefix(ArrayList<String> a) {
        TrieNode root = new TrieNode();
        for(String str : a) {
            insertWord(root, str);
        }

        final ArrayList<StringBuilder> uniquePre = findUniquePre(root);
        final ArrayList<String> result = new ArrayList<>();
        for (StringBuilder str : uniquePre) {
            result.add(str.toString());
        }
        return result;
    }

    private void insertWord(final TrieNode root, final String str) {
        TrieNode node = root;
        for (char ch : str.toCharArray()) {
            node = node.insert(ch);
        }
    }

    private ArrayList<StringBuilder> findUniquePre(TrieNode node) {
        ArrayList<StringBuilder> result = new ArrayList<StringBuilder>();
        for(Data child : node.children) {
            if(child.freq > 1) {
                for (StringBuilder str : findUniquePre(child.node)) {
                    result.add(str.insert(0, child.ch));
                }
            } else {
                final StringBuilder sb = new StringBuilder();
                sb.append(child.ch);
                result.add(sb);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        OrderedTrie t = new OrderedTrie();
        ArrayList<String> input = new ArrayList<String>(Arrays.asList("zebra", "dog", "duck", "dove"));
        final ArrayList<String> prefix = t.prefix(input);
        System.out.println(prefix);
    }
}
