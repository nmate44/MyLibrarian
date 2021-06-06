package com.example.mylibrarian;

import android.content.Context;
import android.os.AsyncTask;

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
import java.util.List;

public class DataGetter extends AsyncTask<String, String, String> {
    private final String apiCall = "https://run.mocky.io/v3/f1a63d3b-343a-4ec5-83cb-9304816bb746";

    List<BookModel> bookList;
    Context context;
    RecyclerView recyclerView;

    public DataGetter(List<BookModel> bookList, Context context, RecyclerView recyclerView) {
        this.bookList = bookList;
        this.context = context;
        this.recyclerView = recyclerView;
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
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        FillBooksView(context, bookList, recyclerView);
    }

    private void FillBooksView(Context context, List<BookModel> bookList, RecyclerView booksView) {
        MainAdapter adapter = new MainAdapter(context, bookList);
        booksView.setLayoutManager(new LinearLayoutManager(context));
        booksView.setAdapter(adapter);
    }
}