/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cay;

/**
 *
 * @author DELL
 */
public class PhieuNhap {
    public String manhap, ngaynhap, macay;
    public int soluong;

    public PhieuNhap(String manhap, String ngaynhap, String macay, int soluong) {
        this.manhap = manhap;
        this.ngaynhap = ngaynhap;
        this.macay = macay;
        this.soluong = soluong;
    }

    public String getManhap() {
        return manhap;
    }

    public void setManhap(String manhap) {
        this.manhap = manhap;
    }

    public String getNgaynhap() {
        return ngaynhap;
    }

    public void setNgaynhap(String ngaynhap) {
        this.ngaynhap = ngaynhap;
    }

    public String getMacay() {
        return macay;
    }

    public void setMacay(String macay) {
        this.macay = macay;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
    
    
}
