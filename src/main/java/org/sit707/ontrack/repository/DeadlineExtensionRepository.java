package org.sit707.ontrack.repository;

import org.sit707.ontrack.entity.DeadlineExtension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeadlineExtensionRepository extends JpaRepository<DeadlineExtension, String> {

}

