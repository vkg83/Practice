package com.vkg.pactice.smc;


public class Client {
    public static void main(String[] args) {
        Parser p = new Parser(new Printer());
        Lexer l = new Lexer(p);
        l.lexLine("F AVG V 10 F SUM V RAJA V 245 A DATA. A AMOUNT.");
    }
}
