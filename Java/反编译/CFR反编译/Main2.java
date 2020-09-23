// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Main2.java

package com.learn.java;

import java.io.PrintStream;

public class Main2
{

	public Main2()
	{
	}

	public static void main(String args[])
	{
		System.out.println((new StringBuilder()).append("方法返值为：").append(test()).toString());
	}

	public static int test()
	{
		int number = 1;
		System.out.println((new StringBuilder()).append("number被赋值为：").append(number).toString());
		int i;
		int number = 2;
		System.out.println((new StringBuilder()).append("number被赋值为：").append(number).toString());
		i = number;
		int number = 3;
		System.out.println((new StringBuilder()).append("number被赋值为：").append(number).toString());
		return i;
		Exception exception;
		exception;
		int number = 3;
		System.out.println((new StringBuilder()).append("number被赋值为：").append(number).toString());
		throw exception;
	}
}
