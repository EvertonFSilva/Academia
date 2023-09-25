package modalidades;

public class ModalidadeFactory {
    public static ServicoModalidade criarModalidade(String modalidade) {
        switch (modalidade) {
            case "Musculacao":
                return new Musculacao();
            case "Funcional":
                return new Funcional();
            case "Spinning":
            	return new Spinning();
            case "Yoga":
            	return new Yoga();
            default:
                throw new IllegalArgumentException("Modalidade desconhecida: " + modalidade);
        }
    }
}
