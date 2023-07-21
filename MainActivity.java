package com.example.myapplication;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText[] editTextMarks = new EditText[8];
    private Button btnCalculate;
    private Button btnreset;
    private TextView tvSGPA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editTextMarks[0] = findViewById(R.id.editText1);
        editTextMarks[1] = findViewById(R.id.editText2);
        editTextMarks[2] = findViewById(R.id.editText3);
        editTextMarks[3] = findViewById(R.id.editText4);
        editTextMarks[4] = findViewById(R.id.editText5);
        editTextMarks[5] = findViewById(R.id.editText6);
        editTextMarks[6] = findViewById(R.id.editText7);
        editTextMarks[7] = findViewById(R.id.editText8);
        btnCalculate = findViewById(R.id.btnCalculate);
        btnreset=findViewById(R.id.btnReset);
        tvSGPA = findViewById(R.id.tvSGPA);
        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetEditTextFields();
            }
        });

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateSGPA(editTextMarks, tvSGPA);
            }
    });
    }
        private void calculateSGPA(EditText[] editTextMarks, TextView tvSGPA) {
            double[] gradePoints = {10, 9, 8, 7, 6, 5, 4, 0};
            int[] marksRanges = {90, 80, 70, 60, 50, 45, 40, 0};
            int[] creditPoints = {4, 4, 3, 1, 1, 1, 3, 3};

            int totalCredits = 0;
            double totalGradePoints = 0;

            for (int i = 0; i < editTextMarks.length; i++) {
                String marksStr = editTextMarks[i].getText().toString();
                int marks = Integer.parseInt(marksStr.isEmpty() ? "0" : marksStr);
                int credits = creditPoints[i];

                for (int j = 0; j < marksRanges.length; j++) {
                    if (marks >= marksRanges[j]) {
                        totalGradePoints += credits * gradePoints[j];
                        break;
                    }
                }

                totalCredits += credits;
            }

            double sgpa = totalGradePoints / totalCredits;

            if (!Double.isNaN(sgpa)) {
                tvSGPA.setText(String.format("SGPA: %.2f", sgpa));
            } else {
                tvSGPA.setText("Please enter valid marks!");
            }
        }

    private void resetEditTextFields() {
        editTextMarks[0].setText("");
        editTextMarks[1].setText("");
        editTextMarks[2].setText("");
        editTextMarks[3].setText("");
        editTextMarks[4].setText("");
        editTextMarks[5].setText("");
        editTextMarks[6].setText("");
        editTextMarks[7].setText("");
        tvSGPA.setText("SGPA:");

    }

}

