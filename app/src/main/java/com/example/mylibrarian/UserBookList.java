package com.example.mylibrarian;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class UserBookList extends Fragment {

    private List<BookModel> bookList;
    private DBHelper database;
    private UserBookAdapter adapter;

    public UserBookList() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.bookList = BookList.getInstance().getBookList();
        this.database = MainActivity.getInstance().myDb;
        this.adapter = new UserBookAdapter(this.getActivity(), this.bookList, NavHostFragment.findNavController(UserBookList.this));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_book_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView userBooksView = view.findViewById(R.id.userBooksView);
        userBooksView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        userBooksView.setAdapter(this.adapter);
    }
}