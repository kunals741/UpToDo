package com.kunal.uptodo.views

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.kunal.uptodo.adapters.ChooseColorAdapter
import com.kunal.uptodo.constants.IntentKeyConstants
import com.kunal.uptodo.constants.PageName
import com.kunal.uptodo.data.ColorList
import com.kunal.uptodo.databinding.ActivityCreateNewCategoryBinding

class CreateNewCategoryActivity : BaseActivity() {

    private lateinit var binding: ActivityCreateNewCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateNewCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() = binding.apply {
        rvChooseColor.adapter = ChooseColorAdapter(ColorList().getColorList())
    }


    //todo : add upload icon, color select icon, other funtionality, listeners


    override fun handleBack() {
        //todo
    }

    override fun pageType() = PageName.CreateNewCategoryActivity

    companion object {

        fun launchCreateNewCategory(
            source: String,
            activity: Activity
        ) {
            val intent = Intent(activity, CreateNewCategoryActivity::class.java)
            intent.apply {
                putExtra(IntentKeyConstants.SOURCE, source)
            }
            activity.startActivity(intent)
        }
    }
}