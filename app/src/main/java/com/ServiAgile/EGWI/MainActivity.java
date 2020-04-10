package com.ServiAgile.EGWI;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import androidx.appcompat.app.ActionBar;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.drm.DrmStore;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    EditText txUser,txPass;
    Button btLogin;
    TextView lbOlvido,lbError;
    RequestQueue rq;
    private final int REQUEST_ACCESS_FINE = 0;
    String JsonSend;
    LottieDrawable lienzo;
    String colo="#10A335";

    @SuppressLint("RestrictedApi")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btLogin= findViewById(R.id.btLogin);
        txUser= findViewById(R.id.txUser);
        txPass= findViewById(R.id.txPass);
        lbOlvido= findViewById(R.id.lbOlvido);
        lbError= findViewById(R.id.lbError);

        rq = Volley.newRequestQueue(getApplicationContext());
    //    this.ven = getWindow();
     //   Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(Color.parseColor(colo)));

     //   ven.setNavigationBarColor(Color.parseColor(colo));
    //    ven.setStatusBarColor(Color.parseColor(colo));
        txUser.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                lbError.setText("");
            }
        });
        txPass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                lbError.setText("");
            }
        });

        lbOlvido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MySingleton.getMakeT(MainActivity.this,3).shower("Como lo sentimos pana");
            }
        });
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txUser.getText().length()!=0&&txPass.getText().length()!=0){

                    new Handler().postDelayed(new Runnable(){
                        @Override
                        public void run() {
                            JsonSend ="{\"u\":\""+txUser.getText()+"\",\"p\":\""+txPass.getText()+"\"}";
                            Login(txUser.getText().toString().trim(),txPass.getText().toString().trim(),JsonSend);
                        }
                    },0);
                }else{MySingleton.getMakeT(MainActivity.this,3).shower("Datos incompletos");}
            }
        });
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.INTERNET)
                == PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET},REQUEST_ACCESS_FINE);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        ActionBar barra = getSupportActionBar();
        MySingleton.getInstance2(MainActivity.this,2).changeUI(colo,this.getWindow(),barra," ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        lbOlvido= findViewById(R.id.lbOlvido);
        lbOlvido.setText(Html.fromHtml("<u>Olvidé mi contraseña</u>"));
    }

    void Login( String u, String p, String data){
        final String parametros = data;

            JsonObjectRequest s = new JsonObjectRequest(Request.Method.GET, "http://192.168.0.4:8080/WSAndroidP3/ServletLogin?p=" + p + "&u=" + u,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                //    JSONArray data = new JSONArray(response); for array de jsons
                                switch (response.getString("Caso")) {
                                    case "A":
                                        switch (response.getString("TipoUsuario")) {
                                            case "1":
                                                MySingleton.instanceData().aquiTeVa(response);
                                                Intent nw1 = new Intent(MainActivity.this, lobbieMeseroActivity.class);
                                                startActivity(nw1);
                                                finish();
                                                break;
                                            case "2":
                                            case "3":
                                                Intent nw3 = new Intent(MainActivity.this, lobbieMeseroActivity.class);
                                                startActivity(nw3);
                                                finish();
                                                break;
                                            case "4":
                                            case "5":
                                        }
                                    case "B":

                                    case "C":
                                    case "D":
                                }
                            } catch (Exception err) {
                                MySingleton.getMakeT(MainActivity.this,3).shower("error en response");
                            }

                        }
                    }
                    , new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    lbOlvido.setText("E- " + error.toString());

                }
            });/*{
            @Override
            public String getBodyContentType(){return "application/json; charset=utf-8";}

            @Override
            public byte[] getBody() {
                try {
                    return para == null ? null : para.getBytes("utf-8");
                }catch (UnsupportedOperationException | UnsupportedEncodingException e){return null;}
            }
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> para = new HashMap<>();
                para.put("u", txUser.getText().toString());
                para.put("p",txPass.getText().toString());
                return para;
            }
            public Map<String,String> getHeaders() throws AuthFailureError{
                Map<String, String> para = new HashMap<>();
                para.put("Content-Type","application/x-www-form-urlencoded");
                return para;
            }

        };      */

            rq.add(s);
            //MySingleton.getInstance(MainActivity.this,1).agregarACola(s);

    }

}
