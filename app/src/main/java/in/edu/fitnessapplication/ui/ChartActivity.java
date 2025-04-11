package in.edu.fitnessapplication.ui;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import in.edu.fitnessapplication.R;
import in.edu.fitnessapplication.service.ChartDataService;

public class ChartActivity extends AppCompatActivity {

    private BarChart chartWater, chartFood, chartExercise;
    private ChartDataService chartDataService;

    private int userId = 1; // Replace with the logged-in user's ID

    // Green color scheme
    private final int darkGreen = Color.parseColor("#1E8449");
    private final int mediumGreen = Color.parseColor("#2E7D32");
    private final int lightGreen = Color.parseColor("#66BB6A");
    private final int veryLightGreen = Color.parseColor("#C8E6C9");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        chartWater = findViewById(R.id.chartWater);
        chartFood = findViewById(R.id.chartFood);
        chartExercise = findViewById(R.id.chartExercise);

        chartDataService = new ChartDataService(this);

        loadChart(chartWater, chartDataService.getWeeklyWater(userId), "Water Intake (ml)", mediumGreen, 2000);
        loadChart(chartFood, chartDataService.getWeeklyFood(userId), "Calories (kcal)", lightGreen, 2000);
        loadChart(chartExercise, chartDataService.getWeeklyExercise(userId), "Exercise Duration (min)", darkGreen, 0);
    }

    private void loadChart(BarChart chart, Map<String, Integer> dataMap, String label, int color, int targetLine) {
        List<BarEntry> entries = new ArrayList<>();
        List<String> xLabels = new ArrayList<>();

        int index = 0;
        for (Map.Entry<String, Integer> entry : dataMap.entrySet()) {
            entries.add(new BarEntry(index++, entry.getValue()));
            xLabels.add(entry.getKey());
        }

        BarDataSet dataSet = new BarDataSet(entries, label);
        dataSet.setColor(color);
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(10f);

        BarData barData = new BarData(dataSet);
        barData.setBarWidth(0.9f);

        chart.setData(barData);
        chart.setFitBars(true);

        // Customize appearance
        Description desc = new Description();
        desc.setText(""); // remove default description
        chart.setDescription(desc);

        chart.getLegend().setTextColor(darkGreen);
        chart.setDrawGridBackground(false);
        chart.setDrawBarShadow(false);
        chart.setDrawValueAboveBar(true);

        // X-axis customization
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        xAxis.setTextColor(darkGreen);

        // Y-axis customization
        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setTextColor(darkGreen);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setDrawGridLines(true);
        leftAxis.setGridColor(veryLightGreen);

        // Hide right Y-axis
        chart.getAxisRight().setEnabled(false);

        // Add target line if applicable (water and food)
        if (targetLine > 0) {
            LimitLine line = new LimitLine(targetLine, "Target");
            line.setLineColor(darkGreen);
            line.setLineWidth(1f);
            line.setTextColor(darkGreen);
            line.setTextSize(10f);
            leftAxis.addLimitLine(line);
        }

        chart.animateY(1000);
        chart.invalidate(); // refresh chart
    }
}