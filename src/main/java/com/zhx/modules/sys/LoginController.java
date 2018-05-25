package com.zhx.modules.sys;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhx.log.OpLog;
import com.zhx.modules.constants.Const;
import com.zhx.modules.frames.BaseController;
import com.zhx.modules.sys.user.bean.SysUser;
import com.zhx.modules.sys.user.service.SysUserService;
import com.zhx.modules.utils.DESUtils;

/**
 * 登录controller
 * @author xwl
 *
 */
@Controller
public class LoginController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private SysUserService userService;
	
	/**
	 * 用户登录
	 * @param request
	 * @param response
	 * @param user
	 */
	@OpLog(optName="用户后台登录",optKey="用户登录",optType=Const.OPLOG_TYPE_SELECT)
	@RequestMapping(value="/loginIn",method=RequestMethod.POST)
	@ResponseBody
	public String loginIn(HttpServletRequest request,HttpServletResponse response,SysUser user){
		logger.info("user login,user info:"+user);
		String result = "";
		try {
			HttpSession session = request.getSession();
			//根据输入的用户名查询用户是否存在
			SysUser loginUser = userService.queryByUserCode(user.getUserCode());
			if(null!=loginUser){//用户存在
				//用户是否启用
				if("0".equals(loginUser.getUseStatus())){
					result = returnJson4Fail("该用户被禁用，请联系管理员！");
				}else{
					String pwd = DESUtils.decrypt(loginUser.getPassword());//获取数据库中该用户的密码
					if(pwd.equals(user.getPassword())){//如果密码一致，登录成功
						//查询该登录用户的权限信息
						List<Map<String,Object>> right = (List<Map<String, Object>>) queryRights(loginUser.getId());
						session.setAttribute(Const.SESSION_RIGHT, right);//用户的权限
						session.setAttribute(Const.SESSION_USER, loginUser);//用户信息
						session.setAttribute(Const.SESSION_USER_ID, loginUser.getId());//用户信息
						session.setAttribute(Const.SESSION_USER_NAME, loginUser.getUserName());//用户名称
						session.setAttribute(Const.SESSION_RIGHT_CHANGED, false);//用户名称
						session.setAttribute(Const.SESSION_RIGHT_CHANGED_MENU, "");//用户名称
						result = returnJson4Success("登录成功！");
						logger.info("login success,user info:"+loginUser);
					}else{
						//密码错误
						result = returnJson4Fail("密码错误！");
					}
				}
			}else{//用户不存在
				result = returnJson4Fail("该用户不存在！");
			}
		} catch (Exception e) {
			result = returnJson4Exception(e);
			logger.error(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 用户退出登录
	 * @param request
	 * @param response
	 */
	@OpLog(optName="用户后台退出登录",optKey="退出登录",optType=Const.OPLOG_TYPE_SELECT)
	@RequestMapping(value="/loginOut",method=RequestMethod.GET)
	public String loginOut(HttpServletRequest request,HttpServletResponse response){
		request.getSession().removeAttribute(Const.SESSION_USER);
		request.getSession().removeAttribute(Const.SESSION_USER_ID);
		request.getSession().removeAttribute(Const.SESSION_USER_NAME);
		request.getSession().removeAttribute(Const.SESSION_RIGHT);
		request.getSession().removeAttribute(Const.SESSION_RIGHT_CHANGED);
		request.getSession().removeAttribute(Const.SESSION_RIGHT_CHANGED_MENU);
		return "web/login";
	}
	
	/**
	 * 跳转到首页
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index(HttpServletRequest request,HttpServletResponse response,SysUser user){
		return "web/index";
	}
	
	/**
	 * 跳转到欢迎页
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/welcome",method=RequestMethod.GET)
	public String welcome(){
		return "web/welcome";
	}
	
	/**
	 * 跳转到个人资料
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/person",method=RequestMethod.GET)
	public String person(HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("user", getUser(request));
		return "web/person/profile";
	}
	
	/**
	 * 跳转到联系我们
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/contactus",method=RequestMethod.GET)
	public String contactus(HttpServletRequest request,HttpServletResponse response){
		return "web/person/contacts";
	}
}
