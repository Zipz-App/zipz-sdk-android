package android.android.zlibrary.model.redeem_transaction;

import android.android.zlibrary.model.transactions_response.Transaction;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RTResponse {

    @SerializedName("redeem_transaction")
    @Expose
    private RedeemTransaction redeemTransaction;

    public RedeemTransaction getRedeemTransaction() {
        return redeemTransaction;
    }

    public void setRedeemTransaction(RedeemTransaction redeemTransaction) {
        this.redeemTransaction = redeemTransaction;
    }
}
