package canhptph44323.fpoly.alo.duan_mau_mob2041.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import canhptph44323.fpoly.alo.duan_mau_mob2041.Daos.ThuThuDao;
import canhptph44323.fpoly.alo.duan_mau_mob2041.R;
import canhptph44323.fpoly.alo.duan_mau_mob2041.model.ThuThu;


public class Them_ThanhVien_Fragment extends Fragment {


    private ThuThuDao ttDao;
    private EditText edUserThemND, edPassThemND, edPassThemND2;
    private Button btnHuyThemND, btnDangkyThemND;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_them__thanh_vien_, container, false);
        anhXa(view);
        btnDangkyThemND.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = edUserThemND.getText().toString().trim();
                String password = edPassThemND.getText().toString().trim();
                String passAgain = edPassThemND2.getText().toString().trim();

                if (!userName.isEmpty() || !password.isEmpty() || !passAgain.isEmpty()) {
                    if (password.equals(passAgain)) {
                        boolean flag = ttDao.insert(new ThuThu(userName,"canhTan",password,0));
                        if (flag  ) {
                            Toast.makeText(requireActivity(), "Thêm mới tài khoản thành công!", Toast.LENGTH_SHORT).show();
                            edUserThemND.getText().clear();
                            edPassThemND2.getText().clear();
                            edPassThemND.getText().clear();
                        } else {
                            Toast.makeText(requireActivity(), "Tên đăng nhập đã tồn tại trong hệ thống!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(requireContext(), "Mật khẩu không trùng khớp!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(requireContext(), "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                }


            }
        });
        btnHuyThemND.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edUserThemND.getText().clear();
                edPassThemND2.getText().clear();
                edPassThemND.getText().clear();
            }
        });


        return view;


    }

    private void anhXa(View view) {
        btnDangkyThemND = view.findViewById(R.id.btnDangkyThemND);
        btnHuyThemND = view.findViewById(R.id.btnHuyThemND);
        edUserThemND = view.findViewById(R.id.edUserThemND);
        edPassThemND = view.findViewById(R.id.edPassThemND);
        edPassThemND2 = view.findViewById(R.id.edPassThemND2);
        ttDao=new ThuThuDao(requireActivity());
    }





}