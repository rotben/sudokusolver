package benjamin_rothfuss.de.sudokusolver

class Field(val posX: Int, val posY: Int, value: Int) {

    private var possibleNumbers: MutableList<Int> = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
    var value: Int = 0

    fun deleteFromPossibleNumbers(number: Int) {
        possibleNumbers.remove(number)
        if (numberIsFound()) {
            value = possibleNumbers[0]
        }
    }

    private fun numberIsFound(): Boolean {
        return possibleNumbers.size == 1
    }
}