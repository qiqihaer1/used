package com.keduox.util.exception;

/**
*  @author  马普琪    微信(bamaoer77)  微博（八猫儿77）
*/
public class NoUidException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public NoUidException() {
		super("用户对象数据中，没有uid数据");
	}
	
}
