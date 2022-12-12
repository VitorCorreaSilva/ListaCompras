package com.example.livroreceitas.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.livroreceitas.R;
import com.example.livroreceitas.model.ReceitaModel;
import com.example.livroreceitas.view.ReceitaActivity;

import java.util.ArrayList;
import java.util.List;

public class ReceitaAdapter extends RecyclerView.Adapter<ReceitaViewHolder> {

    private Context context;
    private List<ReceitaModel> receitaList;
    private int lastPosition = -1;

    public ReceitaAdapter(Context context, List<ReceitaModel> receitaList) {
        this.context = context;
        this.receitaList = receitaList;
    }

    @NonNull
    @Override
    public ReceitaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_receita_item, parent,false);
        return new ReceitaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReceitaViewHolder holder, int position) {
        Glide.with(context)
               .load(receitaList.get(position).getImagem())
                      .into(holder.imageView);

        holder.mNome.setText(receitaList.get(position).getNome());
        holder.mDescricao.setText(receitaList.get(position).getDescricao());
        holder.mTempo.setText(receitaList.get(position).getTempo());
        holder.mRendimento.setText(receitaList.get(position).getRendimento());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ReceitaActivity.class);
                intent.putExtra("Imagem", receitaList.get(holder.getAdapterPosition()).getImagem());
                intent.putExtra("Descricao", receitaList.get(holder.getAdapterPosition()).getDescricao());
                intent.putExtra("Key", receitaList.get(holder.getAdapterPosition()).getKey());
                //intent.putParcelableArrayListExtra("Ingredientes", receitaList.get(holder.getAdapterPosition()).getIngredientes());
                //intent.putExtra("Processo", (Parcelable) receitaList.get(holder.getAdapterPosition()).getPassos());
                context.startActivity(intent);
            }
        });
        setAnimation(holder.itemView, position);
    }

    public void setAnimation(View view, int position){
        if(position > lastPosition){
            ScaleAnimation animation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            animation.setDuration(1500);
            view.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return receitaList.size();
    }

    public void listaFiltrada(ArrayList<ReceitaModel> filtroList) {
        receitaList = filtroList;
        notifyDataSetChanged();
    }
}

class ReceitaViewHolder extends RecyclerView.ViewHolder{

    ImageView imageView;
    TextView mNome, mDescricao, mRendimento, mTempo;
    CardView cardView;

    public ReceitaViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.card_receita_imagem);
        mNome = itemView.findViewById(R.id.card_receita_nome);
        mDescricao = itemView.findViewById(R.id.card_receita_descricao);
        mRendimento = itemView.findViewById(R.id.card_receita_rendimento);
        mTempo = itemView.findViewById(R.id.card_receita_tempo);

        cardView = itemView.findViewById(R.id.card_receita);
    }
}
