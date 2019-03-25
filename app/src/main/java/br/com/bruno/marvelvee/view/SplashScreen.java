package br.com.bruno.marvelvee.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import br.com.bruno.marvelvee.R;

public class SplashScreen extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        Thread timer = new Thread(){
            @Override
            public void run(){
                try{
                    sleep(3000);
                } catch(InterruptedException ex){
                    ex.printStackTrace();
                } finally {
                    Intent intent = new Intent (SplashScreen.this, MainUserGoogle.class);
                    startActivity(intent);
                }
            }
        };
        timer.start();
    }
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}


