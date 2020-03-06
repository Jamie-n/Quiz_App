package quizApp.quizz;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class UrlRequest {

    private static HttpURLConnection connection;


    public ArrayList<TriviaQuestion> getQuestions(int numberOfQs,int cat, String difficultyLevel) {


        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();

        try {
            URL url = new URL("https://opentdb.com/api.php?amount="+numberOfQs+"&category="+cat+"&difficulty"+difficultyLevel);

            responseContent = makeRequest(url);

            responseContent.replace(0, 29, "");


            JSONArray questions = new JSONArray(responseContent.toString());
            ArrayList<TriviaQuestion> questionList = new ArrayList<>();

            for (int i = 0; i < questions.length(); i++) {

                ArrayList<Object> allAnswers = new ArrayList<>();
                JSONObject question = questions.getJSONObject(i);
                String category = question.getString("category");
                String type = question.getString("type");
                String difficulty = question.getString("difficulty");
                String questionAsk = question.getString("question");
                String correctAnswer = question.getString("correct_answer");
                JSONArray incorrectAnswers = question.getJSONArray("incorrect_answers");
                JSONArray answers = question.getJSONArray("incorrect_answers").put(question.getString("correct_answer"));

                for (Object o: answers){
                    allAnswers.add(o);
                }

                TriviaQuestion triviaQuestion = new TriviaQuestion(category, type, difficulty, questionAsk, correctAnswer, incorrectAnswers, allAnswers);
                questionList.add(triviaQuestion);

            }

            return questionList;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Categories> getCategories() throws IOException {
        URL url = new URL("https://opentdb.com/api_category.php");
        StringBuffer responseContent = new StringBuffer();
        responseContent = makeRequest(url);

        responseContent.replace(0, 21, "");


        JSONArray questions = new JSONArray(responseContent.toString());
        ArrayList<Categories> categoriesArrayList = new ArrayList<>();

        for (int i = 0; i < questions.length(); i++) {
            JSONObject question = questions.getJSONObject(i);
            String category = question.getString("name");
            int id = question.getInt("id");

            categoriesArrayList.add(new Categories(category,id));
        }
        return categoriesArrayList;
    }


    public StringBuffer makeRequest(URL url) throws IOException {

        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();

        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();

            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            }
            while ((line = reader.readLine()) != null) {
                responseContent.append(line);
            }
            reader.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        if(responseContent.length() == 0){
            return null;
        }

        return responseContent;
    }

}



