package com.yunhuayuan.chenlian.business.user.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.yunhuayuan.chenlian.business.common.net.ServerErrorCode;
import com.yunhuayuan.chenlian.business.user.model.bean.LoginRequest;
import com.yunhuayuan.chenlian.business.user.presenter.UserOperator;
import com.yunhuayuan.chenlian.business.user.presenter.ipresenter.IUserOperator;
import com.yunhuayuan.chenlian.business.user.view.viewlistener.ILogin;
import com.yunhuayuan.chenlian.view.MainActivity;
import com.yunhuayuan.chenlian.yuhuayn.R;

public class LoginActivity extends Activity implements View.OnClickListener, ILogin {

    private EditText phone;
    private EditText checkCode;

    IUserOperator iop = new UserOperator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        initListener();
    }

    private void initView() {
        phone = (EditText) findViewById(R.id.id_et_phone);
        checkCode = (EditText) findViewById(R.id.id_et_checkcode);
    }

    private void initListener() {
        findViewById(R.id.id_bt_login).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_bt_login:
                loginListener();
                break;
        }
    }

    private void loginListener() {
        String strPhone = phone.getText().toString();
        String strCheckCode = checkCode.getText().toString();

        LoginRequest lr = new LoginRequest();
        lr.setCheckCode(strCheckCode);
        lr.setPhone(strPhone);

        iop.login(this, lr);
    }

    @Override
    public void loginResult(String code, String msg) {
        if (ServerErrorCode.SUCCESS.getCode().equals(code)) {
            startActivity(new Intent(this, MainActivity.class));
            finish();

        } else {
            Toast.makeText(this, null!=msg?msg:"退出失败!", Toast.LENGTH_SHORT).show();
        }
    }
}

