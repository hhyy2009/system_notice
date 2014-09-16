package com.chinaboxun.hessian.dao.entity;

import java.util.List;
import java.util.Map;

/***
 * 封装的登录用户数据实体类
 * @author yuzhongming
 * @createDate 2011-12-4 上午10:45:47
 * @since jdk1.6.0_26
 * @version 1.0
 *
 */
public class UserSessionObject implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	/********* 集团数据    ************/
	private String ecId;			//集团编号
	private String ecName;			//集团名称
	private String ecLogo;			//集团简称
	private String ecTel;			//集团电话
	private String ecFax;			//集团传真
	private String ecLinkMobile;	//集团联系人手机
	private String ecLinkMan;		//集团联系人姓名
	private String indCode;			//所属行业编号
	private String indName;			//所属行业名称 
	private String areaCode;		//所属区域编号
	private String areaName;		//所属区域名称 
	private String customNo;		//客户编号（对应BOSS客户数据）
	private String bsAccessNo;		//集团服务号码
	private String bsFeeNo;			//计费号码
	private String bsOtype;			//运营商标识(BOSS)
	private String bsCtype;			//通道类型(BOSS)	
	
	/********* 用户数据  *********/
	private String userId;			//用户编号
	private String userName;		//用户姓名
	private String userMobile;		//用户手机号
	private String userAccount;     //用户账号
	private String userTel;			//用户电话
	private String posId;			//用户职务编号（1员工，2主管）
	private String userVtype;		//用户验证类型（0普通密码，1手机验证码）
	private String userType;		//用户类型（1平台管理员，2集团管理员，3集团普通用户）
	private String rightModel;		//权限使用模式（1表示使用所属角色权限；2表示使用自定义权限）
	private String userSex;			//用户性别（M表示男性，F表示女性）
	private String userBirth;		//用户生日
	private String userEmail;		//用户邮箱
	private String userAddr;		//用户地址
	private String userSts;			//用户状态
	
	/********* 用户所属数据  *********/
	private String userDeptId;		//用户所属部门编号(最大长度31位)
	private String userDeptName;	//用户所属部门名称
	private String userRoleId;		//用户所属角色编号(数据类型)
	private String userRoleName;	//用户所属角色名称
	
	private List<Map> userDepts;   //用户所属部门，一个用户可以属于多个部门 
	
	private Map<String, String> vipMap;     //会员类型Map
	private String menuNoStr;
	private String menuUrl;
	
	public List<Map> getUserDepts() {
		return userDepts;
	}
	public void setUserDepts(List<Map> userDepts) {
		this.userDepts = userDepts;
	}
	public String getMenuNoStr() {
		return menuNoStr;
	}
	public void setMenuNoStr(String menuNoStr) {
		this.menuNoStr = menuNoStr;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	/********* 用户所拥有菜单的集团（顶级菜单） *********/
	private Map<String,String> menuMap;		//“一级菜单编号”做键，菜单所属产品的“产品编号”+“№”+“产品名称”为值
	
	/********* 集团所使用的产品的服务价格  *********/
	private Map<String, String> priceMap;	//“运营标识”+“№”+“产品编号”为键，“服务编号”+“№”+“价格”为值
	
	private List<String> productNoList;		//产品编号列表
	
	
	
	public List<String> getProductNoList() {
		return productNoList;
	}
	public void setProductNoList(List<String> productNoList) {
		this.productNoList = productNoList;
	}
	public String getEcId() {
		return ecId;
	}
	public void setEcId(String ecId) {
		this.ecId = ecId;
	}
	public String getEcName() {
		return ecName;
	}
	public void setEcName(String ecName) {
		this.ecName = ecName;
	}
	public String getEcLogo() {
		return ecLogo;
	}
	public void setEcLogo(String ecLogo) {
		this.ecLogo = ecLogo;
	}
	public String getEcTel() {
		return ecTel;
	}
	public void setEcTel(String ecTel) {
		this.ecTel = ecTel;
	}
	public String getEcFax() {
		return ecFax;
	}
	public void setEcFax(String ecFax) {
		this.ecFax = ecFax;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getEcLinkMobile() {
		return ecLinkMobile;
	}
	public void setEcLinkMobile(String ecLinkMobile) {
		this.ecLinkMobile = ecLinkMobile;
	}
	public String getIndCode() {
		return indCode;
	}
	public void setIndCode(String indCode) {
		this.indCode = indCode;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getCustomNo() {
		return customNo;
	}
	public void setCustomNo(String customNo) {
		this.customNo = customNo;
	}
	public String getBsAccessNo() {
		return bsAccessNo;
	}
	public void setBsAccessNo(String bsAccessNo) {
		this.bsAccessNo = bsAccessNo;
	}
	public String getBsFeeNo() {
		return bsFeeNo;
	}
	public void setBsFeeNo(String bsFeeNo) {
		this.bsFeeNo = bsFeeNo;
	}
	public String getBsOtype() {
		return bsOtype;
	}
	public void setBsOtype(String bsOtype) {
		this.bsOtype = bsOtype;
	}
	public String getBsCtype() {
		return bsCtype;
	}
	public void setBsCtype(String bsCtype) {
		this.bsCtype = bsCtype;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	public String getUserTel() {
		return userTel;
	}
	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}
	public String getPosId() {
		return posId;
	}
	public void setPosId(String posId) {
		this.posId = posId;
	}
	public String getUserVtype() {
		return userVtype;
	}
	public void setUserVtype(String userVtype) {
		this.userVtype = userVtype;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getRightModel() {
		return rightModel;
	}
	public void setRightModel(String rightModel) {
		this.rightModel = rightModel;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public String getUserBirth() {
		return userBirth;
	}
	public void setUserBirth(String userBirth) {
		this.userBirth = userBirth;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getEcLinkMan() {
		return ecLinkMan;
	}
	public void setEcLinkMan(String ecLinkMan) {
		this.ecLinkMan = ecLinkMan;
	}
	public String getIndName() {
		return indName;
	}
	public void setIndName(String indName) {
		this.indName = indName;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getUserDeptId() {
		return userDeptId;
	}
	public void setUserDeptId(String userDeptId) {
		this.userDeptId = userDeptId;
	}
	public String getUserDeptName() {
		return userDeptName;
	}
	public void setUserDeptName(String userDeptName) {
		this.userDeptName = userDeptName;
	}
	public String getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(String userRoleId) {
		this.userRoleId = userRoleId;
	}
	public String getUserRoleName() {
		return userRoleName;
	}
	public void setUserRoleName(String userRoleName) {
		this.userRoleName = userRoleName;
	}
	public String getUserAddr() {
		return userAddr;
	}
	public void setUserAddr(String userAddr) {
		this.userAddr = userAddr;
	}
	public String getUserSts() {
		return userSts;
	}
	public void setUserSts(String userSts) {
		this.userSts = userSts;
	}
	public Map<String, String> getMenuMap() {
		return menuMap;
	}
	public void setMenuMap(Map<String, String> menuMap) {
		this.menuMap = menuMap;
	}
	public Map<String, String> getPriceMap() {
		return priceMap;
	}
	public void setPriceMap(Map<String, String> priceMap) {
		this.priceMap = priceMap;
	}
	public Map<String, String> getVipMap() {
		return vipMap;
	}
	public void setVipMap(Map<String, String> vipMap) {
		this.vipMap = vipMap;
	}
	
}
