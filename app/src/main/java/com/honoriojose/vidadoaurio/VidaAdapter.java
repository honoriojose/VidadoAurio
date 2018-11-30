package com.honoriojose.vidadoaurio;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class VidaAdapter extends RecyclerView.Adapter<VidaAdapter.VidaViewHolder> {
    List<Vida> vidas;
    Context context;

    public VidaAdapter(Context ctx){
        this.context = ctx;
        this.vidas = new ArrayList<>();

        vidas.add(new Vida("Emanuel e o Mano:Aurio","Filhos Abençoados",R.drawable.jorgue));
        vidas.add(new Vida("Descrição por vossa conta","Só mesmo Eu",R.drawable.jorge));
        vidas.add(new Vida("Descrição por vossa conta","A mana Daniela",R.drawable.jorge4));
        vidas.add(new Vida("Descrição por vossa conta","Seja líder de si mesmo",R.drawable.jorge10));
        vidas.add(new Vida("Descrição por vossa conta","A família esta sempre por perto",R.drawable.joge12));
        vidas.add(new Vida("Descrição por vossa conta","Os amigos do peito",R.drawable.jorge13));
       vidas.add(new Vida("Descrição por vossa conta","O homem da casa",R.drawable.jorge14));
        vidas.add(new Vida("Descrição por vossa conta","A pausa sempre acompanha-me",R.drawable.jorge16));
        vidas.add(new Vida("Vamos brincar ","A diversão acima de tudo ",R.drawable.jorge18));
        vidas.add(new Vida("Aurio","Hora da brincadeira",R.drawable.jorge20));
        vidas.add(new Vida("Aurio e Wilson ","Ao lado do Primo mais velho",R.drawable.jorge24));
        vidas.add(new Vida("Honório M. José","O programador desta App Android",R.drawable.jorge25));
    }

    @NonNull
    @Override
    public VidaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View View= LayoutInflater.from(context).inflate(R.layout.item_layout, viewGroup, false);
        return new VidaViewHolder(View);
    }

    @Override
    public void onBindViewHolder(@NonNull final VidaViewHolder vidaViewHolder, int i) {
            final Vida Vida=vidas.get(i);
            vidaViewHolder.imagem.setImageResource(Vida.getRes());
            vidaViewHolder.nome.setText(Vida.getNome());
            vidaViewHolder.descricao.setText(Vida.getDescricao());
            vidaViewHolder.imagem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    vidaViewHolder.openActivity(Vida);
                }
            });

    }

    @Override
    public int getItemCount(){ return vidas.size(); }

    class VidaViewHolder extends RecyclerView.ViewHolder {
        TextView nome;
        TextView descricao;
        ImageView imagem;

        public VidaViewHolder(View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.aurio_name);
            descricao = itemView.findViewById(R.id.aurio_description);
            imagem = itemView.findViewById(R.id.aurio_image);
        }

        public void openActivity(Vida vida) {

            Intent intent = new Intent(context, VidaActivity.class);
            intent.putExtra("imege", vida.res);
            context.startActivity(intent);
        }

    }
}
