package checkout_error;

public class IntInvalido extends Throwable{
    @Override
    public String toString(){
        return "IntInvalido: int inválido";
    }
}