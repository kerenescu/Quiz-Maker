package com.example.project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class QuestionID {

    public static int answerIndex = 0;
    public static void getQuestion(String[] args){
        if(User.userAuthentication(args)){
            String delims="'";
            String questionText = args[3].split(delims)[1];
            if(!(Question.checkExistingQuestion(questionText))){
                System.out.println("{ 'status' : 'error', 'message' : 'Question does not exist'}");
            }
            else{
                int idForReturn = idExistingQuestion(questionText);
                System.out.println("{ 'status' : 'ok', 'message' : '" + idForReturn + "'}");
            }

        }
    }
    public static int idExistingQuestion(String questionName) {
        String foundID = null;
        try (BufferedReader br = new BufferedReader(new FileReader(("src/main/java/com/example/project/QuestionList.txt")))) {
            String line;
            String comma = ",";
            String check;

            while ((line = br.readLine()) != null) {
                check = line.split(comma)[3];
                if (check.equals(questionName)) {
                    foundID = line.split(comma)[0];
                }
            }
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }

        int result = Integer.parseInt(foundID);
        return result;
    }
    public static String typeExistingQuestion(String questionName) {
        String foundType = null;
        try (BufferedReader br = new BufferedReader(new FileReader(("src/main/java/com/example/project/QuestionList.txt")))) {
            String line;
            String comma = ",";
            String check;

            while ((line = br.readLine()) != null) {
                check = line.split(comma)[3];
                if (check.equals(questionName)) {
                    foundType = line.split(comma)[4];
                }
            }
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }

        return foundType;
    }
    public static void answersExistingQuestion(String questionName) {
        String foundType = null;
        try (BufferedReader br = new BufferedReader(new FileReader(("src/main/java/com/example/project/QuestionList.txt")))) {
            String line;
            String comma = ",";
            String check;
            int lineLength = 0;
            int actualIndex = 0;


            //answerIndex = 0;
            int hereSouldBeTheAnswer = 5;
            int commaOccurrence = 0;
            String answer = null;
            String answerValue = null;

            while ((line = br.readLine()) != null) {
                lineLength = line.length();
                check = line.split(comma)[3];
                commaOccurrence = countChar(line, ',');

                if (check.equals(questionName)) {
                    System.out.print("\"answers\":\"[");
                    answer = line.split(comma)[hereSouldBeTheAnswer];

                    if(hereSouldBeTheAnswer <= (commaOccurrence - 2)){
                        answerIndex ++;
                        //answerValue = line.split(comma)[hereSouldBeTheAnswer + 1];
                        System.out.print("{\"answer_name\":\"" + answer + "\", \"answer_id\":\"" + answerIndex + "\"}");
                        hereSouldBeTheAnswer += 2;
                        answer = line.split(comma)[hereSouldBeTheAnswer];
                    }
                    while(hereSouldBeTheAnswer <= (commaOccurrence - 2)){
                        System.out.print(", ");
                        answerIndex ++;
                        //answerValue = line.split(comma)[hereSouldBeTheAnswer + 1];
                        System.out.print("{\"answer_name\":\"" + answer + "\", \"answer_id\":\"" + answerIndex + "\"}");
                        hereSouldBeTheAnswer += 2;
                        answer = line.split(comma)[hereSouldBeTheAnswer];
                    }

                    System.out.print(", ");
                    answerIndex ++;
                    //answerValue = line.split(comma)[hereSouldBeTheAnswer + 1];
                    System.out.print("{\"answer_name\":\"" + answer + "\", \"answer_id\":\"" + answerIndex + "\"}");

                    System.out.print("]\"}");
                }
            }
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }


    }
    public static String findQuestionByID(String id) {
        String foundQName = null;
        int idExists = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(("src/main/java/com/example/project/QuestionList.txt")))) {
            String line;
            String comma = ",";
            String check;
            idExists = 0;
            //String idStringFormat = Integer.toString(id);

            while ((line = br.readLine()) != null) {
                check = line.split(comma)[0];
                if (check.equals(id)) {
                    foundQName = line.split(comma)[3];
                    idExists++;
                }
            }
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
        if (idExists == 0){
            foundQName = "notfound";
        }
            return foundQName;
    }
    public static int countChar(String lineLength, char character)
    {
        int count = 0;

        for(int i=0; i < lineLength.length(); i++)
        {    if(lineLength.charAt(i) == character)
            count++;
        }

        return count;
    }
}
