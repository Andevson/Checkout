package checkout_error;

public class ProdutoInvalido extends Throwable{
    @Override
    public String toString(){
        return "ProdutoInvalido: Produto inválido";
    }
}