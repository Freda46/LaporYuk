package com.dariaweis.freda.laporyuk;

/**
 * Created by FreeDay on 18/01/2018.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class adapterfreda extends RecyclerView.Adapter<adapterfreda.ViewHolder> {
    private ArrayList<freda> daftarfreda;
    private Context context;

    public adapterfreda(ArrayList<freda> daftarfreda, Context context) {
        this.daftarfreda = daftarfreda;
        this.context = context;
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTanggal, txtLat, txtKejadian, txtLng, txtNama;
        Button btnMap;

        ViewHolder(View v){
            super(v);
            context = itemView.getContext();

            txtNama = (TextView)v.findViewById(R.id.txtNama);
            txtTanggal = (TextView)v.findViewById(R.id.txtTanggal);
            txtLat = (TextView)v.findViewById(com.dariaweis.freda.laporyuk.R.id.txtLat);
            txtKejadian = (TextView)v.findViewById(com.dariaweis.freda.laporyuk.R.id.txtKejadian);
            txtLng = (TextView)v.findViewById(com.dariaweis.freda.laporyuk.R.id.txtLng);
            btnMap = (Button)v.findViewById(R.id.btnLokasi);

            btnMap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context,maps.class);
                    context.startActivity(i);
                }
            });


        }
    }


    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_freda, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);
        return  viewHolder;
    }


    public void onBindViewHolder(ViewHolder holder, final int position) {
        //final String kordinat = daftarfreda.get(position).getKejadian();




        final String waktu = daftarfreda.get(position).getWaktu();
        final String nama = daftarfreda.get(position).getNama();
        final String kejadian = daftarfreda.get(position).getKejadian();
        final String lat = daftarfreda.get(position).getLat();
        final String lng = daftarfreda.get(position).getLng();
        //final String kordinat = "Lat : "+lat+", Long : "+lng+" ";
        holder.txtTanggal.setText(waktu);
        holder.txtNama.setText(nama);
        holder.txtLat.setText("Latitude    : "+lat);
        holder.txtKejadian.setText(kejadian);
        holder.txtLng.setText("Longitude   : "+lng);

        holder.btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(context,maps.class);
                i.putExtra("lat", lat);
                i.putExtra("lng", lng);
                context.startActivity(i);

            }
        });
    }


    public int getItemCount() {
        return daftarfreda.size();
    }

}
