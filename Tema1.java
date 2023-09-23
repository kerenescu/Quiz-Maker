package com.example.project;

import java.io.*;
public class Tema1 {

	public static void main(final String[] args)
	{
		if(args == null) {
			System.out.println("Hello world!");
		}
		else if(args[0].equals("-cleanup-all")){
			Cleanups.cleanupUsers();
			Cleanups.cleanupQuestions();
			Cleanups.cleanupQuizzez();
			System.out.println( "{'status' : 'ok', 'message' : 'Cleanup finished successfully'}");
		}
		else if(args[0].equals("-create-user")) {
			User.create(args);
		}
		else if(args[0].equals("-create-question")) {
			Question.create(args);
		}
		else if(args[0].equals("-get-question-id-by-text")) {
			QuestionID.getQuestion(args);
		}
		else if(args[0].equals("-get-all-questions")) {
			ExistingQuestions.display(args);
		}
		else if(args[0].equals("-create-quizz")) {
			Quizz.create(args);
		}
		else if(args[0].equals("-get-quizz-by-name")) {
			Quizz.getQuizz(args);
		}
		else if(args[0].equals(("-get-all-quizzes"))) {
			ExistingQuizzez.display(args);
		}
		else if(args[0].equals("-get-quizz-details-by-id")){
			QuizzID.getQuizzDetailsByID(args);

		}
		else if(args[0].equals("-submit-quizz")){
			SubmitQuizz.submission(args);

		}
		else if(args[0].equals("-delete-quizz-by-id")){
			DeleteQuiz.delete(args);

		}
		else if(args[0].equals("-get-my-solutions")){
			QuizzID.getQuizzDetailsByID(args);

		}

	}
}
