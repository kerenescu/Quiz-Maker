package com.example.project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ExistingQuestions {
    public static void display(String[] args){
        if(User.userAuthentication(args)){
            System.out.print("{ 'status' : 'ok', 'message' : '[");
            whatIsTheQuestionName();
        }
    }
    public static void whatIsTheQuestionName(){

            try (BufferedReader br = new BufferedReader(new FileReader(("src/main/java/com/example/project/QuestionList.txt")))) {
                String line;
                String comma = ",";
                String questionName;
                String foundID;
                if((line = br.readLine()) != null){
                    foundID = line.split(comma)[0];
                    questionName = line.split(comma)[3];
                    System.out.print("{\"question_id\" : \"" + foundID + "\", \"question_name\" : \"" + questionName + "\"}");
                }
                while ((line = br.readLine()) != null) {
                    foundID = line.split(comma)[0];
                    questionName = line.split(comma)[3];
                    System.out.print(", {\"question_id\" : \"" + foundID + "\", \"question_name\" : \"" + questionName + "\"}");
                }
                System.out.println("]'}");
            }
            catch (IOException e) {
                //exception handling left as an exercise for the reader
            }
    }
}
