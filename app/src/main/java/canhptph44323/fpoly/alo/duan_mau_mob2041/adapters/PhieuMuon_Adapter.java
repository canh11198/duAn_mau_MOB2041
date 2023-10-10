package canhptph44323.fpoly.alo.duan_mau_mob2041.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import canhptph44323.fpoly.alo.duan_mau_mob2041.Daos.PhieuMuon_Dao;
import canhptph44323.fpoly.alo.duan_mau_mob2041.R;
import canhptph44323.fpoly.alo.duan_mau_mob2041.model.phieuMuon;

public class PhieuMuon_Adapter extends RecyclerView.Adapter<PhieuMuon_Adapter.ViewHolder>{

    private ArrayList<phieuMuon> list;
    private Context context;

    PhieuMuon_Dao phieuMuonDAO;

    public PhieuMuon_Adapter(ArrayList<phieuMuon> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycler_phieumuon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txtmaPM.setText("Mã PM: " + String.valueOf(list.get(position).getMaPM()));
        holder.txtTenTV.setText("Tên TV:" + list.get(position).getTenTV());
        holder.txtTenSach.setText("Tên sách:" +list.get(position).getTenSach());
        holder.txtTienThue.setText("Tiền thuê:" +String.valueOf(list.get(position).getTienThue()));
        holder.txtNgayThue.setText("Ngày thuê:" +String.valueOf(list.get(position).getNgay()));
        String trangThai = "";
        if (list.get(position).getTraSach() == 1) {
            trangThai = "Đã trả sách";
            holder.btntraSach.setVisibility(View.GONE);
        } else {
            trangThai = "Chưa trả sách";
            holder.btntraSach.setVisibility(View.VISIBLE);
        }
        holder.txttrangThai.setText("Trạng thái:" + trangThai);
        holder.btntraSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phieuMuonDAO = new PhieuMuon_Dao(context);
                boolean kiemtra = phieuMuonDAO.ThayDoiTrangThai(list.get(holder.getAdapterPosition()).getMaPM());
                if (kiemtra){
                    list.clear();
                    list = phieuMuonDAO.getDsPhieuMuon();
                    notifyDataSetChanged();
                }else {
                    Toast.makeText(context, "Thay đổi trạng thái không thành công!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtmaPM, txtTenTV, txtTenSach, txtTienThue, txttrangThai, txtNgayThue;
        Button btntraSach;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtmaPM = itemView.findViewById(R.id.txtmaPM);
            txtTenTV = itemView.findViewById(R.id.txttenTV);
            txtTenSach = itemView.findViewById(R.id.txttenSach);
            txtTienThue = itemView.findViewById(R.id.txtTien);
            txttrangThai = itemView.findViewById(R.id.txttrangThai);
            txtNgayThue = itemView.findViewById(R.id.txtNgay);

            btntraSach = itemView.findViewById(R.id.btnTraSach);


        }
    }
}
