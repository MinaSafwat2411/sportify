package com.faswet.sportify.data.drive


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.Scope
import com.google.api.client.extensions.android.http.AndroidHttp
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.api.client.http.FileContent
import com.google.api.client.json.gson.GsonFactory
import com.google.api.services.drive.Drive
import com.google.api.services.drive.DriveScopes
import com.google.api.services.drive.model.File
import com.google.api.services.drive.model.Permission
import java.io.FileOutputStream

class GoogleDriveUploader(private val context: Context): IGoogleDriveUploader {

    companion object {
        private const val TAG = "GoogleDriveUploader"
        private const val WEB_CLIENT_ID = "288993934101-hll6tdl1dqd39ggr8ccncrui5ufani04.apps.googleusercontent.com"
    }

    private var driveService: Drive? = null

    override fun getSignInIntent(): Intent {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestScopes(Scope(DriveScopes.DRIVE_FILE))
            .requestIdToken(WEB_CLIENT_ID)
            .build()

        val googleSignInClient = GoogleSignIn.getClient(context, gso)
        return googleSignInClient.signInIntent
    }

    override fun handleSignInResult(account: GoogleSignInAccount?, onReady: (Boolean) -> Unit) {
        if (account == null) {
            onReady(false)
            return
        }

        val credential = GoogleAccountCredential.usingOAuth2(
            context,
            listOf(DriveScopes.DRIVE_FILE)
        ).apply {
            selectedAccount = account.account
        }

        driveService = Drive.Builder(
            AndroidHttp.newCompatibleTransport(),
            GsonFactory.getDefaultInstance(),
            credential
        ).setApplicationName("Sportify").build()

        onReady(true)
    }

    override fun uploadUriFile(uri: Uri, fileName: String, mimeType: String, onUploaded: (String?) -> Unit) {
        if (driveService == null) {
            onUploaded(null)
            return
        }

        val inputStream = context.contentResolver.openInputStream(uri)
        val tempFile = java.io.File.createTempFile("temp", null, context.cacheDir)
        val outputStream = FileOutputStream(tempFile)

        inputStream?.copyTo(outputStream)
        inputStream?.close()
        outputStream.close()

        val fileMetadata = File().apply {
            name = fileName
        }

        val mediaContent = FileContent(mimeType, tempFile)

        Thread {
            try {
                val uploadedFile = driveService?.files()?.create(fileMetadata, mediaContent)?.setFields("id")?.execute()

                val permission = Permission().apply {
                    type = "anyone"
                    role = "reader"
                }

                driveService!!.permissions().create(uploadedFile?.id, permission).execute()

                val shareableLink = "https://drive.google.com/uc?id=${uploadedFile?.id}"
                Log.d(TAG, "File uploaded: $shareableLink")
                onUploaded(shareableLink)
            } catch (e: Exception) {
                Log.e(TAG, "Upload failed", e)
                onUploaded(null)
            }
        }.start()
    }

    override fun uploadAudio(audioUri: Uri, onUploaded: (String?) -> Unit) {
        uploadUriFile(audioUri, "audio.mp3", "audio/mpeg", onUploaded)
    }

    override fun uploadDocument(documentUri: Uri, fileName: String, onUploaded: (String?) -> Unit) {
        uploadUriFile(documentUri, fileName, "application/pdf", onUploaded)
    }

    override fun uploadFile(
        fileUri: Uri,
        fileName: String,
        mimeType: String,
        onUploaded: (String?) -> Unit
    ) {
        uploadUriFile(fileUri, fileName, mimeType, onUploaded)
    }

    override fun uploadImage(imageUri: Uri, onUploaded: (String?) -> Unit) {
        uploadUriFile(imageUri, "image.jpg", "image/jpeg", onUploaded)
    }

    override fun uploadPdf(pdfUri: Uri, onUploaded: (String?) -> Unit) {
        uploadUriFile(pdfUri, "pdf.pdf", "application/pdf", onUploaded)
    }

    override fun uploadSlideShow(powerPointUri: Uri, onUploaded: (String?) -> Unit) {
        uploadUriFile(powerPointUri, "powerpoint.pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation", onUploaded)
    }

    override fun uploadSpreadsheet(excelUri: Uri, onUploaded: (String?) -> Unit) {
        uploadUriFile(excelUri, "excel.xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", onUploaded)
    }

    override fun uploadVideo(videoUri: Uri, onUploaded: (String?) -> Unit) {
        uploadUriFile(videoUri, "video.mp4", "video/mp4", onUploaded)
    }
}
