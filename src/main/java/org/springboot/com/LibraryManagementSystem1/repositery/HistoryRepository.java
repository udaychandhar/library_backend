package org.springboot.com.LibraryManagementSystem1.repositery;

import org.springboot.com.LibraryManagementSystem1.dto.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Integer> {

}
