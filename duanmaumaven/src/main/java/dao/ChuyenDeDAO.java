/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.ChuyenDe;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import ui.QuanLiChuyenDeJDialog;
import utils.JdbcHelper;
import utils.MsgBox;

/**
 *
 * @author Admin
 */
public class ChuyenDeDAO extends EduSysDAO<ChuyenDe, String> {

    @Override
    public void insert(ChuyenDe entity) {
        String INSERT_SQL = "insert into ChuyenDe values (?,?,?,?,?,?)";
        JdbcHelper.update(INSERT_SQL, entity.getMaCD(), entity.getTenCD(), entity.getHocPhi(),
                entity.getThoiLuong(), entity.getHinh(), entity.getMoTa());
    }

    @Override
    public void update(ChuyenDe entity) {
        String UPDATE_SQL = "update ChuyenDe SET TenCD=?, HocPhi=?, ThoiLuong=?, Hinh=?, MoTa=?  WHERE MaCD=?";
        JdbcHelper.update(UPDATE_SQL, entity.getTenCD(), entity.getHocPhi(), entity.getThoiLuong(),
                entity.getHinh(), entity.getMoTa(), entity.getMaCD());
    }

    @Override
    public void delete(String key) {
        String DELETE_SQL = "DELETE FROM ChuyenDe WHERE MaCD=?";
        JdbcHelper.update(DELETE_SQL, key);
    }

    @Override
    public List<ChuyenDe> selectAll() {
        String SELECT_ALL_SQL = "SELECT * FROM ChuyenDe";
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public ChuyenDe selectById(String key) {
        String SELECT_BY_ID = "SELECT * FROM ChuyenDe WHERE MaCD=?";
        List<ChuyenDe> list = this.selectBySql(SELECT_BY_ID, key);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    protected List<ChuyenDe> selectBySql(String sql, Object... args) {
        List<ChuyenDe> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                ChuyenDe cd = new ChuyenDe();
                cd.setMaCD(rs.getString("MaCD"));
                cd.setTenCD(rs.getString("TenCD"));
                cd.setHocPhi(rs.getDouble("HocPhi"));
                cd.setThoiLuong(rs.getInt("ThoiLuong"));
                cd.setHinh(rs.getString("Hinh"));
                cd.setMoTa(rs.getString("MoTa"));
                list.add(cd);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
