package entity;

import java.util.Date;

public abstract class HoaDon {
    protected int maHD;
    protected String gioHD;
    protected Date ngayHD;
    protected String hoTen;
    private String kHD; 
    protected double donGia;

    public HoaDon(int maHD, Date ngayHD, String hoTen, String kHD, double donGia) {
        this.maHD = maHD;
        this.hoTen = hoTen;
        this.ngayHD = ngayHD;
        this.kHD = kHD;
        this.donGia = donGia;
        
    }
    public abstract double tinhThanhTien();
    public int getmaHD(){
        return maHD;
    }
    public Date getngayHD(){
        return ngayHD;
    }

    public String gethoTen(){
        return hoTen;
    }

    public String getkHD(){
        return kHD;
    }
    public double donGia(){
        return donGia;
    }
    

    
   
    



}
