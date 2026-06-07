package estruturas;

public class ListaDocumentos {

    public static class No {
    public String documento;
    public No proximo;

    No(String documento) {
        this.documento = documento;
        this.proximo = null;
    }

    public String getDocumento() {
        return documento;
    }

    public No getProximo() {
        return proximo;
    }
}

    private No cabeca;
    private int tamanho;

    public ListaDocumentos() {
        this.cabeca = null;
        this.tamanho = 0;
    }

    public void adicionar(String documento) {
        No atual = cabeca;
        while (atual != null) {
            if (atual.documento.equals(documento)) {
                return; 
            }
            atual = atual.proximo;
        }

        
        No novo = new No(documento);
        novo.proximo = cabeca;
        cabeca = novo;
        tamanho++;
    }

    public boolean contem(String documento) {
        No atual = cabeca;
        while (atual != null) {
            if (atual.documento.equals(documento)) {
                return true;
            }
            atual = atual.proximo;
        }
        return false;
    }

    public No getCabeca() {
        return cabeca;
    }

    public int getTamanho() {
        return tamanho;
    }
}
