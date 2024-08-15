import java.util.Optional;

public class ArvoreBinariaDeBusca {

    public ArvoreBinariaDeBusca() {
    }

    private class No {
        Produto produto;
        No esquerda, direita, pai;

        public No(Produto produto) {
            this.produto = produto;
        }

    }

    private No raiz;

    public void inserirProduto(Produto produto) {
        No n = new No(produto);

        if (raiz == null) {
            raiz = n;
        } else {
            inserirNo(n, raiz);
        }

    }

    public Optional<Produto> buscarProduto(int codigoProduto) {
        if (raiz != null && raiz.produto.codigo == codigoProduto) {
            return Optional.of(raiz.produto);
        }
        return buscarNo(codigoProduto, raiz);
    }


    public void imprimirEmOrdem() {
        imprimirEmOrdemRecursivo(raiz);
    }

    public void removerProduto(int codigo) {
        raiz = removerRecursivo(raiz, codigo);
    }

    public Optional<Produto> encontrarMenor() {
        if (raiz == null) {
            return Optional.empty();
        }
        No atual = raiz;
        while (atual.esquerda != null) {
            atual = atual.esquerda;
        }
        return Optional.of(atual.produto);
    }

    public Optional<Produto> encontrarMaior() {
        if (raiz == null) {
            return Optional.empty();
        }
        No atual = raiz;
        while (atual.direita != null) {
            atual = atual.direita;
        }
        return Optional.of(atual.produto);
    }


    private void inserirNo(No no, No noAtual) {
        if (no.produto.codigo > noAtual.produto.codigo) {
            if (noAtual.direita == null) {
                no.pai = noAtual;
                noAtual.direita = no;
            } else {
                inserirNo(no, noAtual.direita);
            }

        } else {
            if (noAtual.esquerda == null) {
                no.pai = noAtual;
                noAtual.esquerda = no;
            } else {
                inserirNo(no, noAtual.esquerda);
            }
        }
    }

    private Optional<Produto> buscarNo(int codigoProduto, No noAtual) {
        Optional<Produto> produtoOptional = Optional.empty();

        if (noAtual != null) {
            if (codigoProduto > noAtual.produto.codigo) {
                return buscarNo(codigoProduto, noAtual.direita);
            } else if (codigoProduto < noAtual.produto.codigo) {
                return buscarNo(codigoProduto, noAtual.esquerda);
            } else {
                produtoOptional = Optional.of(noAtual.produto);
            }
        }

        return produtoOptional;
    }

    private void imprimirEmOrdemRecursivo(No atual) {
        if (atual != null) {
            imprimirEmOrdemRecursivo(atual.esquerda);
            System.out.println("Código: " + atual.produto.getCodigo() + ", Nome: " + atual.produto.getNome() + ", Quantidade: " + atual.produto.getQuantidade());
            imprimirEmOrdemRecursivo(atual.direita);
        }
    }

    private No removerRecursivo(No atual, int codigo) {
        if (atual == null) {
            return null;
        }

        if (codigo < atual.produto.getCodigo()) {
            atual.esquerda = removerRecursivo(atual.esquerda, codigo);
        } else if (codigo > atual.produto.getCodigo()) {
            atual.direita = removerRecursivo(atual.direita, codigo);
        } else {
            // Caso 1: Nó é uma folha
            if (atual.esquerda == null && atual.direita == null) {
                return null;
            }

            // Caso 2: Nó tem dois filhos
            if (atual.esquerda != null && atual.direita != null) {
                No sucessor = encontrarMenorNo(atual.direita);
                atual.produto = sucessor.produto;
                atual.direita = removerRecursivo(atual.direita, sucessor.produto.getCodigo());
            } else {
                // Caso 3: Não implementado
            }
        }

        return atual;
    }

    private No encontrarMenorNo(No atual) {
        while (atual.esquerda != null) {
            atual = atual.esquerda;
        }
        return atual;
    }

}
