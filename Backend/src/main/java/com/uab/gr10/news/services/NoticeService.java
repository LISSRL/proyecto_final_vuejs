package com.uab.gr10.news.services;

import com.uab.gr10.news.models.Notice;
import com.uab.gr10.news.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    public Iterable<Notice> getNotices() {
        return noticeRepository.findAll();
    }

    public Optional<Notice> getNoticeById(UUID id) {
        return noticeRepository.findById(id);
    }

    public Notice saveNotice(Notice notice) {
        return noticeRepository.save(notice);
    }

    public Optional<Notice> deleteNoticeById(UUID id) {
        final Optional<Notice> notice = getNoticeById(id);
        noticeRepository.deleteById(id);
        return notice;
    }

}
