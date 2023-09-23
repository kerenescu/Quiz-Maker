package com.example.project;

import java.io.*;
import java.util.Arrays;
import java.util.*;
import java.lang.*;
import java.io.InputStreamReader;


public class Question {
    public static int id = 0;
    public static void create(String[] args){

        if(User.userAuthentication(args)){
            String delims = "'";
            String usernameData = (args[1].split(delims))[1];
            String passwordData = (args[2].split(delims))[1];
            String questionName = (args[3].split(delims))[1];
            //System.out.println(questionName);
            checkExistingQuestion(questionName);
            if(checkExistingQuestion(questionName)){
                System.out.println("{ 'status' : 'error', 'message' : 'Question already exists'}");
            }
            else if(args.length == 5){
                System.out.println("{ 'status' : 'error', 'message' : 'No answer provided'}");
            }
            else if(args.length == 7){
                System.out.println("{ 'status' : 'error', 'message' : 'Only one answer provided'}");
            }
            else if(noText(args)){
                System.out.println("{ 'status' : 'error', 'message' : 'No question text provided'}");
            }
            else if(dupe(args)){
                System.out.println("{ 'status' : 'error', 'message' : 'Same answer provided more than once'}");
            }
            else if(noDescriptionOrNoFlag(args) != 0) {
                if (oneDescriptionTwoFlag(args) == 2) {
                    if (noDescriptionOrNoFlag(args) == 1) {
                        System.out.println("{ 'status' : 'error', 'message' : 'Answer 1 has no answer description'}");
                    } else if (noDescriptionOrNoFlag(args) == 2) {
                        System.out.println("{ 'status' : 'error', 'message' : 'Answer 2 has no answer description'}");
                    } else if (noDescriptionOrNoFlag(args) == 3) {
                        System.out.println("{ 'status' : 'error', 'message' : 'Answer 3 has no answer description'}");
                    } else if (noDescriptionOrNoFlag(args) == 4) {
                        System.out.println("{ 'status' : 'error', 'message' : 'Answer 4 has no answer description'}");
                    } else {
                        System.out.println("{ 'status' : 'error', 'message' : 'Answer 5 has no answer description'}");
                    }
                }
                else {
                    if (noDescriptionOrNoFlag(args) == 1) {
                        System.out.println("{ 'status' : 'error', 'message' : 'Answer 1 has no answer correct flag'}");
                    } else if (noDescriptionOrNoFlag(args) == 2) {
                        System.out.println("{ 'status' : 'error', 'message' : 'Answer 2 has no answer correct flag'}");
                    } else if (noDescriptionOrNoFlag(args) == 3) {
                        System.out.println("{ 'status' : 'error', 'message' : 'Answer 3 has no answer correct flag'}");
                    } else if (noDescriptionOrNoFlag(args) == 4) {
                        System.out.println("{ 'status' : 'error', 'message' : 'Answer 4 has no answer correct flag'}");
                    } else {
                        System.out.println("{ 'status' : 'error', 'message' : 'Answer 5 has no answer correct flag'}");
                    }
                }
            }
            else if(!singleAnswerCheck(args)){
                System.out.println("{ 'status' : 'error', 'message' : 'Single correct answer question has more than one correct answer'}");
            }
            else{
                System.out.println("{ 'status' : 'ok', 'message' : 'Question added successfully'}");
                addQuestion(usernameData, passwordData, questionName, args);
            }
        }
    }
    public static boolean checkExistingQuestion(String questionName) {
        try (BufferedReader br = new BufferedReader(new FileReader(("src/main/java/com/example/project/QuestionList.txt")))) {
            String line;
            String comma = ",";
            String check;
            while ((line = br.readLine()) != null) {
                check = line.split(comma)[3];
                if (check.equals(questionName)) {
                    return true;
                }
            }
            return false;
        }
        catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
        return false;
    }
    public static void addQuestion(String username, String password, String question, String[] args) {

        id++;
        String delims = "'";
        try (FileWriter fw = new FileWriter("src/main/java/com/example/project/QuestionList.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            out.print(id);

            String type = (args[4].split(delims))[1];
            int i = 0;
            for(String index: args){
                //out.println("," + (index.split(delims))[1]);
                if(i > 0){
                    out.print("," + ((index.split(delims))[1]));
                }
                i ++;
            }
            out.println();
        }
        catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }
    public static boolean compareText(String myString, String givenString){
        if(myString.equals(givenString))
            return true;
        return false;
    }
    public static boolean noText(String[] args){
        String delims = "'";
        String textPlace = (args[3].split(delims))[0];
        if(compareText(textPlace, "-text ")){
            return false;
        }
        return true;
    }
    public static boolean singleAnswerCheck(String[] args){
        String delims = "'";
        String typeYeah = (args[4].split(delims))[1];
        if(typeYeah.equals("single")){
            //System.out.println("single, intr-adevar");
            int i = 0, count = 0;
            //if(compareText(args[5].split(delims)[0],"-answer-1 ")){
                for(String index: args){
                    if((i > 5) && (i % 2 == 0)){
                        if((index.split(delims))[1].equals("1")){
                            count ++;
                        }
                    }
                    i ++;
                }
            //}
            if(count == 1){
                return true;
            }
            return false;
        }
        return false;
    }
    public static boolean dupe(String[] args){
        int i = 0, j = 0;
        String delims = "'";
        String[] insertAnswers = new String[5];
        for(String index: args){
            //out.println("," + (index.split(delims))[1]);
            if((i > 4) && (i % 2 == 1)){
                insertAnswers[j] = index.split(delims)[1];
                j ++;
            }
            i ++;
        }
        i = 0; j = 0;
        int k = 0;
        for(String index : args){
            if((k > 4) && (k % 2 == 1))
                for(i = 0; i < insertAnswers.length - 1; i ++)
                    for(j = i + 1; j < insertAnswers.length; j ++)
                        if((insertAnswers[i] != null) && (insertAnswers[i].equals(insertAnswers[j])))
                            return true;
            k ++;
        }

        return false;
    }
    public static int noDescriptionOrNoFlag(String[] args){
        int i = 0, j = 0, answerNo = 0;
        String delims = "'";
        String descriptionPlace, substringDescriptionPlace, correctnessPlace, substringCorrectnessPlace;
        for (String index : args) {
            //out.println("," + (index.split(delims))[1]);
            if ((i > 4) && (i % 2 == 1) && ((i + 1) < args.length)) {
                answerNo = 1;
                descriptionPlace = (args[i].split(delims))[0];
                substringDescriptionPlace = descriptionPlace.substring(0, Math.min(descriptionPlace.length(), 9));
                correctnessPlace = (args[i + 1].split(delims))[0];
                substringCorrectnessPlace = correctnessPlace.substring(0, Math.min(correctnessPlace.length(), 9));
                if (!(compareText(substringDescriptionPlace, substringCorrectnessPlace))) {
                    return answerNo;
                }
            }
            i = i + 1;
        }
        return 0;
    }
    public static int oneDescriptionTwoFlag(String[] args){
        int i = 0, j = 0, answerNo = 0;
        String delims = "'";
        String descriptionPlace, substringDescriptionPlace, correctnessPlace, substringCorrectnessPlace;
            for (String index : args) {
                //out.println("," + (index.split(delims))[1]);
                if ((i > 4) && (i % 2 == 1) && ((i + 1) < args.length)) {
                    answerNo = 1;
                    descriptionPlace = (args[i].split(delims))[0];
                    if(descriptionPlace.length() == 21){
                        return 2;
                    }
                    //substringDescriptionPlace = descriptionPlace.substring(0, Math.min(descriptionPlace.length(), 9));
                    correctnessPlace = (args[i + 1].split(delims))[0];
                    if(correctnessPlace.length() == 9){

                        return 1;
                    }
                    //substringCorrectnessPlace = correctnessPlace.substring(0, Math.min(correctnessPlace.length(), 9));
                }
                i = i + 1;
            }
        return 0;
    }
}
