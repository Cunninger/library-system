package cn.yam.controller;

import cn.yam.config.CaptchaConfig;
import cn.yam.domain.Borrowrecords;
import cn.yam.domain.Readers;
import cn.yam.service.ReadersService;
import cn.yam.utils.TokenUtils;
import cn.yam.vo.*;
import cn.yam.domain.Users;
import cn.yam.service.UsersService;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.el.parser.Token;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (users)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("/users")
@Slf4j
public class UsersController {
    /**
     * 服务对象
     */
    @Autowired
    private UsersService usersService;

    @Autowired
    private ReadersService readersService;
    @Autowired
    private CaptchaConfig captchaConfig;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation("通过主键查询单条数据")
    @GetMapping("selectOne")
    public Users selectOne(Integer id) {
        return usersService.selectByPrimaryKey(id);
    }


    /**
     * 新增用户
     */
    @ApiOperation("新增用户")
    @PostMapping("add")
    public int add(@RequestBody Users users) {
        return usersService.insert(users);
    }

    /**
     * 删除用户
     */
    @ApiOperation("删除用户")
    @DeleteMapping("delete")
    public int delete(Integer id) {
        return usersService.deleteByPrimaryKey(id);
    }

    /**
     * 更新用户
     */
    @ApiOperation("更新用户")
    @PutMapping("update")
    public int update(@RequestBody Users users) {
        return usersService.updateByPrimaryKey(users);
    }

    /**
     * 查询所有用户
     */
    @ApiOperation("查询所有用户")
    @GetMapping("selectAll")
    public List<Users> selectAll() {
        return usersService.selectAll();
    }

    /**
     * 根据角色查询用户
     */
    @ApiOperation("根据角色查询用户")
    @GetMapping("selectByRole")
    public List<Users> selectByRole(String role) {
        return usersService.selectByRole(role);
    }

    /**
     * 登录
     */
    @ApiOperation("登录")
    @PostMapping("login")
    public ApiResponse<Users> login(@RequestBody @Valid LoginVo loginVo) {
        String captcha = captchaConfig.getCaptchaMap().get("CAPTCHA");
        if (!captcha.equals(loginVo.getCaptcha().toLowerCase())) {
            return new ApiResponse<>(1001, "验证码错误", null);
        }
        Users user = usersService.login(loginVo);
        if (user == null) {
            return new ApiResponse<>(400, "用户名或密码错误", null);
        }
        String token = TokenUtils.genToken(user.getUserId().toString(), user.getPassword());
        user.setToken(token);

        return new ApiResponse<>(200, "登录成功", user);
    }


    /**
     * 注册
     */
    @ApiOperation("注册")
    @PostMapping("register")
    public ApiResponse<Users> register(@RequestBody RegisterVo registerVo) {
//        int a = 1/0;
        if (registerVo == null) {
            return new ApiResponse<>(400, "用户名或密码不能为空", null);
        }

        Users users = new Users();
        BeanUtils.copyProperties(registerVo, users);
        boolean success = usersService.insert(users) > 0;
        if (success) {
            return new ApiResponse<>(200, "注册成功", users);
        } else {
            return new ApiResponse<>(500, "注册失败", null);
        }

    }

    /**
     * 修改密码
     */
    @ApiOperation("修改密码")
    @PostMapping("updatePassword")
    public ApiResponse<Users> updatePassword(@RequestBody @Valid PwdVO pwdVO) {
        if (pwdVO == null) {
            return new ApiResponse<>(400, "填写字段不能为空！", null);
        }
        Users user = usersService.selectByPrimaryKey(pwdVO.getUserId());
        if (user == null) {
            return new ApiResponse<>(400, "用户不存在", null);
        }
        if (!user.getPassword().equals(pwdVO.getOldPwd())) {
            return new ApiResponse<>(400, "原密码错误", null);
        }
        user.setPassword(pwdVO.getNewPwd());
        int result = usersService.updateByPrimaryKey(user);
        if (result > 0) {
            return new ApiResponse<>(200, "修改成功", user);
        } else {
            return new ApiResponse<>(500, "修改失败", null);
        }
    }


    @ApiOperation("分页查询")
    @GetMapping("selectByPage")
    public PageResult<Users> selectByPage(@RequestParam Integer page, @RequestParam Integer size) {
        return usersService.selectByPage(page, size);
    }

    @ApiOperation("根据用户名或者邮箱或者手机号查询")
    @GetMapping("selectByUsernameOrEmailOrPhone")
    public List<Users> selectByUsernameOrEmailOrPhone(String keywords) {
        String username = keywords;
        String email = keywords;
        String phone = keywords;
        return usersService.selectByUsernameOrEmailOrPhone(username, email, phone);
    }

    /**
     * 生成验证码
     *
     * @param request  请求对象
     * @param response 响应对象
     * @throws Exception 异常
     */
    @ApiOperation("生成验证码")
    @GetMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 创建GifCaptcha对象
        GifCaptcha gifCaptcha = new GifCaptcha(130, 48, 4);
        // 输出验证码图片
        CaptchaUtil.out(gifCaptcha, request, response);
        // 获取验证码文本
        String verCode = gifCaptcha.text().toLowerCase();

        captchaConfig.getCaptchaMap().put("CAPTCHA", verCode);
//        log.info("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx验证码：{}xxxxxxxxxxx", verCode);
//        throw new RuntimeException("Deliberate exception to trace the code flow");
    }


}
