package com.example.ytuattendancesystem.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewGroup
import com.example.ytuattendancesystem.R


class SplashActivity : AppCompatActivity() {

    // https://github.com/ykabaran/TwitterSplash/blob/master/src/com/yildizkabaran/twittersplash/MainActivity.java
    private var mDelayHandler: Handler? = null
    private val SPLASH_DELAY: Long = 1000

    internal val mRunnable: Runnable = Runnable {
        if (!isFinishing) {

            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


    private val mMainView: ViewGroup? = null
//    private val mSplashView: SplashView? = null
    private val mContentView: View? = null
    private val mHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
//
//        val splashView = SplashView(applicationContext)
//        splashView.setDuration(500)
//        splashView.setHoleFillColor(Color.WHITE)
//        splashView.setIconColor(Color.rgb(23, 169, 229))
//        splashView.setIconResource(R.drawable.ytulogo)
//        splashView.setRemoveFromParentOnEnd(true)
//
        //Initialize the Handler
        mDelayHandler = Handler()

        //Navigate with delay
        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)
    }





    public override fun onDestroy() {

        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }

        super.onDestroy()
    }
}
