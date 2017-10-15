package io.github.httpssophiasun0515.hackgt2017;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by apple on 10/15/17.
 */

public class CityImage extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        final String cityName = getIntent().getStringExtra("cityName").toString();
        TextView name = (TextView) findViewById(R.id.name);
        name.setText(cityName);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
                Intent myIntent = new Intent(CityImage.this, WebPage.class);
                Bundle b = new Bundle();
                b.putString("cityName", cityName);
                myIntent.putExtras(b);
                startActivity(myIntent);
//                Snackbar.make(view, "Recognize Landmark Info", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

//
            }
        });
    }
}
