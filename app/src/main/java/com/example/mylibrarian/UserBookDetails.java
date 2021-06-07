package com.example.mylibrarian;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class UserBookDetails extends Fragment {

    private List<BookModel> userBookList;
    private int bookPosition;

    public UserBookDetails() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_book_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.userBookList = UserBookList.getInstance().getUserBookList();
        this.bookPosition = UserBookList.getInstance().getCurrentPos();

        TextView detailedTitle = view.findViewById(R.id.detailsUserBookTitle);
        ImageView detailedImg = view.findViewById(R.id.detailsUserBookImg);
        TextView detailedAuthor = view.findViewById(R.id.detailsUserBookAuthor);
        TextView detailedGenre = view.findViewById(R.id.detailsUserBookGenre);
        TextView detailedPublished = view.findViewById(R.id.detailsUserBookPublished);
        TextView detailedIsbn = view.findViewById(R.id.detailsUserBookIsbn);
        TextView detailedPages = view.findViewById(R.id.detailsUserBookPages);
        TextView detailedDescription = view.findViewById(R.id.detailsUserBookDescription);

        detailedTitle.setText(userBookList.get(this.bookPosition).getTitle());
        Glide.with(this).load(userBookList.get(this.bookPosition).getImg()).into(detailedImg);
        detailedAuthor.setText(userBookList.get(this.bookPosition).getAuthor());
        detailedGenre.setText(userBookList.get(this.bookPosition).getGenre());
        detailedPublished.setText(userBookList.get(this.bookPosition).getPublished());
        detailedIsbn.setText(userBookList.get(this.bookPosition).getIsbn());
        detailedPages.setText(userBookList.get(this.bookPosition).getPages().toString());
        detailedDescription.setText(userBookList.get(this.bookPosition).getDescription());

        view.findViewById(R.id.detailsUserBookBackBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(UserBookDetails.this).navigate(R.id.action_userBookDetails_to_userBookList);
            }
        });
    }
}