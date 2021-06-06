package com.example.mylibrarian;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class BookList extends Fragment {

    private static BookList instance;
    private List<BookModel> bookList;
    int currentPos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        instance = this;
        return inflater.inflate(R.layout.fragment_book_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView booksView = view.findViewById(R.id.booksView);
        NavController navController = NavHostFragment.findNavController(BookList.this);
        DataGetter dataGetter = new DataGetter(this.getActivity(), navController);
        dataGetter.setRecyclerView(booksView);
        dataGetter.execute();
        this.bookList = dataGetter.getBookList();
        view.findViewById(R.id.tempBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_bookList_to_userBookList);
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static BookList getInstance() {
        return instance;
    }

    public int getCurrentPos() {
        return this.currentPos;
    }

    public void setCurrentPos(int pos) {
        this.currentPos = pos;
    }

    public List<BookModel> getBookList() {
        return this.bookList;
    }

}