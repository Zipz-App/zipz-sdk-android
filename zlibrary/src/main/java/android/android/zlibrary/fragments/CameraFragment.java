package android.android.zlibrary.fragments;

import android.Manifest;
import android.android.zlibrary.R;
import android.android.zlibrary.app.ZipzApplication;
import android.android.zlibrary.help.BarCodeScanInterface;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.BeepManager;
import com.google.zxing.client.android.Intents;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.journeyapps.barcodescanner.DefaultDecoderFactory;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CameraFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_camera, container, false);
    }

    private BarCodeScanInterface barCodeScanInterface;
    private static final int RC_HANDLE_CAMERA_PERM = 2;
    private DecoratedBarcodeView barcodeView;
    private BeepManager beepManager;
    private String lastCode;

    private BarcodeCallback callback = new BarcodeCallback() {
        @Override
        public void barcodeResult(BarcodeResult result) {
            if (result == null || result.getText() == null || result.getText().equals(lastCode)) {
                // Prevent duplicate scans
                return;
            }

            lastCode = result.getText();

            if (lastCode != null && lastCode.toLowerCase().contains("zipz app")) {
                if (beepManager != null) {
                    beepManager.playBeepSoundAndVibrate();
                }
                if (barCodeScanInterface != null) {
                    barCodeScanInterface.onQRCodeFound(lastCode);

                }
                ZipzApplication.getInstance().getmSessionManager().insertQRCode(lastCode);
                removeCameraFragment();
            }
        }

        @Override
        public void possibleResultPoints(List<ResultPoint> resultPoints) {
        }
    };

    public CameraFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // barCodeScanInterface = (BarCodeScanInterface) getParentFragment();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        barCodeScanInterface = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        barcodeView = view.findViewById(R.id.barcodeScanner);

        Collection<BarcodeFormat> formats = Collections.singletonList(BarcodeFormat.QR_CODE);
        barcodeView.getBarcodeView().setDecoderFactory(new DefaultDecoderFactory(formats));

        Intent intent = new Intent();
        intent.putExtra(Intents.Scan.CAMERA_ID, Camera.CameraInfo.CAMERA_FACING_BACK);

        barcodeView.initializeFromIntent(intent);
        barcodeView.decodeContinuous(callback);
        barcodeView.setStatusText("");

        FragmentActivity activity = getActivity();
        if (activity != null) {
            beepManager = new BeepManager(activity);

            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, RC_HANDLE_CAMERA_PERM);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case RC_HANDLE_CAMERA_PERM: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    barcodeView.resume();
                } else {
//                    Helper.showAlertDialog(getContext(), getString(R.string.app_name), getString(R.string.CameraPermissionDeniedError), new OnClickOkListener() {
//                        @Override
//                        public void onClickOk() {
//                            if (barCodeScanInterface != null) {
//                                barCodeScanInterface.onCameraPermissionRejected();
//                            }
//                        }
//                    });
                }
                break;
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        barcodeView.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        barcodeView.pause();
    }


    private void removeCameraFragment() {
        try {

            getActivity().getSupportFragmentManager().popBackStack();
        } catch (Exception e) {
        }
    }
}
