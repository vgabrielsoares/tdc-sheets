package com.tdc.sheets.repository;

import com.tdc.sheets.entity.FichaPersonagem;
import com.tdc.sheets.entity.User;
import com.tdc.sheets.entity.enums.ArquetipoNome;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Repository para operações com fichas de personagem
 */
@Repository
public interface FichaPersonagemRepository extends BaseRepository<FichaPersonagem, Long> {
    
    /**
     * Busca todas as fichas de um usuário específico
     */
    @Query("SELECT f FROM FichaPersonagem f WHERE f.user = :user AND f.isActive = true")
    List<FichaPersonagem> findByUser(@Param("user") User user);
    
    /**
     * Busca todas as fichas de um usuário específico com paginação
     */
    @Query("SELECT f FROM FichaPersonagem f WHERE f.user = :user AND f.isActive = true")
    Page<FichaPersonagem> findByUser(@Param("user") User user, Pageable pageable);
    
    /**
     * Busca fichas por ID do usuário
     */
    @Query("SELECT f FROM FichaPersonagem f WHERE f.user.id = :userId AND f.isActive = true")
    List<FichaPersonagem> findByUserId(@Param("userId") Long userId);
    
    /**
     * Busca fichas por ID do usuário com paginação
     */
    @Query("SELECT f FROM FichaPersonagem f WHERE f.user.id = :userId AND f.isActive = true")
    Page<FichaPersonagem> findByUserId(@Param("userId") Long userId, Pageable pageable);
    
    /**
     * Busca fichas por nome do personagem (case insensitive)
     */
    @Query("SELECT f FROM FichaPersonagem f WHERE LOWER(f.nomePersonagem) LIKE LOWER(CONCAT('%', :nome, '%')) AND f.isActive = true")
    List<FichaPersonagem> findByNomePersonagemContainingIgnoreCase(@Param("nome") String nome);
    
    /**
     * Busca fichas por nome do personagem com paginação
     */
    @Query("SELECT f FROM FichaPersonagem f WHERE LOWER(f.nomePersonagem) LIKE LOWER(CONCAT('%', :nome, '%')) AND f.isActive = true")
    Page<FichaPersonagem> findByNomePersonagemContainingIgnoreCase(@Param("nome") String nome, Pageable pageable);
    
    /**
     * Busca fichas por origem
     */
    @Query("SELECT f FROM FichaPersonagem f WHERE f.origem.id = :origemId AND f.isActive = true")
    List<FichaPersonagem> findByOrigemId(@Param("origemId") Long origemId);
    
    /**
     * Busca fichas por linhagem
     */
    @Query("SELECT f FROM FichaPersonagem f WHERE f.linhagem.id = :linhagemId AND f.isActive = true")
    List<FichaPersonagem> findByLinhagemId(@Param("linhagemId") Long linhagemId);
    
    /**
     * Busca fichas que possuem um arquétipo específico
     */
    @Query("SELECT DISTINCT f FROM FichaPersonagem f JOIN f.arquetipos a WHERE a.arquetipo.nome = :arquetipoNome AND f.isActive = true")
    List<FichaPersonagem> findByArquetipoNome(@Param("arquetipoNome") ArquetipoNome arquetipoNome);
    
    /**
     * Busca fichas que possuem uma classe específica
     */
    @Query("SELECT DISTINCT f FROM FichaPersonagem f JOIN f.classes c WHERE c.classe.id = :classeId AND f.isActive = true")
    List<FichaPersonagem> findByClasseId(@Param("classeId") Long classeId);
    
    /**
     * Busca fichas por nível mínimo
     * TODO: Ajustar para usar experiencia ou métodos calculados
     */
    // @Query("SELECT f FROM FichaPersonagem f WHERE f.nivel >= :nivelMinimo AND f.isActive = true")
    // List<FichaPersonagem> findByNivelMinimo(@Param("nivelMinimo") Integer nivelMinimo);
    
    /**
     * Busca fichas por faixa de nível
     * TODO: Ajustar para usar experiencia ou métodos calculados
     */
    // @Query("SELECT f FROM FichaPersonagem f WHERE f.nivel BETWEEN :nivelMinimo AND :nivelMaximo AND f.isActive = true")
    // List<FichaPersonagem> findByNivelBetween(@Param("nivelMinimo") Integer nivelMinimo, @Param("nivelMaximo") Integer nivelMaximo);
    
    /**
     * Busca fichas por filtro textual (nome personagem, nome jogador, descrição)
     */
    @Query("SELECT f FROM FichaPersonagem f WHERE " +
           "(LOWER(f.nomePersonagem) LIKE LOWER(CONCAT('%', :filter, '%')) OR " +
           "LOWER(f.user.nomeCompleto) LIKE LOWER(CONCAT('%', :filter, '%')) OR " +
           "LOWER(f.descricao.historia) LIKE LOWER(CONCAT('%', :filter, '%'))) " +
           "AND f.isActive = true")
    Page<FichaPersonagem> findByTextFilter(@Param("filter") String filter, Pageable pageable);
    
