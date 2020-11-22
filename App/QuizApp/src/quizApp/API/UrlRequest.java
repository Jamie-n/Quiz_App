package quizApp.API;

import org.apache.commons.lang3.StringEscapeUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * A helper utility to pull trivia questions from the API
 *
 *
 *
 * @author Jamie N
 *
 */

public class UrlRequest {

    private static HttpURLConnection connection;

    /**
     * Pulls the questions directly from the API
     *
     * @param numberOfQs Number of questions to pull
     * @param cat The category ID of the question
     * @param difficultyLevel The level of difficulty of the questions
     * @return an arrayList of TrivaQuestion
     */


    public ArrayList<TriviaQuestion> getQuestions(int numberOfQs,int cat, String difficultyLevel) {


        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();

        try {
            URL url = new URL("https://opentdb.com/api.php?amount="+numberOfQs+"&category="+cat+"&difficulty="+difficultyLevel);
            System.out.println(url.toString());

            responseContent = makeRequest(url);

            responseContent.replace(0, 29, "");


            JSONArray questions = new JSONArray(responseContent.toString());
            ArrayList<TriviaQuestion> questionList = new ArrayList<>();

            for (int i = 0; i < questions.length(); i++) {

                JSONObject question = questions.getJSONObject(i);
                String category = question.getString("category");
                String type = question.getString("type");
                String difficulty = question.getString("difficulty");
                String questionAsk = StringEscapeUtils.unescapeHtml4(question.getString("question"));
                String correctAnswer = StringEscapeUtils.unescapeHtml4(question.getString("correct_answer"));
                JSONArray tempIncorrect = question.getJSONArray("incorrect_answers");

                ArrayList<String> incorrectAnswers = new ArrayList<>();

                if(tempIncorrect != null){
                    for(int e = 0; e < tempIncorrect.length() ; e ++){
                        incorrectAnswers.add(StringEscapeUtils.unescapeHtml4(tempIncorrect.getString(e)));
                    }
                }

                questionList.add(new TriviaQuestion(category, type, difficulty, questionAsk, correctAnswer, incorrectAnswers));

            }

            return questionList;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * Pulls a category ID list from the API
     *
     * @return An array of Category objects
     * @throws IOException if a connection could not be established
     */

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

    /**
     * Makes the request to the API
     *
     * @param url the URL to make the request to
     * @return the response from the server
     * @throws IOException if connection could not be established
     *
     */


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



