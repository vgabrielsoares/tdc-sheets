package com.tdc.sheets.repository;

import com.tdc.sheets.entity.AtributosPersonagem;
import com.tdc.sheets.entity.FichaPersonagem;
import com.tdc.sheets.entity.enums.Atributos;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository para operações com atributos de personagem
 */
@Repository
public interface AtributosPersonagemRepository extends BaseRepository<AtributosPersonagem, Long> {
    
    /**
     * Busca todos os atributos de uma ficha específica
     */
    @Query("SELECT a FROM AtributosPersonagem a WHERE a.fichaPersonagem = :ficha AND a.isActive = true")
    List<AtributosPersonagem> findByFichaPersonagem(@Param("ficha") FichaPersonagem ficha);
    
    /**
     * Busca todos os atributos de uma ficha por ID
     */
    @Query("SELECT a FROM AtributosPersonagem a WHERE a.fichaPersonagem.id = :fichaId AND a.isActive = true")
    List<AtributosPersonagem> findByFichaPersonagemId(@Param("fichaId") Long fichaId);
    
    /**
     * Busca um atributo específico de uma ficha
     */
    @Query("SELECT a FROM AtributosPersonagem a WHERE a.fichaPersonagem.id = :fichaId AND a.atributo = :atributo AND a.isActive = true")
    Optional<AtributosPersonagem> findByFichaPersonagemIdAndAtributo(@Param("fichaId") Long fichaId, @Param("atributo") Atributos atributo);
    
    /**
     * Busca atributos com valor mínimo
     * TODO: Ajustar para usar campo 'valor' em vez de 'valorBase'
     */
    // @Query("SELECT a FROM AtributosPersonagem a WHERE a.valorBase >= :valorMinimo AND a.isActive = true")
    // List<AtributosPersonagem> findByValorBaseGreaterThanEqual(@Param("valorMinimo") Integer valorMinimo);
    
    /**
     * Busca atributos de um tipo específico
     */
    @Query("SELECT a FROM AtributosPersonagem a WHERE a.atributo = :atributo AND a.isActive = true")
    List<AtributosPersonagem> findByAtributo(@Param("atributo") Atributos atributo);
    
    /**
     * Busca atributos com modificador temporário
     * TODO: Ajustar para usar campo 'valorTemp' em vez de 'modificadorTemporario'
     */
    // @Query("SELECT a FROM AtributosPersonagem a WHERE a.modificadorTemporario != 0 AND a.isActive = true")
    // List<AtributosPersonagem> findWithModificadorTemporario();
    
    /**
     * Busca atributos por ficha com modificador total calculado
     */
    @Query("SELECT a FROM AtributosPersonagem a WHERE a.fichaPersonagem.id = :fichaId AND a.isActive = true ORDER BY a.atributo")
    List<AtributosPersonagem> findByFichaPersonagemIdOrderByAtributo(@Param("fichaId") Long fichaId);
    
    /**
     * Calcula soma total de pontos de atributos de uma ficha
     * TODO: Ajustar para usar campo 'valor' em vez de 'valorBase'
     */
    // @Query("SELECT SUM(a.valorBase) FROM AtributosPersonagem a WHERE a.fichaPersonagem.id = :fichaId AND a.isActive = true")
    // Integer calculateTotalAttributePoints(@Param("fichaId") Long fichaId);
    
    /**
     * Busca atributos por valor total (base + modificadores)
     * TODO: Ajustar para usar campos existentes 'valor' e 'valorTemp'
     */
    // @Query("SELECT a FROM AtributosPersonagem a WHERE (a.valorBase + a.modificadorRacial + a.modificadorItem + a.modificadorTemporario) >= :valorTotal AND a.isActive = true")
    // List<AtributosPersonagem> findByValorTotalGreaterThanEqual(@Param("valorTotal") Integer valorTotal);
    
    /**
     * Busca fichas ordenadas por valor de um atributo específico
     * TODO: Ajustar para usar campos existentes 'valor' e 'valorTemp'
     */
    // @Query("SELECT a FROM AtributosPersonagem a WHERE a.atributo = :atributo AND a.isActive = true ORDER BY (a.valorBase + a.modificadorRacial + a.modificadorItem + a.modificadorTemporario) DESC")
    // List<AtributosPersonagem> findByAtributoOrderByValorTotalDesc(@Param("atributo") Atributos atributo);
    
