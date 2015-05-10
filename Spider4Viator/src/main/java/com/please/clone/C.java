package com.please.clone;

/**
 * 
 * 如果一个类，实现了 Cloneable, Object#clone() 就返回 <b>该对象的<b> 逐域拷贝，否则，就抛出
 * CloneNotSupportedException
 * 
 * 即，谁实现了 Cloneable, 则：Object#clone() 就可以返回 谁的 逐域拷贝
 * 
 * @author marvin
 * 
 */
public class C extends B implements Cloneable {
	public StringBuilder c_sb;

	public C() {
		c_sb = new StringBuilder("c_sb");
	}

	public C clone() {
		try {
			return (C) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		C orgn = new C();
		System.out.println(orgn.a_sb);
		System.out.println(orgn.b_sb);
		System.out.println(orgn.c_sb);

		C cpied = orgn.clone();
		System.out.println(cpied.a_sb);
		System.out.println(cpied.b_sb);
		System.out.println(cpied.c_sb);

	}
}
