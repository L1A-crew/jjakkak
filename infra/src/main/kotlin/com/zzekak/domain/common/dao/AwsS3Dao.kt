/*
 * Zzekak
 * Sir.LOIN Intellectual property. All rights reserved.
 */
package com.zzekak.domain.common.dao

import org.springframework.stereotype.Repository
import org.springframework.web.multipart.MultipartFile
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.GetUrlRequest
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import java.io.File
import java.nio.file.Files

/**
 * @since 2024-07-13
 */
interface AwsS3Dao {
    fun uploadFile(
        path: String,
        fileName: String,
        file: MultipartFile
    ): String

    fun downloadFile(
        path: String,
        fileName: String
    ): ByteArray

    fun deleteFile(
        path: String,
        fileName: String
    )
}

@Repository
internal class AwsS3DaoImpl(
    private val s3Client: S3Client
) : AwsS3Dao {
    override fun uploadFile(
        path: String,
        fileName: String,
        file: MultipartFile
    ): String {
        val tempFile = File.createTempFile("tmp-", fileName)
        tempFile.writeBytes(file.bytes)

        val extension = getFileExtension(file) ?: throw IllegalArgumentException("File extension is not found")

        val putObjectRequest =
            PutObjectRequest.builder()
                .bucket(BUCKET_NAME_LIA)
                .key("$path/$fileName.$extension")
                .contentType(file.contentType)
                .build()

        s3Client.putObject(putObjectRequest, tempFile.toPath())
        Files.delete(tempFile.toPath())

        val s3Utilities = s3Client.utilities()
        val getUrlRequest =
            GetUrlRequest.builder()
                .bucket(BUCKET_NAME_LIA)
                .key("$path/$fileName")
                .build()

        val fileUrl = s3Utilities.getUrl(getUrlRequest).toString()

        return fileUrl
    }

    override fun downloadFile(
        path: String,
        fileName: String
    ): ByteArray {
        TODO("Not yet implemented")
    }

    override fun deleteFile(
        path: String,
        fileName: String
    ) {
        TODO("Not yet implemented")
    }

    private fun getFileExtension(file: MultipartFile): String? {
        val fileName = file.originalFilename ?: return null
        return if (fileName.contains(".")) {
            fileName.substring(fileName.lastIndexOf(".") + 1)
        } else {
            null
        }
    }

    companion object {
        const val BUCKET_NAME_LIA = "lia.zzekak"
    }
}
