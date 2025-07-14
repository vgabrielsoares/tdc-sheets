package com.tdc.sheets.repository;

import com.tdc.sheets.entity.Equipamentos;
import com.tdc.sheets.entity.enums.TiposEquipamentos;
import com.tdc.sheets.entity.enums.Raridades;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository para operações com equipamentos
 */
@Repository
public interface EquipamentosRepository extends JpaRepository<Equipamentos, Long> {
    
    /**
     * Busca equipamentos por nome (case insensitive)
     */
    @Query("SELECT e FROM Equipamentos e WHERE LOWER(e.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Equipamentos> findByNomeContainingIgnoreCase(@Param("nome") String nome);
    
    /**
     * Busca equipamentos por nome com paginação
     */
    @Query("SELECT e FROM Equipamentos e WHERE LOWER(e.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    Page<Equipamentos> findByNomeContainingIgnoreCase(@Param("nome") String nome, Pageable pageable);
    
    /**
     * Busca equipamentos por tipo
     */
    @Query("SELECT e FROM Equipamentos e WHERE e.tipo = :tipo")
    List<Equipamentos> findByTipo(@Param("tipo") TiposEquipamentos tipo);
    
    /**
     * Busca equipamentos por tipo com paginação
     */
    @Query("SELECT e FROM Equipamentos e WHERE e.tipo = :tipo")
    Page<Equipamentos> findByTipo(@Param("tipo") TiposEquipamentos tipo, Pageable pageable);
    
    /**
     * Busca equipamentos por raridade
     */
    @Query("SELECT e FROM Equipamentos e WHERE e.raridade = :raridade")
    List<Equipamentos> findByRaridade(@Param("raridade") Raridades raridade);
    
    /**
     * Busca equipamentos por raridade com paginação
     */
    @Query("SELECT e FROM Equipamentos e WHERE e.raridade = :raridade")
    Page<Equipamentos> findByRaridade(@Param("raridade") Raridades raridade, Pageable pageable);
    
    /**
     * Busca equipamentos por preço específico (text match)
     */
    @Query("SELECT e FROM Equipamentos e WHERE LOWER(e.preco) LIKE LOWER(CONCAT('%', :preco, '%'))")
    List<Equipamentos> findByPrecoContaining(@Param("preco") String preco);
    
    /**
     * Busca equipamentos por peso máximo
     */
    @Query("SELECT e FROM Equipamentos e WHERE e.peso <= :pesoMax")
    List<Equipamentos> findByPesoLessThanEqual(@Param("pesoMax") Integer pesoMax);
    
    /**
     * Busca equipamentos por faixa de peso
     */
    @Query("SELECT e FROM Equipamentos e WHERE e.peso BETWEEN :pesoMin AND :pesoMax")
    List<Equipamentos> findByPesoBetween(@Param("pesoMin") Integer pesoMin, @Param("pesoMax") Integer pesoMax);
    
    /**
     * Busca equipamentos por múltiplos tipos
     */
    @Query("SELECT e FROM Equipamentos e WHERE e.tipo IN :tipos")
    List<Equipamentos> findByTipoIn(@Param("tipos") List<TiposEquipamentos> tipos);
    
    /**
     * Busca equipamentos por múltiplas raridades
     */
    @Query("SELECT e FROM Equipamentos e WHERE e.raridade IN :raridades")
    List<Equipamentos> findByRaridadeIn(@Param("raridades") List<Raridades> raridades);
    
    /**
     * Busca equipamentos por filtro textual (nome ou descrição)
     */
    @Query("SELECT e FROM Equipamentos e WHERE " +
           "(LOWER(e.nome) LIKE LOWER(CONCAT('%', :filter, '%')) OR " +
           "LOWER(e.descricao) LIKE LOWER(CONCAT('%', :filter, '%')))")
    List<Equipamentos> findByTextFilter(@Param("filter") String filter);
    
    /**
     * Busca equipamentos por filtro textual com paginação
     */
    @Query("SELECT e FROM Equipamentos e WHERE " +
           "(LOWER(e.nome) LIKE LOWER(CONCAT('%', :filter, '%')) OR " +
           "LOWER(e.descricao) LIKE LOWER(CONCAT('%', :filter, '%')))")
    Page<Equipamentos> findByTextFilter(@Param("filter") String filter, Pageable pageable);
    
    /**
     * Busca equipamentos ordenados por nome (crescente)
     */
    @Query("SELECT e FROM Equipamentos e ORDER BY e.nome ASC")
    List<Equipamentos> findAllOrderByNomeAsc();
    
    /**
     * Busca equipamentos ordenados por nome (decrescente)
     */
    @Query("SELECT e FROM Equipamentos e ORDER BY e.nome DESC")
    List<Equipamentos> findAllOrderByNomeDesc();
    
    /**
     * Busca equipamentos ordenados por peso (crescente)
     */
    @Query("SELECT e FROM Equipamentos e ORDER BY e.peso ASC")
    List<Equipamentos> findAllOrderByPesoAsc();
    
    /**
     * Busca equipamentos ordenados por raridade
     */
    @Query("SELECT e FROM Equipamentos e ORDER BY e.raridade DESC")
    List<Equipamentos> findAllOrderByRaridadeDesc();
    
    /**
     * Busca equipamentos mais leves
     */
    @Query("SELECT e FROM Equipamentos e ORDER BY e.peso ASC")
    List<Equipamentos> findLightest(Pageable pageable);
    
    /**
     * Busca equipamentos mais pesados
     */
    @Query("SELECT e FROM Equipamentos e ORDER BY e.peso DESC")
    List<Equipamentos> findHeaviest(Pageable pageable);
    
    /**
     * Busca equipamentos únicos (raridade LENDARIO)
     */
    @Query("SELECT e FROM Equipamentos e WHERE e.raridade = 'LENDARIO'")
    List<Equipamentos> findEquipamentosLendarios();
    
    /**
     * Busca equipamentos comuns
     */
    @Query("SELECT e FROM Equipamentos e WHERE e.raridade = 'COMUM'")
    List<Equipamentos> findEquipamentosComuns();
    
    /**
     * Busca equipamentos sem peso definido
     */
    @Query("SELECT e FROM Equipamentos e WHERE e.peso IS NULL")
    List<Equipamentos> findEquipamentosSemPeso();
    
    /**
     * Busca equipamentos sem preço definido
     */
    @Query("SELECT e FROM Equipamentos e WHERE e.preco IS NULL")
    List<Equipamentos> findEquipamentosSemPreco();
    
    /**
     * Estatísticas de equipamentos
     */
    @Query("SELECT COUNT(e) FROM Equipamentos e")
    long countAllEquipamentos();
    
    @Query("SELECT COUNT(e) FROM Equipamentos e WHERE e.tipo = :tipo")
    long countByTipo(@Param("tipo") TiposEquipamentos tipo);
    
    @Query("SELECT COUNT(e) FROM Equipamentos e WHERE e.raridade = :raridade")
    long countByRaridade(@Param("raridade") Raridades raridade);
    
    @Query("SELECT AVG(e.peso) FROM Equipamentos e WHERE e.peso IS NOT NULL")
    Double getAveragePeso();
    
    @Query("SELECT MAX(e.peso) FROM Equipamentos e WHERE e.peso IS NOT NULL")
    Integer getMaxPeso();
    
    @Query("SELECT MIN(e.peso) FROM Equipamentos e WHERE e.peso IS NOT NULL")
    Integer getMinPeso();
    
    /**
     * Busca equipamentos por critérios combinados
     */
    @Query("SELECT e FROM Equipamentos e WHERE " +
           "(:tipo IS NULL OR e.tipo = :tipo) AND " +
           "(:raridade IS NULL OR e.raridade = :raridade) AND " +
           "(:preco IS NULL OR LOWER(e.preco) LIKE LOWER(CONCAT('%', :preco, '%'))) AND " +
           "(:pesoMax IS NULL OR e.peso <= :pesoMax) AND " +
           "(:nome IS NULL OR LOWER(e.nome) LIKE LOWER(CONCAT('%', :nome, '%')))")
    Page<Equipamentos> findByCriteria(@Param("tipo") TiposEquipamentos tipo,
                                      @Param("raridade") Raridades raridade,
                                      @Param("preco") String preco,
                                      @Param("pesoMax") Integer pesoMax,
                                      @Param("nome") String nome,
                                      Pageable pageable);
}
