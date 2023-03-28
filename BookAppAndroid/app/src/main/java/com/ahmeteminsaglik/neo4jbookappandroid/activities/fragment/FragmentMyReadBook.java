package com.ahmeteminsaglik.neo4jbookappandroid.activities.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ahmeteminsaglik.neo4jbookappandroid.R;

public class FragmentMyReadBook extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_read_book_layout, container, false); // layout icindeki tasarimlari baglamak icin kullanilir.
    }
}
