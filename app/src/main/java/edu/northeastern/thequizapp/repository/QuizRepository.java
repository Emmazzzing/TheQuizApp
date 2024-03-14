package edu.northeastern.thequizapp.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import edu.northeastern.thequizapp.model.QuestionList;
import edu.northeastern.thequizapp.retrofit.QuestionAPI;
import edu.northeastern.thequizapp.retrofit.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizRepository {

    private QuestionAPI questionAPI;

    public QuizRepository() {
        this.questionAPI = new RetrofitInstance().getRetrofitInstance().create(QuestionAPI.class);
    }
    public LiveData<QuestionList> getQuestionsFromAPI(){
        MutableLiveData<QuestionList> list = new MutableLiveData<>();
        Call<QuestionList> respose = questionAPI.getQuestions();
        respose.enqueue(new Callback<QuestionList>() {
            @Override
            public void onResponse(Call<QuestionList> call, Response<QuestionList> response) {
                QuestionList questions = response.body();
                list.setValue(questions);
            }

            @Override
            public void onFailure(Call<QuestionList> call, Throwable t) {

            }
        });
        return list;
    }
}
