package cn.yam.controller;

import cn.yam.common.BaseResponse;
import cn.yam.common.ResultUtils;
import cn.yam.domain.Bookclassification;
import cn.yam.domain.Books;
import cn.yam.service.BookclassificationService;
import cn.yam.service.BorrowrecordsService;
import cn.yam.service.impl.BookclassificationServiceImpl;
import cn.yam.vo.PageResult;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * (bookclassification)表控制层
 *
 * @author xxxxx
 */

@RestController
@RequestMapping("/bookclassification")

public class BookclassificationController {
    /**
     * 服务对象
     */
    @Autowired
    private BookclassificationService bookclassificationService;// 引入bookclassificationService

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation("通过主键查询单条数据")
    @GetMapping("selectOne")
    public Bookclassification selectOne(Integer id) {
        return bookclassificationService.selectByClassificationId(id);
    }


    @ApiOperation("添加数据")
    @PostMapping("insert")
    public int insert(@RequestBody Bookclassification record) {
        return bookclassificationService.insert(record);
    }


    /**
     * 删除
     */
    @ApiOperation("删除数据")
    @DeleteMapping("delete")
    public int delete(Integer id) {
        return bookclassificationService.deleteByClassificationId(id);
    }

    /**
     * 查询所有数据
     */
    @ApiOperation("查询所有数据")//
    @GetMapping("selectAll")// 前端请求selectAll，调用selectAll方法
    public List<Bookclassification> selectAll() {

        return bookclassificationService.selectAll();
    }

    /**
     * 根据主键更新数据
     */
    @ApiOperation("根据主键更新数据")
    @PutMapping("update")
    public BaseResponse<Integer> update(@RequestBody Bookclassification record) {
        int i = bookclassificationService.updateByClassificationId(record);
        return ResultUtils.success(i);
    }
    

    /**
     * 根据分类描述或者分类名 查询
     */
    @ApiOperation("根据分类描述或者分类名查询")
    @GetMapping("selectByClassDescriptionOrClassName")
    public BaseResponse<List<Bookclassification>> selectByClassDescriptionOrClassName(String keywords) {
        String classDescription = keywords;
        String className = keywords;
        List<Bookclassification> bookclassifications = bookclassificationService.selectByClassDescriptionOrClassName(classDescription, className);
       return ResultUtils.success(bookclassifications);
    }


    @ApiOperation("分页查询")
    @GetMapping("selectByPage")
    public PageResult<Bookclassification> selectByPage(@RequestParam Integer page, @RequestParam Integer size) {
        // 调用服务层方法进行分页查询
        return bookclassificationService.selectByPage(page, size);
    }

}
