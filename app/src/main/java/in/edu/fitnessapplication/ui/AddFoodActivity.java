package in.edu.fitnessapplication.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import in.edu.fitnessapplication.R;
import in.edu.fitnessapplication.repository.FoodRepository;

public class AddFoodActivity extends AppCompatActivity {

    private EditText edtFoodName, edtCalories;
    private Button btnSaveFood;
    private FoodRepository foodRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        edtFoodName = findViewById(R.id.edtFoodName);
        edtCalories = findViewById(R.id.edtCalories);
        btnSaveFood = findViewById(R.id.btnSaveFood);
        foodRepository = new FoodRepository(this);

        btnSaveFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String foodName = edtFoodName.getText().toString().trim();
                String caloriesStr = edtCalories.getText().toString().trim();

                if (!foodName.isEmpty() && !caloriesStr.isEmpty()) {
                    int calories = Integer.parseInt(caloriesStr);

                    // Providing dummy values for id and category
                    int foodId = 0; // or generate a unique one if required
                    String foodCategory = "General";

                    long result = foodRepository.addFood(foodId, foodName, foodCategory, calories);

                    if (result != -1) {
                        Toast.makeText(AddFoodActivity.this, "Food saved", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(AddFoodActivity.this, "Error saving food", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AddFoodActivity.this, "Enter all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
