package banco.repositorios.interfaces;

import entidades.EntidadeEndereco;

public interface ServicoRepositorioEndereco {

	EntidadeEndereco buscarEnderecoPorId(int enderecoId);

	int inserirEndereco(EntidadeEndereco endereco);

}
