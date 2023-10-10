package canhptph44323.fpoly.alo.duan_mau_mob2041.model;

public class phieuMuon {
    private int maPM;
    private int maTV;
    private String maTT;

    private int maSach;
    private String tenSach;
    private String ngay;
    private int traSach;
    private int tienThue;
    private String tenTV;
    private String tenTT;

    public phieuMuon(int maPM, int maTV, String tenTV, String maTT,String tenTT, int maSach, String tenSach, String ngay, int traSach, int tienThue) {
        this.maPM = maPM;
        this.maTV = maTV;
        this.maTT = maTT;
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.ngay = ngay;
        this.traSach = traSach;
        this.tienThue = tienThue;
        this.tenTV = tenTV;
        this.tenTT = tenTT;
    }

    public phieuMuon( int maTV, String maTT, int maSach, String ngay, int traSach, int tienThue) {
        this.maTV = maTV;
        this.maTT = maTT;
        this.maSach = maSach;
        this.ngay = ngay;
        this.traSach = traSach;
        this.tienThue = tienThue;
    }

    public int getMaPM() {
        return maPM;
    }

    public void setMaPM(int maPM) {
        this.maPM = maPM;
    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public String getMaTT() {
        return maTT;
    }

    public void setMaTT(String maTT) {
        this.maTT = maTT;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public int getTraSach() {
        return traSach;
    }

    public void setTraSach(int traSach) {
        this.traSach = traSach;
    }

    public int getTienThue() {
        return tienThue;
    }

    public void setTienThue(int tienThue) {
        this.tienThue = tienThue;
    }

    public String getTenTV() {
        return tenTV;
    }

    public void setTenTV(String tenTV) {
        this.tenTV = tenTV;
    }

    public String getTenTT() {
        return tenTT;
    }

    public void setTenTT(String tenTT) {
        this.tenTT = tenTT;
    }
}
