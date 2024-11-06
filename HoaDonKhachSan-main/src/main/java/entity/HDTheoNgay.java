package entity;

import java.util.Date;

public class HDTheoNgay extends HoaDon {
    private int soNgayThue;

    public HDTheoNgay(int maHD, Date ngayHD, String hoTen, double donGia, int soNgayThue) {
        super(maHD, ngayHD, hoTen, "SN", donGia);
        this.soNgayThue = soNgayThue; 
    }

    @Override
    public double tinhThanhTien() {
        if (soNgayThue > 7) {
            int ngayKhongGiam = 7;
            int ngayGiam = soNgayThue - ngayKhongGiam;
            return (ngayKhongGiam * donGia) + (ngayGiam * donGia * 0.8);
        } else {
            return soNgayThue * donGia;
        }
    }

    public int getSoNgayThue() {
        return soNgayThue;
    }
}
