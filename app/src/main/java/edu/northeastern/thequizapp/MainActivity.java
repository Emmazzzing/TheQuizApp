package edu.northeastern.thequizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.List;

import edu.northeastern.thequizapp.databinding.ActivityMainBinding;
import edu.northeastern.thequizapp.model.Question;
import edu.northeastern.thequizapp.model.QuestionList;
import edu.northeastern.thequizapp.repository.QuizRepository;
import edu.northeastern.thequizapp.viewmodel.QuizViewModel;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mainBinding;
    QuizViewModel viewModel;
    List<Question> questionList;

    static int result = 0;
    static int totalQuestion = 0;
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Data Binding
        mainBinding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_main
        );

        // Resetting the Scores:
        result = 0;
        totalQuestion = 0;

        // Creating an instance of 'QuizViewModel'
        viewModel = new ViewModelProvider(this)
                .get(QuizViewModel.class);

        // Displaying the First Question:
        DisplayFirstQuestion();

        mainBinding.nextBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayNextQuestion();
            }
        });
    }

    private void DisplayNextQuestion() {
        // finished the quiz
        if (mainBinding.nextBTN.getText().equals("Finish")){
            totalQuestion = questionList.size();
            Intent i = new Intent(MainActivity.this, ResultsActivity.class);
            startActivity(i);
            finish();
        }

        // not finish so display next question
        int selectBtn = mainBinding.radiogroup.getCheckedRadioButtonId();
        if (selectBtn != -1){
            RadioButton select = findViewById(selectBtn);
            //check当前是否答对
            if (select.getText().toString().equals(questionList.get(i).getCorrectOption())){
                result++;
                mainBinding.textView2.setText("Correct Answer: " + result);
            }
            //check是否还有
            if (questionList.size() - i > 1) {
                i++;
                // Displaying the next Questions
                mainBinding.question.setText("Question "+(i+1)+ " : "+
                        questionList.get(i).getQuestion());
                mainBinding.radio1.setText(questionList.get(i).getOption1());
                mainBinding.radio2.setText(questionList.get(i).getOption2());
                mainBinding.radio3.setText(questionList.get(i).getOption3());
                mainBinding.radio4.setText(questionList.get(i).getOption4());
                if (i == questionList.size()-1){
                    mainBinding.nextBTN.setText("Finish");
                }
                mainBinding.radiogroup.clearCheck();
            }

        }else{
            Toast.makeText(this,"You need to make a selection",Toast.LENGTH_SHORT).show();
        }
    }

    private void DisplayFirstQuestion() {
        viewModel.getQuestionListLiveData().observe(this, new Observer<QuestionList>() {
            @Override
            public void onChanged(QuestionList questions) {
                questionList = questions;
                mainBinding.question.setText(questionList.get(0).getQuestion());
                mainBinding.radio1.setText(questionList.get(0).getOption1());
                mainBinding.radio2.setText(questionList.get(0).getOption2());
                mainBinding.radio3.setText(questionList.get(0).getOption3());
                mainBinding.radio4.setText(questionList.get(0).getOption4());
            }
        });
    }


}