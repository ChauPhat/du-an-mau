/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.KhoaHoc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.JdbcHelper;

/**
 *
 * @author Admin
 */
public class KhoaHocDAO extends EduSysDAO<KhoaHoc, String> {

    @Override
    public void insert(KhoaHoc entity) {
        String INSERT_SQL = "insert into KhoaHoc(MaCD,HocPhi,ThoiLuong,NgayKG,GhiChu,MaNV) values (?,?,?,?,?,?)";
        JdbcHelper.update(INSERT_SQL, entity.getMaCD(), entity.getHocPhi(), entity.getThoiLuong(),
                entity.getNgayKG(), entity.getGhiChu(), entity.getMaNV());
    }

    @Override
    public void update(KhoaHoc entity) {
        String UPDATE_SQL = "update KhoaHoc SET  HocPhi=?, ThoiLuong=?, NgayKG=?, GhiChu=? WHERE MaKH=?";
        JdbcHelper.update(UPDATE_SQL, entity.getHocPhi(), entity.getThoiLuong(),
                entity.getNgayKG(), entity.getGhiChu(), entity.getMaKH());
    }

    @Override
    public void delete(String key) {
        String DELETE_SQL = "DELETE FROM KhoaHoc WHERE MaKH=?";
        JdbcHelper.update(DELETE_SQL, key);
    }

    @Override
    public List<KhoaHoc> selectAll() {
        String SELECT_ALL_SQL = "SELECT * FROM KhoaHoc";
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public KhoaHoc selectById(String key) {
        String SELECT_BY_ID = "SELECT * FROM KhoaHoc WHERE MaKH=?";
        List<KhoaHoc> list = this.selectBySql(SELECT_BY_ID, key);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    protected List<KhoaHoc> selectBySql(String sql, Object... args) {
        List<KhoaHoc> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                KhoaHoc kh = new KhoaHoc();
                kh.setMaKH(rs.getInt("MaKH"));
                kh.setMaCD(rs.getString("MaCD"));
                kh.setHocPhi(rs.getDouble("HocPhi"));
                kh.setThoiLuong(rs.getInt("ThoiLuong"));
                kh.setNgayKG(rs.getDate("NgayKG"));
                kh.setGhiChu(rs.getString("GhiChu"));
                kh.setMaNV(rs.getString("MaNV"));
                kh.setNgayTao(rs.getDate("NgayTao"));
                list.add(kh);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<KhoaHoc> selectByChuyenDe(String macd) {
        String sql = "select * from KhoaHoc where macd = ?";
        return this.selectBySql(sql, macd);
    }

    public List<Integer> selectYears() {
        String sql = "select distinct year(ngaykg) year from khoahoc order by year desc";
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql);
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

}
