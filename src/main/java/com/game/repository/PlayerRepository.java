package com.game.repository;

import com.game.entity.PlayerEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {

    @Query(value = "select * from player pl where (:name is null or pl.name like %:name%) " +
            " and (:title is null or pl.title like %:title%) " +
            " and (:race is null or pl.race = :race) " +
            " and (:profession is null or pl.profession = :profession) " +
            " and (:after is null or pl.birthday >= :after) " +
            " and (:before is null or pl.birthday <= :before) " +
            " and (:banned is null or pl.banned = :banned) " +
            " and (:minExperience is null or pl.experience >= :minExperience) " +
            " and (:maxExperience is null or pl.experience <= :maxExperience) " +
            " and (:minLevel is null or pl.level >= :minLevel) " +
            " and (:maxLevel is null or pl.level <= :maxLevel)"
            , nativeQuery = true)
    List<PlayerEntity> findAllPlayers(@Param("name") String name, @Param("title") String title, @Param("race") String race,
                                      @Param("profession") String profession, @Param("after") LocalDate after,
                                      @Param("before") LocalDate before, @Param("banned") Boolean banned,
                                      @Param("minExperience") Integer minExperience, @Param("maxExperience") Integer maxExperience,
                                      @Param("minLevel") Integer minLevel, @Param("maxLevel") Integer maxLevel, Pageable pageable);

    @Query(value = "select count(pl.id) from player pl where (:name is null or pl.name like %:name%) " +
            " and (:title is null or pl.title like %:title%) " +
            " and (:race is null or pl.race = :race) " +
            " and (:profession is null or pl.profession = :profession) " +
            " and (:after is null or pl.birthday >= :after) " +
            " and (:before is null or pl.birthday <= :before) " +
            " and (:banned is null or pl.banned = :banned) " +
            " and (:minExperience is null or pl.experience >= :minExperience) " +
            " and (:maxExperience is null or pl.experience <= :maxExperience) " +
            " and (:minLevel is null or pl.level >= :minLevel) " +
            " and (:maxLevel is null or pl.level <= :maxLevel)"
            , nativeQuery = true)
    Long countAllPlayers(@Param("name") String name, @Param("title") String title, @Param("race") String race,
                                      @Param("profession") String profession, @Param("after") LocalDate after,
                                      @Param("before") LocalDate before, @Param("banned") Boolean banned,
                                      @Param("minExperience") Integer minExperience, @Param("maxExperience") Integer maxExperience,
                                      @Param("minLevel") Integer minLevel, @Param("maxLevel") Integer maxLevel);

}
