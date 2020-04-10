package com.ServiAgile.EGWI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ServiAgile.EGWI.ui.lobbiemesero.LobbieMeseroFragment;

public class lobbieMeseroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lobbie_mesero_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, LobbieMeseroFragment.newInstance())
                    .commitNow();
        }
    }
}
