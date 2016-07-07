package com.vkg.pactice.trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
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

public class Trie {
    class TrieNode {
        int freq;
        Map<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    }
    public ArrayList<String> prefix(ArrayList<String> a) {
        TrieNode root = new TrieNode();
        for(String str : a) {
            TrieNode node = root;
            root.freq++;
            for(char ch : str.toCharArray()) {
                if(!node.children.containsKey(ch)) {
                    node.children.put(ch, new TrieNode());
                }

                node = node.children.get(ch);
                node.freq++;
            }
        }

        final ArrayList<String> result = new ArrayList<>();
        for (String str : a) {
            result.add(findUniquePre(root, str).toString());
        }
        return result;
    }

    private StringBuilder findUniquePre(TrieNode node, String str) {
        StringBuilder result = new StringBuilder();
        int i = 0;
        while(node.freq > 1) {
            final char ch = str.charAt(i);
            result.append(ch);
            node = node.children.get(ch);
            i++;
        }

        return result;
    }

    public static void main(String[] args) {
        Trie t = new Trie();
        ArrayList<String> input = new ArrayList<String>(Arrays.asList("zebra", "dog", "duck", "dove"));
        final ArrayList<String> prefix = t.prefix(input);
        System.out.println(prefix);
    }
}
