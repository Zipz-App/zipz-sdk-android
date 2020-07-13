package android.android.zlibrary.help;

import androidx.annotation.NonNull;


public interface BarCodeScanInterface {
    void onBarCodeScanFailure(@NonNull Exception e);
    void onQRCodeFound(String qrcode);
    void onCameraPermissionRejected();
}
