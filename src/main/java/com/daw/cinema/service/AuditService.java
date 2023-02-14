package com.daw.cinema.service;

import com.daw.cinema.entity.Audit;
import com.daw.cinema.repository.AuditRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuditService {

    private final AuditRepository auditRepository;

    public void save(Audit audit) {
        auditRepository.save(audit);
    }
}
