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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import canhptph44323.fpoly.alo.duan_mau_mob2041.Daos.ThanhVien_Dao;
import canhptph44323.fpoly.alo.duan_mau_mob2041.R;
import canhptph44323.fpoly.alo.duan_mau_mob2041.model.ThanhVien;

public class ThanhVien_Adapter  extends RecyclerView.Adapter<ThanhVien_Adapter.ViewHolder>{
    private Context context;
    private ArrayList<ThanhVien> list;

    private ThanhVien_Dao thanhVienDAO;

    public ThanhVien_Adapter(Context context, ArrayList<ThanhVien> list,ThanhVien_Dao thanhVienDAO) {
        this.context = context;
        this.list = list;
        this.thanhVienDAO = thanhVienDAO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recyler_thanhvien, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txtmaTV.setText("Mã thành viên: " + String.valueOf(list.get(position).getMaTV()));
        holder.txthoTen.setText("Họ tên: "+ list.get(position).getHoTen());
        holder.txtnamSinh.setText("Năm sinh: "+ list.get(position).getNamSinh());

        holder.ivedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogCapNhat(list.get(holder.getAdapterPosition()));
            }
        });
        holder.ivdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int check = thanhVienDAO.xoaThongTinThanhVien(list.get(holder.getAdapterPosition()).getMaTV());

                switch (check){
                    case 1:
                        Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        loadData();
                        break;
                    case 0:
                        Toast.makeText(context, "Xóa không thành công", Toast.LENGTH_SHORT).show();
                        break;
                    case -1:
                        Toast.makeText(context, "Thành viên tồn tại phiếu mượn không thể xóa", Toast.LENGTH_SHORT).show();
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
        TextView txtmaTV, txthoTen, txtnamSinh;
        ImageView ivedit, ivdelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtmaTV = itemView.findViewById(R.id.txtmaTV);
            txthoTen = itemView.findViewById(R.id.txtHoten);
            txtnamSinh = itemView.findViewById(R.id.txtNamsinh);
            ivedit = itemView.findViewById(R.id.ivedit);
            ivdelete = itemView.findViewById(R.id.ivdelete);
        }
    }

    private void showDialogCapNhat(ThanhVien thanhVien){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_suathanhvien,null);
        builder.setView(view);

        TextView txtmatv = view.findViewById(R.id.txtmatv);
        EditText edhoten = view.findViewById(R.id.edhoTen);
        EditText ednamsinh = view.findViewById(R.id.ednamSinh);

        txtmatv.setText("Mã thành viên: "+ thanhVien.getMaTV());
        edhoten.setText(thanhVien.getHoTen());
        ednamsinh.setText(thanhVien.getNamSinh());

        builder.setNegativeButton("Cập nhật", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                int id = thanhVien.getMaTV();
                String hoten = edhoten.getText().toString();
                String namsinh = ednamsinh.getText().toString();

                boolean check = thanhVienDAO.capNhatThongTinTV(id,hoten,namsinh);
                if (check){
                    Toast.makeText(context, "Cập nhật thông tin thành công", Toast.LENGTH_SHORT).show();

                    loadData();

                }else {
                    Toast.makeText(context, "Cập nhật thông tin không thành công", Toast.LENGTH_SHORT).show();
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
        list = thanhVienDAO.getDSthanhVien();
        notifyDataSetChanged();
    }
}
