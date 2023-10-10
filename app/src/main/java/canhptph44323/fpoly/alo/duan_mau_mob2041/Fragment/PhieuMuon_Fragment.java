package canhptph44323.fpoly.alo.duan_mau_mob2041.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import canhptph44323.fpoly.alo.duan_mau_mob2041.Daos.PhieuMuon_Dao;
import canhptph44323.fpoly.alo.duan_mau_mob2041.Daos.Sach_Dao;
import canhptph44323.fpoly.alo.duan_mau_mob2041.Daos.ThanhVien_Dao;
import canhptph44323.fpoly.alo.duan_mau_mob2041.R;
import canhptph44323.fpoly.alo.duan_mau_mob2041.adapters.PhieuMuon_Adapter;
import canhptph44323.fpoly.alo.duan_mau_mob2041.model.Sach;
import canhptph44323.fpoly.alo.duan_mau_mob2041.model.ThanhVien;
import canhptph44323.fpoly.alo.duan_mau_mob2041.model.phieuMuon;


public class PhieuMuon_Fragment extends Fragment {


    PhieuMuon_Dao phieuMuonDAO;
    RecyclerView recyclerQLphieumuon;
    ArrayList<phieuMuon> list;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_phieu_muon_, container, false);

        recyclerQLphieumuon = view.findViewById(R.id.recyclerQLPhieuMuon);
        FloatingActionButton floadAdd = view.findViewById(R.id.flAddPM);

        loadData();

        floadAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        return view;
    }

    private void loadData() {
        phieuMuonDAO = new PhieuMuon_Dao(getContext());
        list = phieuMuonDAO.getDsPhieuMuon();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerQLphieumuon.setLayoutManager(linearLayoutManager);
        PhieuMuon_Adapter adapter = new PhieuMuon_Adapter(list, getContext());
        recyclerQLphieumuon.setAdapter(adapter);
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_them_pm, null);

        Spinner spnThanhVien = view.findViewById(R.id.spnThanhVien);
        Spinner spnSach = view.findViewById(R.id.spnSach);

        getDataThanhVien(spnThanhVien);
        getDataSach(spnSach);

        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                //Lấy mã thành viên
                HashMap<String, Object> hsTV = (HashMap<String, Object>) spnThanhVien.getSelectedItem();
                int maTV = (int) hsTV.get("maTV");

                //lấy mã sách
                HashMap<String, Object> hsSach = (HashMap<String, Object>) spnSach.getSelectedItem();
                int maSach = (int) hsSach.get("maSach");

                int tien = (int) hsSach.get("giaThue");
                themPhieuMuon(maTV, maSach, tien);
            }
        });

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    private void getDataThanhVien(Spinner spnThanhVien) {
        ThanhVien_Dao thanhVienDAO = new ThanhVien_Dao(getContext());
        ArrayList<ThanhVien> list = thanhVienDAO.getDSthanhVien();

        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();
        for (ThanhVien tv : list) {
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("maTV", tv.getMaTV());
            hs.put("hoTen", tv.getHoTen());
            listHM.add(hs);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(), listHM, android.R.layout.simple_list_item_1, new String[]{"hoTen"}, new int[]{android.R.id.text1});
        spnThanhVien.setAdapter(simpleAdapter);
    }

    private void getDataSach(Spinner spnSach) {
        Sach_Dao sachDAO = new Sach_Dao(getContext());
        ArrayList<Sach> list = sachDAO.getDsDauSach();

        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();
        for (Sach sc : list) {
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("maSach", sc.getMaSach());
            hs.put("tenSach", sc.getTenSach());
            hs.put("giaThue", sc.getGiaThue());
            listHM.add(hs);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(), listHM, android.R.layout.simple_list_item_1, new String[]{"tenSach"}, new int[]{android.R.id.text1});
        spnSach.setAdapter(simpleAdapter);
    }

    private void themPhieuMuon(int maTV, int maSach, int tien) {

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("THONGTIN", Context.MODE_PRIVATE);
        String matt = sharedPreferences.getString("maTT", "");

        //Lấy ngày hiện tại
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String ngay = simpleDateFormat.format(currentTime);

        phieuMuon phieuMuon = new phieuMuon(maTV, matt, maSach, ngay, 0, tien);
        boolean kiemtra = phieuMuonDAO.themPm(phieuMuon);
        if (kiemtra) {
            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
            loadData();
        } else {
            Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
        }
    }
}