package com.conch.wanandroid

import org.junit.Test

/**
 * 对字符串进行置换操作
 * @author YeJain
 * @date 2022/9/18 11:44
 */
class StringPermuteTest {
    @Test
    fun testStringPermute() {
        val str = "ABCD"
        permute(str, 0, str.length - 1)
    }

    private fun permute(str: String, left: Int, right: Int) {
        if (left == right) {
            println(str)
            return
        }

        var newStr: String
        for (i in left..right) {
            newStr = swapString(str, left, i)
            permute(newStr, left + 1, right)
        }
    }


    private fun swapString(str: String, startPosition: Int, endPosition: Int): String {
        val temp: Char
        val charArr = str.toCharArray()
        temp = charArr[startPosition]
        charArr[startPosition] = charArr[endPosition]
        charArr[endPosition] = temp
        return charArr.concatToString()
    }


}