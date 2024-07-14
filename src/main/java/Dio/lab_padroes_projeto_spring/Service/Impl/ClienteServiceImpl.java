package Dio.lab_padroes_projeto_spring.Service.Impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Dio.lab_padroes_projeto_spring.Model.Cliente;
import Dio.lab_padroes_projeto_spring.Model.ClienteRepository;
import Dio.lab_padroes_projeto_spring.Model.Endereco;
import Dio.lab_padroes_projeto_spring.Model.EnderecoRepository;
import Dio.lab_padroes_projeto_spring.Service.ClienteService;
import Dio.lab_padroes_projeto_spring.Service.ViaCepService;

@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired 
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;
    

    @Override
    public void atualizar(Long id, Cliente cliente) {
        Optional<Cliente> clienteBd=clienteRepository.findById(id);
        if(clienteBd.isPresent()){
            SalvarClienteCep(cliente);
            
        }
    }

    @Override
    public Cliente buscarPorId(Long id) {
        Optional<Cliente> cliente=clienteRepository.findById(id);
        return cliente.get();
    
    }

    @Override
    public Iterable<Cliente> buscarTodos() {
        
        return clienteRepository.findAll();
    }

    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
        

        
    }

    @Override
    public void inserir(Cliente cliente) {
        getEndereco(cliente);
    }

    private void getEndereco(Cliente cliente) {
        SalvarClienteCep(cliente);
    }

    private void SalvarClienteCep(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;

        });
        cliente.setEndereco(endereco);

        clienteRepository.save(cliente);
    }
    

}
