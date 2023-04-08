package com.ahmeteminsaglik.neo4jbookappandroid.activities.fragment.profil.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmeteminsaglik.neo4jbookappandroid.R;
import com.ahmeteminsaglik.neo4jbookappandroid.activities.HomeActivity;
import com.ahmeteminsaglik.neo4jbookappandroid.activities.fragment.profil.FragmentProfilProcess;
import com.ahmeteminsaglik.neo4jbookappandroid.model.EnumRelationship;
import com.ahmeteminsaglik.neo4jbookappandroid.model.RelationshipUser;
import com.ahmeteminsaglik.neo4jbookappandroid.utility.CardUtility;

import java.util.List;

public class RelationshipUserRVAdapter extends RecyclerView.Adapter<RelationshipUserRVAdapter.CardViewHolder> {
    boolean btnFunctionInProgress = false;
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
        RelationshipUser relationshipUser = list.get(position);
        holder.indexNo.setText(String.valueOf(list.size() - position));
        holder.txtName.setText(relationshipUser.getName());
        holder.txtLastname.setText(relationshipUser.getLastname());
        holder.relationshipType.setText(relationshipUser.getEnumRelationship().getName());
        holder.cardView.setBackgroundResource(CardUtility.getCardBackgroundColorByRelationshipType(relationshipUser.getEnumRelationship().getName()));
        holder.removeRelationshipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public synchronized void onClick(View view) {
                if (!btnFunctionInProgress) {
                    btnFunctionInProgress = true;
                    if (relationshipUser.getEnumRelationship().equals(EnumRelationship.FOLLOWED)) {
                        FragmentProfilProcess.removeFollowedUserRelationship(relationshipUser.getId());
                        Toast.makeText(activity, relationshipUser.getName() + " " + relationshipUser.getLastname() + "is not following any more.", Toast.LENGTH_LONG).show();
                    }
                    if (relationshipUser.getEnumRelationship().equals(EnumRelationship.FOLLOWER)) {
                        FragmentProfilProcess.removeFollowerUserRelationship(relationshipUser.getId());
                        Toast.makeText(activity, "Follower is removed", Toast.LENGTH_LONG).show();
                    }
//                    Intent intent = new Intent(activity.getApplicationContext(), HomeActivity.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    activity.getApplicationContext().startActivity(intent);
//                    activity.finish();
                } else {
                    Toast.makeText(activity, "Please wait relationship is removing.", Toast.LENGTH_LONG).show();
                }

            }
        });

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
        public Button removeRelationshipBtn;

        public CardViewHolder(@NonNull View view) {
            super(view);
            cardView = view.findViewById(R.id.relationshipCardView);
            indexNo = view.findViewById(R.id.indexNo);
            txtName = view.findViewById(R.id.txtName);
            txtLastname = view.findViewById(R.id.txtLastname);
            relationshipType = view.findViewById(R.id.relationshipType);
            removeRelationshipBtn = view.findViewById(R.id.removeRelationshipBtn);
        }
    }


}
