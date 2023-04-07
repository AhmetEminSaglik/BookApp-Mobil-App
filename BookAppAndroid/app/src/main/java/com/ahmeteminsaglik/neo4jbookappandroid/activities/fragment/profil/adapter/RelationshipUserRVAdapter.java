package com.ahmeteminsaglik.neo4jbookappandroid.activities.fragment.profil.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmeteminsaglik.neo4jbookappandroid.R;
import com.ahmeteminsaglik.neo4jbookappandroid.model.RelationshipUser;

import java.util.List;

public class RelationshipUserRVAdapter extends RecyclerView.Adapter<RelationshipUserRVAdapter.CardViewHolder> {
    private AppCompatActivity activity;
    private List<RelationshipUser> list;

    public RelationshipUserRVAdapter(AppCompatActivity activity, List<RelationshipUser> list) {
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(activity.getApplicationContext())
                .inflate(R.layout.relationship_card_design, parent, false);

        return new CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        RelationshipUser user = list.get(position);
        holder.indexNo.setText(String.valueOf(list.size() - position));
        holder.txtName.setText(user.getName());
        holder.txtLastname.setText(user.getLastname());
        holder.relationshipType.setText(user.getEnumRelationship().getName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public TextView indexNo;
        public TextView txtName;
        public TextView txtLastname;
        public TextView relationshipType;

        public CardViewHolder(@NonNull View view) {
            super(view);
            cardView = view.findViewById(R.id.authorCardView);
            indexNo = view.findViewById(R.id.indexNo);
            txtName = view.findViewById(R.id.txtName);
            txtLastname = view.findViewById(R.id.txtLastname);
            relationshipType = view.findViewById(R.id.relationshipType);
        }
    }


}
