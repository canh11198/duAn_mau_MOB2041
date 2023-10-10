package canhptph44323.fpoly.alo.duan_mau_mob2041.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import canhptph44323.fpoly.alo.duan_mau_mob2041.Daos.ThongKe_Dao;
import canhptph44323.fpoly.alo.duan_mau_mob2041.R;
import canhptph44323.fpoly.alo.duan_mau_mob2041.adapters.Top10_Adapter;
import canhptph44323.fpoly.alo.duan_mau_mob2041.model.Sach;


public class ThongKe_Fragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thong_ke_,container,false);

        RecyclerView recyclerTop10 = view.findViewById(R.id.recyclerTop10);
        ThongKe_Dao thongKeDAO = new ThongKe_Dao(getContext());
        ArrayList<Sach> list = thongKeDAO.getTop10();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerTop10.setLayoutManager(linearLayoutManager);

        Top10_Adapter adapter = new Top10_Adapter(getContext(),list);
        recyclerTop10.setAdapter(adapter);
        return view;
    }
}