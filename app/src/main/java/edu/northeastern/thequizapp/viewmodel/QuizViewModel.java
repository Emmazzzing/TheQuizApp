package edu.northeastern.thequizapp.viewmodel;

import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import edu.northeastern.thequizapp.model.QuestionList;
import edu.northeastern.thequizapp.repository.QuizRepository;

public class QuizViewModel extends ViewModel {
    QuizRepository repository = new QuizRepository();
    LiveData<QuestionList> questionListLiveData;

    public QuizViewModel() {
        this.questionListLiveData = repository.getQuestionsFromAPI();
    }

    public LiveData<QuestionList> getQuestionListLiveData() {
        return questionListLiveData;
    }
}
