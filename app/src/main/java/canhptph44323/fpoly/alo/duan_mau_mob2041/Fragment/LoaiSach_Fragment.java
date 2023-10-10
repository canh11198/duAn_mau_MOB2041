package canhptph44323.fpoly.alo.duan_mau_mob2041.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import canhptph44323.fpoly.alo.duan_mau_mob2041.Daos.LoaiSach_Dao;
import canhptph44323.fpoly.alo.duan_mau_mob2041.R;
import canhptph44323.fpoly.alo.duan_mau_mob2041.adapters.LoaiSach_Adapter;
import canhptph44323.fpoly.alo.duan_mau_mob2041.model.item_click;
import canhptph44323.fpoly.alo.duan_mau_mob2041.model.loaiSach;


public class LoaiSach_Fragment extends Fragment {
    RecyclerView recyclerLoaiSach;
    EditText edtloaisach;
    int maloai;
    LoaiSach_Dao dao;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loai_sach_,container,false);
        recyclerLoaiSach = view.findViewById(R.id.recyclerLoaisach);
        edtloaisach = view.findViewById(R.id.edtloaisach);
        Button btnthem = view.findViewById(R.id.btnThem);
        Button btnsua = view.findViewById(R.id.btnSua);
        dao = new LoaiSach_Dao(getContext());

        loadData();

        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenloai = edtloaisach.getText().toString();
                if (dao.themLoaiSach(tenloai)){
                    //thông báo + load lại danh sách
                    loadData();
                    edtloaisach.setText("");
                }else {
                    Toast.makeText(getContext(), "Thêm loại sách không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenloai = edtloaisach.getText().toString();
                loaiSach loaiSach = new loaiSach(maloai,tenloai);
                if (dao.suaLoaiSach(loaiSach)){
                    loadData();
                    edtloaisach.setText("");
                }else {
                    Toast.makeText(getContext(), "Thay đổi thông tin thất bại", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return view;
    }
    private void loadData(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerLoaiSach.setLayoutManager(linearLayoutManager);
        ArrayList<loaiSach> list = dao.getDSloaiSach();
        LoaiSach_Adapter adapter= new LoaiSach_Adapter(getContext(),list,new item_click(){
            @Override
            public void onClickLs(loaiSach loaiSach) {
                edtloaisach.setText(loaiSach.getTenLoai());
                maloai = loaiSach.getMaLoai();
            }

        });
        recyclerLoaiSach.setAdapter(adapter);

    }
}