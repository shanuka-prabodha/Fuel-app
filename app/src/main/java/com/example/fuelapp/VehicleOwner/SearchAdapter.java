package com.example.fuelapp.VehicleOwner;

import android.content.Context;
import android.graphics.Color;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fuelapp.Model.Station;
import com.example.fuelapp.Model.TimeTrack;
import com.example.fuelapp.R;

import java.util.ArrayList;

class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.searchHolder> {

    ArrayList<Station> stationList;
    Context mcontext;
    Button btnview;
    private OnItemClickListener mListener;

    public SearchAdapter(Context mcontext, ArrayList<Station> stationList) {
        this.stationList = stationList;
        this.mcontext = mcontext;

    }

    @NonNull
    @Override
    public searchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.searchrecyclerviewlayout, parent, false);
        return new searchHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final searchHolder holder, final int position) {

        holder.txtName.setText(stationList.get(position).getName());
        if (stationList.get(position).getIspetrol()) {
            holder.pstatus.setText("Available");
            holder.pstatus.setBackgroundColor(Color.parseColor("#3ddc84"));
        }
        else {
            holder.pstatus.setText("Finished");
            holder.pstatus.setBackgroundColor(Color.parseColor("#b00020"));
        }
        if (stationList.get(position).getIsdiesel()) {
            holder.dStatus.setText("Available");
            holder.dStatus.setBackgroundColor(Color.parseColor("#3ddc84"));
        }
        else {
            holder.dStatus.setText("Finished");
            holder.dStatus.setBackgroundColor(Color.parseColor("#b00020"));
        }




    }

    @Override
    public int getItemCount() {
        return stationList.size();
    }


    class searchHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        TextView txtName, pstatus, dStatus;

        public searchHolder(@NonNull View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.location);
            pstatus = (TextView) itemView.findViewById(R.id.pt);
            dStatus = (TextView) itemView.findViewById(R.id.ds);


            itemView.setOnClickListener(this);

            itemView.setOnCreateContextMenuListener(this);
        }


        @Override
        public void onClick(View view) {
//            if (mListener != null) {
//
//            }

            int position = getAdapterPosition();

            System.out.println("position "+ position );

            if (position != RecyclerView.NO_POSITION) {
                mListener.onItemClick(position);
            }
        }


        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {

        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            return false;
        }


    }


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {

        mListener = listener;

    }

}
