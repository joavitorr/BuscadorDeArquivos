# BuscadorDeArquivos

Trabalho Final — Algoritmos e Estruturas de Dados  
FURB — Professor Gilvan Justino

## Descrição

Aplicação em Java que realiza indexação e pesquisa de palavras em arquivos `.txt`. Utiliza um **mapa de dispersão (HashMap)** implementado manualmente para indexar as palavras e permitir buscas rápidas e eficientes.

## Estrutura do Projeto

```
BuscadorDeArquivos/
├── src/
│   ├── estruturas/
│   │   ├── ListaEncadeada.java    # Lista encadeada genérica
│   │   ├── NoMapa.java            # Nó do mapa (chave String + valor genérico)
│   │   ├── MapaDispersao.java     # HashMap com chave String
│   │   └── ListaDocumentos.java   # Lista de documentos por palavra
│   └── App/
│       ├── Indexador.java         # Lê arquivos .txt e popula o índice
│       ├── GerenciadorIndice.java # Salva e carrega o índice em disco
│       ├── Buscador.java          # Realiza buscas no índice
│       └── Main.java              # Ponto de entrada da aplicação
├── bin/                           # Arquivos compilados (.class)
├── textos/                        # Pasta com arquivos .txt a indexar
├── indice.txt                     # Índice gerado automaticamente
└── README.md
```

## Pré-requisitos

- Java JDK 11 ou superior
- Git Bash (Windows) ou terminal Linux/Mac

Verificar versão do Java:
```bash
java -version
javac -version
```

## Compilação

Na raiz do projeto, execute:

```bash
mkdir -p bin
javac -d bin src/estruturas/*.java src/App/*.java
```

## Execução

```bash
java -cp bin Main
```

## Uso da Aplicação

### Primeira execução (sem índice salvo)

```
Nenhum índice encontrado.
Informe o diretório a ser indexado: C:/Users/SEU_USUARIO/Desktop/BuscadorDeArquivos/textos
Indexando: C:/Users/SEU_USUARIO/Desktop/BuscadorDeArquivos/textos/arquivo1.txt
Indexando: C:/Users/SEU_USUARIO/Desktop/BuscadorDeArquivos/textos/arquivo2.txt
Índice salvo com sucesso!
```

> **Importante:** No Windows, use barras `/` em vez de `\` no caminho.

### Execuções seguintes (índice já salvo)

```
Índice encontrado! Carregando...
Índice carregado com sucesso!
```

O programa carrega o índice do disco sem precisar reindexar.

### Busca por uma palavra

```
Digite palavra(s) para buscar (ou 'sair' para encerrar): java

Documentos que contém "java":
  1. C:/Users/.../textos/arquivo1.txt
  2. C:/Users/.../textos/arquivo2.txt
```

### Busca por múltiplas palavras (AND)

```
Digite palavra(s) para buscar (ou 'sair' para encerrar): java estruturas

Documentos que contém todas as palavras:
  1. C:/Users/.../textos/arquivo1.txt
```

Quando múltiplas palavras são informadas, apenas documentos que contêm **todas** elas são exibidos.

### Encerrar

```
Digite palavra(s) para buscar (ou 'sair' para encerrar): sair
Encerrando...
```

## Regras de Indexação

- Palavras com menos de 3 letras são ignoradas
- Palavras compostas apenas por números ou pontos são ignoradas
- Não há distinção entre maiúsculas e minúsculas
- Pontuações são descartadas automaticamente

## Reindexar (forçar nova indexação)

Para reindexar, delete o arquivo `indice.txt` e execute novamente:

```bash
rm indice.txt
java -cp bin App.Main
```

## Estruturas de Dados Utilizadas

| Estrutura | Uso |
|---|---|
| `ListaEncadeada<T>` | Armazena entradas em cada bucket do mapa |
| `NoMapa<T>` | Par chave (String) + valor genérico |
| `MapaDispersao<T>` | Índice principal: palavra → lista de documentos |
| `ListaDocumentos` | Lista de arquivos associados a cada palavra |

> Nenhuma estrutura nativa do Java (`ArrayList`, `HashMap`, etc.) foi utilizada.

## Git — Comandos Úteis

### Clonar o repositório
```bash
git clone https://github.com/joavitorr/BuscadorDeArquivos.git
cd BuscadorDeArquivos
```

### Salvar alterações
```bash
git add .
git commit -m "Descrição da alteração"
git push
```

### Ver status
```bash
git status
git log --oneline
```
