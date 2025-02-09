package cn.yam.service.impl;

import cn.yam.domain.Bookclassification;
import cn.yam.domain.Books;
import cn.yam.vo.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import cn.yam.domain.Borrowrecords;
import cn.yam.mapper.BorrowrecordsMapper;
import cn.yam.service.BorrowrecordsService;

import java.util.List;

@Service
public class BorrowrecordsServiceImpl implements BorrowrecordsService {

    @Autowired
    private BorrowrecordsMapper borrowrecordsMapper;

    @Override
    public int deleteByPrimaryKey(Integer recordId) {
        return borrowrecordsMapper.deleteByPrimaryKey(recordId);
    }

    @Override
    public int insert(Borrowrecords record) {
        return borrowrecordsMapper.insert(record);
    }

    @Override
    public int insertSelective(Borrowrecords record) {
        return borrowrecordsMapper.insertSelective(record);
    }

    @Override
    public Borrowrecords selectByPrimaryKey(Integer recordId) {
        return borrowrecordsMapper.selectByPrimaryKey(recordId);
    }

    @Override
    public int updateByPrimaryKeySelective(Borrowrecords record) {
        return borrowrecordsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Borrowrecords record) {
        return borrowrecordsMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Borrowrecords> selectAll() {
        return borrowrecordsMapper.selectAll();
    }

    @Override
    public List<Borrowrecords> selectByReaderId(Integer userId) {
        return borrowrecordsMapper.selectByReaderId(userId);
    }

    @Override
    public List<Borrowrecords> selectByBookId(Integer bookId) {
        return borrowrecordsMapper.selectByBookId(bookId);
    }

    @Override
    public List<Borrowrecords> selectByBorrowDateBetween(String startDate, String endDate) {
        return borrowrecordsMapper.selectByBorrowDateBetween(startDate, endDate);
    }

    @Override
    public List<Borrowrecords> selectAllByReturnDateBetween(String startDate, String endDate) {
        return borrowrecordsMapper.selectAllByReturnDateBetween(startDate, endDate);
    }

    @Override
    public List<Borrowrecords> selectAllByUserId(Integer userId) {
        return borrowrecordsMapper.selectAllByUserId(userId);
    }


    @Override
    public PageResult<Borrowrecords> selectPageByUserId(Integer userId, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        // 查询
        List<Borrowrecords> alllist = borrowrecordsMapper.selectAllByUserId(userId);
        Page p = (Page) alllist;// 强转为Page类型
        long total = p.getTotal();
        List result = p.getResult();
        return new PageResult<Borrowrecords>(total, result);
    }

    @Override
    public PageResult<Borrowrecords> selectPage(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        // 查询
        List<Borrowrecords> alllist = borrowrecordsMapper.selectAll();
        Page p = (Page) alllist;// 强转为Page类型
        long total = p.getTotal();
        List result = p.getResult();
        return new PageResult<Borrowrecords>(total, result);
    }


}
