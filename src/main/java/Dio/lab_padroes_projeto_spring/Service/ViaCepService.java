package Dio.lab_padroes_projeto_spring.Service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Dio.lab_padroes_projeto_spring.Model.Endereco;

@FeignClient(name = "viacep",url="https://viacep.com.br/ms")
public interface ViaCepService {
   @RequestMapping (method = RequestMethod.GET,value="/{cep}/json/")
    Endereco consultarCep(@PathVariable("cep")String cep);

}
