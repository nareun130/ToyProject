package com.nareun.mallapikotlin.util

import jakarta.annotation.PostConstruct
import lombok.extern.log4j.Log4j2
import net.coobird.thumbnailator.Thumbnails
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.FileSystemResource
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.UUID

@Component
@Log4j2
class CustomFileUtil {

    @Value("\${com.nareun.upload.path}")
    private lateinit var uploadPath: String

    val log = LoggerFactory.getLogger(CustomFileUtil::class.java)

    //upload directory 생성
    @PostConstruct
    fun init() {
        val tempFolder = File(uploadPath)
        if (!tempFolder.exists()) {
            tempFolder.mkdir()
        }
        uploadPath = tempFolder.absolutePath
        log.info("-------------------------------------")
        log.info(uploadPath)
    }

    //파일 저장 메서드
    @Throws(RuntimeException::class)
    fun saveFiles(files: List<MultipartFile>?): List<String>? {

        if (files.isNullOrEmpty()) {
//            return null
            return mutableListOf()
        }

        val uploadNames = mutableListOf<String>()

        files.forEach { multipartFile ->
            //업로드 파일들에 대하여 겹치지 않도록 UUID 생성
            val savedName: String = UUID.randomUUID().toString() + "_" + multipartFile.originalFilename

            val savePath : Path = Paths.get(uploadPath, savedName)
            try {
                Files.copy(multipartFile.inputStream, savePath)

                //썸네일 관련
                val contentType = multipartFile.contentType

                if (contentType != null && contentType.startsWith("image")) {
                    // 이미지 여부 확인
                    val thumbnailPath :Path = Paths.get(uploadPath, "s_$savedName")
                    //savedPath.toFile() : Path객체 -> File객체
                    Thumbnails.of(savePath.toFile())
                        .size(200, 200)
                        //thumbnailPath.toFile() : Path 객체 -> File객체
                        .toFile(thumbnailPath.toFile())
                }

                uploadNames.add(savedName)
            } catch (e: IOException) {
                throw RuntimeException(e.message)
            }
        }
        return uploadNames
    }

    fun getFile(fileName: String): ResponseEntity<Resource> {
        //File.separator : unitx나 윈도우 운영체제에 따른 파일 구분자 ('/', '\' 등)
        var resource: Resource = FileSystemResource("$uploadPath${File.separator}$fileName")

        if (!resource.exists()) {
            //파일 존재x -> 기본이미지 반환
            resource = FileSystemResource("$uploadPath${File.separator}default.jpeg")
        }

        //응답에 쓰일 HTTP헤더 초기화
        val headers : HttpHeaders = HttpHeaders()

        try {
            headers.add("Content-Type", Files.probeContentType(resource.file.toPath()))
        } catch (e: Exception) {
            //500 코드 반환
            return ResponseEntity.internalServerError().build()
        }

        return ResponseEntity.ok().headers(headers).body(resource)
    }

    //파일 삭제
    @Throws(RuntimeException::class)
    fun deleteFiles(fileNames: List<String>?) {
        if (fileNames.isNullOrEmpty()) {
            return
        }

        fileNames.forEach { fileName ->
            val thumbnailFileName = "s_$fileName"
            val thumbnailPath = Paths.get(uploadPath, thumbnailFileName)
            val filePath = Paths.get(uploadPath, fileName)

            try {
                Files.deleteIfExists(filePath)
                Files.deleteIfExists(thumbnailPath)
            } catch (e: IOException) {
                throw RuntimeException(e.message)
            }
        }
    }
}