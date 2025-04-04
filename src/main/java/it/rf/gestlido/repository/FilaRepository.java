package it.rf.gestlido.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.rf.gestlido.model.Fila;

@Repository
public interface FilaRepository extends JpaRepository<Fila, Integer>{

}
