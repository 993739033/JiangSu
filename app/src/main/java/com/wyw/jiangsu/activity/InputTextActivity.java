package com.wyw.jiangsu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.wyw.jiangsu.R;

import butterknife.BindView;

public class InputTextActivity extends BaseActivity {
    @BindView(R.id.et_input_text)
    EditText etInputText;
    @BindView(R.id.submit)
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_text);
        String string=getIntent().getStringExtra("input");
        if(!TextUtils.isEmpty(string)){
            etInputText.setText(string);
        }
    }

    @Override
    public void bindData() {
        setTitle("");
    }

    @Override
    public void bindListener() {
        submit.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra("return", etInputText.getText().toString());
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}
