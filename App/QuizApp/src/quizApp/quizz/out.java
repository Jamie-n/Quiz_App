package quizApp.quizz;

public class out {


    public static void main(String[] args){
        QuestionController questionController = new QuestionController();
        questionController.setQuestions(10,10,"easy");


        System.out.println(questionController.getCurrentQuestion());

        for(Object o: questionController.getAllAnswers()) {
            System.out.println(o);
        }
    }
}
