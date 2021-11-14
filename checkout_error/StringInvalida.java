package checkout_error;

public class StringInvalida extends Throwable{
    @Override
    public String toString(){
        return "StringInvalida: String inválida";
    }
}