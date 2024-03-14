package edu.northeastern.thequizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import edu.northeastern.thequizapp.databinding.ActivityResultsBinding;

public class ResultsActivity extends AppCompatActivity {
    ActivityResultsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_results);
        binding.txtAnswer.setText(
                "Your score is: "
                        +MainActivity.result
                        +"/" +MainActivity.totalQuestion);
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ResultsActivity.this, MainActivity.class);
                startActivity(in);
            }
        });
    }
}