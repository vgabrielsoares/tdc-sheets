package com.tdc.sheets.repository;

import com.tdc.sheets.entity.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Repository base com queries comuns para todas as entidades do sistema
 * 
 * @param <T> Tipo da entidade
 * @param <ID> Tipo do ID da entidade
 */
@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity, ID> extends JpaRepository<T, ID> {
    
    /**
     * Busca entidades ativas (is_active = true)
     */
    @Query("SELECT e FROM #{#entityName} e WHERE e.isActive = true")
    List<T> findAllActive();
    
    /**
     * Busca entidades ativas com paginação
     */
    @Query("SELECT e FROM #{#entityName} e WHERE e.isActive = true")
    Page<T> findAllActive(Pageable pageable);
    
    /**
     * Busca entidade ativa por ID
     */
    @Query("SELECT e FROM #{#entityName} e WHERE e.id = :id AND e.isActive = true")
    Optional<T> findActiveById(ID id);
    
    /**
     * Busca entidades criadas após uma data específica
     */
    @Query("SELECT e FROM #{#entityName} e WHERE e.createdAt >= :date AND e.isActive = true")
    List<T> findCreatedAfter(LocalDateTime date);
    
    /**
     * Busca entidades modificadas após uma data específica
     */
    @Query("SELECT e FROM #{#entityName} e WHERE e.updatedAt >= :date AND e.isActive = true")
    List<T> findModifiedAfter(LocalDateTime date);
    
    /**
     * Busca entidades entre datas
     */
    @Query("SELECT e FROM #{#entityName} e WHERE e.createdAt BETWEEN :startDate AND :endDate AND e.isActive = true")
    List<T> findCreatedBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    /**
     * Conta entidades ativas
     */
    @Query("SELECT COUNT(e) FROM #{#entityName} e WHERE e.isActive = true")
    long countActive();
    
    /**
     * Soft delete - marca como inativo
     */
    @Query("UPDATE #{#entityName} e SET e.isActive = false, e.updatedAt = CURRENT_TIMESTAMP WHERE e.id = :id")
    void softDelete(ID id);
    
    /**
     * Soft delete múltiplos registros
     */
    @Query("UPDATE #{#entityName} e SET e.isActive = false, e.updatedAt = CURRENT_TIMESTAMP WHERE e.id IN :ids")
    void softDeleteAll(List<ID> ids);
    
    /**
     * Restaura um registro soft deleted
     */
    @Query("UPDATE #{#entityName} e SET e.isActive = true, e.updatedAt = CURRENT_TIMESTAMP WHERE e.id = :id")
    void restore(ID id);
}
