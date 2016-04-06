package ru.eastbanctech.careeersday.android;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.widget.Toast;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final long TIME_OUT = 5000;
    @Bind(R.id.switch1) SwitchCompat switchView1;
    @Bind(R.id.switch2) SwitchCompat switchView2;
    @Bind(R.id.switch3) SwitchCompat switchView3;
    private WebApi webApi;

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            updateBulbList();
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        webApi = new Retrofit.Builder()
                .baseUrl(getString(R.string.api_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WebApi.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateBulbList();
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacksAndMessages(null);
    }

    private void updateBulbList() {
        Call<Map<String, Boolean>> call = webApi.list();
        call.enqueue(new Callback<Map<String, Boolean>>() {
            @Override
            public void onResponse(Call<Map<String, Boolean>> call, Response<Map<String, Boolean>> response) {
                Map<String, Boolean> map = response.body();
                String bulbName1 = (String) switchView1.getTag();
                String bulbName2 = (String) switchView2.getTag();
                String bulbName3 = (String) switchView3.getTag();
                switchView1.setChecked(map.get(bulbName1) != null ? map.get(bulbName1) : false);
                switchView2.setChecked(map.get(bulbName2) != null ? map.get(bulbName2) : false);
                switchView3.setChecked(map.get(bulbName3) != null ? map.get(bulbName3) : false);
                handler.sendEmptyMessageDelayed(0, TIME_OUT);
            }

            @Override
            public void onFailure(Call<Map<String, Boolean>> call, Throwable t) {
                handler.sendEmptyMessageDelayed(0, TIME_OUT);
            }
        });
    }

    @OnClick({R.id.switch1, R.id.switch2, R.id.switch3})
    void onSwitchBoxClicked(SwitchCompat view) {
        final String bulbName = (String) view.getTag();

        final Call<Boolean> call = webApi.switchBulb(bulbName, view.isChecked());
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Toast.makeText(MainActivity.this, bulbName + " is changed successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(MainActivity.this, bulbName + " change error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
