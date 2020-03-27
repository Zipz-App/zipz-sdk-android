package android.android.zlibrary.fragments;

import android.android.zlibrary.App;
import android.android.zlibrary.R;
import android.android.zlibrary.help.LogManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

import java.io.IOException;


public class ProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                int resultCode = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(getActivity());
                if (resultCode == ConnectionResult.SUCCESS) {
                    GetAdvertiseId getAdvertiseId = new GetAdvertiseId();
                    Log.d("id:", "run() called" + getAdvertiseId.toString() + "");
                    getAdvertiseId.execute();
                }
            }
        }, 700);
    }


    private static class GetAdvertiseId extends AsyncTask<Void, Void, AdvertisingIdClient.Info> {

        @Override
        protected AdvertisingIdClient.Info doInBackground(Void... voids) {
            AdvertisingIdClient.Info adInfo = null;
            try {
                adInfo = AdvertisingIdClient.getAdvertisingIdInfo(App.getInstance());
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
                    Log.d("id", "onPostExecute() = [" + id + "]");
                }
            }
        }
    }


}
