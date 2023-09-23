package com.example.project;

import java.io.File;

public class Cleanups {
    public static void cleanupUsers(){
        File users = new File("src/main/java/com/example/project/UserList.txt");
        users.delete();
        User.id = 0;
    }
    public static void cleanupQuestions(){
        File questions = new File("src/main/java/com/example/project/QuestionList.txt");
        questions.delete();
        Question.id = 0;
    }
    public static void cleanupQuizzez(){
        File quizzes = new File("src/main/java/com/example/project/QuizzezList.txt");
        quizzes.delete();
        Quizz.id = 0;
    }
}
