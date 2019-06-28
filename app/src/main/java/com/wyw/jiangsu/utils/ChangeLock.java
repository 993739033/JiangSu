package com.wyw.jiangsu.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 小写转 大写
 */
public class ChangeLock {

	public String numToUpperETA1(String c) {
		String u[] = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
		char[] str = String.valueOf(c).toCharArray();
		String rstr = "";
		for (int i = 0; i < str.length; i++) {
			rstr = rstr + u[Integer.parseInt(str[i] + "")];
		}
		return rstr;
	}

	public String changeToQuantity_unit1(String value) {
		String rv = "";
		if (value.contains(".")) {
			int dindex = value.indexOf(".");
			String zs = value.substring(0, dindex);
			if (!value.endsWith(".")) {
				String xs = value.substring(dindex + 1);
				Integer inta = Integer.parseInt(xs);
				if (inta > 0) {
					rv = changeToQuantity_unit(zs) + "点" + numToUpperETA1(xs);
				} else {
					rv = changeToQuantity_unit(zs);
				}
			} else {
				rv = changeToQuantity_unit(zs);
			}
		} else
			rv = changeToQuantity_unit(value);
		return rv;
	}

	public String changeToQuantity_unit(String value) {
		String Numbers = "";
		String Chinese = "";
		String zhengfix = "";
		String xiaofix = "";
		Pattern p = Pattern.compile("\\d");
		Matcher m = p.matcher(value);
		Pattern p1 = Pattern.compile("[\\u4e00-\\u9fa5]");
		Matcher m1 = p1.matcher(value);
		while (m.find()) {
			Numbers += m.group();
		}
		while (m1.find()) {
			Chinese += m1.group();
		}
		if (Numbers != "") {

			double a = Double.parseDouble(Numbers);
			char[] sbq = { '拾', '佰', '仟' }; // 段内位置表示
			char[] wy = { '万', '亿' }; // 段名表示
			char[] numCaps = { '零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖' }; // 数字表示
			long midVal = (long) (a * 100); // 转化成整形
			String valStr = String.valueOf(midVal); // 转化成字符串

			String zheng = valStr.substring(0, valStr.length() - 2); // 取整数部分
			String xiao = valStr.substring(valStr.length() - 2); // 取小数部分

			// 整数部分转化的结果
			// 小数部分转化的结果
			// 处理小数点后面的数
			if (xiao.equals("00")) { // 如果小数部分为0
				xiaofix = "";
			} else {
				xiaofix = numCaps[xiao.charAt(0) - '0'] + ""
						+ numCaps[xiao.charAt(1) - '0'] + ""; // 否则把角分转化出来
			}
			// 处理小数点前面的数
			char[] zhen_char = zheng.toCharArray(); // 把整数部分转化成字符数组
			boolean preZero = false; // 标志当前位的上一位是否为有效0位（如万位的0对千位无效）
			byte zeroSerNum = 0; // 连续出现0的次数

			for (int i = 0; i < zhen_char.length; i++) { // 循环处理每个数字

				int idx = (zhen_char.length - i - 1) % 4; // 取段内位置
				// System.out.println("idx:"+idx);

				int vidx = (zhen_char.length - i - 1) / 4; // 取段位置
				// System.out.println("vidx:"+vidx);

				if (zhen_char[i] == '0') { // 如果当前字符是0
					preZero = true;
					zeroSerNum++; // 连续0次数递增
					if (idx == 0 && vidx > 0 && zeroSerNum < 4) {
						zhengfix += wy[vidx - 1];
						preZero = false; // 不管上一位是否为0，置为无效0位
					}
				} else {
					zeroSerNum = 0; // 连续0次数清零
					if (preZero) { // 上一位为有效0位
						zhengfix += numCaps[0]; // 只有在这地方用到'零'
						preZero = false;
					}
					zhengfix += numCaps[zhen_char[i] - '0']; // 转化该数字表示
					if (idx > 0)
						zhengfix += sbq[idx - 1];
					if (idx == 0 && vidx > 0) {
						zhengfix += wy[vidx - 1]; // 段结束位置应该加上段名如万,亿
					}
				}
			}
			if (!xiao.equals("00")) { // 如果小数部分为0
				zhengfix += '点';
			}
		}
		// if(zhengfix.length() > 0) zhengfix += '圆';
		// 如果整数部分存在,则有圆的字样
		zhengfix += Chinese;
		return zhengfix + xiaofix; // 返回正确表示
	}

	public String numToUpper(char c) {
		String u[] = { "〇", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
		char[] str = String.valueOf(c).toCharArray();
		String rstr = "";
		for (int i = 0; i < str.length; i++) {
			rstr = rstr + u[Integer.parseInt(str[i] + "")];
		}
		return rstr;
	}

	public String numToUpperModer(int c) {
		String u[] = { "", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十",
				"十一", "十二" };
		return u[c];
	}

	public String Change(String str) {

		char[] aa = str.toCharArray();
		String cc = "";
		String a = "";
		for (int i = 0; i < aa.length; i++) {
			if (aa[i] <= '9' && aa[i] >= '0') {
				a = numToUpper(aa[i]);
			} else {
				a = "" + aa[i] + "";
			}
			cc += a;
		}
		return cc;
	}

	public String numToUpperETA(char c) {
		String u[] = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
		char[] str = String.valueOf(c).toCharArray();
		String rstr = "";
		for (int i = 0; i < str.length; i++) {
			rstr = rstr + u[Integer.parseInt(str[i] + "")];
		}
		return rstr;
	}

	public String ChangeETA(String str) {
		char[] aa = str.toCharArray();
		String cc = "";
		String a = "";
		for (int i = 0; i < aa.length; i++) {
			if (aa[i] <= '9' && aa[i] >= '0') {
				a = numToUpperETA(aa[i]);
			} else {
				a = "" + aa[i] + "";
			}
			cc += a;
		}
		return cc;
	}

	public String numToUpperday(int a) {
		String u[] = { "", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十",
				"十一", "十二", "十三", "十四", "十五", "十六", "十七", "十八", "十九", "二十",
				"二十一", "二十二", "二十三", "二十四", "二十五", "二十六", "二十七", "二十八", "二十九",
				"三十", "三十一" };
		return u[a];
	}

}
