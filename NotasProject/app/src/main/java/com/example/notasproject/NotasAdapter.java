package com.example.notasproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotasAdapter extends RecyclerView.Adapter<NotasAdapter.NotasHolder> {


    private List<Notas> notas = new ArrayList<>();

    private OnItemClickListener listener;


    @NonNull
    @Override
    public NotasHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notas_items,parent,false);

        return new NotasHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotasHolder holder, int position) {

        Notas currentNota = notas.get(position);

        holder.textViewTitulo.setText(currentNota.getTitulo());
        holder.textViewDescripcion.setText(currentNota.getDescripcion());



    }

    @Override
    public int getItemCount() {
        return notas.size();
    }


    public void setNotas(List<Notas> notas) {
        this.notas = notas;
        notifyDataSetChanged();

    }

    public Notas getNotas(int position){

        return notas.get(position);



    }



    class NotasHolder extends RecyclerView.ViewHolder{


        TextView textViewTitulo;
        TextView textViewDescripcion;


        public NotasHolder(@NonNull View itemView) {
            super(itemView);

            textViewTitulo = itemView.findViewById(R.id.textViewTitulo);
            textViewDescripcion = itemView.findViewById(R.id.textViewDescripcion);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION){

                        listener.onItemClick(notas.get(position));

                    }

                }
            });



        }
    }

    public interface OnItemClickListener{

        void onItemClick(Notas notas);

    }

    public void setOnItemClickListener(OnItemClickListener listener){

        this.listener = listener;




    }



}
