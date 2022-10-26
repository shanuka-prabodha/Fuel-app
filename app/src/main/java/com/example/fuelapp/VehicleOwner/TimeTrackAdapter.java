package com.example.fuelapp.VehicleOwner;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.fuelapp.Model.TimeTrack;
import com.example.fuelapp.R;


import java.util.ArrayList;

class TimeTrackAdapter extends RecyclerView.Adapter<TimeTrackAdapter.vehiHolder>{

    ArrayList<TimeTrack> trackList;
    Context mcontext;


    public TimeTrackAdapter(Context mcontext, ArrayList<TimeTrack> trackList){
        this.trackList = trackList;
        this.mcontext = mcontext;

    }

    @NonNull
    @Override
    public vehiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerviewlayout,parent,false);
         return new vehiHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final vehiHolder holder, final int position) {

        holder.txtName.setText(trackList.get(position).getName());
        holder.txtStart.setText((CharSequence) trackList.get(position).getStart());
        holder.txtEnd.setText(String.valueOf(trackList.get(position).getExit()));
        holder.txtWait.setText(String.valueOf(trackList.get(position).getWait()));


    }

    @Override
    public int getItemCount() {

        return trackList.size();
    }




    class vehiHolder extends RecyclerView.ViewHolder{
        TextView txtName,txtStart,txtEnd,txtWait;

        public vehiHolder(@NonNull View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.name);
            txtStart = (TextView) itemView.findViewById(R.id.start);
            txtEnd = (TextView) itemView.findViewById(R.id.exit);
            txtWait = (TextView) itemView.findViewById(R.id.wait);

        }

    }
}
