package com.codegemz.kotlinx.lesson3

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainView().setContentView(this)
    }
}

class MainView : AnkoComponent<MainActivity> {
    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        verticalLayout {

            textView {
                // to support multi language, strings are put into: res/values/string.xml
                text = resources.getString(R.string.layout_elements)
                textSize = 22f
                textColor = Color.GRAY
            }.lparams(width = wrapContent) {
                gravity = Gravity.CENTER_HORIZONTAL
                topMargin = dip(30)
            }

            verticalLayout {
                gravity = Gravity.CENTER_HORIZONTAL
                leftPadding = dip(50)
                rightPadding = dip(50)

                val displayTextView = textView {
                    // you can also write the text right here
                    text = "TextView"
                    textSize = 24f
                    textColor = Color.GRAY
                }.lparams {
                    topMargin = dip(40)
                }

                val items = arrayOf("One", "Two", "Three")
                lateinit var firstSpinner: Spinner
                lateinit var enterEditText: EditText

                linearLayout {
                    firstSpinner = spinner {
                        adapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, items)
                    }.lparams(width = wrapContent) {
                        topMargin = dip(40)
                    }

                    enterEditText = editText {
                        hint = "EditText"
                    }.lparams(width = dip(150)) {
                        topMargin = dip(40)
                    }
                }

                val applyButton = button("Apply") {
                    leftPadding = dip(50)
                    rightPadding = dip(50)

                    onClick {
                        val enteredText = enterEditText.text.toString()
                        if (enteredText.isNotBlank()) {
                            displayTextView.text = enteredText
                        }
                        toast("You pressed the button!")
                    }
                }.lparams(width = matchParent) {
                    topMargin = dip(40)
                }

                checkBox {
                    text = resources.getString(R.string.disable_button)
                    setOnClickListener {
                        val disableCheckBox = it as CheckBox
                        applyButton.isEnabled = !disableCheckBox.isChecked
                    }
                }.lparams() {
                    topMargin = dip(40)
                }
            }

            // for easy reuse, colors are stored in: res/values/colors.xml
            val backgroundColor = ContextCompat.getColor(ctx, R.color.colorGray)
            background = ColorDrawable(backgroundColor)
        }
    }
}
