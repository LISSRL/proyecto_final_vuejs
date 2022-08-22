package com.uab.gr10.news.services;

import com.uab.gr10.news.models.Notice;
import com.uab.gr10.news.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class NoticeService {


    private NoticeRepository noticeRepository = new NoticeRepository() {
        @Override
        public Notice findDistinctByName(String name) {
            return null;
        }

        @Override
        public <S extends Notice> S save(S entity) {
            return null;
        }

        @Override
        public <S extends Notice> Iterable<S> saveAll(Iterable<S> entities) {
            return null;
        }

        @Override
        public Optional<Notice> findById(UUID uuid) {
            return Optional.empty();
        }

        @Override
        public boolean existsById(UUID uuid) {
            return false;
        }

        @Override
        public Iterable<Notice> findAll() {
            return null;
        }

        @Override
        public Iterable<Notice> findAllById(Iterable<UUID> uuids) {
            return null;
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public void deleteById(UUID uuid) {

        }

        @Override
        public void delete(Notice entity) {

        }

        @Override
        public void deleteAllById(Iterable<? extends UUID> uuids) {

        }

        @Override
        public void deleteAll(Iterable<? extends Notice> entities) {

        }

        @Override
        public void deleteAll() {

        }
    } ;

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
