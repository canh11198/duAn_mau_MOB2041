package canhptph44323.fpoly.alo.duan_mau_mob2041.Fragment;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import canhptph44323.fpoly.alo.duan_mau_mob2041.Daos.LoaiSach_Dao;
import canhptph44323.fpoly.alo.duan_mau_mob2041.Daos.Sach_Dao;
import canhptph44323.fpoly.alo.duan_mau_mob2041.R;
import canhptph44323.fpoly.alo.duan_mau_mob2041.adapters.Sach_Adapter;
import canhptph44323.fpoly.alo.duan_mau_mob2041.model.Sach;
import canhptph44323.fpoly.alo.duan_mau_mob2041.model.loaiSach;


public class Sach_Fragment extends Fragment {

    Sach_Dao sachDAO;
    RecyclerView recyclerSach;
    SearchView searchView;
    Button btnTang, btnGiam;
    ArrayList<Sach> listSearch, list;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sach_, container, false);
        recyclerSach = view.findViewById(R.id.recyclerSach);
        FloatingActionButton floadadd = view.findViewById(R.id.fladd);


        sachDAO = new Sach_Dao(getContext());

        loadData();
        btnTang = view.findViewById(R.id.btnTang);
        btnGiam = view.findViewById(R.id.btnGiam);
        searchView = view.findViewById(R.id.search_sach);

        floadadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDiaLog();
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                listSearch = new ArrayList<>();
                if (query.length() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getTenSach().toUpperCase().contains(query.toUpperCase())) {
                            Sach sach = new Sach();
                            sach.setMaSach(list.get(i).getMaSach());
                            sach.setTenSach(list.get(i).getTenSach());
                            sach.setNamXuatBan(list.get(i).getNamXuatBan());
                            sach.setTenloai(list.get(i).getTenloai());
                            sach.setGiaThue(list.get(i).getGiaThue());
                            sach.setMaLoai(list.get(i).getMaLoai());
                            sach.setSoLuongDaMuon(list.get(i).getSoLuongDaMuon());
                            listSearch.add(sach);
                        }
                    }
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    recyclerSach.setLayoutManager(linearLayoutManager);
                    Sach_Adapter adapter = new Sach_Adapter(getContext(), listSearch, getDSloaiSach(), sachDAO);
                    recyclerSach.setAdapter(adapter);
                } else {
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    recyclerSach.setLayoutManager(linearLayoutManager);
                    Sach_Adapter adapter = new Sach_Adapter(getContext(), list, getDSloaiSach(), sachDAO);
                    recyclerSach.setAdapter(adapter);

                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                listSearch = new ArrayList<>();
                if (newText.length() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getTenSach().toUpperCase().contains(newText.toUpperCase())) {
                            Sach sach = new Sach();
                            sach.setMaSach(list.get(i).getMaSach());
                            sach.setTenSach(list.get(i).getTenSach());
                            sach.setNamXuatBan(list.get(i).getNamXuatBan());
                            sach.setTenloai(list.get(i).getTenloai());
                            sach.setGiaThue(list.get(i).getGiaThue());
                            sach.setMaLoai(list.get(i).getMaLoai());
                            sach.setSoLuongDaMuon(list.get(i).getSoLuongDaMuon());
                            listSearch.add(sach);
                        }
                    }
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    recyclerSach.setLayoutManager(linearLayoutManager);
                    Sach_Adapter adapter = new Sach_Adapter(getContext(), listSearch, getDSloaiSach(), sachDAO);
                    recyclerSach.setAdapter(adapter);
                } else {
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    recyclerSach.setLayoutManager(linearLayoutManager);
                    Sach_Adapter adapter = new Sach_Adapter(getContext(), list, getDSloaiSach(), sachDAO);
                    recyclerSach.setAdapter(adapter);
                }
                return false;
            }
        });
        btnTang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(list, new Comparator<Sach>() {
                    @Override
                    public int compare(Sach o1, Sach o2) {
                        if (o1.getGiaThue() > o2.getGiaThue()) {
                            return 1;
                        } else {
                            if (o1.getGiaThue() == o2.getGiaThue()) {
                                return 0;
                            }else {
                                return -1;
                            }
                        }
                    }
                });
                Sach_Adapter adapter = new Sach_Adapter(getContext(), list, getDSloaiSach(), sachDAO);
                recyclerSach.setAdapter(adapter);
            }
        });
        btnGiam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(list, new Comparator<Sach>() {
                    @Override
                    public int compare(Sach o1, Sach o2) {
                        if (o1.getGiaThue() > o2.getGiaThue()) {
                            return -1;
                        } else {
                            if (o1.getGiaThue() == o2.getGiaThue()) {
                                return 0;
                            }else {
                                return 1;
                            }
                        }
                    }
                });
                Sach_Adapter adapter = new Sach_Adapter(getContext(), list, getDSloaiSach(), sachDAO);
                recyclerSach.setAdapter(adapter);
            }
        });
        return view;

    }


    private void loadData() {

        list = sachDAO.getDsDauSach();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerSach.setLayoutManager(linearLayoutManager);
        Sach_Adapter adapter = new Sach_Adapter(getContext(), list, getDSloaiSach(), sachDAO);
        recyclerSach.setAdapter(adapter);


    }

    private void showDiaLog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_themsach, null);
        builder.setView(view);

        EditText edttensach = view.findViewById(R.id.edttensach);
        EditText edttien = view.findViewById(R.id.edttien);
        EditText edtNamXuatBan = view.findViewById(R.id.edtNamXuatBan);
        Spinner spnloaisach = view.findViewById(R.id.spnLoaisach);

        SimpleAdapter simpleAdapter = new SimpleAdapter(
                getContext(),
                getDSloaiSach(),
                android.R.layout.simple_list_item_1,
                new String[]{"tenLoai"},
                new int[]{android.R.id.text1}
        );
        spnloaisach.setAdapter(simpleAdapter);
        builder.setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String tensach = edttensach.getText().toString();
                int tien = Integer.parseInt(edttien.getText().toString());
                int namXuatBan = Integer.parseInt(edtNamXuatBan.getText().toString());

                HashMap<String, Object> hs = (HashMap<String, Object>) spnloaisach.getSelectedItem();
                int maloai = (int) hs.get("maLoai");
                boolean check = sachDAO.themSachMoi(tensach, tien, maloai, namXuatBan);
                if (check) {
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    //load data
                    loadData();
                } else {
                    Toast.makeText(getContext(), "Thêm không thành công", Toast.LENGTH_SHORT).show();
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

    private ArrayList<HashMap<String, Object>> getDSloaiSach() {
        LoaiSach_Dao loaiSachDAO = new LoaiSach_Dao(getContext());
        ArrayList<loaiSach> list = loaiSachDAO.getDSloaiSach();
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();

        for (loaiSach loaiSach : list) {
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("maLoai", loaiSach.getMaLoai());
            hs.put("tenLoai", loaiSach.getTenLoai());
            listHM.add(hs);
        }
        return listHM;
    }


}