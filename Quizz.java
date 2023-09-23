package com.example.project;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Quizz implements Cloneable{
    public static int id = 0;
    static ArrayList<Integer> vectorFrecventa = new ArrayList<Integer>(100000);
    public static int numar = 5;
    public static void create(String[] args){
        if(User.userAuthentication(args)){
            String delims = "'";
            String quizzNameData = args[3].split(delims)[1];
            if(checkExistingQuizzname(quizzNameData)){
                System.out.println("{ 'status' : 'error', 'message' : 'Quizz name already exists'}");
            }
            else if(args.length > 15){
                System.out.println("{ 'status' : 'error', 'message' : 'Quizz has more than 10 questions'}");
            }
            else if(!(addQuizz(args).equals("-1"))){
                System.out.println("{ 'status' : 'error', 'message' : 'Question ID for question " + addQuizz(args) + " does not exist'}");
            }
            else {
                vectorFrecventa.add(0);
                System.out.println("{ 'status' : 'ok', 'message' : 'Quizz added succesfully'}");
            }
        }
    }
    public static boolean checkExistingQuizzname(String quizzName) {
        try (BufferedReader br = new BufferedReader(new FileReader(("src/main/java/com/example/project/QuizzezList.txt")))) {
            String line;
            String comma = ",";
            String check;
            //System.out.println(quizzName);
            while ((line = br.readLine()) != null) {
                check = line.split(comma)[3];
                if (check.equals(quizzName)) {
                    //System.out.println(check);
                    //System.out.println("true");
                    return true;
                }
            }
            //System.out.println("false");
            return false;
        }
        catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
        return false;
    }
    public static String addQuizz(String[] args) {

        id++;
        String delims = "'";
        try (FileWriter fw = new FileWriter("src/main/java/com/example/project/QuizzezList.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            int i = 0;
            for(String index: args){
               if(i > 0){
//                   if(i <= 3) {
//                       out.print("," + ((index.split(delims))[1]));
//                   }
                   if(i > 3) {
                       if(QuestionID.findQuestionByID(((index.split(delims))[1])).equals("notfound")){
                           id = 0;
                           return (index.split(delims))[1];
                       }
                       //out.print("," + QuestionID.findQuestionByID(((index.split(delims))[1])));
                   }
                }
                i ++;
            }

            out.print(id);

            i = 0;
            for(String index: args){
                if(i > 0){
                   if(i <= 3) {
                       out.print("," + ((index.split(delims))[1]));
                   }
                    else {
                        out.print("," + QuestionID.findQuestionByID(((index.split(delims))[1])));
                    }
                }
                i ++;
            }

            out.println();
        }
        catch (IOException e) {
            //exception handling left as an exercise for the reader
        }

        System.out.println();
        return "-1";
    }
//    public static void addValueOfTruth(int truth) {
//        if (id >= numar) {
//            numar *= 2;
//            ArrayList<Integer> vectorClona = (ArrayList)vectorFrecventa.clone();
//            vectorFrecventa = new ArrayList<Integer>(numar);
//            System.arraycopy(vectorClona, 0, vectorFrecventa, 0, numar / 2);
//        }
//
//        vectorFrecventa.set(id, truth);
//        ++id;
//    }
    public static void getQuizz(String[] args){
        if(User.userAuthentication(args)){
            String delims="'";
            String quizNameData = args[3].split(delims)[1];
            if(!(Quizz.checkExistingQuizzname(quizNameData))){
                System.out.println("{ 'status' : 'error', 'message' : 'Quizz does not exist'}");
            }
            else{
                int idForReturn = idExistingQuizz(quizNameData);
                System.out.println("{ 'status' : 'ok', 'message' : '" + idForReturn + "'}");
            }

        }
    }
    public static int idExistingQuizz(String quizName) {
        String foundID = null;
        try (BufferedReader br = new BufferedReader(new FileReader(("src/main/java/com/example/project/QuizzezList.txt")))) {
            String line;
            String comma = ",";
            String check;

            while ((line = br.readLine()) != null) {
                check = line.split(comma)[3];
                if (check.equals(quizName)) {
                    foundID = line.split(comma)[0];
                }
            }
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }

        int result = Integer.parseInt(foundID);
        return result;
    }
}
