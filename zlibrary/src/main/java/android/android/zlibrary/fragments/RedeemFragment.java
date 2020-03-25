package android.android.zlibrary.fragments;

import android.android.zlibrary.R;
import android.android.zlibrary.help.BarCodeScanInterface;
import android.android.zlibrary.help.Helper;
import android.android.zlibrary.model.VenueListModel;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.squareup.picasso.Picasso;


public class RedeemFragment extends Fragment implements BarCodeScanInterface {

    private ImageView img;
    private TextView txtRedeemLabel;
    private TextView txtTitle;
    private TextView txtType;
    private TextView txtDescription;
    private TextView txtOfferPrice;
    private TextView txtRegularPrice;
    private TextView txtExpiresTime;
    private TextView txtServerName;
    private TextView txtVenueNameAndAddress;
    private Button btnRedeem;
    private Button btnShare;
    private Button btnContinueBuying;
    private RelativeLayout containerRedeemed;
    private TextView txtRedeemed;
    private boolean isValidBarcodeFound = false;

    private LinearLayout containerRedeemedMC;
    private TextView tvQrCode;
    private ProgressBar progressBar;
    private ImageView imQrCode;
    private Bitmap bitmap;
    private RelativeLayout rvQRCode;
    private boolean ifQrGenerate = false;
    private static final String LAST_ACTIVE_MILLIS_PREFERENCE_KEY = "last_active_millis";
    private SharedPreferences sharedPrefs;


    public RedeemFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle data;

        if (savedInstanceState != null) {
            data = savedInstanceState;
        } else {
            data = getArguments();
        }

        if (data != null) {
            if (data.containsKey("purchaseData")) {
                Object purchase = data.getParcelable("purchaseData");
            }
        }
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_redeem, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtRedeemed = view.findViewById(R.id.txtRedeemed);
        txtRedeemed.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

        containerRedeemed = view.findViewById(R.id.containerRedeemed);
        containerRedeemed.setVisibility(View.GONE);
        containerRedeemed.getLayoutParams().height = txtRedeemed.getMeasuredHeight() * 3;

        img = view.findViewById(R.id.img);
        img.getLayoutParams().width = Helper.getImgDimen(getContext());
        img.getLayoutParams().height = Helper.getImgDimen(getContext());

        txtRedeemLabel = (TextView) view.findViewById(R.id.txtRedeemLabel);
        txtRedeemLabel.setText(getString(R.string.RedeemLabelNotRedeemed));
        txtTitle = (TextView) view.findViewById(R.id.txtTitle);
        btnRedeem = (Button) view.findViewById(R.id.btnRedeem);
        btnRedeem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    isValidBarcodeFound = false;
                    String tag = CameraFragment.class.getName();
                    CameraFragment cameraFragment = new CameraFragment();
                    getChildFragmentManager().beginTransaction()
                            .add(R.id.cameraContainer, cameraFragment, tag)
                            .commit();

                } catch (Exception e) {

                }
            }
        });

        btnShare = (Button) view.findViewById(R.id.btnShare);

        btnShare.setVisibility(View.GONE);

        btnContinueBuying = (Button) view.findViewById(R.id.btnContinueBuying);


        txtType = (TextView) view.findViewById(R.id.txtType);

        txtDescription = (TextView) view.findViewById(R.id.txtDescription);

        txtOfferPrice = (TextView) view.findViewById(R.id.txtOfferPrice);
        txtRegularPrice = (TextView) view.findViewById(R.id.txtRegularPrice);
        txtRegularPrice.setPaintFlags(txtRegularPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        txtExpiresTime = (TextView) view.findViewById(R.id.txtExpiresTime);

        txtServerName = (TextView) view.findViewById(R.id.txtServerName);

        txtVenueNameAndAddress = view.findViewById(R.id.txtVenueNameAndAddress);
        containerRedeemedMC = view.findViewById(R.id.containerRedeemedMC);
        tvQrCode = view.findViewById(R.id.tv_qr_code_string);
        imQrCode = view.findViewById(R.id.im_qr_code);
        progressBar = view.findViewById(R.id.progress_bar_qr);
        rvQRCode = view.findViewById(R.id.rvQRCode);

        updateOfferData();
    }

    private void updateOfferData() {

        VenueListModel venueListModel = new VenueListModel(13, "Fast-food", "Taco Bell", "https://www.zipz.app/images/places/Taco-Bell-Top-Center-Shop-1575302084.png", "Loja P34 - Piso paulista",
                20.456, 44.45666, true, false, 200, "distance", 1, 1, 1,
                "Sao Paolo", "Top center shopping", "place name", "state", "-Shop.Itaaguera", "fast food", "order");

        txtTitle.setText("teste all");
        txtType.setText("Refrigerante");
        txtDescription.setText("test test test");

        txtOfferPrice.setText("R$ 9.00 - 10% Off");
        txtRegularPrice.setText("R$ 10.00");

        Picasso.get().load(venueListModel.getPlace_logo()).into(img);


    }

    private Bitmap setQRBitmap(String content, int flag) {
        Bitmap bitmap = null;
        int width = 556, height = 556;
        QRCodeWriter writer = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, width, height);
            bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    if (getActivity() != null) {
                        bitmap.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : getActivity().getResources().getColor(R.color.white));
                    }
                }
            }
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return bitmap;
    }


    @Override
    public void onBarCodeScanFailure(@NonNull Exception e) {
        // Deprecated
        Log.d("qr code", "onBarCodeScanFailure() called with: e = [" + e + "]");
    }

    public void onQRCodeFound(String qrCode) {
        if (!isValidBarcodeFound) {
            isValidBarcodeFound = true;
            Toast.makeText(getActivity(), "gfgfgdf ", Toast.LENGTH_SHORT).show();
            Log.d("qr code", "onQRCodeFound() called with: qrCode = [" + qrCode + "]");
            Bundle params = new Bundle();
            removeCameraFragment();
        }
    }

    @Override
    public void onCameraPermissionRejected() {
        removeCameraFragment();
    }

    private void removeCameraFragment() {
        try {
            CameraFragment cameraFragment = (CameraFragment) getChildFragmentManager().findFragmentByTag(CameraFragment.class.getName());

            if (cameraFragment != null) {
                getChildFragmentManager().beginTransaction()
                        .remove(cameraFragment)
                        .commit();
            }
        } catch (Exception e) {
            //
        }
    }

}
