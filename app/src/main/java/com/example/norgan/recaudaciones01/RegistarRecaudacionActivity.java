package com.example.norgan.recaudaciones01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class RegistarRecaudacionActivity extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener{
    Button btnRetro,btnRegistrar,btnCancelar;
    EditText idRegistrar,montoRegistrar,ubicacionRegistar,lugarRegistar;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar_recaudacion);

        btnCancelar = (Button)findViewById(R.id.btnCancelar);
        btnRegistrar =(Button)findViewById(R.id.btnRegistrar);
        btnRetro = (Button)findViewById(R.id.btnRetro);

        idRegistrar = (EditText)findViewById(R.id.idRegistrar);
        montoRegistrar = (EditText)findViewById(R.id.montoRegistrar);
        ubicacionRegistar = (EditText)findViewById(R.id.ubicacionRegistar);
        lugarRegistar = (EditText)findViewById(R.id.lugarRegistar);


        request = Volley.newRequestQueue(getApplicationContext());

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cargarWebService();


            }
        });


        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent5 = new Intent(RegistarRecaudacionActivity.this,PrincipalActivity.class);
                startActivity(intent5);


            }
        });



        btnRetro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent3 = new Intent(RegistarRecaudacionActivity.this,PrincipalActivity.class);
                startActivity(intent3);

            }
        });



    }

    private void cargarWebService() {

        String url="http://192.168.1.40/Recaudaciones/wsJSONRegistro.php?documento="+idRegistrar.getText().toString()+"&monto="+montoRegistrar.getText().toString()+"&origen="+ubicacionRegistar.getText().toString()+"&lugar="+lugarRegistar.getText().toString();
        url=url.replace(" ","%20");
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);

    }




    @Override
    public void onErrorResponse(VolleyError error) {

        Toast.makeText(this, "No se pudo Registar"+error.toString(), Toast.LENGTH_SHORT).show();


    }


    @Override
    public void onResponse(JSONObject response) {

        Toast.makeText(this, "Se Registro Correctamente", Toast.LENGTH_SHORT).show();
        idRegistrar.setText("");
        montoRegistrar.setText("");
        ubicacionRegistar.setText("");
        lugarRegistar.setText("");

    }
}
