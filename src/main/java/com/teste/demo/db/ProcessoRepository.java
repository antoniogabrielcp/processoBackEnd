package com.teste.demo.db;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teste.demo.model.Processo;

public interface ProcessoRepository extends JpaRepository<Processo, Long> {
	
}
