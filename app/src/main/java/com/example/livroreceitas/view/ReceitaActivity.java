package com.example.livroreceitas.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.livroreceitas.R;

public class ReceitaActivity extends AppCompatActivity {

    TextView mDescricao;
    ImageView imageView;
    ListView ingredientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receita);

        mDescricao = (TextView) findViewById(R.id.receita_descricao);
        imageView = (ImageView) findViewById(R.id.receita_imagem);

        ingredientes = (ListView) findViewById(R.id.receita_ingredientes);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            mDescricao.setText(bundle.getString("Descricao"));

            Glide.with(this)
                    .load(bundle.getString("Imagem"))
                    .into(imageView);
        }
    }
}