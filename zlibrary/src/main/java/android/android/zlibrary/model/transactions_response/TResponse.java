package android.android.zlibrary.model.transactions_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TResponse {

    @SerializedName("transactions")
    @Expose
    private List<Transaction> transactionList = null;

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }
}
