package com.example.norgan.recaudaciones01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.norgan.recaudaciones01.entidades.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class TablaRecaudacionesActivity extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener{

    Button btnRetroTabla,goTabla;
    EditText idTabla;
    TextView tvMontoTabla,tvOrigenTabla,tvLugarTabla;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabla_recaudaciones);


        btnRetroTabla = (Button)findViewById(R.id.btnRetroTabla);
        goTabla = (Button)findViewById(R.id.goTabla);
        idTabla=(EditText)findViewById(R.id.idTabla);
        tvMontoTabla = (TextView)findViewById(R.id.tvMontoTabla);
        tvOrigenTabla  = (TextView)findViewById(R.id.tvOrigenTabla);
        tvLugarTabla  = (TextView)findViewById(R.id.tvLugarTabla);

        request = Volley.newRequestQueue(getApplicationContext());

        goTabla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                consultarWebService();

            }
        });




        btnRetroTabla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent5 = new Intent(TablaRecaudacionesActivity.this,PrincipalActivity.class);
                startActivity(intent5);


            }
        });


    }

    private void consultarWebService() {

        String url = "http://192.168.1.40/Recaudaciones/wsJSONConsultarUsuario.php?documento="+idTabla.getText().toString();
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);


    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "No se pudo Consultar"+error.toString(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onResponse(JSONObject response) {

        Toast.makeText(this, "Mensaje"+response, Toast.LENGTH_SHORT).show();


        Usuario miusuario= new Usuario();

        JSONArray json= response.optJSONArray("usuario");
        JSONObject jsonObject = null;


        try {
            jsonObject=json.getJSONObject(0);
            miusuario.setMonto(jsonObject.optInt("monto"));
            miusuario.setOrigen(jsonObject.optString("origen"));
            miusuario.setLugar(jsonObject.optString("lugar"));



        } catch (JSONException e) {

            e.printStackTrace();

        }


        tvMontoTabla.setText("El Monto es :"+miusuario.getMonto());
        tvOrigenTabla.setText("El Origen es :"+miusuario.getOrigen());
        tvLugarTabla.setText("El Lugar es :"+miusuario.getLugar());


    }
}
