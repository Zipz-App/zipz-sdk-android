package android.android.zlibrary.adapter;

import android.android.zlibrary.R;
import android.android.zlibrary.activities.VenueClusterActivity;
import android.android.zlibrary.help.Helper;
import android.android.zlibrary.model.VenueListModel;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VenueClustersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<VenueListModel> dataSet;
    private Context context;
    private Drawable favorite_drawable;
    private Drawable unfavorite_drawable;
    private String openedString;
    private String closedString;
    private static final int VIEW_TYPE_EMPTY = 0;
    private static final int VIEW_TYPE_VENUE = 1;
    private String thereAreNoVenues;
    private String thereAreNoFavoriteVenues;
    private String loading;
    private boolean loadingData = false;
    private boolean isFavoriteSelected = false;
    private boolean isDistanceVisibility = false;


    public VenueClustersAdapter(Context context, List<VenueListModel> dataSet) {
        this.context = context;
        this.dataSet = dataSet;
        this.favorite_drawable = AppCompatResources.getDrawable(context, R.drawable.ic_favorite_yellow);
        this.unfavorite_drawable = AppCompatResources.getDrawable(context, R.drawable.ic_favorite_gray);
        this.openedString = context.getString(R.string.RedeWorkingHoursOpened);
        this.closedString = context.getString(R.string.RedeWorkingHoursClosed);
        this.thereAreNoVenues = "";
        this.thereAreNoFavoriteVenues = "";
        this.thereAreNoFavoriteVenues = context.getString(R.string.RedeMessageThereAreNoFavoriteVenues);
        this.loading = context.getString(R.string.loading);

    }

    public void setLoadingData(boolean loadingData) {
        this.loadingData = loadingData;
    }

    public void setFavoriteSelected(boolean favoriteSelected) {
        isFavoriteSelected = favoriteSelected;
    }

    public void setDistanceVisibility(int position) {
        if (position == 1) {
            isDistanceVisibility = true;
        } else {
            isDistanceVisibility = false;
        }
    }

    class ViewHolderEmpty extends RecyclerView.ViewHolder {
        private TextView txtInfo;

        ViewHolderEmpty(View itemView) {
            super(itemView);
            txtInfo = itemView.findViewById(R.id.txtInfo);
        }
    }

    class ViewHolderVenue extends RecyclerView.ViewHolder {
        private LinearLayout relativeMain;
        private ImageView imgVenue;
        private ImageView imgMute;
        private TextView txtVenueName;
        private TextView txtVenueType;
        private TextView txtVenueNeighbornhood;
        private TextView txtVenueAdrress;
        private TextView txtVenueWorkingHours;
        private TextView txtVenueDistance;

        ViewHolderVenue(View itemView) {
            super(itemView);

            relativeMain = itemView.findViewById(R.id.relativeMain);
            imgVenue = itemView.findViewById(R.id.imgVenue);
            imgMute = itemView.findViewById(R.id.imgMute);
            txtVenueName = itemView.findViewById(R.id.txtVenueClusterName);
            txtVenueType = itemView.findViewById(R.id.txtVenueType);
            txtVenueNeighbornhood = itemView.findViewById(R.id.txtVenueNeighbornhood);
            txtVenueAdrress = itemView.findViewById(R.id.txtVenueAdrress);
            txtVenueWorkingHours = itemView.findViewById(R.id.txtVenueWorkingHours);
            txtVenueDistance = itemView.findViewById(R.id.txtVenueDistance);
            imgVenue.getLayoutParams().width = Helper.getImgDimen(context);
            imgVenue.getLayoutParams().height = Helper.getImgDimen(context);

            if (txtVenueDistance.getMeasuredHeight() == 0)
                txtVenueDistance.measure(View.MeasureSpec.makeMeasureSpec(itemView.getWidth(), View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

            Drawable drawableDistance = AppCompatResources.getDrawable(context, R.drawable.ic_zipz_distance_color);
            if (drawableDistance != null) {
                drawableDistance.setBounds(0, 0, 43, 43);
                txtVenueDistance.setCompoundDrawables(drawableDistance, null, null, null);
            }

        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        if (viewType == VIEW_TYPE_EMPTY)
            viewHolder = new VenueClustersAdapter.ViewHolderEmpty(LayoutInflater.from(context).inflate(R.layout.recycler_item_empty_view, parent, false));
        else
            viewHolder = new VenueClustersAdapter.ViewHolderVenue(LayoutInflater.from(context).inflate(R.layout.recycler_item_venue_cluster_list, parent, false));
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType()) {
            case VIEW_TYPE_EMPTY: {
                VenueClustersAdapter.ViewHolderEmpty viewHolderEmpty = (VenueClustersAdapter.ViewHolderEmpty) holder;
                viewHolderEmpty.txtInfo.setText(loadingData ? loading : isFavoriteSelected ? thereAreNoFavoriteVenues : thereAreNoVenues);
                break;
            }
            case VIEW_TYPE_VENUE: {
                if (position >= 0 && position < dataSet.size()) {
                    VenueClustersAdapter.ViewHolderVenue viewHolderVenue = (VenueClustersAdapter.ViewHolderVenue) holder;

                    final VenueListModel item = dataSet.get(position);

                    if (item != null) {
                        viewHolderVenue.txtVenueName.setText(item.getPlace_name() + " ");
                        viewHolderVenue.txtVenueType.setText(item.getPlace_category_name());
                        String wholeAdress = item.getAddress();

                        viewHolderVenue.txtVenueNeighbornhood.setText(item.getNeighborhood());
                        viewHolderVenue.txtVenueAdrress.setText(item.getAddress());


                        if (isDistanceVisibility) {
                            String distance = item.getFormatedDistance();
                            viewHolderVenue.txtVenueDistance.setText(distance);
                            viewHolderVenue.txtVenueDistance.setVisibility(distance.equals("") ? View.GONE : View.VISIBLE);
                        } else {
                            viewHolderVenue.txtVenueDistance.setVisibility(View.GONE);
                        }

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            ViewCompat.setTransitionName(viewHolderVenue.relativeMain, "container" + position);
                            ViewCompat.setTransitionName(viewHolderVenue.imgVenue, "imgVenue" + position);
                            ViewCompat.setTransitionName(viewHolderVenue.txtVenueName, "txtVenueName" + position);
                            ViewCompat.setTransitionName(viewHolderVenue.txtVenueType, "txtVenueType" + position);
                            ViewCompat.setTransitionName(viewHolderVenue.imgMute, "imgMute" + position);
                        }

                        final View relativeMain = viewHolderVenue.relativeMain;
                        final View imgVenue = viewHolderVenue.imgVenue;
                        final View txtVenueName = viewHolderVenue.txtVenueName;
                        final View txtVenueType = viewHolderVenue.txtVenueType;
                        final View imgMute = viewHolderVenue.imgMute;

                        viewHolderVenue.relativeMain.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(context, VenueClusterActivity.class);
                                intent.putExtra("name", item.getPlace_name());
                                intent.putExtra("type", item.getPlace_category_name());
                                intent.putExtra("address", item.getAddress());
                                context.startActivity(intent);

                            }
                        });
                    }
                }
                break;
            }
        }
    }

    @Override
    public int getItemCount() {
        if (dataSet.size() == 0) return 1;
        return dataSet.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (dataSet.size() == 0)
            return VIEW_TYPE_EMPTY;
        else
            return VIEW_TYPE_VENUE;
    }
}
