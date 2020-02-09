package benjamin_rothfuss.de.sudokusolver

class CanvasManager {
    var canvas = arrayListOf<ArrayList<Field>>()

    init {
        buildStartingCanvas()
    }

    fun renderCanvas(): String {
        var colCount = 0
        var rowCount = 0
        var resultString = ""
        for (row in canvas) {
            for (field in row) {
                colCount++
                resultString += " ${field.value} "
                if ((colCount % 3 == 0) && (colCount < 9)) {
                    resultString += "|"
                }

            }
            colCount = 0
            rowCount++
            resultString += "\n"
            if ((rowCount % 3 == 0) && (rowCount < 9)) {
                resultString += "------------------------------------\n"
            }
        }
        return resultString
    }

    fun setFieldNumber(posX: Int, posY: Int, value: Int) {
        canvas[posY][posX].value = value
    }

    fun solveCanvas() {
        for (row in canvas) {
            for (field in row) {
                checkRow(field)
                checkColumn(field)
            }
        }
        val squareCenters = arrayOf(
            arrayOf(1, 1), arrayOf(1, 4), arrayOf(1, 7),
            arrayOf(4, 1), arrayOf(4, 4), arrayOf(4, 7),
            arrayOf(7, 1), arrayOf(7, 4), arrayOf(7, 7)
        )

        for (field in squareCenters) {
            checkSquare(canvas[field[0]][field[1]])
        }
    }

    private fun checkRow(currentField: Field) {
        for (number in canvas[currentField.posY]) {
            if((number.value != 0) && (number.value != currentField.value)) {
                currentField.deleteFromPossibleNumbers(number.value)
            }
        }
    }

    private fun checkColumn(currentField: Field) {
        for (row in canvas) {
            if ((row[currentField.posX].value != 0) && (row[currentField.posX].value != currentField.value)) {
                currentField.deleteFromPossibleNumbers(row[currentField.posX].value)
            }
        }
    }

    private fun checkDiagonalAdjacent(currentField: Field) {
        val adjacentToCheck = arrayOf(arrayOf(-1, -1), arrayOf(-1, 1), arrayOf(1, 1), arrayOf(1, -1))

        for (coordinate in adjacentToCheck) {
            val currentAdjacentNumber = canvas.getOrNull(currentField.posY + coordinate[0])?.getOrNull(currentField.posX + coordinate[1])?.value
            if ((currentAdjacentNumber != null) && (currentAdjacentNumber != 0) && (currentAdjacentNumber != currentField.value)) {
                currentField.deleteFromPossibleNumbers(currentAdjacentNumber)
            }
        }
    }
    private fun checkSquare(currentField: Field) {
        val startPositionX = currentField.posX / 3
        val startPositionY = currentField.posY / 3

        for (row in startPositionY .. startPositionY + 2) {
            for (col in startPositionX .. startPositionX + 2) {
                if ((canvas[row][col].value != 0) && canvas[row][col].value != currentField.value) {
                    currentField.deleteFromPossibleNumbers(canvas[row][col].value)
                }
            }
        }
    }

    private fun determineSquareToCheck() {

    }

    fun emptyFieldLeft(): Boolean {
        for (row in canvas) {
            for (field in row) {
                if (field.value == 0) return true
            }
        }
        return false
    }

    fun buildStartingCanvas() {
        canvas.clear()
        for (posX in 0..8) {
            canvas.add(ArrayList())
            for (posY in 0..8) {
                canvas[posX].add(Field(posX, posY, 0))
            }
        }
        fillWithValues()
    }

    fun fillWithValues() {
        setFieldNumber(0, 1, 6)
        setFieldNumber(0, 2, 4)
        setFieldNumber(0, 6, 7)
        setFieldNumber(0, 7, 5)

        setFieldNumber(1, 0, 1)
        setFieldNumber(1, 3, 2)
        setFieldNumber(1, 5, 7)
        setFieldNumber(1, 8, 3)

        setFieldNumber(2, 0, 2)
        setFieldNumber(2, 4, 4)
        setFieldNumber(2, 8, 8)

        setFieldNumber(3, 1, 5)
        setFieldNumber(3, 4, 9)
        setFieldNumber(3, 7, 4)

        setFieldNumber(4, 2, 2)
        setFieldNumber(4, 3, 1)
        setFieldNumber(4, 5, 8)
        setFieldNumber(4, 6, 9)

        setFieldNumber(5, 1, 1)
        setFieldNumber(5, 4, 7)
        setFieldNumber(5, 7, 8)

        setFieldNumber(6, 0, 5)
        setFieldNumber(6, 4, 8)
        setFieldNumber(6, 8, 9)

        setFieldNumber(7, 0, 7)
        setFieldNumber(7, 3, 5)
        setFieldNumber(7, 5, 1)
        setFieldNumber(7, 8, 4)

        setFieldNumber(8, 1, 4)
        setFieldNumber(8, 2, 8)
        setFieldNumber(8, 6, 5)
        setFieldNumber(8, 7, 6)
    }


}