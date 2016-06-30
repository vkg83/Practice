package com.vkg.pactice.string;

public class CountAndSay {
    public String countAndSay(int a) {
        String result = "1";
        for(int i = 2; i <= a; i++) {
            result = nextLevel(result);
        }

        return result;
    }

    private String nextLevel(String str) {
        StringBuilder sb = new StringBuilder();
        char prev = ' ';
        int count = 0;
        for(char ch : str.toCharArray()) {
            if(ch != prev) {
                if(prev != ' ')
                    sb.append(count).append(prev);
                count = 1;
                prev = ch;

            } else {
                count++;
            }
        }

        sb.append(count).append(prev);

        return sb.toString();
    }

    public static void main(String[] args) {
        CountAndSay countAndSay = new CountAndSay();
        System.out.println(countAndSay.countAndSay(4));
    }
}
