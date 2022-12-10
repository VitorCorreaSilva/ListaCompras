package com.example.livroreceitas.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.livroreceitas.R;
import com.example.livroreceitas.adapter.ReceitaAdapter;
import com.example.livroreceitas.model.IngredienteModel;
import com.example.livroreceitas.model.ProcessoModel;
import com.example.livroreceitas.model.ReceitaModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<ReceitaModel> receitaList;
    ReceitaModel receita;
    FloatingActionButton buttonAdicionar;
    EditText buscar;
    ReceitaAdapter receitaAdapter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_receita);
        buttonAdicionar = findViewById(R.id.btn_add_receita);
        buscar = findViewById(R.id.txt_buscar);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);



        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Carregando receitas...");

        receitaList = new ArrayList<>();


        receitaAdapter = new ReceitaAdapter(MainActivity.this, receitaList);
        recyclerView.setAdapter(receitaAdapter);

        progressDialog.show();
        FirebaseFirestore.getInstance().collection("receitas")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                receita = new ReceitaModel(document.getData().get("nome").toString(),
                                        document.getData().get("nome").toString(),
                                        document.getData().get("nome").toString(),
                                        document.getData().get("nome").toString(),
                                        document.getData().get("nome").toString()
                                );
                                receitaList.add(receita);
                            }
                            receitaAdapter.notifyDataSetChanged();
                            progressDialog.dismiss();
                        } else {
                            progressDialog.dismiss();
                        }
                    }
                });

        buscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filtro(s.toString());
            }
        });

        buttonAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NovaReceitaActivity.class);
                startActivity(intent);
            }
        });
    }

    public void filtro(String texto) {
        ArrayList<ReceitaModel> filtroList = new ArrayList<>();
        for (ReceitaModel receita: receitaList){
            if(receita.getNome().toLowerCase().contains(texto.toLowerCase())){
                filtroList.add(receita);
            }
        }

        receitaAdapter.listaFiltrada(filtroList);
    }
}