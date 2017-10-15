package io.github.httpssophiasun0515.hackgt2017;

import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import io.github.httpssophiasun0515.hackgt2017.HttpService.CVClient;
import io.github.httpssophiasun0515.hackgt2017.RequestModel.BodyUrl;
import io.github.httpssophiasun0515.hackgt2017.ResponseModel.MSCVResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ImageActivity extends AppCompatActivity {


    private static final String MSCVAPI= "https://westcentralus.api.cognitive.microsoft.com/vision/v1.0/models/landmarks/";
    private static final String subscriptionKey = "694bdd8396634097a0e91db00327d558";
    public static String landMarkName;

    static String cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String url = "";

        Random r = new Random();
        int i = r.nextInt(4);
        ImageView image = (ImageView) findViewById(R.id.imageView2);
        if (i == 0) {
            image.setImageResource(R.drawable.landmark1);
            url = "https://cdntct.com/tct/pic/city/wuhan/attractions/yellow-crane-tower-5.jpg";
        } else if (i == 1) {
            image.setImageResource(R.drawable.landmark2);
            url = "http://media.thisisinsider.com/images/58d919eaf2d0331b008b4bbd-750-562.jpg";
        } else if (i == 2) {
            image.setImageResource(R.drawable.landmark3);
            url = "https://upload.wikimedia.org/wikipedia/commons/thumb/a/ae/Neuschwanstein_Castle_%28532850%29.jpg/1024px-Neuschwanstein_Castle_%28532850%29.jpg";
        } else if (i == 3) {
            image.setImageResource(R.drawable.landmark4);
            url = "http://us.hellomagazine.com/imagenes/travel/2015102127775/cambodia-adventure-holiday/0-137-959/cambodia-temples--a.jpg";
        }
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ImageActivity.this, ImageActivity.class);
                startActivity(myIntent);
            }
        });

        final int identity = i;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(ImageActivity.this, CityImage.class);
                ArrayList<String> info = new ArrayList<String>();
                info.add(ImageActivity.landMarkName);
                info.add(cityName);
                info.add(identity + "");
                startActivity(myIntent);
                Bundle b = new Bundle();
                b.putStringArrayList("info", info);
                myIntent.putExtras(b);
                startActivity(myIntent);
            }
        });

        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(MSCVAPI).
                addConverterFactory(GsonConverterFactory.create()).
                build();

        CVClient computerVisionClient = retrofit.create(CVClient.class);

//        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
//        String textOnClipboard = clipboard.getPrimaryClip().getItemAt(0).getText().toString();
        BodyUrl bodyUrl = new BodyUrl(url);
        //JSONObject jsonObject = new JSONObject();
        Call<MSCVResponse> mscvResponse = computerVisionClient.getLandmark(subscriptionKey, "application/json", bodyUrl);

        mscvResponse.enqueue(new Callback<MSCVResponse>() {
            @Override
            public void onResponse(Call<MSCVResponse> call, Response<MSCVResponse> response) {
                Log.d("response body", response.toString());
//                Toast.makeText(getApplicationContext(), "response" + response.body().getResult().getLandmarks()[0].getName(), Toast.LENGTH_LONG).show();
                landMarkName = response.body().getResult().getLandmarks()[0].getName();
                Log.d("landmark", landMarkName);
                new GetCoordinates().execute(ImageActivity.landMarkName.replace(" ","+"));
            }

            @Override
            public void onFailure(Call<MSCVResponse> call, Throwable t) {
                Log.d("error", t.toString());
            }
        });

    }
}
