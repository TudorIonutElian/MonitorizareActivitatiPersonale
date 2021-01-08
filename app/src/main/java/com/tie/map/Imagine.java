package com.tie.map;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Column;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;

import java.util.ArrayList;
import java.util.List;

public class Imagine extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private ArrayList<InregistrariChart> inregistrariCharts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagine);

        databaseHelper = new DatabaseHelper(getApplicationContext());
        inregistrariCharts = new ArrayList<InregistrariChart>();

        Cursor c = databaseHelper.getData();
        if(c.moveToFirst()){
            do{
                // Creare inregistrare pentru chart
                InregistrariChart inregistrariChart = new InregistrariChart(c.getInt(0), c.getInt(2));

                // Salvare inregistrare chart in lista
                inregistrariCharts.add(inregistrariChart);
            }while(c.moveToNext());
        }

        AnyChartView anyChartView = findViewById(R.id.any_chart_view);

        Cartesian cartesian = AnyChart.column();

        List<DataEntry> data = new ArrayList<>();
        for(int i = 0; i < inregistrariCharts.size(); i++){
            data.add(new ValueDataEntry(String.valueOf(inregistrariCharts.get(i).getId()), inregistrariCharts.get(i).getKg()));
        }



        Column column = cartesian.column(data);

        column.tooltip()
                .titleFormat("{%X}")
                .position(Position.CENTER_BOTTOM)
                .anchor(Anchor.CENTER_BOTTOM)
                .offsetX(0d)
                .offsetY(5d)
                .format("kg{%Value}{groupsSeparator: }");

        cartesian.animation(true);
        cartesian.title("Valori Kilograme Inregistrari");

        cartesian.yScale().minimum(0d);

        cartesian.yAxis(0).labels().format("Kg {%Value}{groupsSeparator: }");

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
        cartesian.interactivity().hoverMode(HoverMode.BY_X);

        cartesian.xAxis(0).title("ID Inregistrare");
        cartesian.yAxis(0).title("Valoare Kilograme");

        anyChartView.setChart(cartesian);

    }
}