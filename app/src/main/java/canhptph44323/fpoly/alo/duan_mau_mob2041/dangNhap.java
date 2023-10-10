package canhptph44323.fpoly.alo.duan_mau_mob2041;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import canhptph44323.fpoly.alo.duan_mau_mob2041.Daos.ThuThuDao;

public class dangNhap extends AppCompatActivity {
    private TextInputEditText usernameEditText, passwordEditText;
    private CheckBox saveAccountCheckBox;
    private Button loginButton;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "MyPrefs";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        usernameEditText = findViewById(R.id.edTenDN);
        passwordEditText = findViewById(R.id.edMatKhau);
        saveAccountCheckBox = findViewById(R.id.saveAccountCheckBox);
        loginButton = findViewById(R.id.loginButton);

        ThuThuDao thuThuDAO = new ThuThuDao(this);

        // Khởi tạo SharedPreferences
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Kiểm tra nếu đã lưu tài khoản, tự động điền thông tin đăng nhập
        if (sharedPreferences.contains(KEY_USERNAME) && sharedPreferences.contains(KEY_PASSWORD)) {
            String savedUsername = sharedPreferences.getString(KEY_USERNAME, "");
            String savedPassword = sharedPreferences.getString(KEY_PASSWORD, "");
            usernameEditText.setText(savedUsername);
            passwordEditText.setText(savedPassword);
            saveAccountCheckBox.setChecked(true);
        }

        // Xử lý sự kiện khi nhấn nút đăng nhập
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(dangNhap.this, "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    if (thuThuDAO.checkDangNhap(username, password)) {
                        // Lưu thông tin đăng nhập nếu checkbox được chọn
                        if (saveAccountCheckBox.isChecked()) {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString(KEY_USERNAME, username);
                            editor.putString(KEY_PASSWORD, password);
                            editor.apply();
                        } else {
                            // Xóa thông tin đăng nhập nếu checkbox không được chọn
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.remove(KEY_USERNAME);
                            editor.remove(KEY_PASSWORD);
                            editor.apply();
                        }

                        Toast.makeText(dangNhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(dangNhap.this, MainActivity.class));
                    } else {
                        Toast.makeText(dangNhap.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}