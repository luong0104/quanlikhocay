/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cay;

/**
 *
 * @author DELL
 */
public class PhieuXuat {
    public String maxuat, ngayxuat, macay;
    public int soluong;

    public PhieuXuat(String maxuat, String ngayxuat, String macay, int soluong) {
        this.maxuat = maxuat;
        this.ngayxuat = ngayxuat;
        this.macay = macay;
        this.soluong = soluong;
    }

    public String getMaxuat() {
        return maxuat;
    }

    public void setMaxuat(String maxuat) {
        this.maxuat = maxuat;
    }

    public String getNgayxuat() {
        return ngayxuat;
    }

    public void setNgayxuat(String ngayxuat) {
        this.ngayxuat = ngayxuat;
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
