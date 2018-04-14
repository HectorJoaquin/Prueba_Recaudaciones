package com.example.norgan.recaudaciones01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.norgan.recaudaciones01.adapter.UsuarioAdapter;
import com.example.norgan.recaudaciones01.entidades.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PrincipalActivity extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener {

    //ListView listView;
    Button btnSiguienteRegistar,irTabla;
    RecyclerView recyclerPrincipal;
    ArrayList<Usuario> listaUsuarios;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        listaUsuarios = new ArrayList<>();


        //listView = (ListView)findViewById(R.id.listView);
        btnSiguienteRegistar = (Button)findViewById(R.id.btnSiguienteRegistar);
        irTabla = (Button)findViewById(R.id.irTabla);


        recyclerPrincipal = (RecyclerView)findViewById(R.id.recyclerPrincipal);
        recyclerPrincipal.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        recyclerPrincipal.setHasFixedSize(true);


        request = Volley.newRequestQueue(getApplicationContext());

        cargaWebServicio();


        //ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(this,R.array.combo_dias,android.R.layout.simple_list_item_1);
        //listView.setAdapter(adaptador);


        //istView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // @Override
                    //          //  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                //  Toast.makeText(PrincipalActivity.this, "Selecciono"+parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();

                // }
            // });


        btnSiguienteRegistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2 = new Intent(PrincipalActivity.this,RegistarRecaudacionActivity.class);
                startActivity(intent2);


            }
        });



        irTabla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent4 = new Intent(PrincipalActivity.this,TablaRecaudacionesActivity.class);
                startActivity(intent4);


            }
        });


    }

    private void cargaWebServicio() {

        String url ="http://192.168.1.40/Recaudaciones/wsJSONConsultarLista.php";
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);




    }

    @Override
    public void onErrorResponse(VolleyError error) {

        Toast.makeText(this, "No se pudo Conectar"+error.toString(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onResponse(JSONObject response) {


        Usuario usuarioo = null;
        JSONArray json= response.optJSONArray("usuario");

            try {

                for (int i =0;i<json.length();i++){
                    usuarioo = new Usuario();
                    JSONObject jsonObject = null;
                jsonObject =json.getJSONObject(i);

                usuarioo.setDocumento(jsonObject.optInt("documento"));
                usuarioo.setMonto(jsonObject.optInt("monto"));
                usuarioo.setOrigen(jsonObject.optString("origen"));
                usuarioo.setLugar(jsonObject.optString("lugar"));
                listaUsuarios.add(usuarioo);


        }

                UsuarioAdapter adapter = new UsuarioAdapter(listaUsuarios);
                recyclerPrincipal.setAdapter(adapter);


            } catch (JSONException e) {
                e.printStackTrace();
            }

    }
}
