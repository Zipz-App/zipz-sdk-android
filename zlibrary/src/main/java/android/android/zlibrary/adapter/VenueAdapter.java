package android.android.zlibrary.adapter;

import android.android.zlibrary.R;
import android.android.zlibrary.model.venueclusterdetails_response.Venue;
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

public class VenueAdapter extends RecyclerView.Adapter<VenueAdapter.ViewHolder> {
    private List<Venue> venueList;


    public VenueAdapter(List<Venue> venueList) {
        this.venueList = venueList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.venue_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Venue venue = venueList.get(position);

        holder.txtVenueName.setText(venue.getName());
        holder.txtVenueAdrress.setText(venue.getAddress());
        holder.txtVenueDistance.setText("1.2");
        holder.txtVenueWorkingHours.setText(venue.getCategory().getName());
        holder.txtVenueNeighbornhood.setText(venue.getNeighborhood());
        Picasso.get().load(venue.getImage()).into(holder.imgVenue);

    }

    @Override
    public int getItemCount() {
        return venueList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgVenue;
        public TextView txtVenueName, txtVenueDistance, txtVenueNeighbornhood,
                txtVenueAdrress, txtVenueWorkingHours;
        public LinearLayout llItem;

        public ViewHolder(View itemView) {
            super(itemView);
            this.imgVenue = itemView.findViewById(R.id.imgVenue);
            this.txtVenueName = itemView.findViewById(R.id.txtVenueName);
            this.txtVenueDistance = itemView.findViewById(R.id.txtVenueDistance);
            this.txtVenueNeighbornhood = itemView.findViewById(R.id.txtVenueNeighbornhood);
            this.txtVenueAdrress = itemView.findViewById(R.id.txtVenueAdrress);
            this.txtVenueWorkingHours = itemView.findViewById(R.id.txtVenueWorkingHours);

        }
    }

}