    /**
     * Busca atributos com penalidade
     * TODO: Ajustar para usar campos existentes ou lógica correta
     */
    // @Query("SELECT a FROM AtributosPersonagem a WHERE (a.modificadorRacial < 0 OR a.modificadorItem < 0 OR a.modificadorTemporario < 0) AND a.isActive = true")
    // List<AtributosPersonagem> findWithPenalties();
    
    /**
     * Busca atributos com bônus
     * TODO: Ajustar para usar campos existentes ou lógica correta
     */
    // @Query("SELECT a FROM AtributosPersonagem a WHERE (a.modificadorRacial > 0 OR a.modificadorItem > 0 OR a.modificadorTemporario > 0) AND a.isActive = true")
    // List<AtributosPersonagem> findWithBonuses();
    
    /**
     * Calcula modificador de um atributo (usado para skills, saves, etc.)
     * TODO: Ajustar para usar campos existentes 'valor' e 'valorTemp' ou usar método da entidade
     */
    // @Query("SELECT FLOOR((a.valorBase + a.modificadorRacial + a.modificadorItem + a.modificadorTemporario - 10) / 2) FROM AtributosPersonagem a WHERE a.fichaPersonagem.id = :fichaId AND a.atributo = :atributo AND a.isActive = true")
    // Integer calculateModifier(@Param("fichaId") Long fichaId, @Param("atributo") Atributos atributo);
    
    /**
     * Busca atributos que estão no limite máximo permitido
     * TODO: Ajustar para usar campos existentes 'valor' e 'valorTemp'
     */
    // @Query("SELECT a FROM AtributosPersonagem a WHERE (a.valorBase + a.modificadorRacial + a.modificadorItem + a.modificadorTemporario) >= 18 AND a.isActive = true")
    // List<AtributosPersonagem> findAtMaximumValue();
    
    /**
     * Busca atributos que estão no limite mínimo
     * TODO: Ajustar para usar campos existentes 'valor' e 'valorTemp'
     */
    // @Query("SELECT a FROM AtributosPersonagem a WHERE (a.valorBase + a.modificadorRacial + a.modificadorItem + a.modificadorTemporario) <= 3 AND a.isActive = true")
    // List<AtributosPersonagem> findAtMinimumValue();
    
    /**
     * Verifica se uma ficha tem todos os atributos configurados
     */
    @Query("SELECT COUNT(a) FROM AtributosPersonagem a WHERE a.fichaPersonagem.id = :fichaId AND a.isActive = true")
    long countAtributosByFicha(@Param("fichaId") Long fichaId);
    
    /**
     * Busca fichas que não têm todos os 6 atributos configurados
     */
    @Query("SELECT f.id FROM FichaPersonagem f WHERE f.id NOT IN (SELECT a.fichaPersonagem.id FROM AtributosPersonagem a WHERE a.isActive = true GROUP BY a.fichaPersonagem.id HAVING COUNT(a) = 6) AND f.isActive = true")
    List<Long> findFichasWithIncompleteAttributes();
    
    /**
     * Estatísticas de atributos
     * TODO: Ajustar para usar campos existentes 'valor' e 'valorTemp'
     */
    // @Query("SELECT AVG(a.valorBase + a.modificadorRacial + a.modificadorItem + a.modificadorTemporario) FROM AtributosPersonagem a WHERE a.atributo = :atributo AND a.isActive = true")
    // Double getAverageValueByAtributo(@Param("atributo") Atributos atributo);
    
    // @Query("SELECT MAX(a.valorBase + a.modificadorRacial + a.modificadorItem + a.modificadorTemporario) FROM AtributosPersonagem a WHERE a.atributo = :atributo AND a.isActive = true")
    // Integer getMaxValueByAtributo(@Param("atributo") Atributos atributo);
    
    // @Query("SELECT MIN(a.valorBase + a.modificadorRacial + a.modificadorItem + a.modificadorTemporario) FROM AtributosPersonagem a WHERE a.atributo = :atributo AND a.isActive = true")
    // Integer getMinValueByAtributo(@Param("atributo") Atributos atributo);
}