    /**
     * Busca fichas compartilhadas publicamente
     * TODO: Field 'publico' not found in CompartilhamentoFicha entity. Available fields: ativo, nivelAcesso, linkToken, expiracao
     * Need to determine the correct logic for public sharing
     */
    // @Query("SELECT DISTINCT f FROM FichaPersonagem f JOIN f.compartilhamentos c WHERE c.publico = true AND f.isActive = true")
    // List<FichaPersonagem> findPublicSharedFichas();
    
    /**
     * Busca fichas compartilhadas com um usuário específico
     */
    @Query("SELECT DISTINCT f FROM FichaPersonagem f JOIN f.compartilhamentos c WHERE c.user.id = :userId AND c.ativo = true AND f.isActive = true")
    List<FichaPersonagem> findSharedWithUser(@Param("userId") Long userId);
    
    /**
     * Busca fichas por usuário ou compartilhadas com ele
     */
    @Query("SELECT DISTINCT f FROM FichaPersonagem f LEFT JOIN f.compartilhamentos c WHERE " +
           "(f.user.id = :userId OR (c.user.id = :userId AND c.ativo = true)) AND f.isActive = true")
    List<FichaPersonagem> findByUserOrSharedWithUser(@Param("userId") Long userId);
    
    /**
     * Busca fichas por usuário ou compartilhadas com ele com paginação
     */
    @Query("SELECT DISTINCT f FROM FichaPersonagem f LEFT JOIN f.compartilhamentos c WHERE " +
           "(f.user.id = :userId OR (c.user.id = :userId AND c.ativo = true)) AND f.isActive = true")
    Page<FichaPersonagem> findByUserOrSharedWithUser(@Param("userId") Long userId, Pageable pageable);
    
    /**
     * Busca fichas mais recentes
     */
    @Query("SELECT f FROM FichaPersonagem f WHERE f.isActive = true ORDER BY f.createdAt DESC")
    List<FichaPersonagem> findRecentFichas(Pageable pageable);
    
    /**
     * Busca fichas mais atualizadas
     */
    @Query("SELECT f FROM FichaPersonagem f WHERE f.isActive = true ORDER BY f.updatedAt DESC")
    List<FichaPersonagem> findRecentlyUpdatedFichas(Pageable pageable);
      /**
     * Estatísticas de fichas - queries de nível comentadas pois precisam ser ajustadas
     * TODO: Ajustar para usar experiencia ou métodos calculados
     */
    @Query("SELECT COUNT(f) FROM FichaPersonagem f WHERE f.isActive = true")
    long countAllFichas();

    @Query("SELECT COUNT(f) FROM FichaPersonagem f WHERE f.user.id = :userId AND f.isActive = true")
    long countFichasByUser(@Param("userId") Long userId);

    // @Query("SELECT AVG(f.nivel) FROM FichaPersonagem f WHERE f.isActive = true")
    // Double getAverageLevel();

    // @Query("SELECT MAX(f.nivel) FROM FichaPersonagem f WHERE f.isActive = true")
    // Integer getMaxLevel();

    // @Query("SELECT MIN(f.nivel) FROM FichaPersonagem f WHERE f.isActive = true")
    // Integer getMinLevel();
    
    /**
     * Busca fichas por data de criação
     */
    @Query("SELECT f FROM FichaPersonagem f WHERE f.createdAt >= :startDate AND f.createdAt < :endDate AND f.isActive = true")
    List<FichaPersonagem> findByCreatedDate(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
    
    /**
     * Busca fichas criadas nos últimos X dias
     */
    @Query("SELECT f FROM FichaPersonagem f WHERE f.createdAt >= :since AND f.isActive = true")
    List<FichaPersonagem> findCreatedSince(@Param("since") LocalDateTime since);
    
    /**
     * Busca fichas modificadas nos últimos X dias
     */
    @Query("SELECT f FROM FichaPersonagem f WHERE f.updatedAt >= :since AND f.isActive = true")
    List<FichaPersonagem> findModifiedSince(@Param("since") LocalDateTime since);
    
    /**
     * Busca fichas que nunca foram compartilhadas
     */
    @Query("SELECT f FROM FichaPersonagem f WHERE f.compartilhamentos IS EMPTY AND f.isActive = true")
    List<FichaPersonagem> findNeverSharedFichas();
    
    /**
     * Busca fichas com mais de X compartilhamentos
     */
    @Query("SELECT f FROM FichaPersonagem f WHERE SIZE(f.compartilhamentos) > :minShares AND f.isActive = true")
    List<FichaPersonagem> findFichasWithMoreThanXShares(@Param("minShares") int minShares);
    
    /**
     * Busca fichas ordenadas por popularidade (número de compartilhamentos)
     */
    @Query("SELECT f FROM FichaPersonagem f WHERE f.isActive = true ORDER BY SIZE(f.compartilhamentos) DESC")
    List<FichaPersonagem> findOrderByPopularity(Pageable pageable);
}
