package com.zhx.modules.sys;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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

import com.zhx.captcha.Captcha;
import com.zhx.captcha.GifCaptcha;
import com.zhx.captcha.SpecCaptcha;
import com.zhx.log.OpLog;
import com.zhx.modules.common.Global;
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
	public String loginIn(HttpServletRequest request,HttpServletResponse response,
			SysUser user,String validCode,Model model){
		logger.info("user login,user info:"+user);
		String result = "";
		try {
			HttpSession session = request.getSession();
			//判断验证码是否正确
			String code = session.getAttribute(Const.SESSION_CAPTCHA_CODE).toString();//获取session中的验证码
			if(code.toLowerCase().equals(validCode.toLowerCase())){
				//根据输入的用户名查询用户是否存在
				SysUser loginUser = userService.queryByUserCode(user.getUserCode());
				if(null!=loginUser){//用户存在
					//用户是否启用
					if("0".equals(loginUser.getUseStatus())){
						result = "该用户被禁用，请联系管理员！";
					}else{
						String pwd = DESUtils.decrypt(loginUser.getPassword());//获取数据库中该用户的密码
						if(pwd.equals(user.getPassword())){//如果密码一致，登录成功
							//查询该登录用户的权限信息
//							List<Map<String,Object>> right = (List<Map<String, Object>>) queryRights(loginUser.getId());
//							session.setAttribute(Const.SESSION_RIGHT, right);//用户的权限
							session.setAttribute(Const.SESSION_USER, loginUser);//用户信息
							session.setAttribute(Const.SESSION_USER_ID, loginUser.getId());//用户信息
							session.setAttribute(Const.SESSION_USER_NAME, loginUser.getUserName());//用户名称
//							session.setAttribute(Const.SESSION_RIGHT_CHANGED, false);//用户名称
//							session.setAttribute(Const.SESSION_RIGHT_CHANGED_MENU, "");//用户名称
							logger.info("login success,user info:"+loginUser);
							//登录成功跳转到首页
							return "web/index";
						}else{
							//密码错误
							result = "密码错误！";
						}
					}
				}else{//用户不存在
					result = "该用户不存在！";
				}
			}else{
				result = "您输出的验证码不正确！";
			}
		} catch (Exception e) {
			result = e.getMessage();
			logger.error(e.getMessage());
		}
		model.addAttribute("user",user);
		model.addAttribute("message",result);
		return "web/login";
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
	 * 获取验证码
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping(value="/captcha",method=RequestMethod.GET)
	public void captcha(HttpServletRequest request,HttpServletResponse response) throws Exception{
		logger.info("create captcha for login start...");
		Captcha captcha = null;
		try{
			//获取要生产的验证码的类型:gif和png
			String captchaType=Global.getConfig(Const.CAPTCHA_TYPE);
			//要生成的验证码中字母的个数
			int captchaCharNum = Integer.valueOf(Global.getConfig(Const.CAPTCHA_CHAR_NUM));
			if(Const.CAPTCHA_TYPE_GIF.equals(captchaType)){
		        captcha = new GifCaptcha(120,40,captchaCharNum).makeCode();//gif格式动画验证码：宽，高，字母个数
			}else if(Const.CAPTCHA_TYPE_PNG.equals(captchaType)){
				captcha = new SpecCaptcha(120,40,captchaCharNum).makeCode();//png格式验证码：宽，高，字母个数
			}
			String code = captcha.text();//具体字母
			request.getSession().setAttribute(Const.SESSION_CAPTCHA_CODE, code);
			logger.info("captcha-------------->"+code);
			response.setContentType("image/png"); 
			captcha.out(response.getOutputStream());
		}catch(Exception e){
			logger.error(e.getMessage());
			throw e;
		}
		logger.info("create captcha for login end...");
	}
}
