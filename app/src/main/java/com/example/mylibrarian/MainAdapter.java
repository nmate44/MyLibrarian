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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private Context mainContext;
    private List<BookModel> userBookList;
    private NavController navController;

    public MainAdapter(Context mainContext, List<BookModel> userBookList, NavController navController) {
        this.mainContext = mainContext;
        this.userBookList = userBookList;
        this.navController = navController;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView;
        LayoutInflater inflater = LayoutInflater.from(mainContext);
        myView = inflater.inflate(R.layout.book_item, parent, false);
        return new ViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(userBookList.get(position).getTitle());
        holder.author.setText(userBookList.get(position).getAuthor());
        holder.advancement.setText(userBookList.get(position).getPages().toString());
        holder.progressBar.setProgress(50);
        Glide.with(mainContext).load(userBookList.get(position).getImg()).into(holder.img);
        holder.bookDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_bookList_to_bookDetails3);
                BookList.getInstance().setCurrentPos(position);
                System.out.println("*Button " + position + " clicked*");
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

        public ViewHolder (@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.detailsTitle);
            this.author = itemView.findViewById(R.id.bookAuthor);
            this.advancement = itemView.findViewById(R.id.bookAdvancement);
            this.progressBar = itemView.findViewById(R.id.bookProgressBar);
            this.img = itemView.findViewById(R.id.bookImg);
            this.bookDetailsBtn = itemView.findViewById(R.id.bookDetailsBtn);
        }
    }

}
