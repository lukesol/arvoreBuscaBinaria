import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        ArvoreBinariaDeBusca estoque = new ArvoreBinariaDeBusca();

        estoque.inserirProduto(new Produto(101, "notebook", 50));
        estoque.inserirProduto(new Produto(50, "mouse", 200));
        estoque.inserirProduto(new Produto(200, "teclado", 150));

        Optional<Produto> produto = estoque.buscarProduto(404);
        if (produto.isPresent()) {
            System.out.println("Produto encontrado " + produto.get().getNome() + " , Quantidade " + produto.get().getQuantidade());
        } else {
            System.out.println("Produto não encontrado, tente outro codigo.");
        }

        System.out.println();
        System.out.println("Imprimindo na ordem: ");
        estoque.imprimirEmOrdem();
        System.out.println();

        if (estoque.encontrarMenor().isPresent()) {
            System.out.println("Menor Código: " + estoque.encontrarMenor().get().codigo);
        }

        if (estoque.encontrarMaior().isPresent()) {
            System.out.println("Maior Código: " + estoque.encontrarMaior().get().codigo);
        }

        System.out.println();
        estoque.removerProduto(50);
        System.out.println("Imprimindo na ordem após remoção: ");
        estoque.imprimirEmOrdem();

    }
}