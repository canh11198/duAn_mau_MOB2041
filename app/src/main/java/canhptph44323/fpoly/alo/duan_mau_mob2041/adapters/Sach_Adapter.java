package canhptph44323.fpoly.alo.duan_mau_mob2041.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

import canhptph44323.fpoly.alo.duan_mau_mob2041.Daos.LoaiSach_Dao;
import canhptph44323.fpoly.alo.duan_mau_mob2041.Daos.Sach_Dao;
import canhptph44323.fpoly.alo.duan_mau_mob2041.R;
import canhptph44323.fpoly.alo.duan_mau_mob2041.model.Sach;
import canhptph44323.fpoly.alo.duan_mau_mob2041.model.item_click;
import canhptph44323.fpoly.alo.duan_mau_mob2041.model.loaiSach;

public class Sach_Adapter  extends RecyclerView.Adapter<Sach_Adapter.ViewHolder>{
    private Context context;
    private ArrayList<Sach> list;
    private ArrayList<HashMap<String,Object>> listHM;

    private Sach_Dao sachDAO;

    public Sach_Adapter(Context context, ArrayList<Sach> list,ArrayList<HashMap<String,Object>> listHM,Sach_Dao sachDAO) {
        this.context = context;
        this.list = list;
        this.listHM = listHM;
        this.sachDAO = sachDAO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycler_sach, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txtmasach.setText(String.valueOf(list.get(position).getMaSach()));
        holder.txttensach.setText(list.get(position).getTenSach());
        holder.txtgiathue.setText(String.valueOf(list.get(position).getGiaThue()));
        holder.txtmaloai.setText(String.valueOf(list.get(position).getMaLoai()));
        holder.txttenloai.setText(list.get(position).getTenloai());

        holder.imgedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(list.get(holder.getAdapterPosition()));
            }
        });

        holder.imgdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int check = sachDAO.xoaSach(list.get(holder.getAdapterPosition()).getMaSach());
                switch (check){
                    case 1:
                        Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        loadData();
                        break;
                    case 0:
                        Toast.makeText(context, "Xóa Không thành công", Toast.LENGTH_SHORT).show();
                        break;
                    case -1 :
                        Toast.makeText(context, "Không xóa được sách có trong phiếu mượn", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtmasach, txttensach, txtgiathue, txtmaloai, txttenloai;
        ImageView imgdelete, imgedit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtmasach = itemView.findViewById(R.id.txtmasach);
            txttensach = itemView.findViewById(R.id.txttensach);
            txtgiathue = itemView.findViewById(R.id.txtgiathue);
            txtmaloai = itemView.findViewById(R.id.txtmaLoai);
            txttenloai = itemView.findViewById(R.id.txtTenLoai);

            imgdelete = itemView.findViewById(R.id.imgDelete);
            imgedit = itemView.findViewById(R.id.imgedit);

        }
    }

    private void showDialog(Sach sach) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_suasach,null);
        builder.setView(view);

        EditText edttsach = view.findViewById(R.id.edttsach);
        EditText edttien = view.findViewById(R.id.edtien);
        TextView txtmasach = view.findViewById(R.id.txtmaSach);
        Spinner spnLoaiSach = view.findViewById(R.id.spnLoaiSach);

        txtmasach.setText("Mã sách:"+ sach.getMaSach());
        edttsach.setText(sach.getTenSach());
        edttien.setText(String.valueOf(sach.getGiaThue()));

        SimpleAdapter simpleAdapter = new SimpleAdapter(
                context,
                listHM,
                android.R.layout.simple_list_item_1,
                new String[]{"tenLoai"},
                new int[]{android.R.id.text1}
        );
        spnLoaiSach.setAdapter(simpleAdapter);
        int index = 0;
        int postion = -1;
        for (HashMap<String,Object> item: listHM){
            if ((int)item.get("maLoai") == sach.getMaLoai()){
                postion = index;
            }
            index++;
        }
        spnLoaiSach.setSelection(postion);

        builder.setNegativeButton("Cập nhật", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String tensach = edttsach.getText().toString();
                int tien = Integer.parseInt(edttien.getText().toString());
                HashMap<String,Object> hs = (HashMap<String, Object>) spnLoaiSach.getSelectedItem();
                int maloai = (int) hs.get("maLoai");
                boolean check = sachDAO.capnhatThongTinSach(sach.getMaSach(),tensach,tien,maloai);
                if (check){
                    Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    //load data
                    loadData();
                }else {
                    Toast.makeText(context, "Thêm không thành công", Toast.LENGTH_SHORT).show();
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

    private void loadData(){
        list.clear();
        list = sachDAO.getDsDauSach();
        notifyDataSetChanged();
    }
}
