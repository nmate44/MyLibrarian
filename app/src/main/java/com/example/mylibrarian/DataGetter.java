package com.example.mylibrarian;

import android.content.Context;
import android.os.AsyncTask;

import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DataGetter extends AsyncTask<String, String, String> {
    private final String apiCall = "https://run.mocky.io/v3/f1a63d3b-343a-4ec5-83cb-9304816bb746";

    List<BookModel> bookList = new ArrayList<>();
    Context context;
    RecyclerView recyclerView;
    NavController navController;
    MainAdapter adapter;

    public DataGetter(Context context, NavController navController) {
        this.context = context;
        this.navController = navController;
        this.adapter = new MainAdapter(context, this.bookList, navController);
    }

    @Override
    protected String doInBackground(String... strings) {
        String rawData = "";
        try {
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL(apiCall);
                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream inputStream = urlConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                int data = inputStreamReader.read();
                while(data != -1) {
                    rawData += (char) data;
                    data = inputStreamReader.read();
                }
                return rawData;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rawData;
    }

    @Override
    protected void onPostExecute(String s) {
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray jsonArray = jsonObject.getJSONArray("books");
            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonListElement = jsonArray.getJSONObject(i);
                BookModel book = new BookModel();
                book.setId(jsonListElement.getString("id"));
                book.setTitle(jsonListElement.getString("title"));
                book.setAuthor(jsonListElement.getString("author"));
                book.setPublished(jsonListElement.getString("published"));
                book.setGenre(jsonListElement.getString("genre"));
                book.setDescription(jsonListElement.getString("description"));
                book.setPages(jsonListElement.getInt("pages"));
                book.setIsbn(jsonListElement.getString("isbn"));
                book.setImg(jsonListElement.getString("img"));
                this.bookList.add(book);
                System.out.println("Adding book from API to List: " + book);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        fillBooksView(this.context);
    }

    public void fillBooksView(Context context) {
        System.out.println("Filling booksView w/: " + this.bookList);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        this.recyclerView.setAdapter(adapter);
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public List<BookModel> getBookList() {
        System.out.println("Getting book list: " + this.bookList);
        return this.bookList;
    }
}