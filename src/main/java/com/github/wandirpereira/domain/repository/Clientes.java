package com.github.wandirpereira.domain.repository;

import com.github.wandirpereira.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface Clientes extends JpaRepository<Cliente, Integer> {

   List<Cliente> findByNomeLike(String nome);

//   @Query(value = "select c from Cliente c where c.nome like :nome ")
   @Query(value = "select * from cliente c where c.nome like '%:nome%' ", nativeQuery = true)
   List<Cliente>encontrarPorNome(@Param("nome") String nome);

   List<Cliente> findByNomeOrIdOrderById(String nome, Integer id);

   List<Cliente> findOneByNome(String nome);

   boolean existsByNome(String nome);
   @Modifying
   void deleteByNome(String nome);

   @Query(" select c from Cliente c left join fetch c.pedidos p where c.id = :id ")
   Cliente findClienteFetchPedidos( @Param("id") Integer id );

}
