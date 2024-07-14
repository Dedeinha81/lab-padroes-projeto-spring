package Dio.lab_padroes_projeto_spring.Model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface EnderecoRepository extends CrudRepository<Endereco,String> {

}
