package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mOButton;
    private Button mXButton;
    private TextView mQuestionTextView;
    private TextView mQuestionNumberTextView;

    private int Quiz_MAX = 10;

    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_01, true),
            new Question(R.string.question_02, true),
            new Question(R.string.question_03, true),
            new Question(R.string.question_04, true),
            new Question(R.string.question_05, false),
            new Question(R.string.question_06, false),
            new Question(R.string.question_07, false),
            new Question(R.string.question_08, false),
            new Question(R.string.question_09, false),
            new Question(R.string.question_10, true)
    };

    private int mCurrentIndex = 0;
    private int mScore = 0;

    private void updateQuestion() {
        if (mCurrentIndex < Quiz_MAX) {
            int question = mQuestionBank[mCurrentIndex].getTextResId();
            mQuestionTextView.setText(question);
            String question_num = mCurrentIndex + 1 + "/10";
            mQuestionNumberTextView.setText(question_num);
        }else{
            clearQuiz();
        }
    }

    private void checkAnswer(boolean userPressedButton) {

        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messageId = 0;
        mCurrentIndex++;

        if (userPressedButton == answerIsTrue) {
            messageId = R.string.correct_toast;
            mScore += 10;
            updateQuestion();
        } else {
            messageId = R.string.incorrect_toast;
            updateQuestion();
        }

        Toast.makeText(this,
                messageId,
                Toast.LENGTH_SHORT).show();

    }

    private void clearQuiz() {
        mQuestionTextView.setText(String.valueOf(mScore));
        mQuestionTextView.setTextSize(55);
        mQuestionNumberTextView.setText("Clear");
        checkScore();
        Toast.makeText(MainActivity.this,
                R.string.clear_toast,
                Toast.LENGTH_SHORT).show();
    }

    private void checkScore() {
        if (mScore <= 20) {
            mQuestionTextView.setTextColor(Color.parseColor("#FF7474"));
        } else if (mScore > 20 && mScore <= 50) {
            mQuestionTextView.setTextColor(Color.parseColor("#F37229"));
        } else if (mScore > 50 && mScore <= 80) {
            mQuestionTextView.setTextColor(Color.parseColor("#DFA533"));
        } else if (mScore > 80){
            mQuestionTextView.setTextColor(Color.parseColor("#52B230"));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuestionTextView = (TextView) findViewById(R.id.question_text);
        mQuestionNumberTextView = (TextView) findViewById(R.id.quiz_number);

        mOButton = (Button) findViewById(R.id.o_button);
        mOButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentIndex < Quiz_MAX) {
                    checkAnswer(true);
                } else {
                    clearQuiz();
                }
            }
        });
        mXButton = (Button) findViewById(R.id.x_button);
        mXButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentIndex < Quiz_MAX) {
                    checkAnswer(false);
                } else {
                    clearQuiz();
                }
            }
        });

        updateQuestion();

    }

}