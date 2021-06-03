package com.example.mylibrarian;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String apiCall = "https://run.mocky.io/v3/a903d1b6-1437-4417-bc21-1b5e1c571d8f";

    List<BookModel> bookList;
    RecyclerView booksView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataGetter dataGetter = new DataGetter();
        bookList = new ArrayList<>();
        booksView = findViewById(R.id.booksView);

        dataGetter.execute();
    }

    public class DataGetter extends AsyncTask<String, String, String> {
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
                JSONArray jsonArray = jsonObject.getJSONArray("userBooks");
                for(int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonListElement = jsonArray.getJSONObject(i);
                    BookModel book = new BookModel();
                    book.setId(jsonListElement.getInt("id"));
                    book.setTitle(jsonListElement.getString("title"));
                    book.setAuthor(jsonListElement.getString("author"));
                    book.setGenre(jsonListElement.getString("genre"));
                    book.setDescription(jsonListElement.getString("description"));
                    book.setIsbn(jsonListElement.getString("isbn"));
                    book.setAdvancement((float)jsonListElement.getDouble("advancement"));
                    book.setImg(jsonListElement.getString("img"));
                    bookList.add(book);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            FillBooksView(bookList);
        }
    }

    private void FillBooksView(List<BookModel> bookList) {
        MainAdapter adapter = new MainAdapter(this, bookList);
        booksView.setLayoutManager(new LinearLayoutManager(this));
        booksView.setAdapter(adapter);
    }
}