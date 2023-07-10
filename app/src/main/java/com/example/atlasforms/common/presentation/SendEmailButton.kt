package com.example.atlasforms.common.presentation

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.atlasforms.common.domain.AnswerForm

@Composable
fun SendEmailButton(form: AnswerForm) {
    val context = LocalContext.current

    Button(onClick = {context.findActivity().sendEmail(form) }) {
        Text("Send Email")
    }

}

fun Activity.sendEmail(form:AnswerForm) {
    Intent(Intent.ACTION_SEND).apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_EMAIL, arrayOf(form.email))
        putExtra(Intent.EXTRA_SUBJECT, "Atlas Forms Incoming Form")
        putExtra(Intent.EXTRA_TEXT, FormToEmail().ConvertFormToEmail(form))
        type = "message/rfc822"
    }
        .also(::startActivity)
}
fun Context.findActivity(): Activity {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    throw IllegalStateException("no activity")
}

class FormToEmail(){
    fun ConvertFormToEmail(form:AnswerForm):String{
        var email = ""
        for(question in form.questionList){
            email += question.question + question.answer + "\n"
        }
        return email
    }
}