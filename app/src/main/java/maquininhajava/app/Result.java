package maquininhajava.app;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Result extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        Bundle data = getIntent().getExtras();

        if (data != null) {
            String resultStatus = data.getString(BundleCodes.RESULT_STATUS);
            Long paymentId = data.getLong(BundleCodes.PAYMENT_ID);
            Integer installments = data.getInt(BundleCodes.INSTALLMENTS);
            Double amount = data.getDouble(BundleCodes.AMOUNT);
            String cardType = data.getString(BundleCodes.CARD_TYPE);
            String error =  data.getString(BundleCodes.ERROR);
            String errorDetail = data.getString(BundleCodes.ERROR_DETAIL);
            String truncCardHolder = data.getString(BundleCodes.TRUNC_CARD_HOLDER);

            if(resultStatus.equals("OK")) {
                LinearLayout success = findViewById(R.id.sucesso);
                success.setVisibility(View.VISIBLE);
            } else {
                LinearLayout failed = findViewById(R.id.falha);
                failed.setVisibility(View.VISIBLE);
            }
        }

        Uri uri = getIntent().getData();
        if (uri != null) {
            String resultStatus = uri.getQueryParameter(BundleCodes.RESULT_STATUS);
            String paymentId = uri.getQueryParameter(BundleCodes.PAYMENT_ID);
            String installments = uri.getQueryParameter(BundleCodes.INSTALLMENTS);
            String amount = uri.getQueryParameter(BundleCodes.AMOUNT);
            String cardType = uri.getQueryParameter(BundleCodes.CARD_TYPE);
            String error =  uri.getQueryParameter(BundleCodes.ERROR);
            String errorDetail = uri.getQueryParameter(BundleCodes.ERROR_DETAIL);
            String truncCardHolder = uri.getQueryParameter(BundleCodes.TRUNC_CARD_HOLDER);
        }
    }
}
