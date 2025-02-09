package cn.yam.service;

import cn.yam.domain.Books;
import cn.yam.domain.Borrowrecords;
import cn.yam.vo.PageResult;

import java.util.List;

public interface BorrowrecordsService{

    int deleteByPrimaryKey(Integer recordId);

    int insert(Borrowrecords record);

    int insertSelective(Borrowrecords record);

    Borrowrecords selectByPrimaryKey(Integer recordId);

    int updateByPrimaryKeySelective(Borrowrecords record);

    int updateByPrimaryKey(Borrowrecords record);

    List<Borrowrecords> selectAll();


    List<Borrowrecords> selectByReaderId(Integer userId);

    List<Borrowrecords> selectByBookId(Integer bookId);
    

    List<Borrowrecords> selectByBorrowDateBetween(String startDate, String endDate);

    List<Borrowrecords> selectAllByReturnDateBetween(String startDate, String endDate);

    List<Borrowrecords> selectAllByUserId(Integer userId);


    PageResult<Borrowrecords> selectPageByUserId(Integer userId, Integer page, Integer size);


    PageResult<Borrowrecords> selectPage(Integer page, Integer size);
}
