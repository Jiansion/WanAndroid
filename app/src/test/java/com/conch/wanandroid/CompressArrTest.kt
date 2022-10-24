package com.conch.wanandroid

import org.junit.Test

/**
 * https://leetcode.cn/problems/string-compression/
 * 数组压缩，压缩后长度小于或等于原长度
 * @author YeJain
 * @date 2022/9/18 10:54
 */
class CompressArrTest {

    private fun compress(chars: CharArray): Int {
        val originalSize = chars.size
        if (chars.size <= 1) return originalSize
        var count = 1
        var anchor = 1

        for (i in 1 until originalSize) {
            if (chars[i] == chars[i - 1]) {
                count++
            } else {
                if (count == 1) {
                    chars[anchor++] = chars[i]
                    continue
                } else {
                    val countArr = count.toString().toCharArray()
                    for (c in countArr) {
                        chars[anchor++] = c
                    }
                    chars[anchor++] = chars[i]
                    count = 1
                }
            }
        }

        if (count > 1) {
            val countArr = count.toString().toCharArray()
            for (c in countArr) {
                chars[anchor++] = c
            }
        }
        println("chars=${chars.contentToString()}")
        return anchor
    }


    @Test
    fun testArrCompress() {
        //val arr: CharArray = charArrayOf('a', 'a', 'b', 'b', 'c', 'c', 'c')
        // val arr: CharArray = charArrayOf('a')
        val arr: CharArray =
            charArrayOf('a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b')
        val count = compress(arr)
        println("count--->$count")
    }
}