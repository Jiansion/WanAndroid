package com.conch.wanandroid

import org.junit.Test
import kotlin.math.max

/**
 * 查找字符串，获取最大对称子字符串的长度
 * https://www.cainiaojc.com/note/qad4h3.html
 * @author YeJain
 * @date 2022/9/18 20:55
 */
class SymmetricTest {

    // aba  中心对称
    private fun maxLengthMiddle(arr: CharArray, index: Int): Int {
        var length = 1
        var j = 1
        while ((arr[index - j] == arr[index + j]) && (index - j) >= 0 && arr.size > (index + j)) {
            length += 2
            j++
        }
        return length
    }


    // abba  镜像对称
    private fun maxLengthMirror(arr: CharArray, index: Int): Int {
        var length = 0
        var j = 0
        while ((arr[index - j] == arr[index + j + 1]) && (index - j) >= 0 && arr.size > (index + j + 1)) {
            length += 2
            j++
        }
        return length
    }


    fun runPalindrain(charArray: CharArray): Int {
        if (charArray.size < 2) {
            return charArray.size
        }

        var maxLength = 0
        for (i in 1 until charArray.size) {
            val lengthMiddle = maxLengthMiddle(charArray, i)
            val lengthMirror = maxLengthMirror(charArray, i)
            maxLength = max(max(lengthMiddle, lengthMirror), maxLength)
        }
        return maxLength
    }


    @Test
    fun textSimple() {
        runPalindrain("abcba".toCharArray())
    }
}