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

import java.util.ArrayList;
import java.util.List;

public class UserBookList extends Fragment {

    private List<BookModel> bookList;
    private List<BookModel> userBookList;
    private DBHelper database;
    private UserBookAdapter adapter;
    public static UserBookList instance;
    private int currentPos;

    public UserBookList() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.bookList = BookList.getInstance().getBookList();
        this.database = MainActivity.getInstance().myDb;
        this.userBookList = new ArrayList<>();
        this.adapter = new UserBookAdapter(this.getActivity(), this.userBookList, NavHostFragment.findNavController(UserBookList.this));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        instance = this;
        return inflater.inflate(R.layout.fragment_user_book_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView userBooksView = view.findViewById(R.id.userBooksView);
        userBooksView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        userBooksView.setAdapter(this.adapter);
        fillUserBooksList();
        view.findViewById(R.id.userBooksAddNewBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(UserBookList.this).navigate(R.id.action_userBookList_to_bookList);
            }
        });
    }

    public void fillUserBooksList() {
        List<BookModel> queryResult = this.database.getAllUserBooks();
        for(int i = 0; i < queryResult.size(); i++) {
            for(int j = 0; j < this.bookList.size(); j++) {
                if(this.bookList.get(j).getId().equals(queryResult.get(i).getId())) {
                    this.userBookList.add(this.bookList.get(j));
                    break;
                }
            }
        }
    }

    public void removeFromUserBookList(int position) {
        this.database.removeUserBook(this.bookList.get(position).getId());
    }

    public int getCurrentPos() {
        return this.currentPos;
    }

    public void setCurrentPos(int pos) {
        this.currentPos = pos;
    }

    public List<BookModel> getUserBookList() {
        return this.userBookList;
    }

    public static UserBookList getInstance() {
        return instance;
    }

}