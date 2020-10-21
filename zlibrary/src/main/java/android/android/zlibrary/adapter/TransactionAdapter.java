package android.android.zlibrary.adapter;

import android.android.zlibrary.R;
import android.android.zlibrary.model.transactions_response.Transaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {

    private List<Transaction> transactionList;

    public TransactionAdapter(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    @NonNull
    @Override
    public TransactionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.offer_list_item, parent, false);
        TransactionAdapter.ViewHolder viewHolder = new TransactionAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdapter.ViewHolder holder, int position) {
        final Transaction transaction = transactionList.get(position);

        holder.tvOfferNames.setText(transaction.getVenue().getDescription());
        Picasso.get().load(transaction.getOffer().getImage()).into(holder.imgOffer);
        holder.tvOfferRegularPrice.setText(transaction.getOffer().getName());
        holder.tvOfferRegularPrice.setText(transaction.getOffer().getDescription());
        Picasso.get().load(transaction.getVenue().getImage());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgOffer;
        public TextView tvOfferNames, tvOfferRegularPrice,
                tvOfferPrice, tvOfferDiscount;
        public LinearLayout llItem;

        public ViewHolder(View itemView) {
            super(itemView);
            this.imgOffer = itemView.findViewById(R.id.imgOffer);
            this.tvOfferNames = itemView.findViewById(R.id.tvOfferNames);
            this.tvOfferRegularPrice = itemView.findViewById(R.id.tvOfferRegularPrice);
            this.tvOfferPrice = itemView.findViewById(R.id.tvOfferPrice);
            this.tvOfferDiscount = itemView.findViewById(R.id.tvOfferDiscount);
            this.llItem = itemView.findViewById(R.id.linearLayout);

        }
    }
}
