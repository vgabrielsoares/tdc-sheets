package com.tdc.sheets.repository;

import com.tdc.sheets.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Repository para operações com usuários
 */
@Repository
public interface UserRepository extends BaseRepository<User, Long> {
    
    /**
     * Busca usuário por username
     */
    @Query("SELECT u FROM User u WHERE u.username = :username AND u.isActive = true")
    Optional<User> findByUsername(@Param("username") String username);
    
    /**
     * Busca usuário por username para autenticação
     */
    @Query("SELECT u FROM User u WHERE u.username = :username AND u.isActive = true")
    Optional<User> findByUsernameAndIsActiveTrue(@Param("username") String username);
    
    /**
     * Busca usuário por ID para autenticação
     */
    @Query("SELECT u FROM User u WHERE u.id = :id AND u.isActive = true")
    Optional<User> findByIdAndIsActiveTrue(@Param("id") Long id);
    
    /**
     * Busca usuário por email
     */
    @Query("SELECT u FROM User u WHERE u.email = :email AND u.isActive = true")
    Optional<User> findByEmail(@Param("email") String email);
    
    /**
     * Busca usuário por email para autenticação
     */
    @Query("SELECT u FROM User u WHERE u.email = :email AND u.isActive = true")
    Optional<User> findByEmailAndIsActiveTrue(@Param("email") String email);
    
    /**
     * Busca usuário por username ou email
     */
    @Query("SELECT u FROM User u WHERE (u.username = :usernameOrEmail OR u.email = :usernameOrEmail) AND u.isActive = true")
    Optional<User> findByUsernameOrEmail(@Param("usernameOrEmail") String usernameOrEmail);
    
    /**
     * Verifica se username já existe
     */
    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.username = :username AND u.isActive = true")
    boolean existsByUsername(@Param("username") String username);
    
    /**
     * Verifica se email já existe
     */
    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.email = :email AND u.isActive = true")
    boolean existsByEmail(@Param("email") String email);
    
    /**
     * Busca usuários por nome completo (case insensitive)
     * FIXED: Changed fullName to nomeCompleto and removed isActive condition
     */
    @Query("SELECT u FROM User u WHERE LOWER(u.nomeCompleto) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<User> findByFullNameContainingIgnoreCase(@Param("name") String name);
    
    /**
     * Busca usuários por nome completo com paginação
     * FIXED: Changed fullName to nomeCompleto and removed isActive condition
     */
    @Query("SELECT u FROM User u WHERE LOWER(u.nomeCompleto) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<User> findByFullNameContainingIgnoreCase(@Param("name") String name, Pageable pageable);
    
    /**
     * Busca usuários online (último acesso dentro de um período)
     * TODO: Adicionar campo lastLoginAt à entidade User e banco de dados
     */
    // @Query("SELECT u FROM User u WHERE u.lastLoginAt >= :since AND u.isActive = true")
    // List<User> findUsersOnlineSince(@Param("since") LocalDateTime since);
    
    /**
     * Busca usuários com authority específica
     */
    @Query("SELECT DISTINCT u FROM User u JOIN u.authorities a WHERE a.authority = :authority AND u.isActive = true")
    List<User> findByAuthority(@Param("authority") String authority);
    
    /**
     * Busca usuários administradores
     */
    @Query("SELECT DISTINCT u FROM User u JOIN u.authorities a WHERE a.authority = 'ROLE_ADMIN' AND u.isActive = true")
    List<User> findAdmins();
    
    /**
     * Busca usuários por filtro textual (busca em username, email, nomeCompleto)
     */
    @Query("SELECT u FROM User u WHERE " +
           "(LOWER(u.username) LIKE LOWER(CONCAT('%', :filter, '%')) OR " +
           "LOWER(u.email) LIKE LOWER(CONCAT('%', :filter, '%')) OR " +
           "LOWER(u.nomeCompleto) LIKE LOWER(CONCAT('%', :filter, '%'))) " +
           "AND u.isActive = true")
    Page<User> findByTextFilter(@Param("filter") String filter, Pageable pageable);
    
    /**
     * Busca usuários ordenados por último login
     * TODO: Adicionar campo lastLoginAt à entidade User e banco de dados
     */
    // @Query("SELECT u FROM User u WHERE u.isActive = true ORDER BY u.lastLoginAt DESC NULLS LAST")
    // List<User> findAllOrderByLastLogin();
    
    /**
     * Busca usuários por data de criação
     */
    @Query("SELECT u FROM User u WHERE u.createdAt >= :startDate AND u.createdAt < :endDate AND u.isActive = true")
    List<User> findByCreatedDate(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
    
    /**
     * Estatísticas de usuários
     */
    @Query("SELECT COUNT(u) FROM User u WHERE u.isActive = true")
    long countActiveUsers();
    
    @Query("SELECT COUNT(u) FROM User u WHERE u.createdAt >= :since AND u.isActive = true")
    long countUsersCreatedSince(@Param("since") LocalDateTime since);
    
    /**
     * TODO: Adicionar campo lastLoginAt à entidade User e banco de dados
     */
    // @Query("SELECT COUNT(u) FROM User u WHERE u.lastLoginAt >= :since AND u.isActive = true")
    // long countUsersLoggedInSince(@Param("since") LocalDateTime since);
    
    /**
     * Busca usuários por avatar não nulo
     * TODO: Adicionar campo avatarUrl à entidade User e banco de dados
     */
    // @Query("SELECT u FROM User u WHERE u.avatarUrl IS NOT NULL AND u.isActive = true")
    // List<User> findUsersWithAvatar();
    
    /**
     * Busca usuários que nunca fizeram login
     * TODO: Adicionar campo lastLoginAt à entidade User e banco de dados
     */
    // @Query("SELECT u FROM User u WHERE u.lastLoginAt IS NULL AND u.isActive = true")
    // List<User> findUsersNeverLoggedIn();
    
    /**
     * Busca usuários inativos há mais de X dias
     * TODO: Adicionar campo lastLoginAt à entidade User e banco de dados
     */
    // @Query("SELECT u FROM User u WHERE u.lastLoginAt < :threshold AND u.isActive = true")
    // List<User> findInactiveUsersSince(@Param("threshold") LocalDateTime threshold);
}
