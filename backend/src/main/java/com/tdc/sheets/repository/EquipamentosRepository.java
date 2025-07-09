package com.tdc.sheets.repository;

import com.tdc.sheets.entity.Equipamentos;
import com.tdc.sheets.entity.enums.TiposEquipamentos;
import com.tdc.sheets.entity.enums.Raridades;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository para operações com equipamentos
 */
@Repository
public interface EquipamentosRepository extends BaseRepository<Equipamentos, Long> {
    
    /**
     * Busca equipamentos por nome (case insensitive)
     */
    @Query("SELECT e FROM Equipamentos e WHERE LOWER(e.nome) LIKE LOWER(CONCAT('%', :nome, '%')) AND e.isActive = true")
    List<Equipamentos> findByNomeContainingIgnoreCase(@Param("nome") String nome);
    
    /**
     * Busca equipamentos por nome com paginação
     */
    @Query("SELECT e FROM Equipamentos e WHERE LOWER(e.nome) LIKE LOWER(CONCAT('%', :nome, '%')) AND e.isActive = true")
    Page<Equipamentos> findByNomeContainingIgnoreCase(@Param("nome") String nome, Pageable pageable);
    
    /**
     * Busca equipamentos por tipo
     */
    @Query("SELECT e FROM Equipamentos e WHERE e.tipo = :tipo AND e.isActive = true")
    List<Equipamentos> findByTipo(@Param("tipo") TiposEquipamentos tipo);
    
    /**
     * Busca equipamentos por tipo com paginação
     */
    @Query("SELECT e FROM Equipamentos e WHERE e.tipo = :tipo AND e.isActive = true")
    Page<Equipamentos> findByTipo(@Param("tipo") TiposEquipamentos tipo, Pageable pageable);
    
    /**
     * Busca equipamentos por raridade
     */
    @Query("SELECT e FROM Equipamentos e WHERE e.raridade = :raridade AND e.isActive = true")
    List<Equipamentos> findByRaridade(@Param("raridade") Raridades raridade);
    
    /**
     * Busca equipamentos por raridade com paginação
     */
    @Query("SELECT e FROM Equipamentos e WHERE e.raridade = :raridade AND e.isActive = true")
    Page<Equipamentos> findByRaridade(@Param("raridade") Raridades raridade, Pageable pageable);
    
    /**
     * Busca equipamentos por faixa de preço
     */
    @Query("SELECT e FROM Equipamentos e WHERE e.preco BETWEEN :precoMin AND :precoMax AND e.isActive = true")
    List<Equipamentos> findByPrecoBetween(@Param("precoMin") Double precoMin, @Param("precoMax") Double precoMax);
    
    /**
     * Busca equipamentos por preço máximo
     */
    @Query("SELECT e FROM Equipamentos e WHERE e.preco <= :precoMax AND e.isActive = true")
    List<Equipamentos> findByPrecoLessThanEqual(@Param("precoMax") Double precoMax);
    
    /**
     * Busca equipamentos por peso máximo
     */
    @Query("SELECT e FROM Equipamentos e WHERE e.peso <= :pesoMax AND e.isActive = true")
    List<Equipamentos> findByPesoLessThanEqual(@Param("pesoMax") Double pesoMax);
    
    /**
     * Busca equipamentos por faixa de peso
     */
    @Query("SELECT e FROM Equipamentos e WHERE e.peso BETWEEN :pesoMin AND :pesoMax AND e.isActive = true")
    List<Equipamentos> findByPesoBetween(@Param("pesoMin") Double pesoMin, @Param("pesoMax") Double pesoMax);
    
    /**
     * Busca equipamentos por múltiplos tipos
     */
    @Query("SELECT e FROM Equipamentos e WHERE e.tipo IN :tipos AND e.isActive = true")
    List<Equipamentos> findByTipoIn(@Param("tipos") List<TiposEquipamentos> tipos);
    
    /**
     * Busca equipamentos por múltiplas raridades
     */
    @Query("SELECT e FROM Equipamentos e WHERE e.raridade IN :raridades AND e.isActive = true")
    List<Equipamentos> findByRaridadeIn(@Param("raridades") List<Raridades> raridades);
    
    /**
     * Busca equipamentos por filtro textual (nome ou descrição)
     */
    @Query("SELECT e FROM Equipamentos e WHERE " +
           "(LOWER(e.nome) LIKE LOWER(CONCAT('%', :filter, '%')) OR " +
           "LOWER(e.descricao) LIKE LOWER(CONCAT('%', :filter, '%'))) " +
           "AND e.isActive = true")
    List<Equipamentos> findByTextFilter(@Param("filter") String filter);
    
    /**
     * Busca equipamentos por filtro textual com paginação
     */
    @Query("SELECT e FROM Equipamentos e WHERE " +
           "(LOWER(e.nome) LIKE LOWER(CONCAT('%', :filter, '%')) OR " +
           "LOWER(e.descricao) LIKE LOWER(CONCAT('%', :filter, '%'))) " +
           "AND e.isActive = true")
    Page<Equipamentos> findByTextFilter(@Param("filter") String filter, Pageable pageable);
    
    /**
     * Busca equipamentos ordenados por preço (crescente)
     */
    @Query("SELECT e FROM Equipamentos e WHERE e.isActive = true ORDER BY e.preco ASC")
    List<Equipamentos> findAllOrderByPrecoAsc();
    
