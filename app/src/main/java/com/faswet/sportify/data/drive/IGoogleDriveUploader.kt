package com.faswet.sportify.data.drive

import android.content.Intent
import android.net.Uri
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

interface IGoogleDriveUploader {
    fun getSignInIntent(): Intent
    fun handleSignInResult(account: GoogleSignInAccount?, onReady: (Boolean) -> Unit)
    fun uploadUriFile(uri: Uri, fileName: String, mimeType: String, onUploaded: (String?) -> Unit)

    fun uploadImage(imageUri: Uri, onUploaded: (String?) -> Unit)
    fun uploadVideo(videoUri: Uri, onUploaded: (String?) -> Unit)
    fun uploadAudio(audioUri: Uri, onUploaded: (String?) -> Unit)
    fun uploadDocument(documentUri: Uri, fileName: String, onUploaded: (String?) -> Unit)
    fun uploadPdf(pdfUri: Uri, onUploaded: (String?) -> Unit)
    fun uploadSpreadsheet(excelUri: Uri, onUploaded: (String?) -> Unit)
    fun uploadSlideShow(powerPointUri: Uri, onUploaded: (String?) -> Unit)

    fun uploadFile(fileUri: Uri, fileName: String, mimeType: String, onUploaded: (String?) -> Unit)

}