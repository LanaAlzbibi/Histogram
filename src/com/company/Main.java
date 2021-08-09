package com.company;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;



public class Main {


    private static String FILE_LOCATION = "C:\\Users\\lana\\Downloads\\Histogram\\Histogram\\FileProject\\test.txt";
    private static String FILE_LOCATION2 = "C:\\Users\\lana\\Downloads\\Histogram\\Histogram\\FileProject\\newUpperCasedTest.txt";

    public static void main(String[] args) throws IOException {
        File file = new File( FILE_LOCATION2);                      //Importing and scanning file
        Scanner in = new Scanner(file);
        in.useDelimiter("");
        int[] characterCount = new int[65];
        while(in.hasNext()){                                        //creating array 65 long because we use the Ascii numbers from 32-97 (unique symbols + upper cased letters)
            char ch = in.next().charAt(0);
            //System.out.println(ch);                               //printing every character scanned in the file
            for(int i = 0; i < 65; i++){                            //counting each character
                int helper = i+32;
                if(Character.compare((char)helper,ch) == 0){
                    characterCount[i]++;
                }
            }
            if (in.hasNext() == false ) {
                //System.out.println("out of characters");         //if there are no more characters we print that there are no characters anymore in the file

            }
        }


        try {
            FileWriter myWriter = new FileWriter( "C:\\Users\\lana\\Downloads\\Histogram\\Histogram\\FileProject\\newUpperCasedTest.txt");             //filewriter transforming file to fully uppercased
            String s = new String(transform(FILE_LOCATION));
            myWriter.write(s);
            myWriter.close();
            System.out.println("Successfully wrote to the file newUpperCasedTest.txt. Text is now upper cased.");

        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            FileWriter myWriter = new FileWriter("C:\\Users\\lana\\Downloads\\Histogram\\Histogram\\FileProject\\Histogram.txt");                     //filewriter to create a histogram txt
            String s = new String(Arrays.toString(arrayToString(characterCount)));
            myWriter.write(s);
            myWriter.close();
            System.out.println("Successfully wrote to the file Histogram.txt. Histogram is now completed");

        } catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println(transform(FILE_LOCATION));

        //System.out.println(Arrays.toString(arrayToString(characterCount)));

    }


    public static String[] arrayToString(int[] a){                                                          //transforming the number from the characterCount array to stars
        String[] stringArray = new String[a.length];
        for(int i = 0; i < 65 ; i++ ){
            int helper = i+32;
            if(a[i] > 0){
                stringArray[i] = (char)helper + ": " + convertToStars(a[i]) + "\r\n";
            }
            if(a[i] == 0){
                stringArray[i] =(char)helper + ": \r\n";
            }
        }

        return stringArray;
    }


    private static String convertToStars(int num) {                                                         //used a helper from https://stackoverflow.com/questions/13106906/how-to-create-a-histogram-in-java
        StringBuilder builder = new StringBuilder();
        for (int j = 0; j < num; j++) {
            builder.append('*');
        }
        return builder.toString();
    }


    public static StringBuffer transform (String s) throws IOException {                                    //normalise method turning all lower case to upper case
        String content = Files.readString(Path.of(FILE_LOCATION), StandardCharsets.US_ASCII);
        String str1= content;
        StringBuffer newStr=new StringBuffer(str1);

        for(int i = 0; i < str1.length(); i++) {


            //Checks for lower case character
            if(Character.isLowerCase(str1.charAt(i))) {
                //Convert it into upper case using toUpperCase() function
                newStr.setCharAt(i, Character.toUpperCase(str1.charAt(i)));
            }
            //Checks for upper case character
           /* else if(Character.isUpperCase(str1.charAt(i))) {
                //Convert it into upper case using toLowerCase() function
                newStr.setCharAt(i, Character.toLowerCase(str1.charAt(i)));
            }*/
        }
        //System.out.println("String after case conversion : " + newStr);
        return newStr;                                                                                      //

    }
}




