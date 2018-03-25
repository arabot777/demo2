package com.mmall.demo2;

import com.mmall.demo2.model.Permission;
import com.mmall.demo2.model.Role;
import com.mmall.demo2.model.User;
import com.mmall.demo2.server.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 认证授权核心类
 */
public class AuthRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;


    /**
     *  //授权
     *  对象从session中取
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User) principals.fromRealm(this.getClass().getName()).iterator().next();
        List<String > permissionList = new ArrayList<>();
        Set<Role> roleSet = user.getRoles();
        if (CollectionUtils.isNotEmpty(roleSet)){
            for (Role role: roleSet) {
                Set<Permission> permissionsSet = role.getPermissions();
                if (CollectionUtils.isNotEmpty(permissionsSet)) {
                    for (Permission permission : permissionsSet) {
                        permissionList.add(permission.getName());
                    }
                }
            }
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissionList);
        return info;
    }

    /**
     * 认证登录
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        User user = userService.findByUsername(username);

        return new SimpleAuthenticationInfo(user,user.getPassword(),this.getClass().getName());
    }
}