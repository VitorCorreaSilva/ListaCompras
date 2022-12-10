package com.example.livroreceitas.view;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.livroreceitas.R;
import com.example.livroreceitas.model.ReceitaModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.util.Calendar;

public class NovaReceitaActivity extends AppCompatActivity {

    ImageView imageView;
    Button buttonSelecione, buttonAdicionar;
    ActivityResultLauncher<Intent> imagemLauncher;
    TextView nome, descricao, tempo, rendimento;
    Uri uri;
    String imagemUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_receita);

        imageView = findViewById(R.id.nova_receita_imagem);
        buttonSelecione = findViewById(R.id.nova_receita_selecione);
        buttonAdicionar = findViewById(R.id.nova_receita_upload);
        nome = findViewById(R.id.nova_receita_nome);
        descricao = findViewById(R.id.nova_receita_descricao);
        tempo = findViewById(R.id.nova_receita_tempo);
        rendimento = findViewById(R.id.nova_receita_rendimento);

        imagemLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result != null){
                            uri = result.getData().getData();
                            imageView.setImageURI(uri);
                        }
                    }
                });


        buttonSelecione.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selecioneImagem(v);
            }
        });

        buttonAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionarReceita();
            }
        });
    }

    public void adicionarReceita() {
        //uploadImagem();
        uploadReceita();
    }

    public void uploadImagem(){
        StorageReference storageReference = FirebaseStorage.getInstance().getReference()
                .child("ImagemReceita").child(uri.getLastPathSegment());

        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while(!uriTask.isComplete());
                Uri urlImagem = uriTask.getResult();
                imagemUrl = urlImagem.toString();
            }
        });
    }

    public void uploadReceita(){
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Criando receita....");
        progressDialog.show();

        ReceitaModel receita = new ReceitaModel(
                nome.getText().toString(),
                descricao.getText().toString(),
                tempo.getText().toString(),
                rendimento.getText().toString(),
                imagemUrl
        );

        String currentDate = DateFormat.getDateTimeInstance()
                .format(Calendar.getInstance().getTime());

        FirebaseFirestore.getInstance().collection("receitas")
                .add(receita).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(NovaReceitaActivity.this, "Receita criada com sucesso.", Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(NovaReceitaActivity.this, "Erro ao criar receita.", Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }
                });
    }

    public void selecioneImagem(View view){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        imagemLauncher.launch(intent);
    }
}