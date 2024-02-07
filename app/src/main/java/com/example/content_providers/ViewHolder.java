package com.example.content_providers;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {
    TextView txtdata;
    TextView data;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        txtdata=itemView.findViewById(R.id.text);
        data=itemView.findViewById(R.id.data);
    }
}
