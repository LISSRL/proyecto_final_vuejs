package com.uab.gr10.news.controllers;

import com.uab.gr10.news.models.Notice;
import com.uab.gr10.news.services.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.Collections;
import java.util.UUID;

@RestController
@RequestMapping("api/notices")
@CrossOrigin(origins = "${vue.origin.url}")
public class NoticeController {
    private final NoticeService noticeService;
    @Autowired
    public NoticeController(final NoticeService noticeService){
        this.noticeService = noticeService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Notice>> findAll() {
        return ResponseEntity.ok(noticeService.getNotices());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Notice> findById(@PathVariable(value = "id") UUID id) {
        return noticeService.getNoticeById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(EntityNotFoundException::new);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Notice> deleteById(@PathVariable(value = "id") UUID id) {
        return noticeService.deleteNoticeById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Notice> saveNotice(@RequestBody @Valid Notice notice) {
        return ResponseEntity.ok(noticeService.saveNotice(notice));
    }

    @PostMapping(value = "/files")
    public ResponseEntity<Iterable<Notice>> saveNotices(@RequestParam("files") MultipartFile files) {
        // return ResponseEntity.ok(noticeService.saveNotices(files));
        return ResponseEntity.ok(Collections.emptyList());
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Notice> updateNotice(@RequestBody Notice notice, @PathVariable(value = "id") UUID id) {
        return ResponseEntity.ok(noticeService.update(id,notice));
    }
}
