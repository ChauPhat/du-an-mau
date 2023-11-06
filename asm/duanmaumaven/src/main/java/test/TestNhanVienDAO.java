/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import dao.NhanVienDAO;
import entity.NguoiHoc;
import entity.NhanVien;

/**
 *
 * @author Admin
 */
public class TestNhanVienDAO {

    public static void main(String[] args) {
        NhanVien nv = new NhanVien("lept", "123", "Cao Trọng Lễ", true);
        NhanVienDAO nvd = new NhanVienDAO();
        nvd.insert(nv);
    }
}
