package io.github.httpssophiasun0515.hackgt2017;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by apple on 10/15/17.
 */

public class CityImage extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        final ArrayList<String> info = getIntent().getStringArrayListExtra("info");
        TextView name = (TextView) findViewById(R.id.name);
        name.setText(info.get(0) + "\n" + info.get(1));

        ImageView image = (ImageView) findViewById(R.id.imageView2);
        int i = Integer.parseInt(info.get(2));
        if (i == 0) {
            image.setImageResource(R.drawable.landmark1);
        } else if (i == 1) {
            image.setImageResource(R.drawable.landmark2);
        } else if (i == 2) {
            image.setImageResource(R.drawable.landmark3);
        } else if (i == 3) {
            image.setImageResource(R.drawable.landmark4);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
                Intent myIntent = new Intent(CityImage.this, WebPage.class);
                Bundle b = new Bundle();
                b.putString("cityName", info.get(1));
                myIntent.putExtras(b);
                startActivity(myIntent);
            }
        });
    }
}
