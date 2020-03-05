package quizApp.quizz;

import java.io.IOException;
import java.util.ArrayList;

public class Output {


    public static void main(String[] args) throws IOException {
        ArrayList<TriviaQuestion> questionListArray = new ArrayList<TriviaQuestion>();
        ArrayList<Categories> categoriesArrayList = new ArrayList<Categories>();

        UrlRequest connect = new UrlRequest();

        questionListArray.addAll(connect.getQuestions(5, 20));


        for (TriviaQuestion i : questionListArray)
            System.out.println(i.getQuestion());
    }
}
