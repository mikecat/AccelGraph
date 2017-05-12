package jp.ac.titech.itpro.sdl.accelgraph;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SensorSelectActivity extends Activity {
    public static final String SENSOR_RES_NAME = "sensor";
    List<Sensor> sensorList;
    ArrayAdapter<String> choiceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_select);

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        ArrayList<String> sensorNameList = new ArrayList<String>();
        for (int i = 0; i < sensorList.size(); i++) {
            Sensor sensor = sensorList.get(i);
            sensorNameList.add(sensor.getName() + " (" + sensor.getStringType() + ")");
        }

        ListView choiceView = (ListView)findViewById(R.id.sensorList);
        choiceView.setAdapter(choiceAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sensorNameList));
        choiceView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                Intent data = new Intent();
                data.putExtra(SENSOR_RES_NAME, sensorList.get(pos).getType());
                SensorSelectActivity.this.setResult(Activity.RESULT_OK, data);
                SensorSelectActivity.this.finish();
            }
        });
    }
}
