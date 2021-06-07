package com.example.mylibrarian;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class UserBookAdapter extends RecyclerView.Adapter<UserBookAdapter.ViewHolder> {

    private Context userContext;
    private List<BookModel> userBookList;
    private NavController navController;

    public UserBookAdapter(Context userContext, List<BookModel> userBookList, NavController navController) {
        this.userContext = userContext;
        this.userBookList = userBookList;
        this.navController = navController;
    }

    @NonNull
    @Override
    public UserBookAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView;
        LayoutInflater inflater = LayoutInflater.from(userContext);
        myView = inflater.inflate(R.layout.user_book_item, parent, false);
        return new UserBookAdapter.ViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserBookAdapter.ViewHolder holder, int position) {
        holder.title.setText(userBookList.get(position).getTitle());
        holder.author.setText(userBookList.get(position).getAuthor());
        holder.advancement.setText(userBookList.get(position).getPages().toString());
        holder.progressBar.setProgress(50);
        Glide.with(userContext).load(userBookList.get(position).getImg()).into(holder.img);
        holder.bookDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_userBookList_to_userBookDetails);
                UserBookList.getInstance().setCurrentPos(position);
            }
        });
        holder.bookDeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserBookList.getInstance().removeFromUserBookList(position);
                navController.navigate(R.id.action_userBookList_self);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userBookList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView author;
        TextView advancement;
        ProgressBar progressBar;
        ImageView img;
        Button bookDetailsBtn;
        Button bookDeleteBtn;

        public ViewHolder (@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.userBookTitle);
            this.author = itemView.findViewById(R.id.userBookAuthor);
            this.advancement = itemView.findViewById(R.id.userBookAdvancement);
            this.progressBar = itemView.findViewById(R.id.userBookProgressBar);
            this.img = itemView.findViewById(R.id.userBookImg);
            this.bookDetailsBtn = itemView.findViewById(R.id.userBookDetailsBtn);
            this.bookDeleteBtn = itemView.findViewById(R.id.userBookDeleteBtn);
        }
    }

}
