package canhptph44323.fpoly.alo.duan_mau_mob2041.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

import canhptph44323.fpoly.alo.duan_mau_mob2041.Daos.ThanhVien_Dao;
import canhptph44323.fpoly.alo.duan_mau_mob2041.R;
import canhptph44323.fpoly.alo.duan_mau_mob2041.adapters.ThanhVien_Adapter;
import canhptph44323.fpoly.alo.duan_mau_mob2041.model.ThanhVien;


public class ThanhVien_Fragment extends Fragment {


    ThanhVien_Dao thanhVienDAO;
    RecyclerView recyclerThanhVien;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thanh_vien_,container,false);
        recyclerThanhVien = view.findViewById(R.id.recyclerThanhVien);
        FloatingActionButton floadAdd = view.findViewById(R.id.floadAdd);

        thanhVienDAO = new ThanhVien_Dao(getContext());
        loadData();

        floadAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        return view;
    }

    private void loadData(){

        ArrayList<ThanhVien> list = thanhVienDAO.getDSthanhVien();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerThanhVien.setLayoutManager(linearLayoutManager);
        ThanhVien_Adapter adapter = new ThanhVien_Adapter(getContext(),list,thanhVienDAO);
        recyclerThanhVien.setAdapter(adapter);
    }

    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_themthanhvien,null);
        builder.setView(view);

        EditText edthoTen = view.findViewById(R.id.edthoTen);
        EditText edtnamSinh = view.findViewById(R.id.edtnamSinh);

        builder.setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String hoTen = edthoTen.getText().toString();
                String namSinh = edtnamSinh.getText().toString();

                boolean check = thanhVienDAO.themThanhVien(hoTen,namSinh);
                if (check){
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    loadData();
                }else {
                    Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }

            }
        });

        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}