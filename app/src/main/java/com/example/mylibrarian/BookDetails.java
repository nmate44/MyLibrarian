package com.example.mylibrarian;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class BookDetails extends Fragment {

    private List<BookModel> bookList;
    private int bookPosition;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_book_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.bookList = BookList.getInstance().getBookList();
        this.bookPosition = BookList.getInstance().getCurrentPos();

        TextView detailedTitle = view.findViewById(R.id.detailsBookTitle);
        ImageView detailedImg = view.findViewById(R.id.detailsBookImg);
        TextView detailedAuthor = view.findViewById(R.id.detailsBookAuthor);
        TextView detailedGenre = view.findViewById(R.id.detailsBookGenre);
        TextView detailedPublished = view.findViewById(R.id.detailsBookPublished);
        TextView detailedIsbn = view.findViewById(R.id.detailsBookIsbn);
        TextView detailedDescription = view.findViewById(R.id.detailsBookDescription);

        detailedTitle.setText(bookList.get(this.bookPosition).getTitle());
        Glide.with(this).load(bookList.get(this.bookPosition).getImg()).into(detailedImg);
        detailedAuthor.setText(bookList.get(this.bookPosition).getAuthor());
        detailedGenre.setText(bookList.get(this.bookPosition).getGenre());
        detailedPublished.setText(bookList.get(this.bookPosition).getPublished());
        detailedIsbn.setText(bookList.get(this.bookPosition).getIsbn());
        detailedDescription.setText(bookList.get(this.bookPosition).getDescription());
    }
}