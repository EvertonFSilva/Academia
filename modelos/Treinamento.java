package modelos;

import java.util.Date;

public class Treinamento {
    private String detalhes;
    private Date data;

    public Treinamento(String detalhes, Date data) {
        this.detalhes = detalhes;
        this.data = data;
    }

    public String getDetalhes() {
        return detalhes;
    }
    
    public void setDetalhes(String detalhes) {
    	this.detalhes = detalhes;
    }

    public Date getData() {
        return data;
    }
}