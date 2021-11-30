package com.example.friendfinderapp.Adapter;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.friendfinderapp.Model.SignIn_Model;

import java.util.List;

public class AdapterData {
    private Context ctx;
    private List<SignIn_Model> listDataUser;

    public AdapterData(Context ctx, List<SignIn_Model> listDataUser) {
        this.ctx = ctx;
        this.listDataUser = listDataUser;
    }
    public class HolderData extends RecyclerView.ViewHolder {
        public HolderData(@NonNull View itemView) {
            super(itemView);

        }
    }
}

