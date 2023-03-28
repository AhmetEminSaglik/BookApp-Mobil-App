package com.ahmeteminsaglik.neo4jbookappandroid.activities.fragment.myreadbook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmeteminsaglik.neo4jbookappandroid.R;
import com.ahmeteminsaglik.neo4jbookappandroid.model.Book;

import java.util.List;

public class BookRVAdapter extends RecyclerView.Adapter<BookRVAdapter.CardViewHolder> {
    private Context context;
    private List<Book> readBookList;

    public BookRVAdapter(Context context, List<Book> readBookList) {
        this.context = context;
        this.readBookList = readBookList;
    }


    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.book_card_design, parent, false);

        return new CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        String bookName = readBookList.get(position).getName();
        double point = readBookList.get(position).getPoint();
        int totalRead = readBookList.get(position).getTotalRead();
        holder.txtVBookName.setText(bookName);
        holder.txtVBookPoint.setText(Double.toString(point));
        holder.txtVTotalRead.setText(Integer.toString(totalRead));
        Toast.makeText(context,"view holder'a geldi",Toast.LENGTH_LONG).show();
    }

    @Override
    public int getItemCount() {
        return readBookList.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        public TextView txtVBookName;
        public CardView bookCardView;
        public TextView txtVBookPoint;
        public TextView txtVTotalRead;

        public CardViewHolder(@NonNull View view) {
            super(view);
            bookCardView = view.findViewById(R.id.bookCardView);
            txtVBookName = view.findViewById(R.id.txtVBookName);
            txtVBookPoint = view.findViewById(R.id.txtVPointValue);
            txtVTotalRead = view.findViewById(R.id.txtVTotalReadValue);
        }
    }
}
