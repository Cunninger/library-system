package cn.yam.controller;

import cn.yam.vo.ApiResponse;
import cn.yam.vo.LoginVo;
import cn.yam.domain.Readers;
import cn.yam.service.ReadersService;
import cn.yam.vo.RegisterVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * (readers)表控制层
 *
 * @author xxxxx
 */

@RestController
@RequestMapping("/readers")
public class ReadersController {
    /**
     * 服务对象
     */
    @Autowired
    private ReadersService readersService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation("通过主键查询单条数据")
    @GetMapping("selectOne")
    public Readers selectOne(Integer id) {
        return readersService.selectByPrimaryKey(id);
    }


    /**
     * 通过联系方式查询单条数据
     */
    @ApiOperation("通过联系方式查询单条数据")
    @GetMapping("selectByContactInfo")
    public List<Readers> selectByContactInfo(String contact) {
        return readersService.selectByContactInfo(contact);
    }

    /**
     * 根据读者姓名查
     */
    @ApiOperation("根据读者姓名查")
    @GetMapping("selectByName")
    public List<Readers> selectByName(String name) {
        return readersService.selectByName(name);
    }

    /**
     * 登录
     */
    @ApiOperation("登录")
    @PostMapping("login")
    public ApiResponse<Readers> login(@RequestBody LoginVo loginVo) {
        if (loginVo == null) {
            return new ApiResponse<>(400, "用户名或密码为空", null);
        }
        Readers readers = readersService.login(loginVo);

        if (readers != null) {
            return new ApiResponse<>(200, "注册成功", readers);
        } else {
            return new ApiResponse<>(400, "用户名或密码错误", null);
        }

    }




}
