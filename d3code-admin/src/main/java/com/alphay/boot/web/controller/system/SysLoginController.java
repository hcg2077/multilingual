package com.alphay.boot.web.controller.system;

import com.alphay.boot.common.constant.Constants;
import com.alphay.boot.common.core.domain.AjaxResult;
import com.alphay.boot.common.core.domain.entity.SysMenu;
import com.alphay.boot.common.core.domain.entity.SysUser;
import com.alphay.boot.common.core.domain.model.LoginBody;
import com.alphay.boot.common.utils.SecurityUtils;
import com.alphay.boot.system.domain.vo.RouterVo;
import com.alphay.boot.system.service.ISysMenuService;
import com.alphay.boot.web.service.SysLoginService;
import com.alphay.boot.web.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * 登录验证
 *
 * @author d3code
 */
@RestController
public class SysLoginController
{
    @Autowired
    private SysLoginService loginService;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;

    /**
     * 登录方法
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody)
    {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo()
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public AjaxResult getRouters()
    {
        Long userId = SecurityUtils.getUserId();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId);

        System.out.println("当前用户的菜单"+menus.toString());

        List<RouterVo> routerVos = menuService.buildMenus(menus);

/*
        List<RouterVo> routerVos1 = menuService.listUserMenus(menus);
*/

        return AjaxResult.success(routerVos);
    }
}
