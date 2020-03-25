package android.android.zlibrary.help;

import androidx.annotation.NonNull;


public interface BarCodeScanInterface {
    //void onBarCodeScanSuccesfull(@NonNull List<FirebaseVisionBarcode> barcodes);
    void onBarCodeScanFailure(@NonNull Exception e);
    void onQRCodeFound(String qrcode);
    void onCameraPermissionRejected();
}
