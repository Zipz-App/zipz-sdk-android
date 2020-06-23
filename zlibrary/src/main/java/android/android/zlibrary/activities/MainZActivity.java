package android.android.zlibrary.activities;

import android.android.zlibrary.R;
import android.android.zlibrary.app.ZipzApplication;
import android.android.zlibrary.help.AppStartModel;
import android.android.zlibrary.help.LogManager;
import android.android.zlibrary.model.init_response.InitResponse;
import android.android.zlibrary.retrofit.RestClient;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

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
        initReq();
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

    public static void initReq() {
        JsonObject jsonObject = new JsonObject();
        AppStartModel appStartModel = ZipzApplication.getInstance().getAppStartModel();
        jsonObject.addProperty("app_id", "7701890734418364");
        jsonObject.addProperty("app_secret", "TZdpfS4RvXxIzECimZ8BhT22LHumWfVe");
        jsonObject.addProperty("uuid", ZipzApplication.getInstance().getmSessionManager().getUUID());
        jsonObject.addProperty("device", appStartModel.getDEVICE());
        jsonObject.addProperty("os", appStartModel.getOS());
        jsonObject.addProperty("os_version", appStartModel.getOS_VERSION());
        jsonObject.addProperty("sdk_version", 1);
        TelephonyManager telephonyManager = ((TelephonyManager) ZipzApplication.getInstance().getSystemService(Context.TELEPHONY_SERVICE));
        String simOperatorName = telephonyManager.getSimOperatorName();
        if (simOperatorName != null) {
            jsonObject.addProperty("carrier", simOperatorName);
        } else {
            jsonObject.addProperty("carrier", "");
        }

        Call<InitResponse> initCall = RestClient.getInstance().service.init(jsonObject);
        initCall.enqueue(new Callback<InitResponse>() {
            @Override
            public void onResponse(Call<InitResponse> call, Response<InitResponse> response) {
                Log.d("init code", "response code" + response.code() + "");
                Log.d("init error", "error body" + response.errorBody() + "");
                if (response.isSuccessful() && response.code() == HttpURLConnection.HTTP_OK) {
                    InitResponse initResponse = response.body();
                    assert initResponse != null;
                    if (initResponse.getResponse().getToken() != null) {
                        String token = initResponse.getResponse().getToken();
                        ZipzApplication.getInstance().getmSessionManager().setToken(token);
                        getUserToken();

                    }

                }
            }

            @Override
            public void onFailure(Call<InitResponse> call, Throwable t) {
                Log.d("init", "onFailure() called with: call = [" + call + "], t = [" + t + "]");
            }
        });

    }

    public static String getUserToken() {
        String token = ZipzApplication.getInstance().getmSessionManager().getToken();
        Log.d("token", "getUserInfo() called with: appUser = [" + token + "]");
        return token;
    }

    private void initRequest() {
        JsonObject jsonObject = new JsonObject();
        AppStartModel appStartModel = ZipzApplication.getInstance().getAppStartModel();
        jsonObject.addProperty("app_id", "7701890734418364");
        jsonObject.addProperty("app_secret", "TZdpfS4RvXxIzECimZ8BhT22LHumWfVe");
        jsonObject.addProperty("uuid", ZipzApplication.getInstance().getmSessionManager().getUUID());
        jsonObject.addProperty("device", appStartModel.getDEVICE());
        jsonObject.addProperty("os", appStartModel.getOS());
        jsonObject.addProperty("os_version", appStartModel.getOS_VERSION());
        jsonObject.addProperty("sdk_version", 1);
        TelephonyManager telephonyManager = ((TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE));
        String simOperatorName = telephonyManager.getSimOperatorName();
        if (simOperatorName != null) {
            jsonObject.addProperty("carrier", simOperatorName);
        } else {
            jsonObject.addProperty("carrier", "");
        }

        Call<InitResponse> initCall = RestClient.getInstance().service.init(jsonObject);
        initCall.enqueue(new Callback<InitResponse>() {
            @Override
            public void onResponse(Call<InitResponse> call, Response<InitResponse> response) {
                Log.d("init code", "response code" + response.code() + "");
                Log.d("init error", "error body" + response.errorBody() + "");
                if (response.isSuccessful() && response.code() == HttpURLConnection.HTTP_OK) {
                    InitResponse initResponse = response.body();
//                    assert initResponse != null;
//                    if (initResponse.getResponse().getAppUser() != null) {
//                        if (initResponse.getResponse().getAppUser().getFirstName() != null) {
//                            String firstName = initResponse.getResponse().getAppUser().getFirstName();
//                            String lastName = initResponse.getResponse().getAppUser().getLastName();
//                            Log.d("username", "onResponse() called with: call = [" + firstName + "], response = [" + lastName + "]");
//                            name = firstName + " " + lastName;
//                        }
//
//                    }

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
        if (itemId == R.id.nav_profile) {
            if (isValidDestination(R.id.profileScreen)) {
                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.profileScreen);
            }
        } else if (itemId == R.id.nav_home) {
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
