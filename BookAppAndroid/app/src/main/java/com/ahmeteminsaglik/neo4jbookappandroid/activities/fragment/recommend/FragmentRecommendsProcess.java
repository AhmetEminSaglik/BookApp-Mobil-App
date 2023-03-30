package com.ahmeteminsaglik.neo4jbookappandroid.activities.fragment.recommend;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.ahmeteminsaglik.neo4jbookappandroid.model.Author;
import com.ahmeteminsaglik.neo4jbookappandroid.model.EnumRecommendReason;
import com.ahmeteminsaglik.neo4jbookappandroid.model.EnumUser;
import com.ahmeteminsaglik.neo4jbookappandroid.model.RecommendedAuthor;
import com.ahmeteminsaglik.neo4jbookappandroid.model.Book;
import com.ahmeteminsaglik.neo4jbookappandroid.model.RecommendedBook;
import com.ahmeteminsaglik.neo4jbookappandroid.model.response.abstracts.RestApiErrorResponse;
import com.ahmeteminsaglik.neo4jbookappandroid.model.response.abstracts.RestApiResponse;
import com.ahmeteminsaglik.neo4jbookappandroid.restapi.ManagerAll;
import com.ahmeteminsaglik.neo4jbookappandroid.utility.SharedPreferenceUtility;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class FragmentRecommendsProcess {

    private Context context;

    public FragmentRecommendsProcess(Context context) {
        this.context = context;
    }

    public List<RecommendedAuthor> getAuthorList() {
        return sendRecommendAuthorByPointRequest();
    }

    public List<RecommendedBook> getBookList() {
        List<RecommendedBook> recommendedBookList = new ArrayList<>();
        recommendedBookList.addAll(sendRecommendBookByTotalReadRequest());
        recommendedBookList.addAll(sendRecommendBookByPointRequest());
        return recommendedBookList;
    }

    private List<RecommendedAuthor> sendRecommendAuthorByPointRequest() {
        Call<RestApiResponse<List<Author>>> call = ManagerAll.getInstance().getRecommendAuthorListByPoint();

        try {
            List<Author> authorList = null;
            Response<RestApiResponse<List<Author>>> response = call.execute();
            if (response.code() == 200) {
                authorList = response.body().getData();

            } else/* if (response.code() == 400) */ {
                Gson gson = new Gson();
                RestApiErrorResponse errorResponse = gson.fromJson(response.errorBody().charStream(), RestApiErrorResponse.class);
                String errMsg = errorResponse.getMessage();
                if (errMsg != null) {
                    Log.e("Error  ", errMsg);
                } else {
                    Log.e("Error Message is empty : ", "no mesage");
                }
                Toast.makeText(context, errMsg, Toast.LENGTH_LONG).show();
            }
            return getAuthorRecommen(authorList, EnumRecommendReason.HIGH_POINT.getName());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<RecommendedBook> sendRecommendBookByPointRequest() {
        Call<RestApiResponse<List<Book>>> call = ManagerAll.getInstance().getRecommendedBookListByPoint();

        try {
            List<Book> readBookList = null;
            Response<RestApiResponse<List<Book>>> response = call.execute();
            if (response.code() == 200) {
                readBookList = response.body().getData();
            } else/* if (response.code() == 400) */ {
                Gson gson = new Gson();
                RestApiErrorResponse errorResponse = gson.fromJson(response.errorBody().charStream(), RestApiErrorResponse.class);
                String errMsg = errorResponse.getMessage();
                if (errMsg != null) {
                    Log.e("Error  ", errMsg);
                } else {
                    Log.e("Error Message is empty : ", "no mesage");
                }
                Toast.makeText(context, errMsg, Toast.LENGTH_LONG).show();
            }
            return convertBookListToRecommedBookList(readBookList, EnumRecommendReason.HIGH_POINT.getName());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    private List<RecommendedBook> sendRecommendBookByTotalReadRequest() {
        Call<RestApiResponse<List<Book>>> call = ManagerAll.getInstance().getRecommendedBookListByTotalRead();

        try {
            List<Book> readBookList = null;
            Response<RestApiResponse<List<Book>>> response = call.execute();
            if (response.code() == 200) {
                readBookList = response.body().getData();
            } else/* if (response.code() == 400) */ {
                Gson gson = new Gson();
                RestApiErrorResponse errorResponse = gson.fromJson(response.errorBody().charStream(), RestApiErrorResponse.class);
                String errMsg = errorResponse.getMessage();
                if (errMsg != null) {
                    Log.e("Error  ", errMsg);
                } else {
                    Log.e("Error Message is empty : ", "no mesage");
                }
                Toast.makeText(context, errMsg, Toast.LENGTH_LONG).show();
            }
            return convertBookListToRecommedBookList(readBookList, EnumRecommendReason.BEST_SELLER.getName());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    private List<RecommendedAuthor> getAuthorRecommen(List<Author> authorList, String reason) {
        List<RecommendedAuthor> recommendedAuthorList = new ArrayList<>();
        authorList.forEach(e -> {
            recommendedAuthorList.add(new RecommendedAuthor(e, reason));
        });
        return recommendedAuthorList;
    }

    private List<RecommendedBook> convertBookListToRecommedBookList(List<Book> bookList, String reason) {
        List<RecommendedBook> recommendedBookList = new ArrayList<>();
        for (Book book : bookList) {
            RecommendedBook recBook = new RecommendedBook(book, reason);
            recommendedBookList.add(recBook);
        }
        return recommendedBookList;
    }


}
