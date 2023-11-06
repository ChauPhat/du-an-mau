/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.JdbcHelper;

/**
 *
 * @author Admin
 */
public class ThongKeDAO {

    private List<Object[]> getListOfArray(String sql, String[] cols, Object... args) {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                Object[] values = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    values[i] = rs.getObject(cols[i]);
                }
                list.add(values);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Object[]> getBangDiem(Integer maKH) {
        String sql = "{CALL sp_BangDiem (?)}";
        String[] cols = {"MaNH", "HoTen", "Diem"};
        return this.getListOfArray(sql, cols, maKH);
    }

    public List<Object[]> getLuongNguoiHoc() {
        String sql = "{CALL sp_ThongKeNguoiHoc}";
        String[] cols = {"Nam", "SoLuong", "DauTien", "CuoiCung"};
        return this.getListOfArray(sql, cols);
    }

    public List<Object[]> getDiemChuyenDe() {
        String sql = "{CALL sp_ThongKeDiem}";
        String[] cols = {"ChuyenDe", "SoHV", "ThapNhat", "CaoNhat", "TrungBinh"};
        return this.getListOfArray(sql, cols);
    }

    public List<Object[]> getDoanhThu(int nam) {
        String sql = "{CALL sp_ThongKeDoanhThu (?)}";
        String[] cols = {"ChuyenDe", "SoKH", "SoHV", "DoanhThu", "ThapNhat", "CaoNhat", "TrungBinh"};
        return this.getListOfArray(sql, cols, nam);
    }
}
