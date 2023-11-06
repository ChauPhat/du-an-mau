/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.NguoiHoc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.JdbcHelper;

/**
 *
 * @author Admin
 */
public class NguoiHocDAO extends EduSysDAO<NguoiHoc, String> {

    @Override
    public void insert(NguoiHoc entity) {
        String INSERT_SQL = "insert into NguoiHoc values (?,?,?,?,?,?,?,?,?)";
        JdbcHelper.update(INSERT_SQL, entity.getMaNH(), entity.getHoTen(), entity.getNgaySinh(),
                entity.isGioiTinh(), entity.getDienThoai(), entity.getEmail(), entity.getGhiChu(), entity.getMaNV(),
                entity.getNgayDK());
    }

    @Override
    public void update(NguoiHoc entity) {
        String UPDATE_SQL = "update NguoiHoc SET HoTen=?, NgaySinh=?, GioiTinh=?, DienThoai=?, Email=?, GhiChu=?, "
                + "MaNV=? WHERE MaNH=?";
        JdbcHelper.update(UPDATE_SQL, entity.getHoTen(), entity.getNgaySinh(),
                entity.isGioiTinh(), entity.getDienThoai(), entity.getEmail(), entity.getGhiChu(), entity.getMaNV(),
                entity.getMaNH());
    }

    @Override
    public void delete(String key) {
        String DELETE_SQL = "DELETE FROM NguoiHoc WHERE MaNH=?";
        JdbcHelper.update(DELETE_SQL, key);
    }

    @Override
    public List<NguoiHoc> selectAll() {
        String SELECT_ALL_SQL = "SELECT * FROM NguoiHoc";
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public NguoiHoc selectById(String key) {
        String SELECT_BY_ID = "SELECT * FROM NguoiHoc WHERE MaNH=?";
        List<NguoiHoc> list = this.selectBySql(SELECT_BY_ID, key);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    protected List<NguoiHoc> selectBySql(String sql, Object... args) {
        List<NguoiHoc> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                NguoiHoc nh = new NguoiHoc();
                nh.setMaNH(rs.getString("MaNH"));
                nh.setHoTen(rs.getString("HoTen"));
                nh.setNgaySinh(rs.getDate("NgaySinh"));
                nh.setGioiTinh(rs.getBoolean("GioiTinh"));
                nh.setDienThoai(rs.getString("DienThoai"));
                nh.setEmail(rs.getString("Email"));
                nh.setGhiChu(rs.getString("GhiChu"));
                nh.setMaNV(rs.getString("MaNV"));
                nh.setNgayDK(rs.getDate("NgayDK"));
                list.add(nh);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<NguoiHoc> selectByKeyword(String keyword) {
        String sql = "select * from NguoiHoc where MaNH like ? or HoTen like ? or DienThoai like ? or Email like ? or MaNV like ?";
        return this.selectBySql(sql, "%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%");
    }

    public List<NguoiHoc> selectNotInCourse(int makh, String keyword) {
        String sql = "select * from nguoihoc where hoten like ? and "
                + "manh not in(select manh from hocvien where makh = ?)";
        return this.selectBySql(sql, "%" + keyword + "%", makh);
    }

}
