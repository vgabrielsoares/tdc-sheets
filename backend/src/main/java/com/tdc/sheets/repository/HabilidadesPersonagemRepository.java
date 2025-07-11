package com.tdc.sheets.repository;

import com.tdc.sheets.entity.HabilidadesPersonagem;
import com.tdc.sheets.entity.FichaPersonagem;
import com.tdc.sheets.entity.Habilidade;
import com.tdc.sheets.entity.enums.GrauPericia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository para operações com habilidades de personagem
 */
@Repository
public interface HabilidadesPersonagemRepository extends BaseRepository<HabilidadesPersonagem, Long> {
    
    /**
     * Busca todas as habilidades de uma ficha específica
     */
    @Query("SELECT h FROM HabilidadesPersonagem h WHERE h.fichaPersonagem = :ficha AND h.isActive = true")
    List<HabilidadesPersonagem> findByFichaPersonagem(@Param("ficha") FichaPersonagem ficha);
    
    /**
     * Busca todas as habilidades de uma ficha por ID
     */
    @Query("SELECT h FROM HabilidadesPersonagem h WHERE h.fichaPersonagem.id = :fichaId AND h.isActive = true")
    List<HabilidadesPersonagem> findByFichaPersonagemId(@Param("fichaId") Long fichaId);
    
    /**
     * Busca habilidades por grau de perícia
     */
    @Query("SELECT h FROM HabilidadesPersonagem h WHERE h.grauHabilidade = :grau AND h.isActive = true")
    List<HabilidadesPersonagem> findByGrauHabilidade(@Param("grau") GrauPericia grau);
    
    /**
     * Busca habilidades de uma ficha por grau
     */
    @Query("SELECT h FROM HabilidadesPersonagem h WHERE h.fichaPersonagem.id = :fichaId AND h.grauHabilidade = :grau AND h.isActive = true")
    List<HabilidadesPersonagem> findByFichaPersonagemIdAndGrauHabilidade(@Param("fichaId") Long fichaId, @Param("grau") GrauPericia grau);
    
    /**
     * Busca uma habilidade específica de uma ficha
     */
    @Query("SELECT h FROM HabilidadesPersonagem h WHERE h.fichaPersonagem.id = :fichaId AND h.habilidade.id = :habilidadeId AND h.isActive = true")
    Optional<HabilidadesPersonagem> findByFichaPersonagemIdAndHabilidadeId(@Param("fichaId") Long fichaId, @Param("habilidadeId") Long habilidadeId);
    
    /**
     * Busca habilidades por tipo específico de habilidade
     */
    @Query("SELECT h FROM HabilidadesPersonagem h WHERE h.habilidade = :habilidade AND h.isActive = true")
    List<HabilidadesPersonagem> findByHabilidade(@Param("habilidade") Habilidade habilidade);
    
    /**
     * Busca habilidades com modificador específico
     * TODO: Descomentar quando campo modificador for adicionado à entidade ou ajustar para usar campos existentes
     */
    // @Query("SELECT h FROM HabilidadesPersonagem h WHERE h.modificador = :modificador AND h.isActive = true")
    // List<HabilidadesPersonagem> findByModificador(@Param("modificador") Integer modificador);
    
    /**
     * Busca habilidades com modificador mínimo
     * TODO: Descomentar quando campo modificador for adicionado à entidade ou ajustar para usar campos existentes
     */
    // @Query("SELECT h FROM HabilidadesPersonagem h WHERE h.modificador >= :modificadorMinimo AND h.isActive = true")
    // List<HabilidadesPersonagem> findByModificadorGreaterThanEqual(@Param("modificadorMinimo") Integer modificadorMinimo);
    
    /**
     * Busca habilidades de combate
     */
    @Query("SELECT h FROM HabilidadesPersonagem h WHERE h.habilidade.combate = true AND h.isActive = true")
    List<HabilidadesPersonagem> findHabilidadesCombate();
    
    /**
     * Busca habilidades de combate de uma ficha específica
     */
    @Query("SELECT h FROM HabilidadesPersonagem h WHERE h.fichaPersonagem.id = :fichaId AND h.habilidade.combate = true AND h.isActive = true")
    List<HabilidadesPersonagem> findHabilidadesCombateByFicha(@Param("fichaId") Long fichaId);
    
    /**
     * Busca habilidades não combate
     */
    @Query("SELECT h FROM HabilidadesPersonagem h WHERE h.habilidade.combate = false AND h.isActive = true")
    List<HabilidadesPersonagem> findHabilidadesNaoCombate();
    
    /**
     * Busca habilidades não combate de uma ficha específica
     */
    @Query("SELECT h FROM HabilidadesPersonagem h WHERE h.fichaPersonagem.id = :fichaId AND h.habilidade.combate = false AND h.isActive = true")
    List<HabilidadesPersonagem> findHabilidadesNaoCombateByFicha(@Param("fichaId") Long fichaId);
    
    /**
     * Busca habilidades ordenadas por modificador (descendente)
     * TODO: Descomentar quando campo modificador for adicionado à entidade ou ajustar para usar campos existentes
     */
    // @Query("SELECT h FROM HabilidadesPersonagem h WHERE h.fichaPersonagem.id = :fichaId AND h.isActive = true ORDER BY h.modificador DESC")
    // List<HabilidadesPersonagem> findByFichaPersonagemIdOrderByModificadorDesc(@Param("fichaId") Long fichaId);
    
