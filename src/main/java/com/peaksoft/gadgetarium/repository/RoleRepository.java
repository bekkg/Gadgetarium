package com.peaksoft.gadgetarium.repository;


import com.peaksoft.gadgetarium.model.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    @Query("select r from Role r where r.name=:name")
    Role findByName(@Param("name") String name);
}
