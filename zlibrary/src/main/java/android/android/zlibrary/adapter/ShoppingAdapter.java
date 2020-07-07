package android.android.zlibrary.adapter;

import android.android.zlibrary.R;
import android.android.zlibrary.fragments.VenueClusterDetailsFragment;
import android.android.zlibrary.model.venuecluster_response.VenueCluster;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ShoppingAdapter extends RecyclerView.Adapter<ShoppingAdapter.ViewHolder> {
    private List<VenueCluster> venueClusters;

    public ShoppingAdapter(List<VenueCluster> listdata) {
        this.venueClusters = listdata;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final VenueCluster venueCluster = venueClusters.get(position);

        holder.tvShoppingName.setText(venueCluster.getName());
        Picasso.get().load(venueCluster.getImage()).into(holder.ivShoppingLogo);
        holder.llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                VenueClusterDetailsFragment myFragment = VenueClusterDetailsFragment.
                        newInstance(venueCluster.getUuid(), "shopping", venueCluster.getName(), venueCluster.getAddress(), venueCluster.getImage());
                activity.getSupportFragmentManager().
                        beginTransaction().
                        replace(R.id.nav_host_fragment, myFragment).
                        addToBackStack(null).
                        commitAllowingStateLoss();
            }
        });
    }


    @Override
    public int getItemCount() {
        return venueClusters.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivShoppingLogo;
        public TextView tvShoppingName;
        public LinearLayout llItem;

        public ViewHolder(View itemView) {
            super(itemView);
            this.ivShoppingLogo = itemView.findViewById(R.id.ivShoppingLogo);
            this.tvShoppingName = itemView.findViewById(R.id.tvShoppingName);
            this.llItem = itemView.findViewById(R.id.llItem);
        }
    }

}
