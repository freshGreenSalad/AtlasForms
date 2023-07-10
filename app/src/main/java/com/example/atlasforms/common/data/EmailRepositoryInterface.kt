package com.example.atlasforms.common.data

import android.util.Log
import com.example.atlasforms.common.domain.ApiKeys
import javax.inject.Inject
import com.mailersend.sdk.MailerSend
import com.mailersend.sdk.emails.Email

/*
class EmailRepositoryInterface @Inject constructor():EmailRepository {
    override suspend fun sendEmail() {

            val email = Email();

            email.setFrom("name", "tamakiLabour.small@gmail.com");
            email.addRecipient("name", "tamakiLabour.small@gmail.com");

            email.setSubject("Email subject test");

            email.setPlain("This is the text content");
            email.setHtml("<p>This is the HTML content</p>");

            val ms = MailerSend();

            ms.setToken(ApiKeys.emailTokin);

            try {
                val response = ms.//.send(email);
                Log.d("response.messageId","");
            } catch (e: Exception) {
                e.printStackTrace();
            }
    }
}*/
