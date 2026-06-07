package estruturas;

public class MapaDispersao<T> {

    private ListaEncadeada<NoMapa<T>>[] info;
    private int totalElementos;

  
    public MapaDispersao(int tamanho) {
        info = new ListaEncadeada[tamanho];
        for (int i = 0; i < tamanho; i++) {
            info[i] = new ListaEncadeada<>();
        }
        totalElementos = 0;
    }

    private int calcularHash(String chave) {
        int hash = 0;
        for (int i = 0; i < chave.length(); i++) {
            hash = 31 * hash + chave.charAt(i);
        }
        return Math.abs(hash % info.length);
    }

    public void inserir(String chave, T dado) {
        int pos = calcularHash(chave);
        NoMapa<T> novoNo = new NoMapa<>(chave, dado);

        NoMapa<T> existente = info[pos].buscar(novoNo);
        if (existente != null) {
            existente.setValor(dado);
        } else {
            info[pos].adicionar(novoNo);
            totalElementos++;
        }
    }

    public T buscar(String chave) {
        int pos = calcularHash(chave);
        NoMapa<T> referencia = new NoMapa<>(chave, null);
        NoMapa<T> encontrado = info[pos].buscar(referencia);
        return encontrado != null ? encontrado.getValor() : null;
    }

    public ListaEncadeada<NoMapa<T>>[] getInfo() {
        return info;
    }

    public int getTotalElementos() {
        return totalElementos;
    }
}