package estruturas;

public class NoMapa<T>{

    private String chave;
    private T valor;

    public NoMapa(String chave, T valor) {
        this.chave = chave;
        this.valor = valor;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public T getValor() {
        return valor;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoMapa<?> noMapa = (NoMapa<?>) o;
        return this.chave.equals(noMapa.chave);
    }

    
}
