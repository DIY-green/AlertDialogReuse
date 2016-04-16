package com.diygreen.alertdialogreuse;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static final String DIY_TITLE = "DIY Title";
    private static final String DIY_MESSAGE= "DIY MESSAGE";
    private static final String BTN_SURE_TEXT = "确定";
    private static final String BTN_CANCEL_TEXT = "取消";

    private TextView mADAddressTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        setTitle("MainActivity");
        this.mADAddressTV = (TextView) findViewById(R.id.tv_adaddress);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show1_1:
                showAppSingleAlertDialog("1-1", v.toString());
                break;
            case R.id.btn_show1_2:
                showAppSingleAlertDialog("1-2", v.toString());
                break;
            case R.id.btn_show1_3:
                showAppSingleAlertDialog("1-3", v.toString());
                break;
            case R.id.btn_show2_1:
                showOneBtnAlertDialog("2-1", v.toString());
                break;
            case R.id.btn_show2_2:
                showOneBtnAlertDialog("2-2", v.toString());
                break;
            case R.id.btn_show2_3:
                showTwoBtnAlertDialog("2-3", v.toString());
                break;
            case R.id.btn_show3_1:
                showAlertDialog("3-1", v.toString());
                break;
            case R.id.btn_show3_2:
                showAlertDialog("3-2", v.toString());
                break;
            case R.id.btn_show3_3:
                showAlertDialog("3-3", v.toString());
                break;
            case R.id.btn_next:
                toSecondActivity();
                break;
        }
    }

    private void showAppSingleAlertDialog(String title, String msg) {
        AlertDialog alertDialog = AppAlertDialogManager.displayOneBtnDialog(title, msg);
        mADAddressTV.setText(alertDialog.toString());
        Log.e(TAG, alertDialog.toString());
        alertDialog.show();
    }

    private void showOneBtnAlertDialog(String title, String msg) {
        AlertDialog alertDialog = ContextAlertDialogManager.displayOneBtnDialog(this, title, msg);
        if (alertDialog == null) return;
        mADAddressTV.setText(alertDialog.toString());
        Log.e(TAG, alertDialog.toString());
        alertDialog.show();
    }

    private void showTwoBtnAlertDialog(String title, String msg) {
        AlertDialog alertDialog = ContextAlertDialogManager.displayTwoBtnDialog(this, title, msg);
        if (alertDialog == null) return;
        mADAddressTV.setText(alertDialog.toString());
        Log.e(TAG, alertDialog.toString());
        alertDialog.show();
    }

    private void showAlertDialog(String title, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(DIY_TITLE + title);
        builder.setMessage(DIY_MESSAGE + msg);
        builder.setNegativeButton(BTN_CANCEL_TEXT, null);
        builder.setPositiveButton(BTN_SURE_TEXT, null);
        AlertDialog alertDialog = builder.show();
        mADAddressTV.setText(alertDialog.toString());
        Log.e(TAG, alertDialog.toString());
    }

    private void toSecondActivity() {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ContextAlertDialogManager.destory(this);
    }
}