    /**
     * Busca habilidades ordenadas por grau de perícia
     */
    @Query("SELECT h FROM HabilidadesPersonagem h WHERE h.fichaPersonagem.id = :fichaId AND h.isActive = true ORDER BY h.grauHabilidade DESC")
    List<HabilidadesPersonagem> findByFichaPersonagemIdOrderByGrauHabilidadeDesc(@Param("fichaId") Long fichaId);
    
    /**
     * Busca habilidades com grau acima de leigo
     */
    @Query("SELECT h FROM HabilidadesPersonagem h WHERE h.grauHabilidade != 'LEIGO' AND h.isActive = true")
    List<HabilidadesPersonagem> findHabilidadesAcimaLeigo();
    
    /**
     * Busca habilidades com grau acima de leigo de uma ficha específica
     */
    @Query("SELECT h FROM HabilidadesPersonagem h WHERE h.fichaPersonagem.id = :fichaId AND h.grauHabilidade != 'LEIGO' AND h.isActive = true")
    List<HabilidadesPersonagem> findHabilidadesAcimaLeigoByFicha(@Param("fichaId") Long fichaId);
    
    /**
     * Conta habilidades por grau de perícia de uma ficha
     */
    @Query("SELECT COUNT(h) FROM HabilidadesPersonagem h WHERE h.fichaPersonagem.id = :fichaId AND h.grauHabilidade = :grau AND h.isActive = true")
    long countByFichaPersonagemIdAndGrauHabilidade(@Param("fichaId") Long fichaId, @Param("grau") GrauPericia grau);
    
    /**
     * Conta total de habilidades de uma ficha
     */
    @Query("SELECT COUNT(h) FROM HabilidadesPersonagem h WHERE h.fichaPersonagem.id = :fichaId AND h.isActive = true")
    long countByFichaPersonagemId(@Param("fichaId") Long fichaId);
    
    /**
     * Busca habilidades por nome (busca textual)
     */
    @Query("SELECT h FROM HabilidadesPersonagem h WHERE LOWER(h.habilidade.pericia) LIKE LOWER(CONCAT('%', :nome, '%')) AND h.isActive = true")
    List<HabilidadesPersonagem> findByHabilidadeNomeContainingIgnoreCase(@Param("nome") String nome);
    
    /**
     * Busca habilidades por nome de uma ficha específica
     */
    @Query("SELECT h FROM HabilidadesPersonagem h WHERE h.fichaPersonagem.id = :fichaId AND LOWER(h.habilidade.pericia) LIKE LOWER(CONCAT('%', :nome, '%')) AND h.isActive = true")
    List<HabilidadesPersonagem> findByFichaPersonagemIdAndHabilidadeNomeContainingIgnoreCase(@Param("fichaId") Long fichaId, @Param("nome") String nome);
    
    /**
     * Busca habilidades com paginação
     */
    @Query("SELECT h FROM HabilidadesPersonagem h WHERE h.fichaPersonagem.id = :fichaId AND h.isActive = true")
    Page<HabilidadesPersonagem> findByFichaPersonagemId(@Param("fichaId") Long fichaId, Pageable pageable);
    
    /**
     * Estatísticas de habilidades
     * TODO: Descomentar quando campo modificador for adicionado à entidade ou ajustar para usar campos existentes
     */
    // @Query("SELECT AVG(h.modificador) FROM HabilidadesPersonagem h WHERE h.fichaPersonagem.id = :fichaId AND h.isActive = true")
    // Double getAverageModificadorByFicha(@Param("fichaId") Long fichaId);
    
    // @Query("SELECT MAX(h.modificador) FROM HabilidadesPersonagem h WHERE h.fichaPersonagem.id = :fichaId AND h.isActive = true")
    // Integer getMaxModificadorByFicha(@Param("fichaId") Long fichaId);
    
    // @Query("SELECT MIN(h.modificador) FROM HabilidadesPersonagem h WHERE h.fichaPersonagem.id = :fichaId AND h.isActive = true")
    // Integer getMinModificadorByFicha(@Param("fichaId") Long fichaId);
    
    /**
     * Busca habilidades mais populares (mais usadas entre todas as fichas)
     */
    @Query("SELECT h.habilidade, COUNT(h) as total FROM HabilidadesPersonagem h WHERE h.isActive = true GROUP BY h.habilidade ORDER BY total DESC")
    List<Object[]> findMostPopularHabilidades(Pageable pageable);
    
    /**
     * Verifica se uma ficha tem uma habilidade específica
     */
    @Query("SELECT COUNT(h) > 0 FROM HabilidadesPersonagem h WHERE h.fichaPersonagem.id = :fichaId AND h.habilidade.id = :habilidadeId AND h.isActive = true")
    boolean hasHabilidade(@Param("fichaId") Long fichaId, @Param("habilidadeId") Long habilidadeId);
    
    /**
     * Busca habilidades por IDs de habilidades específicas
     */
    @Query("SELECT h FROM HabilidadesPersonagem h WHERE h.fichaPersonagem.id = :fichaId AND h.habilidade.id IN :habilidadeIds AND h.isActive = true")
    List<HabilidadesPersonagem> findByFichaPersonagemIdAndHabilidadeIdIn(@Param("fichaId") Long fichaId, @Param("habilidadeIds") List<Long> habilidadeIds);
}
