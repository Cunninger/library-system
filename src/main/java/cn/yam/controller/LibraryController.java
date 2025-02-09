package cn.yam.controller;

import cn.yam.domain.Bookclassification;
import cn.yam.domain.Library;
import cn.yam.service.LibraryService;
import cn.yam.service.impl.LibraryServiceImpl;
import cn.yam.vo.PageResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * (library)表控制层
 *
 * @author xxxxx
 */

@RestController
@RequestMapping("/library")
public class LibraryController {
    /**
     * 服务对象
     */
    @Autowired
    private LibraryService libraryService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Library selectOne(Integer id) {
        return libraryService.selectByPrimaryKey(id);
    }


    @ApiOperation("查找所有图书馆信息")
    @GetMapping("selectAll")
    public List<Library> selectAll() {
        return libraryService.selectAll();
    }

    /**
     * 根据主键更新数据
     */
    @ApiOperation("根据主键更新数据")
    @PutMapping("update")

    public int update(@RequestBody Library record) {
        return libraryService.updateByPrimaryKey(record);
    }


    /**
     * 通过图书馆id查找图书馆名
     * @param libraryId
     * @return
     */
    @ApiOperation("通过图书馆id查找图书馆名")
    @GetMapping("selectLibraryNameByLibraryId")
    public List<String> selectLibraryNameByLibraryId(Integer libraryId) {
        return libraryService.selectLibraryNameByLibraryId(libraryId);
    }


    @ApiOperation("分页查询")
    @GetMapping("selectByPage")
    public PageResult<Library> selectByPage(@RequestParam Integer page, @RequestParam Integer size) {
        // 调用服务层方法进行分页查询
        return libraryService.selectByPage(page, size);
    }

    /**
     *  根据主键删除
     */
    @ApiOperation("根据主键删除")
    @DeleteMapping("delete")
    public int deleteByPrimaryKey(Integer libraryId) {
        return libraryService.deleteByPrimaryKey(libraryId);
    }

    /**
     * 添加数据
     * @param record
     * @return
     */
    @ApiOperation("添加数据")
    @PostMapping("insert")
    public int insert(@RequestBody Library record) {
        return libraryService.insert(record);
    }

}
