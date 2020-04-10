package com.ServiAgile.EGWI;

import androidx.appcompat.app.ActionBar;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class MySingleton {
    private static MySingleton miInstance,changecolors,wayToUserData,makeT;
    private RequestQueue colasolicitada;
    private static Context ElContexto;
    private JSONObject data;



    private MySingleton(Context contexto,int caso){
        ElContexto = contexto;
        switch(caso) {
            case 1:
                colasolicitada = crearCola();
            break;
            case 2:
                break;
            case 3:
                break;
        }
    }
    private MySingleton(){}

    public static synchronized MySingleton getInstance(Context contexto,int ca){
        if(miInstance == null){ miInstance = new MySingleton(contexto,ca); } return miInstance;
    }
    public static synchronized MySingleton getInstance2(Context contexto,int ca){
        if(changecolors == null){ changecolors = new MySingleton(contexto,ca); } return changecolors;
    }
    public static  synchronized  MySingleton instanceData(){
        if(wayToUserData==null){wayToUserData = new MySingleton();} return wayToUserData;
    }
    public static  synchronized MySingleton getMakeT(Context contexto, int ca){
        if(makeT==null){makeT = new MySingleton(contexto,ca);} return makeT;
    }

    public<T> void agregarACola (Request<T> request){
        colasolicitada.add(request);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void changeUI(String c, Window v, ActionBar barra){
        v.setStatusBarColor(Color.parseColor(c));
        v.setNavigationBarColor(Color.parseColor(c));
        barra.setBackgroundDrawable(new ColorDrawable(Color.parseColor(c)));
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void changeUI(String c, Window v, ActionBar barra, String title){
        v.setStatusBarColor(Color.parseColor(c));
        v.setNavigationBarColor(Color.parseColor(c));
        barra.setBackgroundDrawable(new ColorDrawable(Color.parseColor(c)));
        barra.setTitle(title);
    }

    public JSONObject aquiTiene(){return data;}
    void aquiTeVa(JSONObject r){data=r;}

    public RequestQueue crearCola(){
        if(colasolicitada == null){
            colasolicitada = Volley.newRequestQueue(ElContexto.getApplicationContext());
        }
        return colasolicitada;
    }
    public  void shower(String msg){
        Toast.makeText(ElContexto,msg, Toast.LENGTH_LONG).show();
    }

}
