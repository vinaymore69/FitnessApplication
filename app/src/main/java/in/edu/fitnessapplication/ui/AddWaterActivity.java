package in.edu.fitnessapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import in.edu.fitnessapplication.util.SessionManager;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import in.edu.fitnessapplication.R;
import in.edu.fitnessapplication.repository.WaterRepository;

public class AddWaterActivity extends AppCompatActivity {

    private EditText edtWaterAmount;
    private Button btnSaveWater;
    private WaterRepository waterRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_water);

        edtWaterAmount = findViewById(R.id.edtWaterAmount);
        btnSaveWater = findViewById(R.id.btnSaveWater);
        waterRepository = new WaterRepository(this);

        SessionManager sessionManager = new SessionManager(this);
        int userId = sessionManager.getUserId();

        if (userId == -1) {
            Toast.makeText(this, "Please login first", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        btnSaveWater.setOnClickListener(v -> {
            String amountStr = edtWaterAmount.getText().toString().trim();

            if (!amountStr.isEmpty()) {
                int amount = Integer.parseInt(amountStr);
                String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

                long result = waterRepository.addWater(userId, currentTime, amount); // pass userId from session

                if (result != -1) {
                    Toast.makeText(this, "Water intake saved", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Error saving data", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Please enter amount", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
