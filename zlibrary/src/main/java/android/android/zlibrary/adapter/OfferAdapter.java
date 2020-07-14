package android.android.zlibrary.adapter;

import android.android.zlibrary.R;
import android.android.zlibrary.activities.OfferActivity;
import android.android.zlibrary.model.venuedetails_response.PublicOffer;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.ViewHolder> {
    private List<PublicOffer> publicOffers;


    public OfferAdapter(List<PublicOffer> publicOffers) {
        this.publicOffers = publicOffers;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.offer_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final PublicOffer offer = publicOffers.get(position);

        holder.tvOfferNames.setText(offer.getName());
        holder.tvOfferRegularPrice.setText(offer.getFullPrice());
        holder.tvOfferRegularPrice.setText(offer.getOfferPrice());
        Picasso.get().load(offer.getImage()).into(holder.imgOffer);
        holder.llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Intent intent = new Intent(activity, OfferActivity.class);
                intent.putExtra("uuid", offer.getUuid());
                activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return publicOffers.size();
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
