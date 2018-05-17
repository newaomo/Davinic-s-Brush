package com.turing.newaomo.davinsbrush.activity.other;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.turing.newaomo.davinsbrush.R;

import java.util.ArrayList;

public class ChartShowActivity extends AppCompatActivity {

    private PieChart pieChart;
    private PieChart pieChart2;

    private int repairCount[] = new int[5];
    private int repairCount2[] = new int[5];



    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    Description description =new Description();
                    description.setText("各种模式使用情况");
                    description.setTextColor(Color.BLACK);
                    description.setTextSize(40);
                    pieChart.setDrawCenterText(false);  //饼状图中间文字不显示
                    pieChart.setDrawEntryLabels(false);
                    pieChart.setDescription(description);
                    pieChart.setDrawHoleEnabled(true);    //设置实心
                    pieChart.setRotationAngle(90); // 初始旋转角度
                    pieChart.setHoleRadius(60f);  //半径
                    pieChart.setTransparentCircleRadius(64f); // 半透明圈
                    pieChart.setUsePercentValues(true);  //显示成百分比
                    Legend mLegend = pieChart.getLegend();  //设置比例图
//        mLegend.setForm(Legend.LegendForm.LINE);  //设置比例图的形状，默认是方形
                    mLegend.setXEntrySpace(7f);
                    mLegend.setYEntrySpace(5f);

                    pieChart.animateXY(1000, 1000);  //设置动画
                    ArrayList<PieEntry> yValues = new ArrayList<PieEntry>();
                    yValues.add(new PieEntry(repairCount[0], "新闻"));
                    yValues.add(new PieEntry(repairCount[1], "日志"));
                    yValues.add(new PieEntry(repairCount[2], "个人"));
                    yValues.add(new PieEntry(repairCount[3], "日常"));
                    yValues.add(new PieEntry(repairCount[4], "简约"));
                    //3、y轴数据
                    PieDataSet pieDataSet = new PieDataSet(yValues, "");
                    pieDataSet.setSliceSpace(0f); //设置个饼状图之间的距离
                    //4、设置颜色
                    ArrayList<Integer> colors = new ArrayList<Integer>();
                    colors.add(Color.rgb(232, 76, 61));
                    colors.add(Color.rgb(53, 152, 219));
                    colors.add(Color.rgb(241, 196, 15));
                    colors.add(Color.rgb(45, 204, 112));
                    colors.add(Color.rgb(1, 150, 112));

                    pieDataSet.setColors(colors);
                    //5、 设置数据
                    PieData pieData = new PieData(pieDataSet);
                    DisplayMetrics metrics = getResources().getDisplayMetrics();
                    float px = 5 * (metrics.densityDpi / 160f);
                    pieDataSet.setSelectionShift(px); // 选中态多出的长度
                    pieData.setValueFormatter(new PercentFormatter());//显示百分比
                    //6、去掉比例尺和说明
//        Legend legend = pieChart.getLegend();//下标说明，false
//        legend.setEnabled(false);
                    pieChart.setDescription(description);
                    //7、显示百分比
                    pieData.setValueFormatter(new PercentFormatter());
                    //8、显示
                    pieChart.setData(pieData);


                    Description description2 =new Description();
                    description2.setText("个人图库占比情况");
                    description2.setTextColor(Color.BLACK);
                    description2.setTextSize(40);
                    pieChart2.setDrawCenterText(false);  //饼状图中间文字不显示
                    pieChart2.setDrawEntryLabels(false);
                    pieChart2.setDescription(description);
                    pieChart2.setDrawHoleEnabled(true);    //设置实心
                    pieChart2.setRotationAngle(90); // 初始旋转角度
                    pieChart2.setHoleRadius(60f);  //半径
                    pieChart2.setTransparentCircleRadius(64f); // 半透明圈
                    pieChart2.setUsePercentValues(true);  //显示成百分比
                    Legend mLegend2 = pieChart.getLegend();  //设置比例图
//        mLegend.setForm(Legend.LegendForm.LINE);  //设置比例图的形状，默认是方形
                    mLegend2.setXEntrySpace(7f);
                    mLegend2.setYEntrySpace(5f);

                    pieChart2.animateXY(1000, 1000);  //设置动画
                    ArrayList<PieEntry> yValues2 = new ArrayList<PieEntry>();
                    yValues2.add(new PieEntry(repairCount2[0], "山水风景"));
                    yValues2.add(new PieEntry(repairCount2[1], "城市交通"));
                    yValues2.add(new PieEntry(repairCount2[2], "美食餐饮"));
                    yValues2.add(new PieEntry(repairCount2[3], "星空夜景"));
                    yValues2.add(new PieEntry(repairCount2[4], "田园山庄"));
                    //3、y轴数据
                    PieDataSet pieDataSet2 = new PieDataSet(yValues2, "");
                    pieDataSet2.setSliceSpace(0f); //设置个饼状图之间的距离
                    //4、设置颜色
                    ArrayList<Integer> colors2 = new ArrayList<Integer>();
                    colors2.add(Color.rgb(232, 76, 61));
                    colors2.add(Color.rgb(53, 152, 219));
                    colors2.add(Color.rgb(1, 150, 112));
                    colors2.add(Color.rgb(241, 196, 15));
                    colors2.add(Color.rgb(45, 204, 112));

                    pieDataSet2.setColors(colors);
                    //5、 设置数据
                    PieData pieData2 = new PieData(pieDataSet2);
                    DisplayMetrics metrics2 = getResources().getDisplayMetrics();
                    float px2 = 5 * (metrics.densityDpi / 160f);
                    pieDataSet.setSelectionShift(px2); // 选中态多出的长度
                    pieData2.setValueFormatter(new PercentFormatter());//显示百分比
                    //6、去掉比例尺和说明
//        Legend legend = pieChart.getLegend();//下标说明，false
//        legend.setEnabled(false);
                    pieChart2.setDescription(description2);
                    //7、显示百分比
                    pieData2.setValueFormatter(new PercentFormatter());
                    //8、显示
                    pieChart2.setData(pieData2);

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_show);
        pieChart = (PieChart)findViewById(R.id.see_average_speed_chart);
        pieChart2 = (PieChart)findViewById(R.id.see_average_speed_chart2);
        getMyData();


    }


    public void getMyData() {
        repairCount[0] = 20;
        repairCount[1] = 9;
        repairCount[2] = 12;
        repairCount[3] = 26;
        repairCount[4] = 33;
        repairCount2[0] = 29;
        repairCount2[1] = 11;
        repairCount2[2] = 20;
        repairCount2[3] = 26;
        repairCount2[4] = 14;
        Message message = Message.obtain();
        message.what = 0;
        mHandler.sendMessage(message);

    }
}

