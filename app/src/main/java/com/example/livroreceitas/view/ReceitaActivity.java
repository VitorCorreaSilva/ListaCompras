package com.example.livroreceitas.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.livroreceitas.R;
import com.example.livroreceitas.model.IngredienteModel;
import com.example.livroreceitas.model.ProcessoModel;
import com.example.livroreceitas.model.ReceitaModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReceitaActivity extends AppCompatActivity {

    TextView mDescricao;
    ImageView imageView;
    String key;
    ReceitaModel receita;
    ProgressDialog progressDialog;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receita);

        gson = new Gson();
        mDescricao = (TextView) findViewById(R.id.receita_descricao);
        imageView = (ImageView) findViewById(R.id.receita_imagem);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            mDescricao.setText(bundle.getString("Descricao"));
            key = bundle.getString("Key");
            Glide.with(this)
                    .load(bundle.getString("Imagem"))
                    .into(imageView);
        }

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Carregando receita....");
        progressDialog.show();

        FirebaseFirestore.getInstance()
                .collection("receitas").document(key)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                Log.d("TESTESTESTESTESTWE", document.getId() + " => " + document.getData());
                                String json = gson.toJson(document.getData());
                                receita = gson.fromJson(json, ReceitaModel.class);
                                receita.setKey(document.getId());
                                simpleAdapterIngredientes(receita.getIngredientes());
                                simpleAdapterProcessos(receita.getPassos());
                                progressDialog.dismiss();
                            }
                        } else {
                            progressDialog.dismiss();
                        }
                    }
                });
    }

    public void simpleAdapterIngredientes(List<IngredienteModel> ingredientes){
        String[] from = {
            "nome", "unidadeMedida", "quantidade"
        };
        int[] to = {
                R.id.ingrediente_nome, R.id.ingrediente_unidade, R.id.ingrediente_quantidade
        };

        List<HashMap<String, String>> listHashmap = new ArrayList<>();
        for (int x = 0;x <ingredientes.size(); x++){
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("nome", ingredientes.get(x).getNome());
            hm.put("unidadeMedida", ingredientes.get(x).getUnidadeMedida());
            hm.put("quantidade", ingredientes.get(x).getQuantidade());
            listHashmap.add(hm);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(),
                listHashmap,
                R.layout.receita_ingrediente_item,
                from, to);

        ListView listView = findViewById(R.id.receita_ingredientes);
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = params.height * ingredientes.size();
        listView.setLayoutParams(params);
        listView.setAdapter(simpleAdapter);
    }

    public void simpleAdapterProcessos(List<ProcessoModel> processos){
        String[] from = {
                "etapa", "descricao"
        };
        int[] to = {
                R.id.processo_passo, R.id.processo_descricao
        };

        List<HashMap<String, String>> listHashmap = new ArrayList<>();
        for (int x = 0;x < processos.size(); x++){
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("etapa", processos.get(x).getEtapa());
            hm.put("descricao", processos.get(x).getDescricao());
            listHashmap.add(hm);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(),
                listHashmap,
                R.layout.receita_processo_item,
                from, to);

        ListView listView = findViewById(R.id.receita_passos);
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = params.height * processos.size();
        listView.setLayoutParams(params);
        listView.setAdapter(simpleAdapter);
    }
}