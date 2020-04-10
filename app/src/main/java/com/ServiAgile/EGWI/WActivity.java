package com.ServiAgile.EGWI;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;

import java.util.Objects;

public class WActivity extends AppCompatActivity {
    Window ven;String colo="#10A335";View miContenido;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_w);
        miContenido = findViewById(R.id.fullscreen_content);
    //    this.ven = getWindow();
    //    Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(Color.parseColor(colo)));
    //    Objects.requireNonNull(getSupportActionBar()).setTitle ("Bienvenido/a");


        //    MySingleton.getInstance2(WActivity.this,ven).cambiarColores(colo);
    //    ven.setNavigationBarColor(Color.parseColor(colo));
    //    ven.setStatusBarColor(Color.parseColor(colo));


        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent intento = new Intent (WActivity.this,MainActivity.class);
                startActivity(intento);
                finish();
            }
        },4000);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        MySingleton.getInstance(WActivity.this,2).changeUI(this.colo,this.getWindow(),actionBar);
        if (actionBar != null) {
            actionBar.hide();
        }

        miContenido.setSystemUiVisibility(
                 View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }
}
