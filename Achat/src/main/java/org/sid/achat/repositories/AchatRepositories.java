package org.sid.achat.repositories;

import org.sid.achat.entities.Achat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AchatRepositories extends JpaRepository<Achat, Long> {
}
