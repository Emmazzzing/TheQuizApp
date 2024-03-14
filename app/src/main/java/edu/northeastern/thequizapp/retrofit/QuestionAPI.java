package edu.northeastern.thequizapp.retrofit;

import edu.northeastern.thequizapp.model.QuestionList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface QuestionAPI {
    //act as a bridge between android app and the webservice


    @GET("myquizapi.php") //endpoint
    // call 即向网络请求 得到的类型是 question list
    Call<QuestionList> getQuestions();
}
