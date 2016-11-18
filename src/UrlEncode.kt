/**
 * Created by xumingmingv on 11/19/16.
 *
 * This snippets do manual url encoding(percent encoding): https://en.wikipedia.org/wiki/Percent-encoding
 */

val reserved = setOf('!', '*', '\'', '(', ')', ';', ':', '@', '&', '=', '+', '$', ',', '/', '?', '#', '[', ']')

fun encode(str: String): String {
    val ret = StringBuilder()
    for (c in str) {
        if (reserved.contains(c)) {
            ret.append('%').append(encode(c))
        } else {
            ret.append(c)
        }
    }

    return ret.toString()
}

fun encode(c: Char): String {
    return Integer.toHexString(c.toInt())
}

fun decode(str: String): String {
    val ret = StringBuilder()
    var i = 0
    while (i < str.length) {
        val c = str[i++]
        if (c == '%') {
            ret.append(decode(str[i++], str[i++]))
        } else {
            ret.append(c)
        }
    }

    return ret.toString()
}

fun decode(enc1: Char, enc2: Char): Char {
    return (hexToByte(enc1) * 16 + hexToByte(enc2)).toChar()
}

fun hexToByte(hex: Char): Byte {
    return when (hex) {
        '0' -> 0
        '1' -> 1
        '2' -> 2
        '3' -> 3
        '4' -> 4
        '5' -> 5
        '6' -> 6
        '7' -> 7
        '8' -> 8
        '9' -> 9
        'a' -> 10
        'A' -> 10
        'b' -> 11
        'B' -> 11
        'c' -> 12
        'C' -> 12
        'd' -> 13
        'D' -> 13
        'e' -> 14
        'E' -> 14
        'f' -> 15
        'F' -> 15
        else -> {
            throw IllegalArgumentException("invalid hex: " + hex)
        }
    }
}

fun main(args: Array<String>) {
    val str = "http://www.bing.com/search?q=snippets&go=Submit&qs=n&form=QBLH&pq=mac+install+jdk&sc=8-13&sp=-1&sk=&cvid=627B45F37F574E4190696AE3ED39B528&setmkt=zh-CN"
    println(encode(str))
    println(decode(encode(str)))
}
