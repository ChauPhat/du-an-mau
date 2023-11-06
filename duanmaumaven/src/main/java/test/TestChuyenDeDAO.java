/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import dao.ChuyenDeDAO;
import entity.ChuyenDe;

/**
 *
 * @author Admin
 */
public class TestChuyenDeDAO {

    public static void main(String[] args) {
        ChuyenDe cd = new ChuyenDe("CD1", "Chuyên đề 1", 100, 50, "", "");
        ChuyenDeDAO cdd = new ChuyenDeDAO();
        cdd.insert(cd);
    }
}
