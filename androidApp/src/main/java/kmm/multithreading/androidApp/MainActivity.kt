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
    private val _7 = _7_Atomics()
    private val _8 = _8_Mutex()
    private val _9 = _9_MutatingViaCopying()
    private val _10 = _10_MultithreadingWithDTO()
    private val _11 = _11_PureMutable()
    private val _12 = _12_DirtyMutable()
    private val _13 = _13_ImmutableIsNotFrozen()
    private val _14 = _14_Trampoline()
    private val _15 = _15_Throttling()

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
        R.id._7.onClick(_7)
        R.id._8.onClick(_8)
        R.id._9.onClick(_9)
        R.id._10.onClick(_10)
        R.id._11.onClick(_11)
        R.id._12.onClick(_12)
        R.id._13.onClick(_13)
        R.id._14.onClick(_14)
        R.id._15.onClick(_15)
    }

    private fun Int.onClick(callWithCallback: CallWithCallback) {
        findViewById<Button>(this).let { btn ->
            btn.setOnClickListener {
                callWithCallback.call {
                    btn.text = btn.text.toString() + " " + it
                }
            }
        }
    }
}
