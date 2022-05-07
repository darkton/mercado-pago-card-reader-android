package maquininhajava.app;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int PAYMENT_REQUEST = 1;
    private Button payButton;
    private EditText currencyInput;
    private EditText descriptionInput;
    private String current = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        payButton = findViewById(R.id.pagar);
        currencyInput = findViewById(R.id.valor);
        descriptionInput = findViewById(R.id.descricao);

        payButton.setOnClickListener(v -> {
            if(descriptionInput.getText().toString().trim().equals("")) {
                descriptionInput.setError("Você precisa preencher este campo.");
                return;
            }

            if(currencyInput.getText().toString().trim().equals("")) {
                currencyInput.setError("Você precisa preencher este campo.");
                return;
            }

            if (Integer.parseInt(cleanString(currencyInput.getText().toString())) > 100) {
                startsPayment();
            } else {
                currencyInput.setError("O valor deve ser maior que R$ 1,00.");
            }
        });

        currencyInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().equals(current)) {
                    currencyInput.removeTextChangedListener(this);
                    String cleanedString = cleanString(s.toString());
                    double parsed = Double.parseDouble(cleanedString);
                    String formatted = NumberFormat.getCurrencyInstance().format((parsed / 100));
                    current = formatted;
                    currencyInput.setText(formatted);
                    currencyInput.setSelection(formatted.length());
                    currencyInput.addTextChangedListener(this);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // :)
            }

            @Override
            public void afterTextChanged(Editable s) {
                // :)
            }
        });
    }

    public void startsPayment() {
        Intent i = new Intent();
        i.setAction(Constants.ACTION);
        Bundle bundle = new Bundle();
        String amountCleaned = cleanString(currencyInput.getText().toString());
        bundle.putDouble(BundleCodes.AMOUNT, Double.parseDouble(amountCleaned) / 100);
        bundle.putString(BundleCodes.DESCRIPTION, descriptionInput.getText().toString());
        String cc_selected = "credit_card";
        bundle.putString(BundleCodes.CARD_TYPE, cc_selected);
        bundle.putInt(BundleCodes.INSTALLMENTS, Integer.valueOf("0"));
        bundle.putString(BundleCodes.APP_ID, "1888570937495923");
        bundle.putString(BundleCodes.APP_SECRET, "TEST-1888570937495923-042015-086c0f4cf9dcb05dd2877fb00f057ac8-588209200");
        bundle.putDouble(BundleCodes.APP_FEE, 0);
        bundle.putBoolean(BundleCodes.IS_KIOSK, true);
        i.putExtras(bundle);
        someActivityResultLauncher.launch(i);
    }

    public boolean isAvailable(Intent intent) {
        final PackageManager mgr = getPackageManager();
        List<ResolveInfo> list =
                mgr.queryIntentActivities(intent,
                        PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent intent = result.getData();
                        Intent ResultIntent = new Intent(getBaseContext(), Result.class);
                        ResultIntent.putExtras(intent);
                        startActivity(ResultIntent);
                    }
                }
            });

    public String cleanString(String s) {
        String a = s.toString().replace("R", "");
        String b = a.replaceAll("[$,.]", "");
        String c = b.replaceAll("\\s+","");
        return c;
    }
}