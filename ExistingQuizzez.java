package com.example.project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ExistingQuizzez {
        public static void display(String[] args){
            if(User.userAuthentication(args)){
                System.out.print("{ 'status' : 'ok', 'message' : '[");
                whatIsTheQuestionName();

            }
        }
        public static void whatIsTheQuestionName(){

            try (BufferedReader br = new BufferedReader(new FileReader(("src/main/java/com/example/project/QuizzezList.txt")))) {
                String line;
                String comma = ",";
                String quizzName;
                String foundID;
                //int commaOccurrence;
                if((line = br.readLine()) != null){
                    foundID = line.split(comma)[0];
                    int intID = Integer.parseInt(foundID);
                    quizzName = line.split(comma)[3];
                    String valueOfTruth;
                    if(Quizz.vectorFrecventa.get(intID - 1) == 0){
                        valueOfTruth = "False";
                    }
                    else{
                        valueOfTruth = "True";
                    }
                    System.out.print("{\"quizz_id\" : \"" + foundID + "\", \"quizz_name\" : \"" + quizzName + "\", \"is_completed\" : \"" + valueOfTruth + "\"}");
                }
                while ((line = br.readLine()) != null) {
                    foundID = line.split(comma)[0];
                    //commaOccurrence = countChar(line, ',');
                    int intID = Integer.parseInt(foundID);
                    quizzName = line.split(comma)[3];
                    String valueOfTruth;
                    if(Quizz.vectorFrecventa.get(intID - 1) == 0){
                        valueOfTruth = "False";
                    }
                    else{
                        valueOfTruth = "True";
                    }
                    System.out.print(", {\"quizz_id\" : \"" + foundID + "\", \"quizz_name\" : \"" + quizzName + "\", \"is_completed\" : \"" + valueOfTruth + "\"}");
                }
                System.out.println("]'}");
            }
            catch (IOException e) {
                //exception handling left as an exercise for the reader
            }
        }


}
