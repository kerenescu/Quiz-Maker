package com.example.project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class QuizzID extends Quizz{
    public static void getQuizzDetailsByID(String[] args){
        if(User.userAuthentication(args)){
            String delims="'";
            String stringID = args[3].split(delims)[1];
            int id = Integer.parseInt(stringID);
            QuestionID.answerIndex = 0;
            whatIsTheQuestionName(id);
            System.out.println("]'}");

        }
    }
    public static boolean findQuizzByID(String id) {
        try (BufferedReader br = new BufferedReader(new FileReader(("src/main/java/com/example/project/QuizzezList.txt")))) {
            String line;
            String comma = ",";
            String check;
            //String idStringFormat = Integer.toString(id);

            while ((line = br.readLine()) != null) {
                check = line.split(comma)[0];
                if (check.equals(id)) {
                    return true;
                }
            }
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
        return false;
    }
    public static boolean whatIsTheQuestionName(int id){

        try (BufferedReader br = new BufferedReader(new FileReader(("src/main/java/com/example/project/QuizzezList.txt")))) {
            String line;
            String comma = ",";
            String quizzName;
            String foundID;
            String valueOfTruth;
            String questionName = null;
            int questionID;
            String stringQuestionID;
            String questionType;
            int commaOccurrence = 0;
            int index = 4;

            if((line = br.readLine()) != null) {
                foundID = line.split(comma)[0];
                int intID = Integer.parseInt(foundID);
                commaOccurrence = countChar(line, ',');

                if (id == intID) {
                    System.out.print("{'status':'ok','message':'[");
                    //questionName = line.split(comma)[index];
                    if(index < (commaOccurrence + 1)){
                        //System.out.println("BAAAA");
                        questionName = line.split(comma)[index];
                        questionID = QuestionID.idExistingQuestion(questionName);
                        questionType = QuestionID.typeExistingQuestion(questionName);
                        //System.out.print("{\"question-name\":\"" + questionName + "\", \"question_index\":\"" + questionID + "\", \"" + questionType  + ":\"single\", ");
                        System.out.print("{\"question-name\":\"" + questionName + "\", \"question_index\":\"" + questionID + "\", \"question_type\":\"" + questionType + "\", ");
                        QuestionID.answersExistingQuestion(questionName);
                        index ++;
                    }
                    while(index < (commaOccurrence + 1)){
                        //System.out.println("BAAAA");
                        questionName = line.split(comma)[index];
                        questionID = QuestionID.idExistingQuestion(questionName);
                        questionType = QuestionID.typeExistingQuestion(questionName);
                        //System.out.print("{\"question-name\":\"" + questionName + "\", \"question_index\":\"" + questionID + "\", \"" + questionType  + ":\"single\", ");
                        System.out.print(", {\"question-name\":\"" + questionName + "\", \"question_index\":\"" + questionID + "\", \"question_type\":\"" + questionType + "\", ");
                        QuestionID.answersExistingQuestion(questionName);
                        index ++;
                    }

                } else {
                    return false;
                }

                //System.out.print("\"{'status':'ok','message':'[{\\\"question-name\\\":\\\"Cerul este albastru\\\", \\\"question_index\\\":\\\"1\\\", \\\"question_type\\\":\\\"single\\\", \\\"answers\\\":\\\"[{\\\"answer_name\\\":\\\"Yes\\\", \\\"answer_id\\\":\\\"1\\\"}, {\\\"answer_name\\\":\\\"No\\\", \\\"answer_id\\\":\\\"2\\\"}]\\\"}, {\\\"question-name\\\":\\\"Temperatura se poate masura in\\\", \\\"question_index\\\":\\\"2\\\", \\\"question_type\\\":\\\"single\\\", \\\"answers\\\":\\\"[{\\\"answer_name\\\":\\\"C\\\", \\\"answer_id\\\":\\\"3\\\"}, {\\\"answer_name\\\":\\\"L\\\", \\\"answer_id\\\":\\\"4\\\"}]\\\"}]'}\"");
                return true;
            }
            while ((line = br.readLine()) != null) {
                foundID = line.split(comma)[0];
                int intID = Integer.parseInt(foundID);
                quizzName = line.split(comma)[3];

                if (id == intID) {
                    System.out.print("{'status':'ok','message':'[");
                    questionName = line.split(comma)[index];
                    while(index < (commaOccurrence + 1)){
                        questionID = QuestionID.idExistingQuestion(questionName);
                        questionType = QuestionID.typeExistingQuestion(questionName);
                        System.out.print(", {\"question-name\":\"" + questionName + "\", \"question_index\":\"" + questionID + "\", \"question_type\":" + questionType);
                        QuestionID.answersExistingQuestion(questionName);
                    }

                } else {
                    return false;
                }

                //System.out.print("\"{'status':'ok','message':'[{\\\"question-name\\\":\\\"Cerul este albastru\\\", \\\"question_index\\\":\\\"1\\\", \\\"question_type\\\":\\\"single\\\", \\\"answers\\\":\\\"[{\\\"answer_name\\\":\\\"Yes\\\", \\\"answer_id\\\":\\\"1\\\"}, {\\\"answer_name\\\":\\\"No\\\", \\\"answer_id\\\":\\\"2\\\"}]\\\"}, {\\\"question-name\\\":\\\"Temperatura se poate masura in\\\", \\\"question_index\\\":\\\"2\\\", \\\"question_type\\\":\\\"single\\\", \\\"answers\\\":\\\"[{\\\"answer_name\\\":\\\"C\\\", \\\"answer_id\\\":\\\"3\\\"}, {\\\"answer_name\\\":\\\"L\\\", \\\"answer_id\\\":\\\"4\\\"}]\\\"}]'}\"");
                return true;

            }
            System.out.println("]'}");
        }
        catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
        return false;
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
