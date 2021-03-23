@file:Suppress("SetTextI18n", "PrivatePropertyName")

package kmm.multithreading.androidApp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kmm.multithreading.shared.*
import kmm.multithreading.shared.util.CallWithCallback

class MainActivity : AppCompatActivity() {

    private val _0 = _0_MicCheck()
    private val _1 = _1_TheProblem()
    private val _2 = _2_Solution()
    private val _3 = _3_EnsureNeverFrozen()
    private val _4 = _4_Freeze()
    private val _5 = _5_Global()
    private val _6 = _6_Leaks()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        R.id._0.onClick(_0)
        R.id._1.onClick(_1)
        R.id._2.onClick(_2)
        R.id._3.onClick(_3)
        R.id._4.onClick(_4)
        R.id._5.onClick(_5)
        R.id._6.onClick(_6)
    }

    private fun Int.onClick(callWithCallback: CallWithCallback) {
        findViewById<Button>(this).let { btn ->
            btn.setOnClickListener {
                callWithCallback.call {
                    btn.text = btn.text.toString() + it
                }
            }
        }
    }
}
