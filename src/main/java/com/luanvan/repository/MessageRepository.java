package com.luanvan.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.luanvan.entity.MessageEntity;

public interface MessageRepository  extends JpaRepository<MessageEntity, Long>{
	@Query("SELECT m FROM MessageEntity m WHERE m.sender.id = ?1 AND m.receiver.id = ?2 OR m.sender.id = ?2 AND m.receiver.id = ?1")
	Optional<List<MessageEntity>> findAll(Long userIdFirst, Long userIdSecond);
}
