package android.android.zlibrary.activities;

import android.android.zlibrary.R;
import android.android.zlibrary.ZipzApplication;
import android.android.zlibrary.help.AppStartModel;
import android.android.zlibrary.help.LogManager;
import android.android.zlibrary.model.init_response.InitResponse;
import android.android.zlibrary.retrofit.RestClient;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;


public class MainZActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    public static String advId;
    public static double latitudeValue, longitudeValue;
    private FusedLocationProviderClient client;
    public static String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zmain);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        init();
        requestPermission();
        initRequest();
        client = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(MainZActivity.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        client.getLastLocation().addOnSuccessListener(MainZActivity.this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    latitudeValue = location.getLatitude();
                    longitudeValue = location.getLongitude();
                }
            }
        });
    }

    private void initRequest() {
        JsonObject jsonObject = new JsonObject();
        AppStartModel appStartModel = ZipzApplication.getInstance().getAppStartModel();
        jsonObject.addProperty("device", appStartModel.getDEVICE());
        jsonObject.addProperty("os", appStartModel.getOS());
        jsonObject.addProperty("os_version", appStartModel.getOS_VERSION());
        jsonObject.addProperty("app_version", "1");
        Call<InitResponse> initCall = RestClient.getInstance().service.init(jsonObject);
        initCall.enqueue(new Callback<InitResponse>() {
            @Override
            public void onResponse(Call<InitResponse> call, Response<InitResponse> response) {
                Log.d("init code", "response code" + response.code() + "");
                Log.d("init error", "error body" + response.errorBody() + "");
                if (response.isSuccessful() && response.code() == HttpURLConnection.HTTP_OK) {
                    InitResponse initResponse = response.body();
                    assert initResponse != null;
                    if (initResponse.getResponse().getAppUser() != null) {
                        if (initResponse.getResponse().getAppUser().getFirstName() != null) {
                            String firstName = initResponse.getResponse().getAppUser().getFirstName();
                            String lastName = initResponse.getResponse().getAppUser().getLastName();
                            Log.d("username", "onResponse() called with: call = [" + firstName + "], response = [" + lastName + "]");
                            name = firstName + " " + lastName;
                        }

                    }

                }
            }

            @Override
            public void onFailure(Call<InitResponse> call, Throwable t) {
                Log.d("init", "onFailure() called with: call = [" + call + "], t = [" + t + "]");
            }
        });
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, 1);
    }

    private void init() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.nav_coupons) {
            if (isValidDestination(R.id.couponsScreen)) {
                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.couponsScreen);
            }
        } else if (itemId == R.id.nav_profile) {
            if (isValidDestination(R.id.profileScreen)) {
                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.profileScreen);
            }
        } else if (itemId == R.id.nav_camera) {
            if (isValidDestination(R.id.cameraScreen)) {
                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.cameraScreen);
            }
        } else if (itemId==R.id.nav_home){
            if (isValidDestination(R.id.homeScreen)) {
                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.homeScreen);
            }
        }
        menuItem.setChecked(true);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(Navigation.findNavController(this, R.id.nav_host_fragment), drawerLayout);
    }

    private boolean isValidDestination(int destination) {
        return destination != Navigation.findNavController(this, R.id.nav_host_fragment).getCurrentDestination().getId();
    }

    private class GetAdvertiseId extends AsyncTask<Void, Void, AdvertisingIdClient.Info> {

        @Override
        protected AdvertisingIdClient.Info doInBackground(Void... voids) {
            AdvertisingIdClient.Info adInfo = null;
            try {
                adInfo = AdvertisingIdClient.getAdvertisingIdInfo(MainZActivity.this);
            } catch (IOException e) {
                LogManager.logError(e);
            } catch (IllegalStateException e) {
                LogManager.logError(e);
            } catch (GooglePlayServicesNotAvailableException e) {
                LogManager.logError(e);
                // Google Play services is not available entirely.
            } catch (GooglePlayServicesRepairableException e) {
                LogManager.logError(e);
            } catch (Exception e) {
                LogManager.logError(e);
            }

            return adInfo;
        }

        @Override
        protected void onPostExecute(@Nullable AdvertisingIdClient.Info adInfo) {
            super.onPostExecute(adInfo);
            if (adInfo != null) {
                final String id = adInfo.getId();
                if (id != null) {
                    advId = id;
                }
            }
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                int resultCode = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainZActivity.this);
                if (resultCode == ConnectionResult.SUCCESS) {
                    GetAdvertiseId getAdvertiseId = new GetAdvertiseId();
                    getAdvertiseId.execute();
                }
            }
        }, 0);
    }

}
