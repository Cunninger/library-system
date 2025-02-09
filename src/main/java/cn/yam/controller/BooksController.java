package cn.yam.controller;

import cn.yam.domain.Books;
import cn.yam.domain.Borrowrecords;
import cn.yam.service.BooksService;
import cn.yam.service.impl.BooksServiceImpl;
import cn.yam.vo.BorrowrecordsVo;
import cn.yam.vo.PageResult;
import cn.yam.vo.StatusVo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * (books)表控制层
 *
 * @author xxxxx
 */

@RestController
@RequestMapping("/books")
public class BooksController {
    /**
     * 服务对象
     */
    @Autowired
    private BooksService booksService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation("通过主键查询单条数据")
    @GetMapping("selectOne")
    public Books selectOne(Integer id) {
        return booksService.selectByPrimaryKey(id);
    }

    @ApiOperation("查询书籍数据")
    @GetMapping("selectAll")
    public List<Books> selectAll() {
        return booksService.selectAll();
    }


    @ApiOperation("添加书籍数据")
    @PostMapping("insert")
    public int insert(@RequestBody Books record) {
        return booksService.insert(record);
    }


    @ApiOperation("删除书籍数据")
    @DeleteMapping("delete")
    public int deleteByPrimaryKey(Integer bookId) {
        return booksService.deleteByPrimaryKey(bookId);
    }


    @ApiOperation("更新书籍数据")
    @PutMapping("update")
    public int updateByPrimaryKey(@RequestBody Books books) {
        // 把毫秒转为年份
        if (books.getYearPublished() != null) {
            // 字符串转整数
            Integer yearPublished = (Integer) books.getYearPublished();
            books.setYearPublished(Integer.valueOf((yearPublished / 1000 / 60 / 60 / 24 / 365 + 1970)));
        }
        return booksService.updateByPrimaryKey(books);
    }


    /**
     * 通过书名或者作者查询书籍
     */
    @ApiOperation("通过书名或者作者查询书籍")
    @GetMapping("selectByAuthorOrTitle")
    public List<Books> selectByAuthorOrTitle(String keywords) {
        return booksService.selectByAuthorOrTitle(keywords, keywords);
    }


    /**
     * 通过出版社查询书籍
     */
    @ApiOperation("通过出版社查询书籍")
    @GetMapping("selectAllByPublisher")
    public List<Books> selectAllByPublisher(String publisher) {
        return booksService.selectAllByPublisher(publisher);
    }


    /**
     * 通过出版年份查询书籍
     */
    @ApiOperation("通过出版年份查询书籍")
    @GetMapping("selectAllByYearPublished")
    public List<Books> selectAllByYearPublished(Object yearPublished) {
        return booksService.selectAllByYearPublished(yearPublished);
    }


    /**
     * 根据状态查询书籍
     */
    @ApiOperation("根据状态查询书籍")
    @GetMapping("selectByStatus")
    public List<Books> selectByStatus(Object status) {
        return booksService.selectByStatus(status);
    }


    /**
     * 根据主键更新书籍状态
     */
    @ApiOperation("根据主键更新书籍状态")
    @PutMapping("updateStatusByBookId")
    public int updateStatusByBookId(@RequestBody StatusVo statusVo) {
        System.out.println(statusVo);
        return booksService.updateStatusByBookId(statusVo);
    }

    /**
     * 分页查询
     */
    @ApiOperation("分页查询")
    @GetMapping("selectByPage")
    public PageResult<Books> selectByPage(@RequestParam Integer page, @RequestParam Integer size) {

        return booksService.selectByPage(page, size);
    }


}
