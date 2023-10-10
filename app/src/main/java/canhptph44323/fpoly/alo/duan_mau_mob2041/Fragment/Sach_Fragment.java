package canhptph44323.fpoly.alo.duan_mau_mob2041.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

import canhptph44323.fpoly.alo.duan_mau_mob2041.Daos.LoaiSach_Dao;
import canhptph44323.fpoly.alo.duan_mau_mob2041.Daos.Sach_Dao;
import canhptph44323.fpoly.alo.duan_mau_mob2041.R;
import canhptph44323.fpoly.alo.duan_mau_mob2041.adapters.Sach_Adapter;
import canhptph44323.fpoly.alo.duan_mau_mob2041.model.Sach;
import canhptph44323.fpoly.alo.duan_mau_mob2041.model.loaiSach;


public class Sach_Fragment extends Fragment {

    Sach_Dao sachDAO;
    RecyclerView recyclerSach;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sach_, container, false);
        recyclerSach = view.findViewById(R.id.recyclerSach);
        FloatingActionButton floadadd = view.findViewById(R.id.fladd);

        sachDAO = new Sach_Dao(getContext());

        loadData();

        floadadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDiaLog();
            }
        });
        return view;

    }

    private void loadData(){

        ArrayList<Sach> list = sachDAO.getDsDauSach();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerSach.setLayoutManager(linearLayoutManager);
        Sach_Adapter adapter = new Sach_Adapter(getContext(), list,getDSloaiSach(),sachDAO);
        recyclerSach.setAdapter(adapter);
    }
    private void showDiaLog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_themsach,null);
        builder.setView(view);

        EditText edttensach = view.findViewById(R.id.edttensach);
        EditText edttien = view.findViewById(R.id.edttien);
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
                HashMap<String,Object> hs = (HashMap<String, Object>) spnloaisach.getSelectedItem();
                int maloai = (int) hs.get("maLoai");
                boolean check = sachDAO.themSachMoi(tensach,tien,maloai);
                if (check){
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    //load data
                   loadData();
              }else {
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

    private ArrayList<HashMap<String, Object>> getDSloaiSach(){
        LoaiSach_Dao loaiSachDAO = new LoaiSach_Dao(getContext());
        ArrayList<loaiSach> list = loaiSachDAO.getDSloaiSach();
        ArrayList<HashMap<String,Object>> listHM = new ArrayList<>();

        for (loaiSach loaiSach : list){
            HashMap<String,Object> hs = new HashMap<>();
            hs.put("maLoai",loaiSach.getMaLoai());
            hs.put("tenLoai",loaiSach.getTenLoai());
            listHM.add(hs);
        }
        return listHM;
    }
}