    /**
     * Busca equipamentos ordenados por preço (decrescente)
     */
    @Query("SELECT e FROM Equipamentos e WHERE e.isActive = true ORDER BY e.preco DESC")
    List<Equipamentos> findAllOrderByPrecoDesc();
    
    /**
     * Busca equipamentos ordenados por peso (crescente)
     */
    @Query("SELECT e FROM Equipamentos e WHERE e.isActive = true ORDER BY e.peso ASC")
    List<Equipamentos> findAllOrderByPesoAsc();
    
    /**
     * Busca equipamentos ordenados por raridade
     */
    @Query("SELECT e FROM Equipamentos e WHERE e.isActive = true ORDER BY e.raridade DESC")
    List<Equipamentos> findAllOrderByRaridadeDesc();
    
    /**
     * Busca equipamentos mais caros
     */
    @Query("SELECT e FROM Equipamentos e WHERE e.isActive = true ORDER BY e.preco DESC")
    List<Equipamentos> findMostExpensive(Pageable pageable);
    
    /**
     * Busca equipamentos mais baratos
     */
    @Query("SELECT e FROM Equipamentos e WHERE e.isActive = true ORDER BY e.preco ASC")
    List<Equipamentos> findCheapest(Pageable pageable);
    
    /**
     * Busca equipamentos mais leves
     */
    @Query("SELECT e FROM Equipamentos e WHERE e.isActive = true ORDER BY e.peso ASC")
    List<Equipamentos> findLightest(Pageable pageable);
    
    /**
     * Busca equipamentos mais pesados
     */
    @Query("SELECT e FROM Equipamentos e WHERE e.isActive = true ORDER BY e.peso DESC")
    List<Equipamentos> findHeaviest(Pageable pageable);
    
    /**
     * Busca equipamentos únicos (raridade UNICO)
     */
    @Query("SELECT e FROM Equipamentos e WHERE e.raridade = 'UNICO' AND e.isActive = true")
    List<Equipamentos> findEquipamentosUnicos();
    
    /**
     * Busca equipamentos comuns
     */
    @Query("SELECT e FROM Equipamentos e WHERE e.raridade = 'COMUM' AND e.isActive = true")
    List<Equipamentos> findEquipamentosComuns();
    
    /**
     * Busca equipamentos sem peso definido
     */
    @Query("SELECT e FROM Equipamentos e WHERE e.peso IS NULL AND e.isActive = true")
    List<Equipamentos> findEquipamentosSemPeso();
    
    /**
     * Busca equipamentos sem preço definido
     */
    @Query("SELECT e FROM Equipamentos e WHERE e.preco IS NULL AND e.isActive = true")
    List<Equipamentos> findEquipamentosSemPreco();
    
    /**
     * Estatísticas de equipamentos
     */
    @Query("SELECT COUNT(e) FROM Equipamentos e WHERE e.isActive = true")
    long countAllEquipamentos();
    
    @Query("SELECT COUNT(e) FROM Equipamentos e WHERE e.tipo = :tipo AND e.isActive = true")
    long countByTipo(@Param("tipo") TiposEquipamentos tipo);
    
    @Query("SELECT COUNT(e) FROM Equipamentos e WHERE e.raridade = :raridade AND e.isActive = true")
    long countByRaridade(@Param("raridade") Raridades raridade);
    
    @Query("SELECT AVG(e.preco) FROM Equipamentos e WHERE e.preco IS NOT NULL AND e.isActive = true")
    Double getAveragePreco();
    
    @Query("SELECT MAX(e.preco) FROM Equipamentos e WHERE e.preco IS NOT NULL AND e.isActive = true")
    Double getMaxPreco();
    
    @Query("SELECT MIN(e.preco) FROM Equipamentos e WHERE e.preco IS NOT NULL AND e.isActive = true")
    Double getMinPreco();
    
    @Query("SELECT AVG(e.peso) FROM Equipamentos e WHERE e.peso IS NOT NULL AND e.isActive = true")
    Double getAveragePeso();
    
    @Query("SELECT MAX(e.peso) FROM Equipamentos e WHERE e.peso IS NOT NULL AND e.isActive = true")
    Double getMaxPeso();
    
    @Query("SELECT MIN(e.peso) FROM Equipamentos e WHERE e.peso IS NOT NULL AND e.isActive = true")
    Double getMinPeso();
    
    /**
     * Busca equipamentos por critérios combinados
     */
    @Query("SELECT e FROM Equipamentos e WHERE " +
           "(:tipo IS NULL OR e.tipo = :tipo) AND " +
           "(:raridade IS NULL OR e.raridade = :raridade) AND " +
           "(:precoMax IS NULL OR e.preco <= :precoMax) AND " +
           "(:pesoMax IS NULL OR e.peso <= :pesoMax) AND " +
           "(:nome IS NULL OR LOWER(e.nome) LIKE LOWER(CONCAT('%', :nome, '%'))) AND " +
           "e.isActive = true")
    Page<Equipamentos> findByCriteria(@Param("tipo") TiposEquipamentos tipo,
                                      @Param("raridade") Raridades raridade,
                                      @Param("precoMax") Double precoMax,
                                      @Param("pesoMax") Double pesoMax,
                                      @Param("nome") String nome,
                                      Pageable pageable);
}
