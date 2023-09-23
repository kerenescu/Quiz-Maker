package com.example.project;

public class SubmitQuizz {
    public static void submission(String[] args){
        if(User.userAuthentication(args)){
            String delims="'";

            if(args.length <= 3){
                System.out.println("{ 'status' : 'error', 'message' : 'No quizz identifier was provided'}");
            }
            else {
                String quizID = args[3].split(delims)[1];
                if(QuizzID.findQuizzByID(quizID) == false){
                    System.out.println("{ 'status' : 'error', 'message' : 'No quiz was found'}");
                }
            }

        }
    }
}
