package android.android.zlibrary.fragments;

import android.android.zlibrary.R;
import android.android.zlibrary.adapter.TransactionAdapter;
import android.android.zlibrary.app.ZipzApplication;
import android.android.zlibrary.model.error_response.ErrorMessage;
import android.android.zlibrary.model.error_response.ErrorResponse;
import android.android.zlibrary.model.transactions_response.TResponse;
import android.android.zlibrary.model.transactions_response.Transaction;
import android.android.zlibrary.model.transactions_response.TransactionResponse;
import android.android.zlibrary.retrofit.RestClient;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.android.zlibrary.fragments.VenueDetailsFragment.checkMessage;
import static android.android.zlibrary.fragments.VenueDetailsFragment.checkRequestCode;

public class TransactionFragment extends Fragment {

    private RecyclerView rvTransaction;
    private List<Transaction> transactionList;


    public static TransactionFragment newInstance() {
        TransactionFragment transactionFragment = new TransactionFragment();
        return transactionFragment;
    }

    ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_transaction, container, false);
        rvTransaction = root.findViewById(R.id.rvTransaction);
        transactionList = new ArrayList<>();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                populateLists();
                String message = checkMessage();
                int code = checkRequestCode();
            }
        }, 1000);

        transactionsRequest();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public static List<Transaction> getTransactionList() {
        return ZipzApplication.getInstance().getmSessionManager().getTransactionList();
    }

    private void populateLists() {
        transactionList = getTransactionList();
        if (transactionList != null && transactionList.size() != 0) {
            TransactionAdapter adapter = new TransactionAdapter(transactionList);
            rvTransaction.setHasFixedSize(true);
            LinearLayoutManager layoutManager
                    = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            rvTransaction.setLayoutManager(layoutManager);
            rvTransaction.setAdapter(adapter);
        }
    }
    public static ErrorResponse getMessageErrorTransaction() {
        return ZipzApplication.getInstance().getmSessionManager().getMessageErrorTransactions();
    }

    public static void transactionsRequest() {

        Call<TransactionResponse> transactionResponseCall = RestClient.getInstance().service
                .transactions();

        transactionResponseCall.enqueue(new Callback<TransactionResponse>() {
            @Override
            public void onResponse(Call<TransactionResponse> call, Response<TransactionResponse> response) {
                if (response.isSuccessful() && response.code() == HttpURLConnection.HTTP_OK) {
                    Log.d("offer details", "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                    TransactionResponse reserveOfferResponse = response.body();
                    if (reserveOfferResponse.getStatus().getSuccess()) {
                        TResponse tResponse = response.body().getResponse();
                        if (tResponse != null) {
                            List<Transaction> transactionList = tResponse.getTransactionList();
                            String errorMessage = "Success.";
                            ZipzApplication.getInstance().getmSessionManager().saveMessageOffer(200, errorMessage);
                            //checkRequestCode();
                            checkMessage();
                            ZipzApplication.getInstance().getmSessionManager().insertTransaction(transactionList);

                        }

                    }
                    else {
                        if (!response.isSuccessful() && response.code() != HttpURLConnection.HTTP_OK)
                        {
                            Gson gson = new Gson();
                            try {
                                ErrorResponse errorResponse = gson.fromJson(response.errorBody().string(), ErrorResponse.class);
                                ZipzApplication.getInstance().getmSessionManager().saveMessageErrorTransactions(errorResponse);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }
            }

            @Override
            public void onFailure(Call<TransactionResponse> call, Throwable t) {
                Log.d("reserve Response", "onFailure() called with: call = [" + call + "], t = [" + t + "]");
            }
        });

    }

}
