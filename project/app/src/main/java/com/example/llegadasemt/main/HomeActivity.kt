package com.example.llegadasemt.main

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.llegadasemt.R
import com.example.llegadasemt.databinding.ActivityHomeBinding
import com.example.llegadasemt.network.RequestView
import com.example.llegadasemt.network.Token

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var requests: RequestView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.homeToolbar)

        val intent = Intent(this, ArrivalsActivity::class.java)

        var icon: Drawable = binding.floatingSearch.icon

        requests = RequestView()
        val tokenWrapper = Token()
        requests.login(tokenWrapper)

        binding.floatingSearch.setOnClickListener {
            if(binding.inputBoxBackground.visibility == View.VISIBLE) {
                fadeOut(binding.inputBoxBackground)
                binding.floatingSearch.icon = icon
                binding.floatingSearch.text = getString(R.string.search_stop)
            }
            else{
                fadeIn(binding.inputBoxBackground)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    binding.floatingSearch.icon = resources.getDrawable(android.R.drawable.ic_menu_close_clear_cancel,null)
                }
                binding.floatingSearch.text = getString(R.string.search_close)
            }
        }

        binding.inputBox.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(txview: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (binding.inputBox.text.toString() == "") {
                        Toast.makeText(this@HomeActivity, getString(R.string.search_incorrect), Toast.LENGTH_SHORT).show()
                    }
                    else{
                        intent.putExtra("stop", binding.inputBox.text.toString())
                        intent.putExtra("token", tokenWrapper.token)
                        startActivity(intent)
                        this@HomeActivity.overridePendingTransition(0,0)
                        return true;
                    }
                }
                return false;
            }
        })
    }
    fun fadeIn(view: View) {
        view.animate()
            .translationY(0f)
            .alpha(1.0f)
            .setDuration(300)
            .setListener(object: AnimatorListenerAdapter() {
                override fun onAnimationStart(animation: Animator?) {
                    super.onAnimationStart(animation)
                    binding.inputBoxBackground.setVisibility(View.VISIBLE)
                }
            })
    }
    fun fadeOut(view: View) {
        view.animate()
            .translationY(view.height.toFloat())
            .alpha(0.0f)
            .setDuration(300)
            .setListener(object: AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    binding.inputBoxBackground.setVisibility(View.INVISIBLE)
                }
            })
    }
    override fun onBackPressed() {
        if (binding.inputBoxBackground.visibility == View.VISIBLE) {
            fadeOut(binding.inputBoxBackground)
            binding.floatingSearch.icon = resources.getDrawable(android.R.drawable.ic_search_category_default,null)
            binding.floatingSearch.text = getString(R.string.search_stop)
        }
        else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        requests.logout()
        super.onDestroy()
    }
}