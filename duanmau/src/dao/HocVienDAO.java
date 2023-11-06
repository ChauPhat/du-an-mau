/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.HocVien;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.JdbcHelper;

/**
 *
 * @author Admin
 */
public class HocVienDAO extends EduSysDAO<HocVien, String> {

    @Override
    public void insert(HocVien entity) {
        String INSERT_SQL = "insert into HocVien(MaKH, MaNH, Diem) values (?,?,?)";
        JdbcHelper.update(INSERT_SQL, entity.getMaKH(), entity.getMaNH(), entity.getDiem());
    }

    @Override
    public void update(HocVien entity) {
        String UPDATE_SQL = "UPDATE HocVien SET Diem = ? WHERE MaHV = ?";
        JdbcHelper.update(UPDATE_SQL, entity.getDiem(), entity.getMaHV());
    }

    @Override
    public void delete(String key) {
        String DELETE_SQL = "DELETE FROM HocVien WHERE MaHV=?";
        JdbcHelper.update(DELETE_SQL, key);
    }

    @Override
    public List<HocVien> selectAll() {
        String SELECT_ALL_SQL = "SELECT * FROM HocVien";
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public HocVien selectById(String key) {
        String SELECT_BY_ID = "SELECT * FROM HocVien WHERE MaHV=?";
        List<HocVien> list = this.selectBySql(SELECT_BY_ID, key);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    protected List<HocVien> selectBySql(String sql, Object... args) {
        List<HocVien> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                HocVien hv = new HocVien();
                hv.setMaHV(rs.getInt("MaHV"));
                hv.setMaKH(rs.getInt("MaKH"));
                hv.setMaNH(rs.getString("MaNH"));
                hv.setDiem(rs.getDouble("Diem"));
                list.add(hv);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<HocVien> selectByKhoaHoc(int makh) {
        String sql = "select * from hocvien where makh = ?";
        return this.selectBySql(sql, makh);
    }

}
