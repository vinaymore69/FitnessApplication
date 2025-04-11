package in.edu.fitnessapplication.ui;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import in.edu.fitnessapplication.R;
import in.edu.fitnessapplication.repository.ExerciseRepository;

public class AddExerciseActivity extends AppCompatActivity {

    private EditText edtExerciseName, edtDuration;
    private Button btnSaveExercise;
    private ExerciseRepository exerciseRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);

        edtExerciseName = findViewById(R.id.edtExerciseName);
        edtDuration = findViewById(R.id.edtDuration);
        btnSaveExercise = findViewById(R.id.btnSaveExercise);
        exerciseRepository = new ExerciseRepository(this);

        btnSaveExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtExerciseName.getText().toString().trim();
                String durationStr = edtDuration.getText().toString().trim();

                if (!name.isEmpty() && !durationStr.isEmpty()) {
                    try {
                        int duration = Integer.parseInt(durationStr);

                        int userId = 1; // maybe get this from shared preferences or intent
                        String exerciseType = "Cardio"; // replace with actual input if available
                        int caloriesBurned = duration * 5; // just an example logic

                        long success = exerciseRepository.addExercise(userId, name, exerciseType, duration, caloriesBurned);

                        if (success != -1) {
                            Toast.makeText(AddExerciseActivity.this, "Exercise saved", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(AddExerciseActivity.this, "Error saving exercise", Toast.LENGTH_SHORT).show();
                        }
                    } catch (NumberFormatException e) {
                        Toast.makeText(AddExerciseActivity.this, "Invalid duration", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AddExerciseActivity.this, "Enter all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}

