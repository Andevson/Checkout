import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Conexao {
    private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String string_connection = " Use database's string connection ";
    private static Connection con = null;
    private static Statement st = null;
    private static ResultSet res = null;
    private static void conectar() throws ClassNotFoundException, SQLException{
        Class.forName(driver);
        con = DriverManager.getConnection(string_connection);
        st = con.createStatement();
    }
    private static void encerrarConexao(){
        if(res != null){
            try {
                res.close();
            } catch (SQLException e) {e.printStackTrace();}
        }
        if(st != null){
            try {
                st.close();
            } catch (SQLException e) {e.printStackTrace();}
        }
        if(con != null){
            try {
                con.close();
            } catch (SQLException e) {e.printStackTrace();}
        }
    }
    public static List<PedidoORM> obterPedidos(){
        List<PedidoORM> pedidos = new ArrayList<>();
        try{
            conectar();
            res = st.executeQuery(" Use query that returns order's id, quantity and bar code ");
            while(res.next()){
                pedidos.add(new PedidoORM(res.getString("Pedido"), res.getString("Quantidade"), res.getString("Cod. barras")));
            }
        }catch(ClassNotFoundException e){
            System.out.print(e.getStackTrace());
        }catch(SQLException e){
            System.out.print(e.getStackTrace());
        }finally{
            encerrarConexao();
        }
        return pedidos;
    }
    public static List<ProdutoORM> obterProdutos(){
        List<ProdutoORM> produtos = new ArrayList<>();
        try{
            conectar();
            res = st.executeQuery(" Use query that returns order's id in group with their products and the product's quantity ");
            while(res.next()){
                produtos.add(new ProdutoORM(res.getString("Pedido"), res.getString("Produto"), res.getInt("Quantidade")));
            }
        }catch(ClassNotFoundException e){
            System.out.print(e.getStackTrace());
        }catch(SQLException e){
            System.out.print(e.getStackTrace());
        }finally{
            encerrarConexao();
        }
        return produtos;
    }
    public static List<ProdutoORM> obterProdutos(String codigo_barras){
        List<ProdutoORM> produtos = new ArrayList<>();
        try{
            conectar();
            res = st.executeQuery(" Use query that returns order's id in group with their products and the produt's quantity ");
            while(res.next()){
                produtos.add(new ProdutoORM(res.getString("Pedido"), res.getString("Produto"), res.getInt("Quantidade")));
            }
        }catch(ClassNotFoundException e){
            System.out.print(e.getStackTrace());
        }catch(SQLException e){
            System.out.print(e.getStackTrace());
        }finally{
            encerrarConexao();
        }
        return produtos;
    }
    public static int obterFator(String produto){
        try{
            conectar();
            res = st.executeQuery(" Use query that returns product's sell factor ");
            if(res.next()){
                return res.getInt("Fator");
            }
        }catch(ClassNotFoundException e){
            System.out.print(e.getStackTrace());
        }catch(SQLException e){
            System.out.print(e.getStackTrace());
        }finally{
            encerrarConexao();
        }
        return 1;
    }
}
