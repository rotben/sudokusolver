package benjamin_rothfuss.de.sudokusolver

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val canvasManager = CanvasManager()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonNextTipp = findViewById<View>(R.id.btn_next_tipp)
        val buttonReset = findViewById<View>(R.id.btn_reset)

        canvasManager.fillWithValues()

        buttonNextTipp.setOnClickListener {
            canvasManager.solveCanvas()
            sudoku_canvas.text = canvasManager.renderCanvas()
        }
        buttonReset.setOnClickListener {
            canvasManager.buildStartingCanvas()
            sudoku_canvas.text = canvasManager.renderCanvas()
        }

        sudoku_canvas.text = canvasManager.renderCanvas()
    }




}
