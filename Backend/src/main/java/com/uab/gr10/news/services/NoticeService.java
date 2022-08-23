package com.uab.gr10.news.services;

import com.uab.gr10.news.models.Notice;
import com.uab.gr10.news.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class NoticeService {

    private final NoticeRepository noticeRepository;

    @Autowired
    public NoticeService(final NoticeRepository noticeRepository){
        this.noticeRepository = noticeRepository;
    }

    public Iterable<Notice> getNotices() {
        return noticeRepository.findAll();
    }

    public Optional<Notice> getNoticeById(UUID id) {
        return noticeRepository.findById(id);
    }

    public Notice saveNotice(Notice notice) {
        return noticeRepository.save(notice);
    }

    public Notice update(UUID id, Notice notice) {
        if (noticeRepository.existsById(id)) {
            Notice noticeFound = noticeRepository.findById(id).get();
                noticeFound.setTitle(notice.getTitle());
                noticeFound.setContent(notice.getContent());
                noticeFound.setAuthor(notice.getAuthor());
                noticeFound.setSubtitle(notice.getSubtitle());
                noticeFound.setCategory(notice.getCategory());
                return noticeRepository.save(noticeFound);
        }
        else{
            return null;
        }
    }

    public Optional<Notice> deleteNoticeById(UUID id) {
        final Optional<Notice> notice = getNoticeById(id);
        noticeRepository.deleteById(id);
        return notice;
    }

}
