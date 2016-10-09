package com.vkg.pactice.other;

public class TryCatchFinally {

        public static void main(String [] args)

        {

            try {

                int i = 42;
                byte b = 1;
                //b = b * 50;

                System.out.println(~i);
                badMethod();

                System.out.print("A");

            }

            catch (Exception ex)

            {

                System.out.print("B");

            }

            finally

            {

                System.out.print("C");

            }

            System.out.print("D");

        }

    public static void badMethod()

    {

        throw new Error(); /* Line 22 */

    }

}