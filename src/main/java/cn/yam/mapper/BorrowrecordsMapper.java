package cn.yam.mapper;

import cn.yam.domain.Bookclassification;
import cn.yam.domain.Borrowrecords;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BorrowrecordsMapper {
    /**
     * delete by primary key
     *
     * @param recordId primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Integer recordId);

    /**
     * insert record to table
     *
     * @param record the record
     * @return insert count
     */
    int insert(Borrowrecords record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(Borrowrecords record);

    /**
     * select by primary key
     *
     * @param recordId primary key
     * @return object by primary key
     */
    Borrowrecords selectByPrimaryKey(Integer recordId);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Borrowrecords record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Borrowrecords record);

    List<Borrowrecords> selectAll();

    List<Borrowrecords> selectByReaderId(@Param("readerId") Integer readerId);

    List<Borrowrecords> selectByBookId(@Param("bookId") Integer bookId);

    List<Borrowrecords> selectByBorrowDateBetween(@Param("minBorrowDate") String minBorrowDate, @Param("maxBorrowDate") String maxBorrowDate);

    List<Borrowrecords> selectAllByReturnDateBetween(@Param("minReturnDate") String minReturnDate, @Param("maxReturnDate") String maxReturnDate);

    List<Borrowrecords> selectAllByUserId(@Param("userId") Integer userId);





}