package com.keduox.util;

/**
*  @author  马普琪    微信(bamaoer77)  微博（八猫儿77）
*/
public class NullUtil {

	/**
	 * 判断一个字符串是否为空
	 * @param str
	 * @return  true  空的    false 不为空
	 */
	public static boolean isNull(String str) {
		if (str == null || str.trim().length() == 0) {
			return true;
		}
		return false;
	}
}
