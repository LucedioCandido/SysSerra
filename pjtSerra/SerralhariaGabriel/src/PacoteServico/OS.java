package PacoteServico;

/**
 *
 * @author Bruno Mezenga
 */
public class OS {
    
    private int ordemX;
    private String ordemXNome;

    public int getOrdemX() {
        return ordemX;
    }

    public void setOrdemX(int ordemX) {
        this.ordemX = ordemX;
    }

    public String getOrdemXNome() {
        return ordemXNome;
    }

    public void setOrdemXNome(String ordemXNome) {
        this.ordemXNome = ordemXNome;
    }

    @Override
    public String toString() {
        return getOrdemXNome(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
