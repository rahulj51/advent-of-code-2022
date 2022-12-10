package d10

import common.readInputFile

fun main() {

    val instructions = readInputFile("src/main/kotlin/d10/input.dat")

    val registerValues = registerValues(instructions)

    val cycles = (1..240)
    val pixels = cycles.fold(mutableListOf<Char>()) { acc, it ->

        val spritePosition = registerValues.getOrElse(it-1) {registerValues.last()}
        val pixelPos = (it - 1) % 40
        val pixel = if (overlaps(pixelPos, spritePosition)) '#' else '.'

        acc.add(pixel)
        acc
    }

    val screenLines = pixels.chunked(40)
    screenLines.forEach { println(it.joinToString("")) }

}

//not handling boundaries???
fun overlaps(pixelPos: Int, spritePosition: Int): Boolean {
    return listOf(spritePosition -1, spritePosition, spritePosition+1).contains(pixelPos)
}


